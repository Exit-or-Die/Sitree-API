package com.eod.sitree.image.service;

import com.eod.sitree.image.domain.modelRepository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public String uploadImage(MultipartFile multipartFile){
        return imageRepository.uploadImage(multipartFile);
    }
}
