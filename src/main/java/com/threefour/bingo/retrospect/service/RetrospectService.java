package com.threefour.bingo.retrospect.service;

import com.threefour.bingo.answer.domain.Answer;
import com.threefour.bingo.answer.domain.AnswerRepository;
import com.threefour.bingo.answer.dto.AnswerDTO;
import com.threefour.bingo.answer.service.AnswerService;
import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.appUser.domain.AppUserRepository;
import com.threefour.bingo.appUser.dto.response.AppUserInfoResponse;
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
import com.threefour.bingo.template.domain.Template;
import com.threefour.bingo.template.domain.TemplateRepository;
import com.threefour.bingo.template.dto.TemplateDTO;
import com.threefour.bingo.template.dto.response.TemplateOneResponse;
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

    @Transactional
    public RetrospectPostResponse writeRetrospect(RetrospectPostRequest request) {

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

        AppUserInfoResponse appUserInfoResponse = appUserService.getUserInfo(request.getAppUserId());

        RetrospectPostResponse response = new RetrospectPostResponse(appUserInfoResponse, answerList);

        return response;
    }

    @Transactional
    public List<SubQuestionDTO> getSubQuestionsFromTemplate(Template template) {

        List<Question> questionList = template.getQuestionList();
        List<SubQuestionDTO> subQuestionDTOList = new ArrayList<>();

        for (Question question : questionList) {
            List<SubQuestionDTO> temp = subQuestionService.getAllSubQuestion(question.getId());

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

        TemplateOneResponse template = templateService.getTemplate(projectId, templateId);

        List<QuestionDTO> questionDTOList = template.getQuestionList();

        TemplateDTO templateDTO = new TemplateDTO(template.getId(), template.getName(), questionDTOList);
        List<TagDTO> tagDTOList = teamEvaluation(projectId, templateId);

        RetrospectGetResponse response = new RetrospectGetResponse(tagDTOList, templateDTO);

        return response;
    }

    public List<TagDTO> teamEvaluation(Long projectId, Long templateId) {

        List<Tag> tagProjectList = tagRepository.findByProjectIdAndTemplateId(
                projectId, null
        );
        List<Tag> tagTemplateList = tagRepository.findByProjectIdAndTemplateId(
                projectId, templateId
        );

        for (int i = 0; i < tagTemplateList.size(); i++) {

            Tag templateTag = tagTemplateList.get(i);
            Tag projectTag = tagProjectList.get(i);
            if (templateTag.isSelected() == true) {
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

}