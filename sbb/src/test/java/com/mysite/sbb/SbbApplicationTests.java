package com.mysite.sbb;

import com.mysite.sbb.entity.QuestionEntity;
import com.mysite.sbb.repository.QuestionRepository;
import com.mysite.sbb.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SbbApplicationTests {

//	@Autowired
//	private QuestionRepository questionRepository;

	@Autowired
	private QuestionService questionService;

//	@Transactional
	@Test
	void testJPA() {
//		QuestionEntity q1 = QuestionEntity.builder()
//				.subject("ssb가 무엇인가요?")
//				.content("sbb에 대해 알고 싶습니다.")
//				.createDate(LocalDateTime.now())
//				.build();
//		this.questionRepository.save(q1);
//
//		QuestionEntity q2 = QuestionEntity.builder()
//				.subject("스프링 부트 모델 질문입니다.")
//				.content("id는 자동으로 생성되나요>")
//				.createDate(LocalDateTime.now())
//				.build();
//		this.questionRepository.save(q2);

//		List<QuestionEntity> all = this.questionRepository.findAll();
//		assertEquals(2, all.size());
//
//		QuestionEntity q = all.get(0);
//		assertEquals("ssb가 무엇인가요?", q.getSubject());

//		Optional<QuestionEntity> oq = this.questionRepository.findById(1);
//		if(oq.isPresent()) {
//			QuestionEntity q = oq.get();
//			assertEquals("ssb가 무엇인가요?", q.getSubject());
//		}

//		QuestionEntity q = this.questionRepository.findBySubject("ssb가 무엇인가요?");
//		assertEquals(1, q.getId());

//		List<QuestionEntity> qList = this.questionRepository.findBySubjectLike("%sbb%");
//		QuestionEntity q = qList.get(0);
//		assertEquals("sbb가 무엇인가요?", q.getSubject());

		for (int i = 0; i <= 300; i++) {
			String subject = String.format("테스트 데이터입니다:[%03d]", i);
			String content = "내용 없음";
			this.questionService.create(subject, content, null);
		}
	}
}
