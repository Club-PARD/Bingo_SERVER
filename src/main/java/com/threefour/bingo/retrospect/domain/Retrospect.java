package com.threefour.bingo.retrospect.domain;

import com.threefour.bingo.appUser.domain.AppUser;

import com.threefour.bingo.project.domain.Project;
import com.threefour.bingo.tag.domain.Tag;
import com.threefour.bingo.template.domain.Template;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Retrospect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appUser_id")
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    private Template template;

//    @Enumerated(EnumType.STRING)
//    private Tag.TeamEvaluation teamEvaluation;

//    @OneToMany(mappedBy = "retrospect", cascade = CascadeType.ALL)
//    private List<Tag> tagList = new ArrayList<>();

    @Builder
    public Retrospect(AppUser appUser, Project project, Template template) {
        this.appUser = appUser;
        this.project = project;
        this.template = template;
    }

}
