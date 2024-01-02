package com.threefour.bingo.template.controller;

import com.threefour.bingo.template.dto.request.TemplatePostRequest;
import com.threefour.bingo.template.dto.response.TemplateAllResponse;
import com.threefour.bingo.template.dto.response.TemplateOneResponse;
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
    public ResponseEntity<TemplateAllResponse> createTemplate(@RequestBody final TemplatePostRequest request) {

        final TemplateAllResponse response = templateService.createTemplate(request);

        return ResponseEntity.ok()
                .body(response);

    }

    @GetMapping("/appUser/{appUserId}/project/{projectId}")
    public ResponseEntity<List<TemplateAllResponse>> getAllTemplates(@PathVariable final Long appUserId
            , @PathVariable Long projectId) {

        final List<TemplateAllResponse> responses = templateService.getAllTemplates(appUserId, projectId);

        return ResponseEntity.ok()
                .body(responses);

    }

    @GetMapping("/appUser/{appUserId}/project/{projectId}/template/{templateId}")
    public ResponseEntity<TemplateOneResponse> getTemplate(@PathVariable final Long appUserId
            , @PathVariable final Long projectId, @PathVariable final Long templateId) {

        final TemplateOneResponse response = templateService.getTemplate(appUserId, projectId, templateId);

        return ResponseEntity.ok()
                .body(response);

    }
}

