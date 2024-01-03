package com.threefour.bingo.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AWSService {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Value("${cloud.aws.region.static}")
    private String region;

//    public String uploadFile(MultipartFile file) throws IOException {
//        String fileName = file.getOriginalFilename();
//        String fileUrl = "https://" + bucketName + "/test/" + fileName;
//
//        ObjectMetadata metadata = new ObjectMetadata();
//        metadata.setContentType(file.getContentType());
//        metadata.setContentLength(file.getSize());
//
//        amazonS3.putObject(bucketName, fileName, file.getInputStream(), metadata);
//
//        return fileUrl;
//    }

    //S3폴더 내 파일 리스트 전달
    public List<String> getFileList(String directory) {
        List<String> fileList = new ArrayList<>();
        log.info("받아온 디렉토리는: ", directory);

        ListObjectsV2Request listObjectsV2Request = new ListObjectsV2Request()
                .withBucketName(bucketName)
//                .withPrefix("projectList" + "/");  //폴더 경로 지정
                .withPrefix("");  //폴더 경로 지정

        ListObjectsV2Result result = amazonS3.listObjectsV2(listObjectsV2Request);
        List<S3ObjectSummary> objectSummaries = result.getObjectSummaries();

        for (S3ObjectSummary objectSummary : objectSummaries) {
            String key = objectSummary.getKey();
            if (!key.equals("")) {
                fileList.add("https://" + bucketName + ".s3." + region + ".amazonaws.com/" + key);
            }
        }

        return fileList;
    }
}