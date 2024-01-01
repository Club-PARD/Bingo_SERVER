package com.threefour.bingo.template.controller;

import com.threefour.bingo.template.dto.request.TemplatePostRequest;
import com.threefour.bingo.template.dto.response.TemplateResponse;
import com.threefour.bingo.template.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/template")
@CrossOrigin(origins = "http://localhost:3000")
public class TemplateController {

    private final TemplateService templateService;

    @PostMapping("")
    public ResponseEntity<TemplateResponse> createTemplate(@RequestBody final TemplatePostRequest request) {

        final TemplateResponse response = templateService.createTemplate(request);

        return ResponseEntity.ok()
                .body(response);

    }

    @GetMapping("/appUser/{appUserId}/project/{projectId}")
    public ResponseEntity<List<TemplateResponse>> getAllTemplates(@PathVariable final Long appUserId
            , @PathVariable Long projectId) {

        final List<TemplateResponse> responses = templateService.getAllTemplates(appUserId, projectId);

        return ResponseEntity.ok()
                .body(responses);

    }

    @GetMapping("/appUser/{appUserId}/project/{projectId}/template/{templateId}")
    public ResponseEntity<TemplateResponse> getTemplate(@PathVariable final Long appUserId
            , @PathVariable final Long projectId, @PathVariable final Long templateId) {

        final TemplateResponse response = templateService.getTemplate(appUserId, projectId, templateId);

        return ResponseEntity.ok()
                .body(response);

    }
}

