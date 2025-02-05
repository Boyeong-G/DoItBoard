package com.mysite.sbb.service;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.entity.AnswerEntity;
import com.mysite.sbb.entity.QuestionEntity;
import com.mysite.sbb.entity.SiteUserEntity;
import com.mysite.sbb.repository.QuestionRepository;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    private Specification<QuestionEntity> search(String kw) {
        return new Specification<QuestionEntity>() {
            @Override
            public Predicate toPredicate(Root<QuestionEntity> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true); // 중복 제거
                Join<QuestionEntity, SiteUserEntity> u1 = q.join("author", JoinType.LEFT);
                Join<QuestionEntity, AnswerEntity> a = q.join("answerEntityList", JoinType.LEFT);
                Join<AnswerEntity, SiteUserEntity> u2 = a.join("author", JoinType.LEFT);
                return cb.or(cb.like(q.get("subject"), "%" + kw + "%"),   // 제목
                             cb.like(q.get("content"), "%" + kw + "%"),             // 내용
                             cb.like(u1.get("username"), "%" + kw + "%"),           // 질문 작성자
                             cb.like(a.get("content"), "%" + kw + "%"),             // 답변 내용
                             cb.like(u2.get("username"), "%" + kw + "%"));          // 답변 작성자
            }
        };
    }

    public Page<QuestionEntity> getList(int page, String kw) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        Specification<QuestionEntity> spec = search(kw);
        return this.questionRepository.findAll(spec, pageable); // Specification 인터페이스를 사용해서 검색
//        return this.questionRepository.findAllByKeyword(kw, pageable); // Query를 사용해서 검색
    }

    public List<QuestionEntity> getList() {
        return this.questionRepository.findAll();
    }

    public QuestionEntity getQuestion(Integer id) {
        Optional<QuestionEntity> question = this.questionRepository.findById(id);
        if(question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    public void create(String subject, String content, SiteUserEntity user) {
        QuestionEntity question = new QuestionEntity();
        question.setSubject(subject);
        question.setContent(content);
        question.setCreateDate(LocalDateTime.now());
        question.setAuthor(user);
        this.questionRepository.save(question);
    }

    public void modify(QuestionEntity question, String subject, String content) {
        question.setSubject(subject);
        question.setContent(content);
        question.setModifyDate(LocalDateTime.now());
        this.questionRepository.save(question);
    }

    public void delete(QuestionEntity question) {
        this.questionRepository.delete(question);
    }

    public void vote(QuestionEntity question, SiteUserEntity siteUser) {
        question.getVoter().add(siteUser);
        this.questionRepository.save(question);
    }
}
