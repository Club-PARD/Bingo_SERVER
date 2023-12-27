package com.threefour.bingo.enrollment.controller;

import com.threefour.bingo.enrollment.dto.request.EnrollmentRequest;
import com.threefour.bingo.enrollment.domain.Enrollment;
import com.threefour.bingo.enrollment.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/enrollment")
public class EnrollmentController {
    public final EnrollmentService enrollmentService;

    @PostMapping("")
    public ResponseEntity<Enrollment> joinProject(@RequestBody EnrollmentRequest request) {
        Enrollment enrollment = enrollmentService.joinProject(request);

        return ResponseEntity.ok()
                .body(enrollment);
    }
}
