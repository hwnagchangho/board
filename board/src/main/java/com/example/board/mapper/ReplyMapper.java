package com.example.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.board.domain.vo.ReplyVO;

@Mapper
public interface ReplyMapper {
//	댓글추가
	public int insert(ReplyVO replyVO);
	
// 댓글 조회
	public ReplyVO select(Long rno);
//	댓글 하나의 전체내용을 가져올 것이기 때문에 ReplyVO로 데이터타입을 잡아주어야 한다.
	
//	댓글 삭제
	public int delete(Long rno);
	
// 해당 게시글 전체 댓글 삭제
	public int deleteAll(Long bno);
	
//	댓글 수정
	public int modify(ReplyVO replyVO);
//	insert, update, delete 같은 경우는 건수이기 (0, 1) 때문에 int로 리턴받으면된다.
	
//	댓글 목록
	public List<ReplyVO> selectAll(Long bno);
}
