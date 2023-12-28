package com.threefour.bingo.question.service;

import com.threefour.bingo.question.domain.Question;
import com.threefour.bingo.question.domain.QuestionRepository;
import com.threefour.bingo.question.dto.QuestionDto;
import com.threefour.bingo.question.dto.request.QuestionGetRequest;
import com.threefour.bingo.question.dto.request.QuestionRequest;
import com.threefour.bingo.subQuestion.domain.SubQuestion;
import com.threefour.bingo.subQuestion.domain.SubQuestionRepository;
import com.threefour.bingo.subQuestion.dto.SubQuestionDto;
import com.threefour.bingo.subQuestion.dto.request.SubQuestionRequest;
import com.threefour.bingo.subQuestion.service.SubQuestionService;
import com.threefour.bingo.template.domain.Template;
import com.threefour.bingo.template.domain.TemplateRepository;
import com.threefour.bingo.test.ResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final TemplateRepository templateRepository;
    private final SubQuestionService subQuestionService;

    @Transactional
    public List<Question> createQuestion(List<QuestionRequest> questionRequestList) {

        List<Question> questionList = new ArrayList<>();

        for (QuestionRequest questionRequest : questionRequestList) {

            log.info("템플릿 아이디: " + questionRequest.getTemplateId());

            Template template = templateRepository.findById(questionRequest.getTemplateId())
                    .orElseThrow(() -> new IllegalArgumentException("Template Not Found"));

            String mainQuestion = questionRequest.getMainQuestion();

            Question newQuestion = new Question(mainQuestion, template, new ArrayList<>());
            questionRepository.saveAndFlush(newQuestion);
            log.info("새 질문 아이디: " + newQuestion.getId());

            questionRequest.getSubQuestionRequest().setQuestionId(newQuestion.getId()); // 새로운 질문의 ID를 설정

            List<SubQuestion> subQuestionList = subQuestionService.createSubQuestions(questionRequest.getSubQuestionRequest());

            newQuestion.updateSubQuestionList(subQuestionList);

            questionList.add(newQuestion);
            questionRepository.save(newQuestion);
        }

        return questionList;
    }

    public List<QuestionDto> getAllQuestion(Long templateId) {
        List<Question> questionList = questionRepository.findByTemplateId(templateId);

        if (questionList == null || questionList.isEmpty()) {
            return new ArrayList<>();
        }

//        List<QuestionDto> questionDtoList =
//                questionList.stream().map(question -> {
//                    List<SubQuestionDto> subQuestionDtoList = question.getSubQuestionList()
//                            .stream()
//                            .map(SubQuestionDto::new)
//                            .collect(Collectors.toList());
//
//                    return new QuestionDto(question.getId(), question.getMainQuestion(), subQuestionDtoList);
//                }).collect(Collectors.toList());
        List<QuestionDto> questionDtoList =
                questionList.stream().map(question -> {
                    List<SubQuestionDto> subQuestionDtoList = subQuestionService.getAllSubQuestion(question.getId());

                    return new QuestionDto(question.getId(), question.getMainQuestion(), subQuestionDtoList);
                }).collect(Collectors.toList());

        return questionDtoList;
    }
}
