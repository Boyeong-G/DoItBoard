package com.mysite.sbb.repository;

import com.mysite.sbb.entity.AnswerEntity;
import com.mysite.sbb.entity.QuestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Integer> {
    Page<AnswerEntity> findAllByQuestionEntity(QuestionEntity question, Pageable pageable);
}
