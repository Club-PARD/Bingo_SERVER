package com.threefour.bingo.template.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemplateRepository extends JpaRepository<Template, Long> {

    List<Template> findByProjectId(Long projectId);

    Template findByProjectIdAndId(Long projectId, Long templateId);

    List<Template> findByAppUserIdAndProjectId(Long appUserId, Long projectId);

    Template findByAppUserIdAndProjectIdAndId(Long appUserId, Long projectId, Long templateId);
}
