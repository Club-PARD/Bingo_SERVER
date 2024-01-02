package com.threefour.bingo.retrospect.domain;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RetrospectRepository extends JpaRepository<Retrospect, Long> {

//    @EntityGraph(attributePaths = {"questions", "questions.subQuestions", "questions.subQuestions.answers"})
    List<Retrospect> findByProjectIdAndTemplateId(Long projectId, Long templateId);
//    List<Retrospect> findByProjectIdAndTemplateId(Long projectId, Long templateId);

}
