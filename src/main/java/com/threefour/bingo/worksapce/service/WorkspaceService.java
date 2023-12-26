package com.threefour.bingo.worksapce.service;

import com.threefour.bingo.worksapce.dto.request.JoinRequest;
import com.threefour.bingo.worksapce.dto.request.WorkspaceRequest;
import com.threefour.bingo.worksapce.dto.response.JoinResponse;
import com.threefour.bingo.worksapce.entity.Workspace;
import com.threefour.bingo.worksapce.repository.WorkspaceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkspaceService {
    private final WorkspaceRepository workspaceRepository;

    @Transactional
    public Workspace createWorkspace(WorkspaceRequest request) {
        return workspaceRepository.save(request.toEntity());
    }

    @Transactional
    public List<Workspace> getAllWorkspaces() {
        return workspaceRepository.findAll();
    }

    @Transactional
    public Workspace getWorkspace(Long id) {
        return workspaceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 워크스페이스입니다"));
    }
}
