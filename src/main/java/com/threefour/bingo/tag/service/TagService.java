package com.threefour.bingo.tag.service;

import com.threefour.bingo.project.domain.Project;
import com.threefour.bingo.project.domain.ProjectRepository;
import com.threefour.bingo.tag.domain.Tag;
import com.threefour.bingo.tag.domain.TagRepository;
import com.threefour.bingo.tag.dto.TagDTO;
import com.threefour.bingo.tag.dto.request.TagGetRequest;
import com.threefour.bingo.tag.dto.request.TagListProjectRequest;
import com.threefour.bingo.tag.dto.request.TagListTemplateRequest;
import com.threefour.bingo.template.domain.Template;
import com.threefour.bingo.template.domain.TemplateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TagService {

    private final ProjectRepository projectRepository;
    private final TagRepository tagRepository;
    private final TemplateRepository templateRepository;

    public List<Tag> createProjectBingo(TagListProjectRequest request) {

        log.info("받아온 아이디는: " + request.getProjectId());

        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Project Not Found"));

        List<Tag> tagList = new ArrayList<>();

        List<String> nameList = request.getName();

        for (String tagName : nameList) {
            Tag tag = new Tag(tagName, 0, 1, project);
            tagRepository.save(tag);
            tagList.add(tag);
        }

        project.updateList(tagList);
        projectRepository.save(project);

        return tagList;
    }

    public List<Tag> createTemplateBingo(TagListTemplateRequest request) {

        log.info("받아온 아이디는: " + request.getProjectId());

        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Project Not Found"));

        Template template = templateRepository.findById(request.getTemplateId())
                .orElseThrow(() -> new IllegalArgumentException("Template Not Found"));

        List<Tag> tagList = new ArrayList<>();

        List<String> nameList = request.getName();


        for (String tagName : nameList) {
            Tag tag = new Tag(tagName, 0, 1, project, template);
            tagRepository.save(tag);
            tagList.add(tag);
        }

        project.updateList(tagList);
        projectRepository.save(project);

        return tagList;
    }

    public List<TagDTO> getTagList(Long projectId, Long templateId) {
        List<Tag> tagList = tagRepository.findByProjectIdAndTemplateId(projectId, templateId);

        List<TagDTO> tagDTOList = tagList.stream()
                .map(tag -> new TagDTO(tag.getId(), tag.getName(), tag.getCount()))
                .collect(Collectors.toList());

        return tagDTOList;

    }

}
