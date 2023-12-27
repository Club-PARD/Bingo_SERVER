package com.threefour.bingo.worksapce.service;

import com.threefour.bingo.Role;
import com.threefour.bingo.appUser.repository.AppUserRepository;
import com.threefour.bingo.enrollment.entity.Enrollment;
import com.threefour.bingo.enrollment.repository.EnrollmentRepository;
import com.threefour.bingo.worksapce.dto.request.WorkspaceCreateRequest;
import com.threefour.bingo.worksapce.dto.request.WorkspaceRequest;
import com.threefour.bingo.worksapce.dto.response.WorkspaceInfoResponse;
import com.threefour.bingo.worksapce.dto.response.WorkspaceResponse;
import com.threefour.bingo.worksapce.entity.Workspace;
import com.threefour.bingo.worksapce.repository.WorkspaceRepository;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final AppUserRepository appUserRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Transactional
    public Workspace createWorkspace(WorkspaceCreateRequest request) {
        return workspaceRepository.save(request.toEntity());
    }

    @Transactional
    public List<WorkspaceInfoResponse> getAllWorkspacesByUser(@PathVariable Long id) {

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
