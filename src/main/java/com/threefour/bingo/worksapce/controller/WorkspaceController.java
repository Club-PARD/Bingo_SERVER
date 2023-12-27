package com.threefour.bingo.worksapce.controller;

import com.threefour.bingo.worksapce.dto.request.WorkspaceCreateRequest;
import com.threefour.bingo.worksapce.dto.request.WorkspaceRequest;
import com.threefour.bingo.worksapce.dto.response.WorkspaceInfoResponse;
import com.threefour.bingo.worksapce.domain.Workspace;
import com.threefour.bingo.worksapce.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/workspace")
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    @PostMapping("")
    public ResponseEntity<Workspace> createWorkspace(@RequestBody final WorkspaceCreateRequest request) {

        Workspace workspace = workspaceService.createWorkspace(request);

        return ResponseEntity.ok()
                .body(workspace);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<WorkspaceInfoResponse>> getAllWorkspacesByUser(@PathVariable final Long id) {

        List<WorkspaceInfoResponse> workspaceResponseList = workspaceService.getAllWorkspacesByUser(id);

        return ResponseEntity.ok()
                .body(workspaceResponseList);
    }

    @GetMapping("")
    public ResponseEntity<WorkspaceInfoResponse> getWorkspace(@RequestBody final WorkspaceRequest request) {

        WorkspaceInfoResponse response = workspaceService.getWorkspace(request);

        return ResponseEntity.ok()
                .body(response);
    }
}
