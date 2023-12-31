package com.threefour.bingo.enrollment.service;

import com.threefour.bingo.ResponseDto;
import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.appUser.domain.AppUserRepository;
import com.threefour.bingo.enrollment.dto.request.EnrollmentRequest;
import com.threefour.bingo.enrollment.domain.Enrollment;
import com.threefour.bingo.enrollment.domain.EnrollmentRepository;
import com.threefour.bingo.enrollment.dto.response.EnrollmentResponse;
import com.threefour.bingo.project.domain.Project;
import com.threefour.bingo.project.domain.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final AppUserRepository appUserRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    public EnrollmentResponse joinProject(EnrollmentRequest request) {

        final AppUser appUser = appUserRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User Not Found"));

        final Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Project Not Found"));

        //코드 틀린 경우
        if (!(request.getCode().equals(project.getCode()))) {
            return null;
        }

        final Enrollment enrollment = new Enrollment(appUser, project, request.getRole());

        enrollmentRepository.save(enrollment);

        final EnrollmentResponse response = new EnrollmentResponse(appUser, project, request.getRole());


        return response;

    }
}
