package com.mysite.sbb.service;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.entity.QuestionEntity;
import com.mysite.sbb.entity.SiteUserEntity;
import com.mysite.sbb.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public Page<QuestionEntity> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.questionRepository.findAll(pageable);
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
