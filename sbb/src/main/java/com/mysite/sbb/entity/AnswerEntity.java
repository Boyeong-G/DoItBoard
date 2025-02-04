package com.mysite.sbb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;


@Getter
@Setter
@Entity
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @ManyToOne
    private QuestionEntity questionEntity;

    @ManyToOne
    private SiteUserEntity author;

    private LocalDateTime modifyDate;

    @ManyToMany
    Set<SiteUserEntity> voter;
}
