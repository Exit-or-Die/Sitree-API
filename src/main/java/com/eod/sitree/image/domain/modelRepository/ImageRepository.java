package com.eod.sitree.image.domain.modelRepository;

import com.amazonaws.services.s3.model.ObjectMetadata;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

public interface ImageRepository {

    String uploadImage(MultipartFile multipartFile);

    default ObjectMetadata getObjectMetadata(MultipartFile file) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());
        return objectMetadata;
    }

    default String createFileName(String fileName){
        return "image/" + UUID.randomUUID() + fileName;
    }
}
