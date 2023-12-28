package com.threefour.bingo.enrollment.service;

import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.appUser.domain.AppUserRepository;
import com.threefour.bingo.enrollment.dto.request.EnrollmentRequest;
import com.threefour.bingo.enrollment.domain.Enrollment;
import com.threefour.bingo.enrollment.domain.EnrollmentRepository;
import com.threefour.bingo.project.domain.Project;
import com.threefour.bingo.project.domain.ProjectRepository;
import com.threefour.bingo.test.ResponseDto;
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
    private final ProjectRepository projectRepository;

    @Transactional
    public ResponseDto<Enrollment> joinProject(EnrollmentRequest request) {
        System.out.println("조인 프로젝트 실행 됨?");

        AppUser appUser = appUserRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User Not Found"));

        log.info("유저 찾았따 : " + appUser.getEmail());

        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Project Not Found"));

        log.info("프로젝트 찾았따 : " + project.getName());

        //코드 틀린 경우
        if (!(request.getCode().equals(project.getCode()))) {
            return ResponseDto.setFailed("wrong code");
        }

        Enrollment enrollment = new Enrollment(appUser, project, request.getRole());

        enrollmentRepository.save(enrollment);

        return ResponseDto.setSuccess("join group", enrollment);

    }
}
