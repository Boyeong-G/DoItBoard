package com.mysite.sbb.service;

import com.mysite.sbb.entity.AnswerEntity;
import com.mysite.sbb.entity.QuestionEntity;
import com.mysite.sbb.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public void create(QuestionEntity question, String content) {
        AnswerEntity answer = AnswerEntity.builder()
                .content(content)
                .createDate(LocalDateTime.now())
                .questionEntity(question)
                .build();
        this.answerRepository.save(answer);
    }
}
