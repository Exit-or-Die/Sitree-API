package com.eod.sitree.image.ui;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.common.response.ResponseDto;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @PostMapping
    public ResponseDto<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileName=file.getOriginalFilename();
            String fileUrl= "https://" + bucket + "/" +fileName;
            ObjectMetadata metadata= new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());
            amazonS3Client.putObject(bucket,fileName,file.getInputStream(),metadata);
            return ResponseDto.ok(fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseDto.error(ApplicationErrorType.IMAGE_UPLOAD_FAIL);
        }
    }
}
