package com.threefour.bingo.worksapce.service;

import com.threefour.bingo.enrollment.domain.Role;
import com.threefour.bingo.enrollment.domain.Enrollment;
import com.threefour.bingo.enrollment.domain.EnrollmentRepository;
import com.threefour.bingo.worksapce.dto.request.WorkspaceCreateRequest;
import com.threefour.bingo.worksapce.dto.request.WorkspaceRequest;
import com.threefour.bingo.worksapce.dto.response.WorkspaceInfoResponse;
import com.threefour.bingo.worksapce.domain.Workspace;
import com.threefour.bingo.worksapce.domain.WorkspaceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Transactional
    public Workspace createWorkspace(WorkspaceCreateRequest request) {

        return workspaceRepository.save(request.toEntity());
    }

    @Transactional
    public List<WorkspaceInfoResponse> getAllWorkspacesByUser(Long id) {

        List<Enrollment> enrollmentList = enrollmentRepository.findByAppUserId(id);

        List<WorkspaceInfoResponse> workspacList = new ArrayList<>();

        for (Enrollment enrollment : enrollmentList) {
            Long workspaceId = enrollment.getWorkspace().getId();
            String name = enrollment.getWorkspace().getName();
            String description = enrollment.getWorkspace().getDescription();
            Role role = enrollment.getRole();

            WorkspaceInfoResponse temp = new WorkspaceInfoResponse(workspaceId, name, description, role);
            workspacList.add(temp);
        }

        return workspacList;
    }

    @Transactional
    public WorkspaceInfoResponse getWorkspace(WorkspaceRequest request) {

        Enrollment enrollment = enrollmentRepository.findByAppUserIdAndWorkspaceId
                (request.getUserId(), request.getWorkspaceId());

        Workspace workspace = enrollment.getWorkspace();

        Long workspaceId = enrollment.getWorkspace().getId();
        String name = enrollment.getWorkspace().getName();
        String description = enrollment.getWorkspace().getDescription();
        Role role = enrollment.getRole();

        WorkspaceInfoResponse response = new WorkspaceInfoResponse(workspaceId, name, description, role);

        return response;
    }
}
