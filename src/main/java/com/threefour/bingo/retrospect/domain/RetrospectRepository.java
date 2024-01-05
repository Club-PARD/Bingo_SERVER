package com.threefour.bingo.retrospect.domain;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RetrospectRepository extends JpaRepository<Retrospect, Long> {

    List<Retrospect> findByProjectIdAndTemplateId(Long projectId, Long templateId);


    List<Retrospect> findByProjectId(Long projectId);

    List<Retrospect> findByAppUserIdAndProjectId(Long appUserId, Long projectId);

    Retrospect findByAppUserIdAndProjectIdAndTemplateId(Long appUserId, Long projectId, Long templateId);

}
