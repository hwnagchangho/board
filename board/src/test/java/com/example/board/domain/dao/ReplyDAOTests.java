package com.example.board.domain.dao;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.board.domain.dao.ReplyDAO;
import com.example.board.domain.vo.ReplyVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class) //SpringJUnit4ClassRunner.class의 자식클래스 상위버전에서 사용
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyDAOTests {
	
	private Long[] arBno = {324L, 323L, 322L, 321L, 320L};
//	규칙성이 없는 값을 규칙성을 부여하기 위해 배열에 담는다.
	
	@Autowired
	ReplyDAO replyDAO;
	
	@Test
	public void mapperTest() {
		log.info("===============");
		log.info(replyDAO);
		log.info("===============");
	}
	
	@Test
	public void registerTest() {
		//5개의 게시글에 2개씩 댓글 달기
		IntStream.rangeClosed(11, 20).forEach(i->{
			ReplyVO replyVO = new ReplyVO();
			replyVO.setBno(arBno[i % 5]);
			replyVO.setReply("댓글 테스트" + i);
			replyVO.setReplier("작성자" + i);
			
			replyDAO.register(replyVO);
		});
	}
	
	@Test
	public void findByRNO_test() {
		log.info(replyDAO.findByRNO(17L));
	}
	
	@Test
	public void removeByRNO_test() {
		log.info(replyDAO.remove(18L));
	}
	
	@Test
	public void removeAllByBNO_test() {
		log.info(replyDAO.removeAllByBNO(320L));
	}
	
	@Test
	public void modifyTest() {
		
		ReplyVO replyVO = replyDAO.findByRNO(16L);
		
		replyVO.setReply("수정된 댓글 내용2222");
		
		log.info("=========================");
		log.info(replyDAO.modify(replyVO));
		log.info("=========================");
	}
	
	@Test
	public void selectAllTest() {
		replyDAO.findAllByBNO(322L).forEach(log::info);
	}
	
}
