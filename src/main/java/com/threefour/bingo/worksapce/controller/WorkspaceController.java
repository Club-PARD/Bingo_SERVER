package com.threefour.bingo.worksapce.controller;

import com.threefour.bingo.test.ResponseDto;
import com.threefour.bingo.worksapce.dto.request.WorkspaceRequest;
import com.threefour.bingo.worksapce.dto.response.WorkspaceResponse;
import com.threefour.bingo.worksapce.entity.Workspace;
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
    public ResponseEntity<Workspace> createWorkspace(@RequestBody WorkspaceRequest request) {
        Workspace workspace = workspaceService.createWorkspace(request);

        return ResponseEntity.ok()
                .body(workspace);
    }

    @GetMapping("")
    public ResponseEntity<List<Workspace>> getAllWorkspaces() {
        List<Workspace> workspaceList = workspaceService.getAllWorkspaces();

        return ResponseEntity.ok()
                .body(workspaceList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Workspace> getWorkspace(@PathVariable Long id) {
        Workspace workspace = workspaceService.getWorkspace(id);

        return ResponseEntity.ok()
                .body(workspace);
    }
}
