package com.threefour.bingo.enrollment.domain;

import com.threefour.bingo.enrollment.domain.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByAppUserId(Long userId);

    List<Enrollment> findByWorkspaceId(Long workspaceId);

    Enrollment findByAppUserIdAndWorkspaceId(Long userId, Long workspaceId);
}
