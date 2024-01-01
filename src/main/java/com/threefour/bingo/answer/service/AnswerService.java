package com.threefour.bingo.answer.service;

import com.threefour.bingo.answer.domain.AnswerRepository;
import com.threefour.bingo.answer.dto.response.AnswerGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

//    public List<AnswerGetResponse> getAllAnswers(Long subQId){
//
//
//    }
}
