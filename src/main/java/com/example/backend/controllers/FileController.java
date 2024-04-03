package com.example.backend.controllers;

import com.example.backend.models.Flatdata;
import com.example.backend.models.Metadata;
import com.example.backend.models.User;
import com.example.backend.s3.S3FileService;
import com.example.backend.security.UserDetails.UserInfo;
import com.example.backend.services.FileService;
import com.example.backend.services.FlatDataService;
import com.example.backend.services.MetaDataService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/v1")
public class FileController {

    private final FileService fileService;

    private final S3FileService s3FileService;
    private final MetaDataService metaDataService;

    private final FlatDataService flatDataService;

    @Autowired
    public FileController(FileService fileService, MetaDataService metaDataService, FlatDataService flatDataService,S3FileService s3FileService){
        this.fileService = fileService;
        this.metaDataService = metaDataService;
        this.flatDataService = flatDataService;
        this.s3FileService = s3FileService;
    }

    @PostMapping("/files")
    ResponseEntity<List<Flatdata>> uploadFile(@RequestParam("specFile") MultipartFile specFile, @RequestParam("dataFile") MultipartFile dataFile, Authentication user) throws IOException {
        List<Flatdata> data = fileService.parseFiles(specFile, dataFile, ((UserInfo)user.getPrincipal()).getId());
        return ResponseEntity.ok(data);
    }

    @GetMapping("/files")
    public ResponseEntity<?> downloadFile(@RequestParam("fileName") @NotBlank @NotNull String fileName) throws IOException {
        Object response = s3FileService.downloadFile(fileName);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"").body(response);
    }

    @GetMapping("/files/flat")
    ResponseEntity<List<Metadata>> getFlatFiles(Authentication user){
        Object userObj = user.getPrincipal();
        ObjectId userId = new ObjectId(((UserInfo)userObj).getId());
        List<Metadata> allMetadata = this.metaDataService.findByUserIdAndType(userId, "flat");
        return ResponseEntity.ok(allMetadata);
    }

    @GetMapping("/files/spec")
    ResponseEntity<List<Metadata>> getSpecFiles(Authentication user){
        Object userObj = user.getPrincipal();
        ObjectId userId = new ObjectId(((UserInfo)userObj).getId());
        List<Metadata> allMetadata = this.metaDataService.findByUserIdAndType(userId, "spec");
        return ResponseEntity.ok(allMetadata);
    }

    @GetMapping("/files/{id}/records")
    ResponseEntity<List<Flatdata>> getSpecFiles(@PathVariable @NotBlank @NotNull String id , Authentication user){
        Object userObj = user.getPrincipal();
        ObjectId userId = new ObjectId(((UserInfo)userObj).getId());
        List<Flatdata> allFlatData = this.flatDataService.findFlatDataByFile(new ObjectId(id), userId);
        return ResponseEntity.ok(allFlatData);
    }

}
