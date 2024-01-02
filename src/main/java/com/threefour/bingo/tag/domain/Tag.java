package com.threefour.bingo.tag.domain;

import com.threefour.bingo.project.domain.Project;
import com.threefour.bingo.template.domain.Template;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer count;

    private boolean isSelected;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    private Template template;

    @Builder
    public Tag(String name, Integer count, boolean isSelected, Project project) {

        this.name = name;
        this.count = count;
        this.isSelected = isSelected;
        this.project = project;

    }

    @Builder
    public Tag(String name, Integer count, boolean isSelected, Project project, Template template) {

        this.name = name;
        this.count = count;
        this.isSelected = isSelected;
        this.project = project;
        this.template = template;

    }

    public void countUp() {
        this.count++;
    }

}
