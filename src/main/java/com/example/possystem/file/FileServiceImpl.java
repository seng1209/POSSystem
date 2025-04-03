package com.example.possystem.file;

import com.example.possystem.file.web.FileDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileServiceImpl implements FileService{

    @Value("${file.server-part}")
    private String serverPart;
    @Value("${file.base-uri}")
    private String fileBaseUri;
    @Value("${file.download-uri}")
    private String fileDownloadUri;

    private String getExtension(String file){
        int lastDotIndex = file.lastIndexOf(".");
        return file.substring(lastDotIndex+1);
    }

    private FileDto save(MultipartFile file){
        // get file extension
        String extension = this.getExtension(file.getOriginalFilename());
        String name = UUID.randomUUID() + "." +extension;
        String uri = fileBaseUri + name;
        Long size = file.getSize();

        // Step 1. Create file part
        Path part = Paths.get(serverPart + name);

        // Step 2. Copy file
        try {
            Files.copy(file.getInputStream(), part);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return FileDto.builder()
                .name(name)
                .uri(uri)
                .downloadUri(fileDownloadUri + name)
                .extension(extension)
                .size(size)
                .build();
    }

    @Override
    public Resource downloadByName(String name) {
        Path path = Paths.get(serverPart + name);

        if (Files.exists(path)){
            return UrlResource.from(path.toUri());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This file not found");
    }

    @Override
    public FileDto findByName(String name) throws IOException {
        Path path = Paths.get(serverPart + name);

        if (Files.exists(path)){
            Resource resource = UrlResource.from(path.toUri());
            return FileDto.builder()
                    .name(name)
                    .uri(fileBaseUri + name)
                    .downloadUri(fileDownloadUri + name)
                    .extension(this.getExtension(name))
                    .size(resource.contentLength())
                    .build();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource is not found");
    }

    @Override
    public void deleteByName(String name) {
        Path path = Paths.get(serverPart + name);

        if (Files.exists(path)){
            try {
                Files.delete(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This file not found");
    }

    @Override
    public void deleteAll() {
        Path path = Paths.get(serverPart);

        File file = new File(String.valueOf(path));
        File[] files = file.listFiles();
        for (File f:files) {
            if (f.isFile() && f.exists()) {
                f.delete();
            }
        }
    }

    @Override
    public List<FileDto> findAll() {
        List<FileDto> fileDtoList = new ArrayList<>();

        Path path = Paths.get(serverPart);

        try {
            Stream<Path> paths = Files.list(path);
            List<Path> pathList = paths.toList();
            for (Path p : pathList){
                Resource resource = UrlResource.from(p.toUri());
                fileDtoList.add(FileDto.builder()
                        .name(resource.getFilename())
                        .uri(fileBaseUri + resource.getFilename())
                        .downloadUri(fileDownloadUri + resource.getFilename())
                        .extension(resource.getFilename())
                        .size(resource.contentLength())
                        .build());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return fileDtoList;
    }

    @Override
    public FileDto uploadSingle(MultipartFile file) {
        return this.save(file);
    }

    @Override
    public List<FileDto> uploadMultiple(List<MultipartFile> files) {
        return files.stream().map(this::save).collect(Collectors.toList());
    }
}
