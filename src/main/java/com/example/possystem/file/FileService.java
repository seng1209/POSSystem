package com.example.possystem.file;

import com.example.possystem.file.web.FileDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {

    Resource downloadByName(String name);

    FileDto findByName(String name) throws IOException;

    void deleteByName(String name);

    void deleteAll();

    List<FileDto> findAll();

    FileDto uploadSingle(MultipartFile file);

    List<FileDto> uploadMultiple(List<MultipartFile> files);

}
