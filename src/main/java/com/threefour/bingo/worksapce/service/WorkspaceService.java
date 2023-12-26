package com.threefour.bingo.worksapce.service;

import com.threefour.bingo.worksapce.dto.request.WorkspaceRequest;
import com.threefour.bingo.worksapce.entity.Workspace;
import com.threefour.bingo.worksapce.repository.WorkspaceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkspaceService {
    private WorkspaceRepository workspaceRepository;

    @Transactional
    public Workspace createWorkspace(WorkspaceRequest request) {
        return workspaceRepository.save(request.toEntity());
    }
}
