package com.threefour.bingo.tag.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findByProjectId(Long projectId);

    List<Tag> findByProjectIdAndTemplateId(Long projectId, Long templateId);
}
