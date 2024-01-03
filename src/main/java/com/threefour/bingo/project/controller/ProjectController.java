package com.threefour.bingo.project.controller;

import com.threefour.bingo.project.dto.request.ProjectCreateRequest;
import com.threefour.bingo.project.dto.response.ProjectAllResponse;
import com.threefour.bingo.project.dto.response.ProjectOneResponse;
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
    public ResponseEntity<ProjectOneResponse> createProject(@RequestHeader(value = "Authorization") final String token, @RequestBody final ProjectCreateRequest request) {

        final ProjectOneResponse response = projectService.createProject(request);


        return ResponseEntity.ok()
                .body(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ProjectAllResponse>> getAllProjectsByUser(@RequestHeader(value = "Authorization") final String token, @PathVariable final Long id) {

        final List<ProjectAllResponse> projectAllResponseList = projectService.getAllProjectsByUser(id);

        return ResponseEntity.ok()
                .body(projectAllResponseList);
    }

    @GetMapping("/user/{userId}/projects/{projectId}")
    public ResponseEntity<ProjectOneResponse> getProject(@RequestHeader(value = "Authorization") final String token, @PathVariable final Long userId
            , @PathVariable Long projectId) {

        final ProjectOneResponse response = projectService.getProject(userId, projectId);

        return ResponseEntity.ok()
                .body(response);
    }

}
