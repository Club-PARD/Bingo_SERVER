package com.threefour.bingo.retrospect.service;

import com.threefour.bingo.answer.domain.Answer;
import com.threefour.bingo.answer.domain.AnswerRepository;
import com.threefour.bingo.answer.dto.AnswerDTO;
import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.appUser.domain.AppUserRepository;
import com.threefour.bingo.project.domain.Project;
import com.threefour.bingo.project.domain.ProjectRepository;
import com.threefour.bingo.question.domain.Question;
import com.threefour.bingo.question.service.QuestionService;
import com.threefour.bingo.retrospect.domain.Retrospect;
import com.threefour.bingo.retrospect.domain.RetrospectRepository;
import com.threefour.bingo.retrospect.dto.request.RetrospectPostRequest;
import com.threefour.bingo.retrospect.dto.request.TeamEvaluationRequest;
import com.threefour.bingo.retrospect.dto.response.RetrospectGetResponse;
import com.threefour.bingo.retrospect.dto.response.RetrospectPostResponse;
import com.threefour.bingo.retrospect.dto.response.TeamEvaluationResponse;
import com.threefour.bingo.subQuestion.domain.SubQuestion;
import com.threefour.bingo.subQuestion.domain.SubQuestionRepository;
import com.threefour.bingo.subQuestion.dto.SubQuestionDTO;
import com.threefour.bingo.subQuestion.service.SubQuestionService;
import com.threefour.bingo.tag.domain.Tag;
import com.threefour.bingo.tag.domain.TagRepository;
import com.threefour.bingo.tag.dto.request.TagListProjectRequest;
import com.threefour.bingo.tag.service.TagService;
import com.threefour.bingo.template.domain.Template;
import com.threefour.bingo.template.domain.TemplateRepository;
import com.threefour.bingo.template.service.TemplateService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    private final QuestionService questionService;
    private final TemplateService templateService;
    private final TagService tagService;

    @Transactional
    /*
    1. 템플릿으로 부터 질문 리스트 받아옴
    2. 질문 리스트 토대로 답변 작성
    */
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

        RetrospectPostResponse response = new RetrospectPostResponse(appUser, answerList);

        return response;
    }

    private List<SubQuestionDTO> getSubQuestionsFromTemplate(Template template) {

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

    public List<RetrospectGetResponse> getRetrospect(Long projectId, Long templateId) {

        List<Retrospect> retrospectList = retrospectRepository.findByProjectIdAndTemplateId(projectId, templateId);

        List<RetrospectGetResponse> responseList = new ArrayList<>();

        return responseList;
    }

    public TeamEvaluationResponse teamEvaluation(TeamEvaluationRequest request) {

        TagListProjectRequest request1 = new TagListProjectRequest(request.getProjectId(),
                request.getName());

        TeamEvaluationResponse response = new TeamEvaluationResponse();

        List<Tag> temp = tagService.createBingo(request1);

        return response;

    }


}
