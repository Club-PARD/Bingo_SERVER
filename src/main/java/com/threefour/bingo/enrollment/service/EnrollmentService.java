package com.threefour.bingo.enrollment.service;

import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.appUser.domain.AppUserRepository;
import com.threefour.bingo.enrollment.dto.request.EnrollmentRequest;
import com.threefour.bingo.enrollment.domain.Enrollment;
import com.threefour.bingo.enrollment.domain.EnrollmentRepository;
import com.threefour.bingo.worksapce.domain.Workspace;
import com.threefour.bingo.worksapce.domain.WorkspaceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final AppUserRepository appUserRepository;
    private final WorkspaceRepository workspaceRepository;

    @Transactional
    public Enrollment joinWorkspace(EnrollmentRequest request) {

        AppUser appUser = appUserRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자"));

        Workspace workspace = workspaceRepository.findById(request.getWorkspaceId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 워크스페이스"));

        if (request.getCode().equals(workspace.getCode())) {
            Enrollment enrollment = new Enrollment(appUser, workspace, request.getRole());
            log.info("코드 맞음!");

            return enrollmentRepository.save(enrollment);
        }

        log.info("코드 틀림!");
        return null;
    }
}
