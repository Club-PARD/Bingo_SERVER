package com.threefour.bingo.project.service;

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
    public Project createProject(ProjectCreateRequest request) {

        return projectRepository.save(request.toEntity());
    }

    @Transactional
    public List<ProjectInfoResponse> getAllProjectsByUser(Long id) {

        List<Enrollment> enrollmentList = enrollmentRepository.findByAppUserId(id);

        List<ProjectInfoResponse> projectInfoResponses = new ArrayList<>();

        for (Enrollment enrollment : enrollmentList) {
            Long projectId = enrollment.getProject().getId();
            String name = enrollment.getProject().getName();
            String description = enrollment.getProject().getDescription();
            Role role = enrollment.getRole();

            ProjectInfoResponse temp = new ProjectInfoResponse(projectId, name, description, role);
            projectInfoResponses.add(temp);
        }

        return projectInfoResponses;
    }

    @Transactional
    public ProjectInfoResponse getProject(ProjectRequest request) {

        Enrollment enrollment = enrollmentRepository.findByAppUserIdAndProjectId
                (request.getUserId(), request.getProjectId());

        Project project = enrollment.getProject();

        Long projectId = enrollment.getProject().getId();
        String name = enrollment.getProject().getName();
        String description = enrollment.getProject().getDescription();
        Role role = enrollment.getRole();

        ProjectInfoResponse response = new ProjectInfoResponse(projectId, name, description, role);

        return response;
    }
}
