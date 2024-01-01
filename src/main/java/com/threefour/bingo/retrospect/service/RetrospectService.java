package com.threefour.bingo.retrospect.service;

import com.threefour.bingo.ResponseDto;
import com.threefour.bingo.answer.domain.Answer;
import com.threefour.bingo.answer.domain.AnswerRepository;
import com.threefour.bingo.answer.dto.AnswerDTO;
import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.appUser.domain.AppUserRepository;
import com.threefour.bingo.project.domain.Project;
import com.threefour.bingo.project.domain.ProjectRepository;
import com.threefour.bingo.question.domain.Question;
import com.threefour.bingo.retrospect.domain.Retrospect;
import com.threefour.bingo.retrospect.domain.RetrospectRepository;
import com.threefour.bingo.retrospect.dto.request.RetrospectPostRequest;
import com.threefour.bingo.retrospect.dto.request.TeamEvaluationPostRequest;
import com.threefour.bingo.retrospect.dto.response.RetrospectPostResponse;
import com.threefour.bingo.subQuestion.domain.SubQuestion;
import com.threefour.bingo.subQuestion.domain.SubQuestionRepository;
import com.threefour.bingo.subQuestion.dto.SubQuestionDTO;
import com.threefour.bingo.subQuestion.service.SubQuestionService;
import com.threefour.bingo.tag.domain.Tag;
import com.threefour.bingo.tag.domain.TagRepository;
import com.threefour.bingo.tag.dto.TagDTO;
import com.threefour.bingo.template.domain.Template;
import com.threefour.bingo.template.domain.TemplateRepository;
import com.threefour.bingo.template.service.TemplateService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RetrospectService {

    private final SubQuestionRepository subQuestionRepository;
    private final AppUserRepository appUserRepository;
    private final TemplateRepository templateRepository;
    private final ProjectRepository projectRepository;
    private final SubQuestionService subQuestionService;
    private final TagRepository tagRepository;
    private final AnswerRepository answerRepository;
    private final RetrospectRepository retrospectRepository;

    @Transactional
    public RetrospectPostResponse writeRetrospect(RetrospectPostRequest request) {

        final AppUser appUser = appUserRepository.findById(request.getAppUserId())
                .orElseThrow(() -> new EntityNotFoundException("User Not Found"));

        final Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new EntityNotFoundException("Project Not Found"));

        final Template template = templateRepository.findById(request.getTemplateId())
                .orElseThrow(() -> new EntityNotFoundException("Template Not Found"));

        final List<SubQuestionDTO> subQuestionList = getSubQuestionsFromTemplate(template);

        final List<AnswerDTO> answerList = createAndSaveAnswers(request.getAnswerList(), subQuestionList, appUser);

        Retrospect retrospect = new Retrospect(appUser, project, template);
        retrospectRepository.save(retrospect);

        RetrospectPostResponse response = new RetrospectPostResponse(appUser, answerList);

        return response;
    }

    private List<SubQuestionDTO> getSubQuestionsFromTemplate(Template template) {

        List<Question> questionList = template.getQuestionList();
        List<SubQuestionDTO> subQuestionDTOList = new ArrayList<>();

        for (Question question : questionList) {
            List<SubQuestionDTO> temp = subQuestionService.getAllSubQuestion(question.getId());

            subQuestionDTOList.addAll(temp);
        }

        return subQuestionDTOList;
    }

    private List<AnswerDTO> createAndSaveAnswers(List<AnswerDTO> answersDTO, List<SubQuestionDTO> subQuestionList, AppUser appUser) {

        if (answersDTO.size() != subQuestionList.size()) {
            throw new IllegalArgumentException("The number of answers does not match the number of sub-questions.");
        }

        List<AnswerDTO> answers = new ArrayList<>();
        for (int i = 0; i < answersDTO.size(); i++) {
            SubQuestionDTO subQuestion = subQuestionList.get(i);

            // 현재 서브 질문에 대한 기존 답변이 있는지 체크
            Answer existingAnswer = answerRepository.findBySubQuestionId(subQuestion.getId());
            if (existingAnswer != null) {
                log.info("설마 여기,,,?");
            } else {
                // 기존 답변이 없다면 새로운 답변을 생성
//                Answer newAnswer = new Answer(answersDTO.get(i).getAns(), subQuestion, appUser);
                AnswerDTO newAnswer = new AnswerDTO(answersDTO.get(i).getAns(), subQuestion);
                SubQuestion check = subQuestionRepository.findById(newAnswer.getSubQuestionDTO().getId())
                        .orElseThrow(() -> new IllegalArgumentException("Sub Q Not Found"));
                answers.add(newAnswer);
                answerRepository.save(newAnswer.toEntity());
            }
        }

        // 변경된 답변을 저장하고 반환
        return answers;
    }


    public ResponseDto<Project> teamEvaluation(TeamEvaluationPostRequest request) {

        Project project = projectRepository.findById(request.getProjectId()).orElseThrow(() -> new IllegalArgumentException("Project Not Found"));

        List<Tag> tagList = project.getTagList();
        List<TagDTO> requestTagList = request.getTagList();

        for (int i = 0; i < tagList.size(); i++) {
            if (requestTagList.get(i).isSelected() == true) {
                tagList.get(i).updateStatus(true);
                log.info("업데이트 됐나용: " + tagList.get(i).getCount() + " " + tagList.get(i).isSelected());
                tagRepository.save(tagList.get(i));
            }
        }

        return ResponseDto.setFailed("그냥 한 거임 실패 아니야");
    }
}
