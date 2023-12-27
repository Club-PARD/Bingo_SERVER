package com.threefour.bingo.project.controller;

import com.threefour.bingo.project.dto.request.ProjectCreateRequest;
import com.threefour.bingo.project.dto.request.ProjectRequest;
import com.threefour.bingo.project.dto.response.ProjectInfoResponse;
import com.threefour.bingo.project.domain.Project;
import com.threefour.bingo.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/project")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("")
    public ResponseEntity<Project> createProject(@RequestBody final ProjectCreateRequest request) {

        Project project = projectService.createProject(request);

        return ResponseEntity.ok()
                .body(project);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ProjectInfoResponse>> getAllProjectsByUser(@PathVariable final Long id) {

        List<ProjectInfoResponse> projectInfoResponseList = projectService.getAllProjectsByUser(id);

        return ResponseEntity.ok()
                .body(projectInfoResponseList);
    }

    @GetMapping("")
    public ResponseEntity<ProjectInfoResponse> getProject(@RequestBody final ProjectRequest request) {

        ProjectInfoResponse response = projectService.getProject(request);

        return ResponseEntity.ok()
                .body(response);
    }
}
