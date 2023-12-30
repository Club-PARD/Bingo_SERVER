package com.threefour.bingo.project.service;

import com.threefour.bingo.ResponseDto;
import com.threefour.bingo.enrollment.domain.Role;
import com.threefour.bingo.enrollment.domain.Enrollment;
import com.threefour.bingo.enrollment.domain.EnrollmentRepository;
import com.threefour.bingo.project.dto.request.ProjectCreateRequest;
import com.threefour.bingo.project.dto.request.ProjectRequest;
import com.threefour.bingo.project.dto.response.ProjectInfoResponse;
import com.threefour.bingo.project.domain.Project;
import com.threefour.bingo.project.domain.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Transactional
    public ResponseDto<Project> createProject(ProjectCreateRequest request) {

        // 이름이 없는 경우
        if (request.getName() == null || request.getName().isEmpty()) {
            ResponseDto.setFailed("Name can not be empty");
        }

        //설명이 없는 경우
        if (request.getDescription() == null || request.getDescription().isEmpty()) {
            ResponseDto.setFailed("Description can not be empty");
        }

        //코드가 없는 경우
        if (request.getCode() == null || request.getCode().isEmpty()) {
            ResponseDto.setFailed("Code can not be empty");
        }

        projectRepository.save(request.toEntity());

        return ResponseDto.setSuccess("Project created", request.toEntity());
    }

    @Transactional
    public ResponseDto<List<ProjectInfoResponse>> getAllProjectsByUser(Long id) {

        List<ProjectInfoResponse> projectInfoResponses = new ArrayList<>();

        List<Enrollment> enrollmentList = enrollmentRepository.findByAppUserId(id);

        if (enrollmentList.isEmpty()) {
            return ResponseDto.setFailed("No Workspaces");
        }

        for (Enrollment enrollment : enrollmentList) {
            Long projectId = enrollment.getProject().getId();
            String name = enrollment.getProject().getName();
            String description = enrollment.getProject().getDescription();
            Role role = enrollment.getRole();

            ProjectInfoResponse temp = new ProjectInfoResponse(projectId, name, description, role);
            projectInfoResponses.add(temp);
        }

        return ResponseDto.setSuccess("Project List", projectInfoResponses);
    }

    @Transactional
    public ResponseDto<ProjectInfoResponse> getProject(Long userId, Long projectId) {

        Enrollment enrollment = enrollmentRepository.findByAppUserIdAndProjectId
                (userId, projectId);

        if (enrollment == null) { //검색한 프로젝트가 없는 경우
            return ResponseDto.setFailed("Workspace Not Found");
        }

        String name = enrollment.getProject().getName();
        String description = enrollment.getProject().getDescription();
        Role role = enrollment.getRole();

        ProjectInfoResponse response = new ProjectInfoResponse(projectId, name, description, role);

        return ResponseDto.setSuccess("Project", response);
    }

}
