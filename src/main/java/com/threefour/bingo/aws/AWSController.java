package com.threefour.bingo.aws;

import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/upload")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AWSController {

    private final AmazonS3 amazonS3;
    private final AWSService awsService;

    @Value("${cloud.aws.s3.bucket}")

//    @PostMapping("")
//    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
//        try {
//            String fileUrl = awsService.uploadFile(file);
//            return ResponseEntity.ok(fileUrl);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

    //board 이미지 전달
    @GetMapping("/cards/{category}/images")
    public List<String> getBoardImages(@PathVariable("category") String category) {

        List<String> boardImageList = awsService.getFileList("board/" + category); //보드는 카테고리 디렉토리가 하나 더있어서

        return boardImageList;
    }

    //profile 이미지 전달
    @GetMapping("members/images")
    public List<String> getProfileImages() {
        List<String> profileImageList = awsService.getFileList("profile");
        return profileImageList;
    }

}
