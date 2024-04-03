package com.example.backend.services;

import com.example.backend.models.Metadata;
import com.example.backend.models.User;
import com.example.backend.repository.MetaDataRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class MetaDataService {

    private final MetaDataRepository metaDataRepository;

    @Autowired
    public MetaDataService(MetaDataRepository metaDataRepository){
        this.metaDataRepository = metaDataRepository;
    }

    public Metadata createFileMetaData(MultipartFile file, String userId, String type) {
        Metadata metadata = new Metadata(file.getSize(), file.getOriginalFilename(), new ObjectId(userId), type);
        return metaDataRepository.save(metadata);
    }

    public List<Metadata> findByUserIdAndType(ObjectId userId, String type) {
        return metaDataRepository.findAllByUserAndTypeOrderByCreatedAtDesc(userId, type).orElse(null);
    }
}
