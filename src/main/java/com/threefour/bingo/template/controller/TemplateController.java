package com.threefour.bingo.template.controller;

import com.threefour.bingo.template.domain.Template;
import com.threefour.bingo.template.dto.request.TemplateCreateRequest;
import com.threefour.bingo.template.dto.request.TemplateRequest;
import com.threefour.bingo.template.dto.response.TemplateResponse;
import com.threefour.bingo.template.service.TemplateService;
import com.threefour.bingo.test.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/template")
public class TemplateController {

    private final TemplateService templateService;

    @PostMapping("")
    public ResponseDto<TemplateResponse> createTemplate(@RequestBody final TemplateCreateRequest request) {
        ResponseDto<TemplateResponse> response = templateService.createTemplate(request);

        return response;
    }

    @GetMapping("")
    public ResponseDto<List<Template>> getAllTemplates(@PathVariable final Long id) {
        ResponseDto<List<Template>> response = templateService.getAllTemplates(id);

        return response;
    }
}

