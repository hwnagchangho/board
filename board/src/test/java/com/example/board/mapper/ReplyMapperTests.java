package com.example.board.mapper;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.board.domain.vo.ReplyVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class) //SpringJUnit4ClassRunner.class의 자식클래스 상위버전에서 사용
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	
	private Long[] arBno = {324L, 323L, 322L, 321L, 320L};
//	규칙성이 없는 값을 규칙성을 부여하기 위해 배열에 담는다.
	
	@Autowired
	ReplyMapper replyMapper;
	
	@Test
	public void mapperTest() {
		log.info("===============");
		log.info(replyMapper);
		log.info("===============");
	}
	
	@Test
	public void insertTest() {
		//5개의 게시글에 2개씩 댓글 달기
		IntStream.rangeClosed(1, 10).forEach(i->{
			ReplyVO replyVO = new ReplyVO();
			replyVO.setBno(arBno[i % 5]);
//			배열 인덱스번호가 01234이다. => 범위는 0~4이다. i에는 1~10까지이다.
//			이때 01234를 범위로 쓰고 싶으면 나머지를 %(모듈러스)로 구한다.
//			어떠한수 % n => 0~n-1이다.
//			따라서 i % 5를 하면 12340 이 나온다.
//			이걸 두번 반복하면 2개씩 댓글이 달린다.
			replyVO.setReply("댓글 테스트" + i);
			replyVO.setReplier("작성자" + i);
			
			replyMapper.insert(replyVO);
		});
//		람다식 => range(int startInclusive, int endExclusive) : 여기서 endExclusive는 7이라고하면 7포함이안되는 7보다 작은6까지만 반복
//		rangeClosed(int startInclusive, int endInclusive) : 7까지 도는것
//		5개의 댓글에 2개씩 작성할것이기 때문에 10으로 잡아줌
		
	}
	@Test
	public void testSelect() {
		log.info(replyMapper.select(10L));
	}
	
	@Test
	public void testDelete() {
		log.info(replyMapper.delete(10L));
	}
	
	@Test
	public void testDeleteAll() {
		log.info(replyMapper.deleteAll(321L));
	}
	
	@Test
	public void updateTest() {
		
		ReplyVO replyVO = replyMapper.select(2L);
		
		if(replyVO != null) {
			replyVO.setReply("수정된 댓글 내용");
		}
		
		log.info(replyVO == null? "없는 댓글입니다." : replyMapper.modify(replyVO) + "건 수정되었습니다.");
	}
	
	@Test
	public void selectAllTest() {
		replyMapper.selectAll(323L).forEach(log::info);
	}
	
}
