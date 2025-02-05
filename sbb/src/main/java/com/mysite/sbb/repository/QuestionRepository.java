package com.mysite.sbb.repository;

import com.mysite.sbb.entity.QuestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {
    QuestionEntity findBySubject(String subject);
    QuestionEntity findBySubjectAndContent(String subject, String content);
    List<QuestionEntity> findBySubjectLike(String subject); // 특정 문자열과 일치
    Page<QuestionEntity> findAll(Pageable pageable);
    Page<QuestionEntity> findAll(Specification<QuestionEntity> spec, Pageable pageable);

    // 검색 기능 추가 구현 (Query를 사용)
//    @Query(value = "SELECT DISTINCT q "
//                 + "FROM question_entity q "
//                 + "LEFT OUTER JOIN site_user_entity u1 ON q.author=u1 "
//                 + "LEFT OUTER JOIN answer_entity a ON a.question=q "
//                 + "LEFT OUTER JOIN site_user_entity u2 ON a.author=u2 "
//                 + "WHERE q.subject Like %:kw% "
//                 + "OR q.content Like %:kw% "
//                 + "OR u1.username Like %:kw% "
//                 + "OR a.content Like %:kw% "
//                 + "OR u2.username Like %:kw% ", nativeQuery = true)
//    Page<QuestionEntity> findAllByKeyword(@Param("kw") String kw, Pageable pageable);
}
