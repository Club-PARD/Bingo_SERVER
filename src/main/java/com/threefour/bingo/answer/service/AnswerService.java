package com.threefour.bingo.answer.service;

import com.threefour.bingo.answer.domain.Answer;
import com.threefour.bingo.answer.domain.AnswerRepository;
import com.threefour.bingo.answer.dto.response.AnswerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public List<AnswerResponse> getAllAnswers(Long subQId) {
        List<Answer> answerList = answerRepository.findBySubQuestionId(subQId);

        List<AnswerResponse> answerResponseList = answerList.stream()
                .map(answer -> new AnswerResponse(answer))
                .collect(Collectors.toList());

        return answerResponseList;
    }


//    public List<AnswerResponse> getAllAnswers(Long subQId) {
//        List<Answer> answerList = answerRepository.findBySubQuestionId(subQId);
//
//        List<AnswerResponse> answerResponseList = new ArrayList<>();
//
//        for (int i = 0; i < answerList.size(); i++) {
//            List<AnswerResponse> answerResponses = answerList.stream()
//                    .map(answer -> new AnswerResponse(answer.getId(), answer.getAns())) // Assuming AnswerDTO has a constructor that takes an Answer as a parameter
//                    .collect(Collectors.toList());
//
//            answerResponseList.addAll(answerResponses);
//
//        }
//
//        return answerResponseList;
//
//    }
}
