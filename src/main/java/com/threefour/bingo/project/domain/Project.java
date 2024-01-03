package com.threefour.bingo.project.domain;

import com.threefour.bingo.tag.domain.Tag;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    private String picture;

    @Column(nullable = false)
    private String code;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Tag> tagList = new ArrayList<>();

    @Builder
    public Project(String name, String description, String picture, String code) {
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.code = code;
    }

    public List<Tag> updateList(List<Tag> tagList) {
        return this.tagList = tagList;
    }

}
