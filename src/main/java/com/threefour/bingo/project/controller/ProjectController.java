package com.threefour.bingo.project.controller;

import com.threefour.bingo.project.dto.request.ProjectCreateRequest;
import com.threefour.bingo.project.dto.request.ProjectRequest;
import com.threefour.bingo.project.dto.response.ProjectInfoResponse;
import com.threefour.bingo.project.domain.Project;
import com.threefour.bingo.project.service.ProjectService;
import com.threefour.bingo.test.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/project")
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("")
    public ResponseDto<Project> createProject(@RequestBody final ProjectCreateRequest request) {

        ResponseDto<Project> response = projectService.createProject(request);

        return response;
    }

    @GetMapping("/{id}")
    public ResponseDto<List<ProjectInfoResponse>> getAllProjectsByUser(@PathVariable final Long id) {

        ResponseDto<List<ProjectInfoResponse>> projectInfoResponseList = projectService.getAllProjectsByUser(id);

        return projectInfoResponseList;
    }

    @GetMapping("")
    public ResponseDto<ProjectInfoResponse> getProject(@RequestBody final ProjectRequest request) {

        ResponseDto<ProjectInfoResponse> response = projectService.getProject(request);

        return response;
    }

}
