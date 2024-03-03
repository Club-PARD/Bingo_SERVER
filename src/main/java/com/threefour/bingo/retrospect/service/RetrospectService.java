package com.threefour.bingo.retrospect.service;

import com.threefour.bingo.answer.domain.Answer;
import com.threefour.bingo.answer.domain.AnswerRepository;
import com.threefour.bingo.answer.dto.AnswerDTO;
import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.appUser.domain.AppUserRepository;
import com.threefour.bingo.appUser.dto.response.AppUserResponse;
import com.threefour.bingo.appUser.service.AppUserService;
import com.threefour.bingo.project.domain.Project;
import com.threefour.bingo.project.domain.ProjectRepository;
import com.threefour.bingo.question.domain.Question;
import com.threefour.bingo.question.dto.QuestionDTO;
import com.threefour.bingo.retrospect.domain.Retrospect;
import com.threefour.bingo.retrospect.domain.RetrospectRepository;
import com.threefour.bingo.retrospect.dto.request.RetrospectPostRequest;
import com.threefour.bingo.retrospect.dto.response.RetrospectGetResponse;
import com.threefour.bingo.retrospect.dto.response.RetrospectPostResponse;
import com.threefour.bingo.subQuestion.domain.SubQuestion;
import com.threefour.bingo.subQuestion.domain.SubQuestionRepository;
import com.threefour.bingo.subQuestion.dto.SubQuestionDTO;
import com.threefour.bingo.subQuestion.service.SubQuestionService;
import com.threefour.bingo.tag.domain.Tag;
import com.threefour.bingo.tag.domain.TagRepository;
import com.threefour.bingo.tag.dto.TagDTO;
import com.threefour.bingo.tag.dto.request.TagPostRequest;
import com.threefour.bingo.tag.service.TagService;
import com.threefour.bingo.template.domain.Template;
import com.threefour.bingo.template.domain.TemplateRepository;
import com.threefour.bingo.template.dto.TemplateDTO;
import com.threefour.bingo.template.dto.response.TemplateDTOResponse;
import com.threefour.bingo.template.service.TemplateService;
import jakarta.persistence.EntityNotFoundException;
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
public class RetrospectService {

    private final SubQuestionRepository subQuestionRepository;
    private final AppUserRepository appUserRepository;
    private final TemplateRepository templateRepository;
    private final ProjectRepository projectRepository;
    private final SubQuestionService subQuestionService;
    private final TagRepository tagRepository;
    private final AnswerRepository answerRepository;
    private final RetrospectRepository retrospectRepository;
    private final TemplateService templateService;
    private final AppUserService appUserService;
    private final TagService tagService;

    @Transactional
    public RetrospectPostResponse writeRetrospect(RetrospectPostRequest request) {

        log.info("templateID in r: {}", request.getTemplateId());
        log.info("projectID in r: {}", request.getProjectId());

        final AppUser appUser = appUserRepository.findById(request.getAppUserId())
                .orElseThrow(() -> new EntityNotFoundException("User Not Found"));

        final Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new EntityNotFoundException("Project Not Found"));

        final Template template = templateRepository.findById(request.getTemplateId())
                .orElseThrow(() -> new EntityNotFoundException("Template Not Found"));

        final List<SubQuestionDTO> subQuestionList = getSubQuestionsFromTemplate(template);

        final List<AnswerDTO> answerList = createAndSaveAnswers(request.getAnswerList(), subQuestionList, appUser);

        Retrospect retrospect = new Retrospect(appUser, project, template);
        retrospectRepository.save(retrospect);

        List<TagPostRequest> temp = request.getTagList();
        log.info("너의 사이즈: {}", temp.size());

        for (int i = 0; i < temp.size(); i++) {
            log.info("너의 아이디은 무엇이냐: {}", temp.get(i).getId());
            log.info("너의 값은 무엇이냐: {}", temp.get(i).getSelected());
        }

        List<TagPostRequest> tagDTOList = request.getTagList();
//        List<TagDTO> tagDTOS = teamEvaluation(retrospect.getId(), request.getTemplateId());
        List<TagDTO> tagDTOS = teamEvaluation(request.getTagList(), request.getProjectId(), request.getTemplateId());

        AppUserResponse appUserResponse = appUserService.getUserInfo(request.getAppUserId());

        RetrospectPostResponse response = new RetrospectPostResponse(appUserResponse, answerList, tagDTOS);

        return response;
    }

    public List<TagDTO> teamEvaluation(List<TagPostRequest> tagPostRequests, Long projectId, Long templateId) {
        log.info("하이 나는 팀 평가");
        List<Tag> tagProjectList = tagRepository.findByProjectIdAndTemplateId(
                projectId, null
        );
        log.info("templateID in team: {}", templateId);
        log.info("projectID in team: {}", projectId);
        List<Tag> tagTemplateList = tagRepository.findByProjectIdAndTemplateId(
                projectId, templateId
        );

        log.info("size of template: {}", tagTemplateList.size());
        log.info("size of project: {}", tagProjectList.size());
        log.info("size of request: {}", tagPostRequests.size());

        for (int i = 0; i < tagPostRequests.size(); i++) {

            Tag templateTag = tagTemplateList.get(i);
            Tag projectTag = tagProjectList.get(i);
            log.info("너의 값은? {}", tagPostRequests.get(i).getSelected());
            if (tagPostRequests.get(i).getSelected() == 1) {
                log.info("하이 나는 트루임");
                templateTag.countUp();
                projectTag.countUp();
                tagRepository.save(templateTag);
                tagRepository.save(projectTag);
            }
        }

        List<TagDTO> tagDTOList = tagTemplateList.stream()
                .map(tag -> new TagDTO(tag.getId(), tag.getName(), tag.getCount()))
                .collect(Collectors.toList());

        return tagDTOList;

    }

    @Transactional
    public List<SubQuestionDTO> getSubQuestionsFromTemplate(Template template) {

        List<Question> questionList = template.getQuestionList();
        List<SubQuestionDTO> subQuestionDTOList = new ArrayList<>();

        for (Question question : questionList) {
            List<SubQuestionDTO> temp = subQuestionService.getAllSubQuestionDTO(question.getId());

            subQuestionDTOList.addAll(temp);
        }

        log.info("size of List<SubQuestionDTO>: {}", subQuestionDTOList.size());

        return subQuestionDTOList;

    }

    private List<AnswerDTO> createAndSaveAnswers(List<AnswerDTO> answersDTO, List<SubQuestionDTO> subQuestionList, AppUser appUser) {

        log.info("size of List<AnswerDTO>: {}", answersDTO.size());
        log.info("size of List<SubQuestionDTO>: {}", subQuestionList.size());
        if (answersDTO.size() != subQuestionList.size()) {
            throw new IllegalArgumentException("The number of answers does not match the number of sub-questions.");
        }

        List<AnswerDTO> answerDTOList = new ArrayList<>();

        for (int i = 0; i < subQuestionList.size(); i++) {
            SubQuestionDTO subQuestionDTO = subQuestionList.get(i);

            SubQuestion subQuestion = subQuestionRepository.findById(subQuestionDTO.getId())
                    .orElseThrow(() -> new EntityNotFoundException("SubQuestion Not Found"));

            AnswerDTO answerDTO = answersDTO.get(i);
            Answer answer = new Answer(answerDTO.getAns(), subQuestion, appUser);

            answerRepository.save(answer);
            answerDTOList.add(answerDTO);
        }

        return answerDTOList;
    }

    public RetrospectGetResponse getRetrospect(Long projectId, Long templateId) {

        // Retrospect 조회
        List<Retrospect> retrospectList = retrospectRepository.findByProjectIdAndTemplateId(projectId, templateId);

        TemplateDTOResponse template = templateService.getTemplateDTO(projectId, templateId);

        List<QuestionDTO> questionDTOList = template.getQuestionList();

        TemplateDTO templateDTO = new TemplateDTO(template.getId(), template.getName(), questionDTOList);
        List<Tag> tagList = tagRepository.findByProjectIdAndTemplateId(projectId, templateId);
        List<TagDTO> tagDTOList = tagService.getTagList(projectId, templateId);

        RetrospectGetResponse response = new RetrospectGetResponse(tagDTOList, templateDTO);

        return response;
    }

//    public RetrospectGetResponse getAllRetrospects(Long appUserId, Long projectId) {
//
//        List<Template> templateList = templateRepository.findByProjectId(projectId);
//
//        List<Retrospect> retrospectListByUser = retrospectRepository.findByProjectIdAndTemplateId(appUserId, projectId);
//
//        for (int i = 0; i < templateList.size(); i++) {
//            Retrospect retrospect = retrospectRepository.findByAppUserIdAndProjectIdAAndTemplateId(appUserId, projectId, templateList.get(i).getId());
//
//            if (retrospect != null) {
//
//            }
//
//        }
//
////        TemplateOneResponse template = templateService.getTemplate(projectId, templateId);
////
////        List<QuestionDTO> questionDTOList = template.getQuestionList();
////
////        TemplateDTO templateDTO = new TemplateDTO(template.getId(), template.getName(), questionDTOList);
////        List<Tag> tagList = tagRepository.findByProjectIdAndTemplateId(projectId, templateId);
////        List<TagDTO> tagDTOList = tagService.getTagList(projectId, templateId);
//
//        RetrospectGetResponse response = new RetrospectGetResponse(tagDTOList, templateDTO);
//
//        return response;
//
//    }

}