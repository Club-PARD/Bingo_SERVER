package com.threefour.bingo.template.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemplateRepository extends JpaRepository<Template, Long> {

    List<Template> findByAppUserIdAndProjectId(Long appUserId, Long projectId);
}
