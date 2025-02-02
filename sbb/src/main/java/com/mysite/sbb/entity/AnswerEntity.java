package com.mysite.sbb.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@Entity
@NoArgsConstructor
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @ManyToOne
    private QuestionEntity questionEntity;

    public AnswerEntity(Integer id, String content, LocalDateTime createDate, QuestionEntity questionEntity) {
        this.id = id;
        this.content = content;
        this.createDate = createDate;
        this.questionEntity = questionEntity;
    }
}
