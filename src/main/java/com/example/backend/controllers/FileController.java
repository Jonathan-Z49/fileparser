package com.example.backend.controllers;

import com.example.backend.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService){
        this.fileService = fileService;
    }

    @PostMapping("/file")
    ResponseEntity<String> uploadFile(@RequestParam("specFile") MultipartFile specFile, @RequestParam("dataFile") MultipartFile dataFile) throws IOException {
        String data = fileService.parseFiles(specFile, dataFile);
        return ResponseEntity.ok(data);
    }

}
