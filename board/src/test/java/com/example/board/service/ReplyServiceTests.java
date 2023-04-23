package com.example.board.service;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.ReplyVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyServiceTests {

	private Long[] arBno = {324L, 323L, 322L, 321L, 320L};
//	규칙성이 없는 값을 규칙성을 부여하기 위해 배열에 담는다.
	
	@Autowired
	private ReplyService replyService;
//	replyservice를 여러개 클래스에서 지정받았으면 하나하나 구분을 해주어야한다.
//	qualifier 어노테이션 사용
	
	@Test
	public void serviceTest() {
		log.info("===============");
		log.info(replyService);
		log.info("===============");
	}
	
//	@Test
//	public void registerTest() {
//		//5개의 게시글에 2개씩 댓글 달기
//		IntStream.rangeClosed(21, 30).forEach(i->{
//			ReplyVO replyVO = new ReplyVO();
//			replyVO.setBno(arBno[i % 5]);
//			replyVO.setReply("댓글 테스트" + i);
//			replyVO.setReplier("작성자" + i);
//			
//			replyService.register(replyVO);
//		});
//	}
	
//	@Test
//	public void findByRNO_test() {
//		log.info(replyService.findByRNO(40L));
//	}
	
//	@Test
//	public void removeByRNO_test() {
//		log.info(replyService.remove(40L));
//	}
	
//	@Test
//	public void removeAllByBNO_test() {
//		log.info(replyService.removeAllByBNO(320L));
//	}
	
//	@Test
//	public void modifyTest() {
//		
//		ReplyVO replyVO = replyService.findByRNO(38L);
//		
//		replyVO.setReply("수정된 댓글 내용2222");
//		
//		log.info("=========================");
//		log.info(replyService.modify(replyVO));
//		log.info("=========================");
//	}
	
	@Test
	public void selectAllTest() {
		replyService.findAllByBNO(322L).forEach(log::info);
	}

}
