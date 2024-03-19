package com.example.backend.services;

import com.example.backend.models.Metadata;
import com.example.backend.repository.MetaDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MetaDataService {

    private final MetaDataRepository metaDataRepository;

    @Autowired
    public MetaDataService(MetaDataRepository metaDataRepository){
        this.metaDataRepository = metaDataRepository;
    }

    public Metadata createFileMetaData(MultipartFile file) {
        Metadata metadata = new Metadata(file.getSize(), file.getOriginalFilename());
        return metaDataRepository.save(metadata);
    }
}
