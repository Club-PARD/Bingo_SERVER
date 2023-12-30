package com.threefour.bingo.template.controller;


import com.threefour.bingo.ResponseDto;
import com.threefour.bingo.template.domain.Template;
import com.threefour.bingo.template.dto.TemplateDTO;
import com.threefour.bingo.template.dto.request.TemplateGetAllRequest;
import com.threefour.bingo.template.dto.request.TemplatePostRequest;
import com.threefour.bingo.template.service.TemplateService;
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
    public ResponseDto<List<TemplateDTO>> getTemplate(@RequestBody final TemplateGetAllRequest request) {

        return templateService.getAllTemplates(request);

    }
}

