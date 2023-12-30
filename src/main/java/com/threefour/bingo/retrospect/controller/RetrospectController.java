package com.threefour.bingo.retrospect.controller;

import com.threefour.bingo.ResponseDto;
import com.threefour.bingo.retrospect.domain.Retrospect;
import com.threefour.bingo.retrospect.domain.TeamEvaluation;
import com.threefour.bingo.retrospect.dto.request.RetrospectPostRequest;
import com.threefour.bingo.retrospect.service.RetrospectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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

    @GetMapping("/tec")
    public ResponseEntity<Map<TeamEvaluation, Long>> getTeamEvaluationCount() {

        Map<TeamEvaluation, Long> countMap = retrospectService.getTeamEvaluationCount();

        return ResponseEntity.ok(countMap);
    }

}
