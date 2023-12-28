package com.threefour.bingo.subQuestion.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubQuestionRepository extends JpaRepository<SubQuestion, Long> {

    List<SubQuestion> findByQuestionId(Long questionId);
}
