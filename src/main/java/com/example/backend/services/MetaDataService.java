package com.example.backend.services;

import com.example.backend.models.Metadata;
import com.example.backend.models.User;
import com.example.backend.repository.MetaDataRepository;
import com.example.backend.s3.S3FileService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class MetaDataService {

    private final MetaDataRepository metaDataRepository;

    private final S3FileService s3FileService;

    @Autowired
    public MetaDataService(MetaDataRepository metaDataRepository, S3FileService s3FileService){
        this.metaDataRepository = metaDataRepository;
        this.s3FileService = s3FileService;
    }

    public Metadata createFileMetaData(MultipartFile file, String userId, String type) throws IOException {
        Metadata metadata = new Metadata(file.getSize(), file.getOriginalFilename(), new ObjectId(userId), type);
        Metadata savedData = metaDataRepository.save(metadata);
        s3FileService.uploadFile(savedData.getId(), file);
        return savedData;
    }

    public List<Metadata> findByUserIdAndType(ObjectId userId, String type) {
        return metaDataRepository.findAllByUserAndTypeOrderByCreatedAtDesc(userId, type).orElse(null);
    }
}
