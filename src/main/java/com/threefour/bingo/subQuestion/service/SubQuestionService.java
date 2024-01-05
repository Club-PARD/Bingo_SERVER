package com.threefour.bingo.subQuestion.service;

import com.threefour.bingo.question.domain.Question;
import com.threefour.bingo.question.domain.QuestionRepository;
import com.threefour.bingo.subQuestion.domain.SubQuestion;
import com.threefour.bingo.subQuestion.domain.SubQuestionRepository;
import com.threefour.bingo.subQuestion.dto.SubQuestionDTO;
import com.threefour.bingo.subQuestion.dto.request.SubQuestionRequest;
import com.threefour.bingo.subQuestion.dto.response.SubQuestionInfoResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

        List<String> questionList = request.getQuestion();

        List<SubQuestion> newSubQuestions = new ArrayList<>();

        for (int i = 0; i < questionList.size(); i++) {
            SubQuestion newSubQuestion = new SubQuestion(questionList.get(i), question);
            subQuestionRepository.save(newSubQuestion);
            newSubQuestions.add(newSubQuestion);
        }

        return newSubQuestions;
    }

    public List<SubQuestionDTO> getAllSubQuestionDTO(Long questionId) {

        List<SubQuestion> subQuestionList = subQuestionRepository.findByQuestionId(questionId);

        if (subQuestionList == null || subQuestionList.isEmpty()) {
            return new ArrayList<>();
        }

        List<SubQuestionDTO> subQuestionDTOList = subQuestionList.stream()
                .map(SubQuestionDTO::new)
                .collect(Collectors.toList());

        return subQuestionDTOList;
    }

    public List<SubQuestionInfoResponse> getAllSubQuestionResponse(Long questionId) {

        List<SubQuestion> subQuestionList = subQuestionRepository.findByQuestionId(questionId);

        if (subQuestionList == null || subQuestionList.isEmpty()) {
            return new ArrayList<>();
        }

        List<SubQuestionInfoResponse> subQuestionDTOList = subQuestionList.stream()
                .filter(subQuestion -> subQuestion.getSubQuestion() == null || !subQuestion.getSubQuestion().isEmpty())
                .map(SubQuestionInfoResponse::new)
                .collect(Collectors.toList());


        return subQuestionDTOList;
    }

}
