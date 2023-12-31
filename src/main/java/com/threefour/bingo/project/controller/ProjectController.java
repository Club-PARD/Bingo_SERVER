package com.threefour.bingo.project.controller;

import com.threefour.bingo.project.dto.request.ProjectCreateRequest;
import com.threefour.bingo.project.dto.response.ProjectInfoResponse;
import com.threefour.bingo.project.service.ProjectService;
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
    public ResponseEntity<ProjectInfoResponse> createProject(@RequestBody final ProjectCreateRequest request) {

        final ProjectInfoResponse response = projectService.createProject(request);


        return ResponseEntity.ok()
                .body(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ProjectInfoResponse>> getAllProjectsByUser(@PathVariable final Long id) {

        final List<ProjectInfoResponse> projectInfoResponseList = projectService.getAllProjectsByUser(id);

        return ResponseEntity.ok()
                .body(projectInfoResponseList);
    }

    @GetMapping("/user/{userId}/projects/{projectId}")
    public ResponseEntity<ProjectInfoResponse> getProject(@PathVariable final Long userId
            , @PathVariable Long projectId) {

        final ProjectInfoResponse response = projectService.getProject(userId, projectId);

        return ResponseEntity.ok()
                .body(response);
    }

}
