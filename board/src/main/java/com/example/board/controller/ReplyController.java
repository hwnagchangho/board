package com.example.board.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.domain.vo.ReplyVO;
import com.example.board.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
// @Controller를 붙혀버리면 view resolver가 관여하기때문에
// @RestController를 붙혀준다. 그러면 관여하지 않고 직접 리턴한 리턴값이 해당화면으로 도착
// 즉 페이지 이동이 없어진다.
@RequiredArgsConstructor
@RequestMapping("/replies/*")
@Log4j
public class ReplyController {
	private final ReplyService replyService;
	
//	댓글 등록
	@PostMapping(value="/new", consumes = "application/json", produces={MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReplyVO replyVO) {
		log.info("create............." + replyVO);
		return replyService.register(replyVO) ? new ResponseEntity<>("success", HttpStatus.OK) : 
			new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
//	게시글 댓글 전체 조회
	@GetMapping(value="/{bno}", produces = {MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<ReplyVO>> getList(Long bno){
		log.info("getList............ : " + bno);
		
		return new ResponseEntity<>(replyService.findAllByBNO(bno), HttpStatus.OK);
	}
}
