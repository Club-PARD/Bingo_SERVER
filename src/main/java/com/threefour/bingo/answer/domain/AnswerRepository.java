package com.threefour.bingo.answer.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findBySubQuestionId(Long subQuestionId);
}
