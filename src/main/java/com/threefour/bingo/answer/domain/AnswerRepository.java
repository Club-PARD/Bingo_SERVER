package com.threefour.bingo.answer.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Answer findBySubQuestionId(Long subQuestionId);
}
