package com.threefour.bingo.project.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findByCode(String code);

}
