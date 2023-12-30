package com.threefour.bingo.project.controller;

import com.threefour.bingo.ResponseDto;
import com.threefour.bingo.project.dto.request.ProjectCreateRequest;
import com.threefour.bingo.project.dto.request.ProjectRequest;
import com.threefour.bingo.project.dto.response.ProjectInfoResponse;
import com.threefour.bingo.project.domain.Project;
import com.threefour.bingo.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/user/{userId}/projects/{projectId}")
    public ResponseDto<ProjectInfoResponse> getProject(@PathVariable final Long userId
            , @PathVariable Long projectId) {

        ResponseDto<ProjectInfoResponse> response = projectService.getProject(userId, projectId);

        return response;
    }

}
