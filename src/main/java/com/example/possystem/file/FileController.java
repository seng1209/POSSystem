package com.example.possystem.file;

import com.example.possystem.file.web.FileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping(value = "/single", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, headers = "Content-Type= multipart/form-data")
    public FileDto uploadSingle(@RequestPart MultipartFile file){
        if (file.getSize() != 0){
            return fileService.uploadSingle(file);
        }
        return FileDto.builder()
                .name("")
                .uri("")
                .downloadUri("")
                .extension("")
                .size(0L)
                .build();
    }

    @PostMapping(value = "/multiple", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<FileDto> uploadMultiple(@RequestPart List<MultipartFile> files){
        return fileService.uploadMultiple(files);
    }

    @GetMapping("/{name}")
    public FileDto findByName(@PathVariable String name) throws IOException {
        return fileService.findByName(name);
    }

    @GetMapping
    public List<FileDto> findAll(){
        return fileService.findAll();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{name}")
    public void deleteByName(@PathVariable String name){
        fileService.deleteByName(name);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteAll(){
        fileService.deleteAll();
    }

    @GetMapping(value = "/download/{name}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<?> downloadByName(@PathVariable  String name){
        Resource resource = fileService.downloadByName(name);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + resource.getFilename())
                .body(resource);
    }

}
