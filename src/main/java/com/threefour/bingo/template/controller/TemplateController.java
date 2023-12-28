package com.threefour.bingo.template.controller;

import com.threefour.bingo.template.domain.Template;
import com.threefour.bingo.template.dto.TemplateDto;
import com.threefour.bingo.template.dto.request.TemplateGetRequest;
import com.threefour.bingo.template.dto.request.TemplatePostRequest;
import com.threefour.bingo.template.service.TemplateService;
import com.threefour.bingo.test.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/template")
@CrossOrigin(origins = "http://localhost:3000")
public class TemplateController {

    private final TemplateService templateService;

    @PostMapping("")
    public ResponseDto<Template> createTemplate(@RequestBody final TemplatePostRequest request) {

        ResponseDto<Template> response = templateService.createTemplate(request);

        return response;
    }

    @GetMapping("")
    public ResponseDto<List<TemplateDto>> getTemplate(@RequestBody final TemplateGetRequest request) {

        return templateService.getTemplate(request);

    }
}

