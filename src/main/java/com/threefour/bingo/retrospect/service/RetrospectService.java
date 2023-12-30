package com.threefour.bingo.retrospect.service;

import com.threefour.bingo.ResponseDto;
import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.appUser.domain.AppUserRepository;
import com.threefour.bingo.question.dto.QuestionDTO;
import com.threefour.bingo.retrospect.domain.Retrospect;
import com.threefour.bingo.retrospect.domain.RetrospectRepository;
import com.threefour.bingo.retrospect.domain.TeamEvaluation;
import com.threefour.bingo.retrospect.dto.request.RetrospectPostRequest;
import com.threefour.bingo.subQuestion.domain.SubQuestion;
import com.threefour.bingo.subQuestion.domain.SubQuestionRepository;
import com.threefour.bingo.subQuestion.dto.SubQuestionDTO;
import com.threefour.bingo.subQuestion.service.SubQuestionService;
import com.threefour.bingo.template.domain.Template;
import com.threefour.bingo.template.domain.TemplateRepository;
import com.threefour.bingo.template.domain.TemplateType;
import com.threefour.bingo.template.dto.TemplateDTO;
import com.threefour.bingo.template.dto.request.TemplateGetRequest;
import com.threefour.bingo.template.service.TemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class RetrospectService {

    private final AppUserRepository appUserRepository;
    private final TemplateRepository templateRepository;
    private final RetrospectRepository retrospectRepository;
    private final SubQuestionRepository subQuestionRepository;
    private final TemplateService templateService;
    private final SubQuestionService subQuestionService;

    public ResponseDto<Retrospect> writeTemplate(RetrospectPostRequest request) {

        AppUser appUser = appUserRepository.findById(request.getAppUserId())
                .orElseThrow(() -> new IllegalArgumentException("App User Not Found"));

        Template template = templateRepository.findById(request.getTemplateId())
                .orElseThrow(() -> new IllegalArgumentException("Template Not Found"));


        TemplateGetRequest templateGetRequest = new TemplateGetRequest(
                1L, 3L, 25L
        );

        TemplateDTO templateDTO = templateService.getTemplate(templateGetRequest);

        if (templateDTO == null) { // 템플릿이 존재하지 않는 경우
            return ResponseDto.setFailed("Template Not Found");
        }

        List<SubQuestionDTO> allSubQuestions = new ArrayList<>();
        List<QuestionDTO> questionList = templateDTO.getQuestionList();
        log.info("메인 질문 리스트 개수: " + questionList.size());
        for (int i = 0; i < questionList.size(); i++) {
            QuestionDTO question = questionList.get(i);
            List<SubQuestionDTO> subQuestionList = subQuestionService.getAllSubQuestion(question.getId());
            allSubQuestions.addAll(subQuestionList);
            log.info("서브 질문 리스트 개수: " + subQuestionList.size());
        }

        List<String> answerList = request.getAnswerList();

        if (answerList == null || answerList.isEmpty()) { // 답변 하나도 안 한 경우
            return ResponseDto.setFailed("Request SubQuestion List Not Found");
        }

        if (allSubQuestions.size() != answerList.size()) { // 모든 답변을 하지 않은 경우
            return ResponseDto.setFailed("User should answer about all questions");
        }

        Long id = templateDTO.getId();
        String name = templateDTO.getName();
        TemplateType templateType = templateDTO.getTemplateType();

        List<SubQuestion> result = new ArrayList<>(allSubQuestions.size());

        for (int i = 0; i < allSubQuestions.size(); i++) {
            SubQuestion temp = new SubQuestion();
            result.add(temp);
            subQuestionRepository.save(temp);
        }


        return ResponseDto.setFailed("Successfully updated the SubQuestions");
    }

    public Map<TeamEvaluation, Long> getTeamEvaluationCount() {
        List<Object[]> results = retrospectRepository.countByTeamEvaluation();
        Map<TeamEvaluation, Long> countMap = new HashMap<>();
        for (Object[] result : results) {
            countMap.put((TeamEvaluation) result[0], (Long) result[1]);
        }
        return countMap;
    }
}
