package com.threefour.bingo.tag.controller;

import com.threefour.bingo.ResponseDto;
import com.threefour.bingo.tag.domain.Tag;
import com.threefour.bingo.tag.dto.request.TagListProjectRequest;
import com.threefour.bingo.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tag")
@CrossOrigin(origins = "http://localhost:3000")
public class TagController {

    private final TagService tagService;

//    @PostMapping("")
//    public ResponseDto<List<Tag>> createBingoBoard(@RequestBody final TagListProjectRequest request) {
//        List<Tag> tagList = tagService.createBingo(request);
//
//        return ResponseDto.setSuccess("Bingo Created", tagList);
//    }
}
