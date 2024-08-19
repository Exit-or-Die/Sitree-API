package com.eod.sitree.image.ui;

import com.eod.sitree.auth.support.AuthNotRequired;
import com.eod.sitree.common.response.ResponseDto;
import com.eod.sitree.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    public ResponseDto<String> uploadFile(@RequestParam("file") MultipartFile file) {
        return ResponseDto.ok(imageService.uploadImage(file));
    }
}
