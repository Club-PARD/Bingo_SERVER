package com.threefour.bingo.tag.service;

import com.threefour.bingo.project.domain.Project;
import com.threefour.bingo.project.domain.ProjectRepository;
import com.threefour.bingo.tag.domain.Tag;
import com.threefour.bingo.tag.domain.TagRepository;
import com.threefour.bingo.tag.dto.request.TagListPostRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TagService {

    private final ProjectRepository projectRepository;
    private final TagRepository tagRepository;

    public List<Tag> createBingo(TagListPostRequest request) {

        log.info("받아온 아이디는: " + request.getProjectId());

        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Project Not Found"));

        List<Tag> tagList = new ArrayList<>();

        List<String> nameList = request.getName();

        for (String tagName : nameList) {
            Tag tag = new Tag(tagName, 0, false, project);
            tagRepository.save(tag);
            tagList.add(tag);
        }

        project.updateList(tagList);
        projectRepository.save(project);

        return tagList;
    }

}
