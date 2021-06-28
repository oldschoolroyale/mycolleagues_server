package com.brm.mycollegues.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Executors;

@Service
public class AWSConfig {
    private static final String ACCESS_KEY = "pVQYUEejfLDGT3WhKwoJkz";
    private static final String SECRET_KEY = "8y6e7V7XDgYfVMDSXVLsGW5UWCmS4fUxacLaLXC9o3GM";
    private static final String BUCKET_NAME = "test_box";
    private final AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);

    @SneakyThrows
    public String uploadFile(MultipartFile file) {

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("https://hb.bizmrg.com/","10"))
                .build();
        int maxUploadThreads = 5;
        TransferManager tm = TransferManagerBuilder.standard()
                .withS3Client(s3Client)
                .withMultipartUploadThreshold((long) (5 * 1024 * 1025))
                .withExecutorFactory(() -> Executors.newFixedThreadPool(maxUploadThreads))
                .build();
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = "test/"+file.getOriginalFilename();

        Upload upload=tm.upload(BUCKET_NAME,fileName,fileObj);
        upload.waitForUploadResult();
        fileObj.delete();
        return "File uploaded : " + fileName;
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {

        }
        return convertedFile;
    }
}
