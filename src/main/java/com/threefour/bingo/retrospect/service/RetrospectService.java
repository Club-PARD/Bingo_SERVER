package com.threefour.bingo.retrospect.service;

import com.threefour.bingo.ResponseDto;
import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.appUser.domain.AppUserRepository;
import com.threefour.bingo.project.domain.Project;
import com.threefour.bingo.project.domain.ProjectRepository;
import com.threefour.bingo.question.dto.QuestionDTO;
import com.threefour.bingo.retrospect.domain.Retrospect;
import com.threefour.bingo.retrospect.domain.RetrospectRepository;
import com.threefour.bingo.retrospect.domain.TeamEvaluation;
import com.threefour.bingo.retrospect.dto.request.RetrospectPostRequest;
import com.threefour.bingo.retrospect.dto.request.TeamEvaluationPostRequest;
import com.threefour.bingo.subQuestion.domain.SubQuestion;
import com.threefour.bingo.subQuestion.domain.SubQuestionRepository;
import com.threefour.bingo.subQuestion.dto.SubQuestionDTO;
import com.threefour.bingo.subQuestion.service.SubQuestionService;
import com.threefour.bingo.tag.domain.Tag;
import com.threefour.bingo.tag.domain.TagRepository;
import com.threefour.bingo.tag.dto.TagDTO;
import com.threefour.bingo.template.domain.Template;
import com.threefour.bingo.template.domain.TemplateRepository;
import com.threefour.bingo.template.domain.TemplateType;
import com.threefour.bingo.template.dto.TemplateDTO;
import com.threefour.bingo.template.dto.request.TemplateGetRequest;
import com.threefour.bingo.template.service.TemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class RetrospectService {

    private final AppUserRepository appUserRepository;
    private final TemplateRepository templateRepository;
    private final ProjectRepository projectRepository;
    private final SubQuestionRepository subQuestionRepository;
    private final TemplateService templateService;
    private final SubQuestionService subQuestionService;
    private final TagRepository tagRepository;


    public ResponseDto<Project> teamEvaluation(TeamEvaluationPostRequest request) {

        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Project Not Found"));

        List<Tag> tagList = project.getTagList();
        List<TagDTO> requestTagList = request.getTagList();

        for (int i = 0; i < tagList.size(); i++) {
//            if()
            if (requestTagList.get(i).isSelected() == true) {
                tagList.get(i).updateStatus(true);
                log.info("업데이트 됐나용: " + tagList.get(i).getCount() + " " + tagList.get(i).isSelected());
                tagRepository.save(tagList.get(i));
            }
        }

        return ResponseDto.setFailed("그냥 한 거임 실패 아니야");
    }
}
