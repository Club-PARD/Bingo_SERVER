package com.threefour.bingo.enrollment.repository;

import com.threefour.bingo.enrollment.entity.Enrollment;
import com.threefour.bingo.worksapce.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByAppUserId(Long userId);

    List<Enrollment> findByWorkspaceId(Long workspaceId);

    Enrollment findByAppUserIdAndWorkspaceId(Long userId, Long workspaceId);
}
