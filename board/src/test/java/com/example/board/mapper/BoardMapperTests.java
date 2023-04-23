package com.example.board.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.board.domain.vo.BoardVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	@Autowired
	private BoardMapper boardMapper;
	
//	@Test
//	public void getListTest() {
//		boardMapper.getList().forEach(log::info);
//		//람다식
//	}
//	
//	@Test
//	public void insertTest() {
//		BoardVO board = new BoardVO();
//		board.setTitle("새로 작성한 글 제목4");
//		board.setContent("새로 작성한 글 내용4");
//		board.setWriter("user04");
//		
//		boardMapper.insert(board);
//		
//		log.info(board);
//	}
	
//	@Test
//	public void readTest() {
//		BoardVO boardVO = boardMapper.read(5L);
//		log.info(boardVO);
//		
//	}
	
	@Test
	public void deleteTest() {
		Long bno = 2L;
		BoardVO board = boardMapper.read(bno);
		if(board != null) {
			log.info("DELETE COUNT :" + boardMapper.delete(board.getBno()));
			return;
		}
		log.info("NO BOARD");
		
	}
	
//	@Test
//	public void updateTest() {
//		
//		BoardVO board = boardMapper.read(1L);
//		if(board != null) {
//			board.setTitle("수정된 제목");
//			board.setContent("수정한 내용");
//			board.setWriter("user00");
//			
//			log.info("UPDATE COUNT :" + boardMapper.update(board));
//			return;
//		}
//		
//		log.info("NO BOARD");
//	}

}
