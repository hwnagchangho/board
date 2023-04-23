package com.example.board.domain.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.board.domain.vo.ReplyVO;
import com.example.board.mapper.ReplyMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor //생성자주입
public class ReplyDAO {
	
	private final ReplyMapper replyMapper;
//	메소드 이름은 mapper에 있는 이름이 너무 db스럽기때문에 변경해준다.
	
//	댓글추가
	public boolean register(ReplyVO replyVO) { 
		return replyMapper.insert(replyVO) == 1;
	}
	
// 댓글 조회
	public ReplyVO findByRNO(Long rno) {
		return replyMapper.select(rno);
	}
//	보통 조회할때 이름형식은 이런식으로 한다. findBy : 찾는다. RNO : rno로  
	
//	댓글 삭제
	public boolean remove(Long rno) {
		return replyMapper.delete(rno) ==1;
	}
	
// 해당 게시글 전체 댓글 삭제
	public boolean removeAllByBNO(Long bno) {
		return replyMapper.deleteAll(bno) != 0;
//		하나라도 삭제가 되었으면 전부 성공
	}
	
//	댓글 수정
	public boolean modify(ReplyVO replyVO) {
		return replyMapper.modify(replyVO) == 1;
	}
//	insert, update, delete 같은 경우는 건수이기 (0, 1) 때문에 int로 리턴받으면된다.
	
//	댓글 목록
	public List<ReplyVO> findAllByBNO(Long bno){
		return replyMapper.selectAll(bno);
	}
}
