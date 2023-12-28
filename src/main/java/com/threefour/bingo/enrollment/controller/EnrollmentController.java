package com.threefour.bingo.enrollment.controller;

import com.threefour.bingo.enrollment.dto.request.EnrollmentRequest;
import com.threefour.bingo.enrollment.domain.Enrollment;
import com.threefour.bingo.enrollment.service.EnrollmentService;
import com.threefour.bingo.test.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/enrollment")
@CrossOrigin(origins = "http://localhost:3000")
public class EnrollmentController {
    public final EnrollmentService enrollmentService;

    @PostMapping("")
    public ResponseDto<Enrollment> joinProject(@RequestBody final EnrollmentRequest request) {
        ResponseDto<Enrollment> response = enrollmentService.joinProject(request);

        return response;
    }
}
