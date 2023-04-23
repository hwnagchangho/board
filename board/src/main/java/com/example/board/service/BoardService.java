package com.example.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.Criteria;

@Service
public interface BoardService {
//	전체 게시글 가져오기
	public List<BoardVO> getList(Criteria criteria);
// 게시물 등록
	public void register(BoardVO board);
//	특정 게시물 가져오기
	public BoardVO get(Long bno);
//	게시글 삭제
	public boolean remove(Long bno);
//	게시글 수정
	public boolean modify(BoardVO board);
	
	public int getTotal(Criteria criteria);
}
