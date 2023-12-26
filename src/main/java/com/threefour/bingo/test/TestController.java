package com.threefour.bingo.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class TestController {
    @Autowired
    TestService testService;

    @PostMapping("")
    public ResponseDto<TestPostDto> postTest(@RequestBody TestPostDto dto) {
        ResponseDto<TestPostDto> dtoResponseDto = testService.addTest(dto);

        return dtoResponseDto;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Test> getTest(@PathVariable Long id) {
        log.info("id: " + id);
        Test test = testService.getTest(id);

        return ResponseEntity.ok()
                .body(test);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Test> updateTest(@PathVariable Long id, @RequestBody TestUpdateDto dto) {
        Test test = testService.updateTest(id, dto);

        return ResponseEntity.ok()
                .body(test);
    }
}
