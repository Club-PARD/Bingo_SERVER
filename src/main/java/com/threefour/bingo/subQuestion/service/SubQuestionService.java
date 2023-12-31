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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
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

            if (newSubQuestion.getSubQuestion() != null && !newSubQuestion.getSubQuestion().isEmpty()) {
                subQuestionRepository.save(newSubQuestion);
                newSubQuestions.add(newSubQuestion);
            }
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

        List<SubQuestionInfoResponse> subQuestionInfoResponseList = new ArrayList<>();

//        for (int i = 0; i < subQuestionList.size(); i++) {
//            if (subQuestionList.get(i).getSubQuestion() != null && !subQuestionList.get(i).getSubQuestion().isEmpty()) {
//                log.info("들어온 질문이 옳음: {}", subQuestionList.get(i).getQuestion());
//                SubQuestionInfoResponse response = new SubQuestionInfoResponse(
//                        subQuestionList.get(i).getId(), subQuestionList.get(i).getSubQuestion()
//                );
//                subQuestionInfoResponseList.add(response);
//            } else {
//                log.info("들어온 질문이 옳지 않음: {}", subQuestionList.get(i).getQuestion());
//            }
//        }


        List<SubQuestionInfoResponse> subQuestionDTOList = subQuestionList.stream()
                .map(SubQuestionInfoResponse::new)
                .collect(Collectors.toList());


        return subQuestionDTOList;
    }

}
