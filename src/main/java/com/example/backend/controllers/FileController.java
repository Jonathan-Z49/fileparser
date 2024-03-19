package com.example.backend.controllers;

import com.example.backend.models.User;
import com.example.backend.security.UserDetails.UserInfo;
import com.example.backend.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("api/v1/files")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService){
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    ResponseEntity<String> uploadFile(@RequestParam("specFile") MultipartFile specFile, @RequestParam("dataFile") MultipartFile dataFile) throws IOException {
        String data = fileService.parseFiles(specFile, dataFile);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/test")
    ResponseEntity<String> test(Authentication user) {
        Object prip = user.getPrincipal();
        System.out.println( ((UserInfo)prip).getAuthorities() );
        System.out.println( ((UserInfo)prip).getId() );
        System.out.println( ((UserInfo)prip).getUsername() );
        return ResponseEntity.ok("TESTED");
    }

}
