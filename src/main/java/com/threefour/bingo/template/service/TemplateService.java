package com.threefour.bingo.template.service;

import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.appUser.domain.AppUserRepository;
import com.threefour.bingo.enrollment.domain.Role;
import com.threefour.bingo.enrollment.domain.Enrollment;
import com.threefour.bingo.enrollment.domain.EnrollmentRepository;
import com.threefour.bingo.question.domain.Question;
import com.threefour.bingo.question.dto.QuestionDto;
import com.threefour.bingo.question.dto.request.QuestionGetRequest;
import com.threefour.bingo.question.service.QuestionService;
import com.threefour.bingo.template.domain.TemplateType;
import com.threefour.bingo.template.domain.Template;
import com.threefour.bingo.template.domain.TemplateRepository;
import com.threefour.bingo.template.dto.TemplateDto;
import com.threefour.bingo.template.dto.request.TemplateGetRequest;
import com.threefour.bingo.template.dto.request.TemplatePostRequest;
import com.threefour.bingo.test.ResponseDto;
import com.threefour.bingo.project.domain.Project;
import com.threefour.bingo.project.domain.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TemplateService {

    private final TemplateRepository templateRepository;
    private final AppUserRepository appUserRepository;
    private final ProjectRepository projectRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final QuestionService questionService;

    @Transactional
    public ResponseDto<Template> createTemplate(TemplatePostRequest request) {

        AppUser appUser = appUserRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User Not Found"));

        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Workspace Not Found"));

        Enrollment enrollment = enrollmentRepository.findByAppUserIdAndProjectId(request.getUserId(), request.getProjectId());
        Role role = enrollment.getRole();
        if (role == Role.TEAM_MEMBER) { // 작성자가 팀멤버 일 때

            return ResponseDto.setFailed("Team member can not make template");
        }

        TemplateType templateType = request.getTemplateType();

        Template newTemplate = new Template(appUser, project, templateType, new ArrayList<>());
        templateRepository.save(newTemplate);

        List<Question> questionList = questionService.createQuestion(request.getQuestionRequestList());
        newTemplate.updateQuestionList(questionList);

        templateRepository.save(newTemplate);

        return ResponseDto.setSuccess("Template created", newTemplate);

    }

    @Transactional
    public ResponseDto<List<TemplateDto>> getTemplate(TemplateGetRequest request) {

        List<Template> templateList = templateRepository.findByAppUserIdAndProjectId(request.getAppUserId(), request.getProjectId());

        if (templateList == null || templateList.isEmpty()) {
            return ResponseDto.setFailed("Template Not Found");
        }

//        List<TemplateDto> templateDtoList = templateList.stream().map(template -> {
//            List<QuestionDto> questionDtoList = template.getQuestionList()
//                    .stream()
//                    .map(QuestionDto::new)
//                    .collect(Collectors.toList());
//
//            return new TemplateDto(template.getId(), template.getTemplateType(), questionDtoList);
//        }).collect(Collectors.toList());

        List<TemplateDto> templateDtoList = templateList.stream().map(template -> {
            List<QuestionDto> questionDtoList = questionService.getAllQuestion(request.getTemplateId());

            return new TemplateDto(template.getId(), template.getTemplateType(), questionDtoList);
        }).collect(Collectors.toList());

        return ResponseDto.setSuccess("Template", templateDtoList);

    }

}
