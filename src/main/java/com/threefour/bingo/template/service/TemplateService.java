package com.threefour.bingo.template.service;

import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.appUser.domain.AppUserRepository;
import com.threefour.bingo.enrollment.domain.Role;
import com.threefour.bingo.enrollment.domain.Enrollment;
import com.threefour.bingo.enrollment.domain.EnrollmentRepository;
import com.threefour.bingo.question.domain.Question;
import com.threefour.bingo.question.dto.QuestionDTO;
import com.threefour.bingo.question.dto.request.QuestionRequest;
import com.threefour.bingo.question.service.QuestionService;
import com.threefour.bingo.tag.domain.Tag;
import com.threefour.bingo.tag.domain.TagRepository;
import com.threefour.bingo.tag.dto.TagDTO;
import com.threefour.bingo.tag.dto.request.TagListTemplateRequest;
import com.threefour.bingo.tag.service.TagService;
import com.threefour.bingo.template.domain.TemplateType;
import com.threefour.bingo.template.domain.Template;
import com.threefour.bingo.template.domain.TemplateRepository;
import com.threefour.bingo.template.dto.request.TemplatePostRequest;
import com.threefour.bingo.project.domain.Project;
import com.threefour.bingo.project.domain.ProjectRepository;
import com.threefour.bingo.template.dto.response.TemplateAllResponse;
import com.threefour.bingo.template.dto.response.TemplateOneResponse;
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
    private final TagService tagService;
    private final TagRepository tagRepository;

    @Transactional
    public TemplateAllResponse createTemplate(TemplatePostRequest request) {

        final AppUser appUser = appUserRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User Not Found"));

        final Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Workspace Not Found"));

        final Enrollment enrollment = enrollmentRepository.findByAppUserIdAndProjectId(request.getUserId(), request.getProjectId());
        final Role role = enrollment.getRole();
        if (role == Role.TEAM_MEMBER) { // 작성자가 팀멤버 일 때
            log.info("Team Member can not make template");
            return null;
        }

        final TemplateType templateType = request.getTemplateType();

        final Template newTemplate = new Template(request.getName(), appUser, project, templateType, new ArrayList<>());
        templateRepository.saveAndFlush(newTemplate);
        log.info("새 템플릿 아이디: " + newTemplate.getId());

        List<QuestionRequest> questionRequestList = request.getQuestionRequestList();
        for (QuestionRequest questionRequest : questionRequestList) {
            questionRequest.setTemplateId(newTemplate.getId()); // 새로운 템플릿의 ID를 설정
        }

        final List<Question> questionList = questionService.createQuestion(questionRequestList);
        newTemplate.updateQuestionList(questionList);


        templateRepository.save(newTemplate);

        final List<QuestionDTO> questionDTOList = questionService.getAllQuestion(newTemplate.getId());

        TagListTemplateRequest tagListTemplateRequest = new TagListTemplateRequest(request.getProjectId(), newTemplate.getId(), request.getTagList());
        List<Tag> tagList = tagService.createTemplateBingo(tagListTemplateRequest);
        tagRepository.saveAll(tagList);

        final TemplateAllResponse response = new TemplateAllResponse(newTemplate.getId(), newTemplate.getName(),
                newTemplate.getTemplateType(), questionDTOList);

        return response;

    }

    @Transactional
    public List<TemplateAllResponse> getAllTemplates(Long projectId) {

        final List<Template> templateList = templateRepository.findByProjectId(projectId);

        if (templateList == null || templateList.isEmpty()) {
            return null;
        }

        final List<TemplateAllResponse> templateAllResponseList = templateList.stream().map(template -> {
            List<QuestionDTO> questionDTOList = questionService.getAllQuestion(template.getId());

            return new TemplateAllResponse(template.getId(), template.getName(), template.getTemplateType(), questionDTOList);
        }).collect(Collectors.toList());

        return templateAllResponseList;

    }

    @Transactional
    public TemplateOneResponse getTemplate(Long projectId, Long templateId) {

        final Template template = templateRepository.findByProjectIdAndId(projectId, templateId);

        if (template == null) {
            return null;
        }

        final List<QuestionDTO> questionDTOList = questionService.getAllQuestion(template.getId());

        List<Tag> tagList = tagRepository.findByProjectIdAndTemplateId(projectId, templateId);
        List<TagDTO> tagDTOList = tagList.stream()
                .map(tag -> new TagDTO(tag.getId(), tag.getName(), tag.getCount()))
                .collect(Collectors.toList());

        final TemplateOneResponse response = new TemplateOneResponse(template.getId(), template.getName(), template.getTemplateType(), questionDTOList, tagDTOList);

        return response;

    }
}
