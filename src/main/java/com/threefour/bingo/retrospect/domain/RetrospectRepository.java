package com.threefour.bingo.retrospect.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RetrospectRepository extends JpaRepository<Retrospect, Long> {

    List<Retrospect> findByProjectIdAndTemplateId(Long projectId, Long templateId);

}
