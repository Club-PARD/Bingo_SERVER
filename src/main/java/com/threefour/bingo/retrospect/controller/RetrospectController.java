package com.threefour.bingo.retrospect.controller;

import com.threefour.bingo.retrospect.domain.Retrospect;
import com.threefour.bingo.retrospect.dto.request.RetrospectPostRequest;
import com.threefour.bingo.retrospect.service.RetrospectService;
import com.threefour.bingo.test.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/retrospect")
public class RetrospectController {

    private final RetrospectService retrospectService;

    @GetMapping("")
    public ResponseDto<Retrospect> gASQ(RetrospectPostRequest request) {

        ResponseDto<Retrospect> responseDto = retrospectService.writeTemplate(request);

        return responseDto;
    }
}
