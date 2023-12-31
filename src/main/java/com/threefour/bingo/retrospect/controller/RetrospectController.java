package com.threefour.bingo.retrospect.controller;

import com.threefour.bingo.ResponseDto;
import com.threefour.bingo.project.domain.Project;
import com.threefour.bingo.retrospect.domain.Retrospect;
import com.threefour.bingo.retrospect.dto.request.RetrospectPostRequest;
import com.threefour.bingo.retrospect.dto.request.TeamEvaluationPostRequest;
import com.threefour.bingo.retrospect.dto.response.RetrospectGetResponse;
import com.threefour.bingo.retrospect.dto.response.RetrospectPostResponse;
import com.threefour.bingo.retrospect.service.RetrospectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/retrospect")
//@CrossOrigin(origins = "http://localhost:3000")
public class RetrospectController {

    private final RetrospectService retrospectService;

//    @PostMapping("/evaluation")
//    public ResponseDto<Project> teamEva(@RequestBody final TeamEvaluationPostRequest request) {
//        ResponseDto<Project> responseDto = retrospectService.teamEvaluation(request);
//
//        return responseDto;
//    }

    @PostMapping("/write")
    public ResponseEntity<RetrospectPostResponse> writeRetrospect(@RequestHeader(value = "Authorization") final String token, @RequestBody final RetrospectPostRequest request) {

        RetrospectPostResponse response = retrospectService.writeRetrospect(request);

        return ResponseEntity.ok()
                .body(response);

    }

    @GetMapping("/project/{projectId}/template/{templateId}")
    public ResponseEntity<RetrospectGetResponse> getRetrospects(@RequestHeader(value = "Authorization") final String token, @PathVariable final Long projectId
            , @PathVariable final Long templateId) {

        RetrospectGetResponse response = retrospectService.getRetrospect(projectId, templateId);

        return ResponseEntity.ok()
                .body(response);

    }

}
