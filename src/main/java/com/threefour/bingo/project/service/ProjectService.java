package com.threefour.bingo.project.service;

import com.threefour.bingo.ResponseDto;
import com.threefour.bingo.aws.AWSService;
import com.threefour.bingo.enrollment.domain.Role;
import com.threefour.bingo.enrollment.domain.Enrollment;
import com.threefour.bingo.enrollment.domain.EnrollmentRepository;
import com.threefour.bingo.enrollment.dto.request.EnrollmentRequest;
import com.threefour.bingo.enrollment.service.EnrollmentService;
import com.threefour.bingo.project.dto.request.ProjectCreateRequest;
import com.threefour.bingo.project.dto.response.ProjectAllResponse;
import com.threefour.bingo.project.dto.response.ProjectOneResponse;
import com.threefour.bingo.project.domain.Project;
import com.threefour.bingo.project.domain.ProjectRepository;
import com.threefour.bingo.tag.domain.Tag;
import com.threefour.bingo.tag.domain.TagRepository;
import com.threefour.bingo.tag.dto.TagDTO;
import com.threefour.bingo.tag.dto.request.TagListProjectRequest;
import com.threefour.bingo.tag.service.TagService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final TagRepository tagRepository;
    private final TagService tagService;
    private final EnrollmentService enrollmentService;
    private final AWSService awsService;

    @Transactional
    public ProjectOneResponse createProject(ProjectCreateRequest request) {

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

        final Project project = request.toEntity();
        projectRepository.saveAndFlush(project);

        TagListProjectRequest tagListProjectRequest = new TagListProjectRequest(
                project.getId(), request.getTagList()
        );

        EnrollmentRequest enrollmentRequest = new EnrollmentRequest(request.getUserId(),
                project.getId(), request.getCode(), Role.TEAM_LEADER);

        enrollmentService.joinProject(enrollmentRequest);
        tagService.createProjectBingo(tagListProjectRequest);

        final ProjectOneResponse response = new ProjectOneResponse(project.getId(), project.getName(),
                project.getDescription());

        return response;

    }

    @Transactional
    public List<ProjectAllResponse> getAllProjectsByUser(Long id) {
        final List<String> pictures = awsService.getFileList("projectList");

        final List<ProjectAllResponse> projectAllResponses = new ArrayList<>();

        final List<Enrollment> enrollmentList = enrollmentRepository.findByAppUserId(id);

        if (enrollmentList.isEmpty()) {
            return null;
        }

        for (Enrollment enrollment : enrollmentList) {
            String picture = enrollment.getProject().getPicture();

            if (pictures.contains(picture)) {
                Long projectId = enrollment.getProject().getId();
                String name = enrollment.getProject().getName();
                String description = enrollment.getProject().getDescription();
                Role role = enrollment.getRole();

                ProjectAllResponse temp = new ProjectAllResponse(projectId, name, description, picture, role);
                projectAllResponses.add(temp);
            }
        }

        return projectAllResponses;
    }


    @Transactional
    public ProjectOneResponse getProject(Long userId, Long projectId) {

        final Enrollment enrollment = enrollmentRepository.findByAppUserIdAndProjectId
                (userId, projectId);

        if (enrollment == null) { //검색한 프로젝트가 없는 경우
            return null;
        }

        String name = enrollment.getProject().getName();
        String description = enrollment.getProject().getDescription();
        Role role = enrollment.getRole();

        List<Tag> tagList = tagRepository.findByProjectId(projectId);
        List<TagDTO> tagDTOList = tagList.stream()
                .map(tag -> new TagDTO(tag.getId(), tag.getName(), tag.getCount()))
                .collect(Collectors.toList());


        final ProjectOneResponse response = new ProjectOneResponse(projectId, name, description, role, tagDTOList);

        return response;
    }

}
