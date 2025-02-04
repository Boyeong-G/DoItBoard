package com.mysite.sbb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class QuestionEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "questionEntity", cascade = CascadeType.REMOVE)
    private List<AnswerEntity> answerEntityList;

    @ManyToOne
    private SiteUserEntity author;

    private LocalDateTime modifyDate;

    @ManyToMany
    Set<SiteUserEntity> voter;
}
