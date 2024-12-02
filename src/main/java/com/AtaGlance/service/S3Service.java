package com.AtaGlance.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

@Service
public class S3Service {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName;

    public void uploadFile(MultipartFile multipartFile) throws IOException {
        File file = multiPartFileToFile(multipartFile);
        String fileName = multipartFile.getOriginalFilename();
        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, multipartFile.getInputStream(), null));

        //  System.out.println("s3클라이언트 URL:"+s3Client.getUrl(bucket,fileName).toString());
        // System.out.println("s3클라이언트 fileName:"+fileName);
        //return amazonS3.getUrl(bucketName, fileName).toString();
        file.delete();
    }


    private File multiPartFileToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fileOutputStream = new FileOutputStream(convertedFile)) {
            fileOutputStream.write(file.getBytes());
        }
        return convertedFile;
    }

    public String getPresignedUrl(String fileName) {
        // URL 유효 기간 설정 (예: 1시간)
        Date expiration = new Date();
        long expirationTimeMillis = expiration.getTime() + 600 * 1000; // 10분
        expiration.setTime(expirationTimeMillis);

        // Presigned URL 요청 생성
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, fileName)
                        .withMethod(HttpMethod.GET)
                        .withExpiration(expiration);
        System.out.println("Bucket Name: " + bucketName);
        System.out.println("File Name: " + fileName);

        // URL 반환
        URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();
    }

    public String getURL(String uri) {
        try {
            // S3 URI를 URL로 변환
            return new URL(uri.replaceFirst("s3://", "https://").replaceFirst(bucketName, bucketName + ".s3.amazonaws.com")).toString();
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid S3 URI: " + uri, e);
        }
    }
}