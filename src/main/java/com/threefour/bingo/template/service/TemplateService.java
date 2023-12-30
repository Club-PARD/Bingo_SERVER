package com.threefour.bingo.template.service;

import com.threefour.bingo.ResponseDto;
import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.appUser.domain.AppUserRepository;
import com.threefour.bingo.enrollment.domain.Role;
import com.threefour.bingo.enrollment.domain.Enrollment;
import com.threefour.bingo.enrollment.domain.EnrollmentRepository;
import com.threefour.bingo.question.domain.Question;
import com.threefour.bingo.question.dto.QuestionDTO;
import com.threefour.bingo.question.dto.request.QuestionRequest;
import com.threefour.bingo.question.service.QuestionService;
import com.threefour.bingo.template.domain.TemplateType;
import com.threefour.bingo.template.domain.Template;
import com.threefour.bingo.template.domain.TemplateRepository;
import com.threefour.bingo.template.dto.TemplateDTO;
import com.threefour.bingo.template.dto.request.TemplateGetAllRequest;
import com.threefour.bingo.template.dto.request.TemplateGetRequest;
import com.threefour.bingo.template.dto.request.TemplatePostRequest;
import com.threefour.bingo.project.domain.Project;
import com.threefour.bingo.project.domain.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
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

        Template newTemplate = new Template(request.getName(), appUser, project, templateType, new ArrayList<>());
        templateRepository.saveAndFlush(newTemplate);
        log.info("새 템플릿 아이디: " + newTemplate.getId());

        List<QuestionRequest> questionRequestList = request.getQuestionRequestList();
        for (QuestionRequest questionRequest : questionRequestList) {
            questionRequest.setTemplateId(newTemplate.getId()); // 새로운 템플릿의 ID를 설정
        }

        List<Question> questionList = questionService.createQuestion(questionRequestList);
        newTemplate.updateQuestionList(questionList);

        templateRepository.save(newTemplate);

        return ResponseDto.setSuccess("Template created", newTemplate);

    }


    @Transactional
    public ResponseDto<List<TemplateDTO>> getAllTemplates(TemplateGetAllRequest request) {

        List<Template> templateList = templateRepository.findByAppUserIdAndProjectId(request.getAppUserId(), request.getProjectId());

        if (templateList == null || templateList.isEmpty()) {
            return ResponseDto.setFailed("Template Not Found");
        }

        List<TemplateDTO> templateDTOList = templateList.stream().map(template -> {
            List<QuestionDTO> questionDTOList = questionService.getAllQuestion(template.getId());

            return new TemplateDTO(template.getId(), template.getName(), template.getTemplateType(), questionDTOList);
        }).collect(Collectors.toList());

        return ResponseDto.setSuccess("Template", templateDTOList);

    }

    @Transactional
    public TemplateDTO getTemplate(TemplateGetRequest request) {

        Template template = templateRepository.findByAppUserIdAndProjectIdAndId
                (request.getAppUserId(), request.getProjectId(), request.getTemplateId());

        if (template == null) {
            return new TemplateDTO();
        }

        List<QuestionDTO> questionDTOList = questionService.getAllQuestion(template.getId());

        TemplateDTO templateDto = new TemplateDTO(template.getId(), template.getName(), template.getTemplateType(), questionDTOList);

        log.info("나는 윤성현");

        return templateDto;
    }
}
