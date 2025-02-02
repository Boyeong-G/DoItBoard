package com.mysite.sbb.repository;

import com.mysite.sbb.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {
    QuestionEntity findBySubject(String subject);
    QuestionEntity findBySubjectAndContent(String subject, String content);
    List<QuestionEntity> findBySubjectLike(String subject); // 특정 문자열과 일치
}
