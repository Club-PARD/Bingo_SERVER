package com.threefour.bingo.subQuestion.service;

import com.threefour.bingo.question.domain.Question;
import com.threefour.bingo.question.domain.QuestionRepository;
import com.threefour.bingo.subQuestion.domain.SubQuestion;
import com.threefour.bingo.subQuestion.domain.SubQuestionRepository;
import com.threefour.bingo.subQuestion.dto.SubQuestionDTO;
import com.threefour.bingo.subQuestion.dto.request.SubQuestionRequest;
import com.threefour.bingo.test.ResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubQuestionService {

    private final SubQuestionRepository subQuestionRepository;
    private final QuestionRepository questionRepository;

    @Transactional
    public List<SubQuestion> createSubQuestions(SubQuestionRequest request) {

        Question question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new IllegalArgumentException("Question Not Found"));

        Map<String, String> subQuestions = request.getSubQnA();

        List<SubQuestion> newSubQuestions = new ArrayList<>();

        for (Map.Entry<String, String> entry : subQuestions.entrySet()) {
            String subQuestionText = entry.getKey();
            String answer = entry.getValue();

            if (subQuestionText.isEmpty()) {
                ResponseDto.setFailed("SubQuestion can not be empty");
                return Collections.emptyList();
            }

            SubQuestion newSubQuestion = new SubQuestion(subQuestionText, "", question);
            subQuestionRepository.save(newSubQuestion);
            newSubQuestions.add(newSubQuestion);
        }

        return newSubQuestions;
    }

    public List<SubQuestionDTO> getAllSubQuestion(Long questionId) {

        List<SubQuestion> subQuestionList = subQuestionRepository.findByQuestionId(questionId);

        if (subQuestionList == null || subQuestionList.isEmpty()) {
            return new ArrayList<>();
        }

        List<SubQuestionDTO> subQuestionDTOList = subQuestionList.stream()
                .map(SubQuestionDTO::new)
                .collect(Collectors.toList());

        return subQuestionDTOList;
    }
}
