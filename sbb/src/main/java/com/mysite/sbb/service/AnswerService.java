package com.mysite.sbb.service;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.entity.AnswerEntity;
import com.mysite.sbb.entity.QuestionEntity;
import com.mysite.sbb.entity.SiteUserEntity;
import com.mysite.sbb.repository.AnswerRepository;
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
public class AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerEntity create(QuestionEntity question, String content, SiteUserEntity author) {
        AnswerEntity answer = new AnswerEntity();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestionEntity(question);
        answer.setAuthor(author);
        this.answerRepository.save(answer);
        return answer;
    }

    public Page<AnswerEntity> getList(int page, QuestionEntity question) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 3, Sort.by(sorts));
        return this.answerRepository.findAllByQuestionEntity(question, pageable);
    }

    public AnswerEntity getAnswer(Integer id) {
        Optional<AnswerEntity> answer = this.answerRepository.findById(id);
        if (answer.isPresent()) {
            return answer.get();
        } else {
            throw new DataNotFoundException("answer not found");
        }
    }

    public void modify(AnswerEntity answer, String content) {
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }

    public void delete(AnswerEntity answer) {
        this.answerRepository.delete(answer);
    }

    public void vote(AnswerEntity answer, SiteUserEntity siteUser) {
        answer.getVoter().add(siteUser);
        this.answerRepository.save(answer);
    }
}
