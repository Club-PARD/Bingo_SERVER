package com.threefour.bingo.tag.controller;

import com.threefour.bingo.ResponseDto;
import com.threefour.bingo.tag.domain.Tag;
import com.threefour.bingo.tag.dto.request.TagListPostRequest;
import com.threefour.bingo.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tag")
public class TagController {

    private final TagService tagService;

    @PostMapping("")
    public ResponseDto<List<Tag>> createBingoBoard(@RequestBody final TagListPostRequest request) {
        List<Tag> tagList = tagService.createBingo(request);

        return ResponseDto.setSuccess("Bingo Created", tagList);
    }
}
