package com.eod.sitree.image.infra;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.common.exception.CustomException;
import com.eod.sitree.image.domain.modelRepository.ImageRepository;
import java.io.IOException;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
@RequiredArgsConstructor
public class AmazonS3Repository implements ImageRepository {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public String uploadImage(MultipartFile multipartFile) {
        String fileName = createFileName(multipartFile.getOriginalFilename());
        try(InputStream inputStream = multipartFile.getInputStream()){
            amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, getObjectMetadata(multipartFile)));
        } catch (IOException e){
            throw new CustomException(ApplicationErrorType.IMAGE_UPLOAD_FAIL);
        }
        return fileName;
    }
}
