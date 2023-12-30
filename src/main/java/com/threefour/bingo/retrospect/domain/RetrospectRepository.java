package com.threefour.bingo.retrospect.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RetrospectRepository extends JpaRepository<Retrospect, Long> {
    @Query("SELECT r.teamEvaluation, COUNT(r) FROM Retrospect r GROUP BY r.teamEvaluation")
    List<Object[]> countByTeamEvaluation();


}
