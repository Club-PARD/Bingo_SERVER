package com.threefour.bingo.enrollment.controller;

import com.threefour.bingo.enrollment.dto.request.EnrollmentRequest;
import com.threefour.bingo.enrollment.dto.response.EnrollmentResponse;
import com.threefour.bingo.enrollment.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/enrollment")
//@CrossOrigin(origins = "http://localhost:3000")
public class EnrollmentController {
    public final EnrollmentService enrollmentService;

    @PostMapping("")
    public EnrollmentResponse joinProject(@RequestHeader(value = "Authorization") final String token, @RequestBody final EnrollmentRequest request) {

        final EnrollmentResponse response = enrollmentService.joinProject(request);

        return response;
    }
}
