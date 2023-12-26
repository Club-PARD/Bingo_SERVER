package com.threefour.bingo.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000") // 로컬 호스트의 포트에 맞게 변경
public class TestService {
    @Autowired
    private TestRepository testRepository;

    public ResponseDto<TestPostDto> addTest(TestPostDto dto) {
        Test test = new Test(dto);
        try {
            testRepository.save(test);

            return ResponseDto.setSuccess("add item", dto);
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseDto.setFailed("db 오류");
        }
    }

    public List<Test> getAllTest() {
        return testRepository.findAll();
    }

    public Test getTest(Long id) {
        return testRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(("존재하지 않는 글입니다.")));
    }

    public Test updateTest(Long id, TestUpdateDto dto) {
        String title = dto.getTitle();
        String content = dto.getContent();
        Integer test = dto.getTest();

        Test test1 = testRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException(("존재하지 않은 글입니다.")));

        test1.setTitle(title);
        test1.setContent(content);
        test1.setTest(test);

        testRepository.save(test1);

        log.info("new title: " + title);
        log.info("new content: " + content);
        log.info("new test: " + test);

        return test1;
    }

    public void deleteTest(Long id) {
        testRepository.deleteById(id);
    }
}
