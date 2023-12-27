package com.threefour.bingo.template.service;

import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.appUser.domain.AppUserRepository;
import com.threefour.bingo.enrollment.domain.Role;
import com.threefour.bingo.enrollment.domain.Enrollment;
import com.threefour.bingo.enrollment.domain.EnrollmentRepository;
import com.threefour.bingo.question.domain.Question;
import com.threefour.bingo.template.TemplateEnum;
import com.threefour.bingo.template.domain.Template;
import com.threefour.bingo.template.domain.TemplateRepository;
import com.threefour.bingo.template.dto.request.TemplateCreateRequest;
import com.threefour.bingo.template.dto.response.TemplateResponse;
import com.threefour.bingo.test.ResponseDto;
import com.threefour.bingo.worksapce.domain.Workspace;
import com.threefour.bingo.worksapce.domain.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TemplateService {

    private final TemplateRepository templateRepository;
    private final AppUserRepository appUserRepository;
    private final WorkspaceRepository workspaceRepository;
    private final EnrollmentRepository enrollmentRepository;

    public ResponseDto<TemplateResponse> createTemplate(TemplateCreateRequest request) {
        AppUser appUser = appUserRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User Not Found"));

        Workspace workspace = workspaceRepository.findById(request.getWorkspaceId())
                .orElseThrow(() -> new IllegalArgumentException("Workspace Not Found"));

        Enrollment enrollment = enrollmentRepository.findByAppUserIdAndWorkspaceId(request.getUserId(), request.getWorkspaceId());
        Role role = enrollment.getRole();

        TemplateEnum templateEnum = request.getTemplateEnum();

        List<Question> questionList = request.getQuestionList();

        if (role == Role.TEAM_MEMBER) { // 작성자가 팀멤버 일 때

            return ResponseDto.setFailed("Team member can not make template");
        }

        Template template = new Template(appUser, workspace, templateEnum, questionList);

        TemplateResponse response = new TemplateResponse(appUser, workspace, templateEnum, questionList);

        templateRepository.save(template);

        return ResponseDto.setSuccess("Template created", response);
    }

    public ResponseDto<List<Template>> getAllTemplates(Long workspaceId) {
        List<Template> templateList = templateRepository.findAll();

        return ResponseDto.setSuccess("All Templates", templateList);
    }
}
