package com.example.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.board.domain.vo.ReplyVO;

@Service
public interface ReplyService {
//	댓글추가
	public boolean register(ReplyVO replyVO);
	
// 댓글 조회
	public ReplyVO findByRNO(Long rno);
//	댓글 하나의 전체내용을 가져올 것이기 때문에 ReplyVO로 데이터타입을 잡아주어야 한다.
	
//	댓글 삭제
	public boolean remove(Long rno);
	
// 해당 게시글 전체 댓글 삭제
	public boolean removeAllByBNO(Long bno);
	
//	댓글 수정
	public boolean modify(ReplyVO replyVO);
//	insert, update, delete 같은 경우는 건수이기 (0, 1) 때문에 int로 리턴받으면된다.
	
//	댓글 목록
	public List<ReplyVO> findAllByBNO(Long bno);
}
