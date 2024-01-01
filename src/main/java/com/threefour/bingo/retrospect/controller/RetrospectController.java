package com.threefour.bingo.retrospect.controller;

import com.threefour.bingo.ResponseDto;
import com.threefour.bingo.project.domain.Project;
import com.threefour.bingo.retrospect.domain.Retrospect;
import com.threefour.bingo.retrospect.dto.request.RetrospectPostRequest;
import com.threefour.bingo.retrospect.dto.request.TeamEvaluationPostRequest;
import com.threefour.bingo.retrospect.dto.response.RetrospectPostResponse;
import com.threefour.bingo.retrospect.service.RetrospectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/retrospect")
public class RetrospectController {

    private final RetrospectService retrospectService;

    @PostMapping("/evaluation")
    public ResponseDto<Project> teamEva(@RequestBody final TeamEvaluationPostRequest request) {
        ResponseDto<Project> responseDto = retrospectService.teamEvaluation(request);

        return responseDto;
    }

    @PostMapping("/write")
    public ResponseEntity<RetrospectPostResponse> writeRetrospect(@RequestBody final RetrospectPostRequest request) {
        RetrospectPostResponse response = retrospectService.writeRetrospect(request);

        return ResponseEntity.ok()
                .body(response);
    }

}
