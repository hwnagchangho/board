package com.example.board.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.PageDTO;
import com.example.board.service.BoardService;

import lombok.extern.log4j.Log4j;

/*
 * 기획 
 * Task			URL						Method		Parameter		Form				URL이동
 *전체목록		/board/list				GET													
 * 등록처리		/board/register		POST			모든항목			입력화면 필요		이동
 * 조회			/board/read			GET			bno									
 * 삭제 처리		/board/remove		GET			bno				입력화면 필요		이동
 * 수정 처리		/board/modify		POST			모든항목(객체)	입력화면 필요		이동
 * 
 */

@Controller
@Log4j
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardService	boardService;
	
	@GetMapping("/list")
	public void list(Criteria criteria, Model model) {
		log.info("list.........");
		model.addAttribute("boardList", boardService.getList(criteria));
		model.addAttribute("pageDTO", new PageDTO(boardService.getTotal(criteria), criteria));
	}
	
	@PostMapping("/register")
	public String register(BoardVO boardVO, RedirectAttributes rttr) {
		log.info("/register : "+ boardVO);
		boardService.register(boardVO);
		
//		model.addAttribute("bno", boardVO.getBno());
//		추가된 게시글의 번호를 받아와 n번게시물이 생성되었습니다 하면서 list로 보내주나?
		
//		Flash라는 영역은 Session에 생기고, redirect로 전송할 때 request영역이 초기화된다.
//		초기화 되기 전에 Flash영역에 데이터를 저장해놓고, 초기화 된 후 Flash영역에서 데이터를 가지고온다.
//		데이터를 가지고 왔다면, Flash영역에 있던 데이터는 없어진다.
		rttr.addFlashAttribute("bno", boardVO.getBno());
//		redirect로 전송할 때는 경로 앞에 "redirect:" 을 붙혀준다.
		return "redirect:/board/list";
		// /board/list => 화면으로 가겠다는건데 redirect를 써주면 전송방식이 redirect로 바뀐다.
		// redirect => 이전의 request에 담겨놨던 정보들을 전부 초기화해서 가는것.
		// 그렇게 때문에 model.addAttribute("bno", boardVO.getBno());
		//이런식으로 model에 담아서 보냈지만 초기화가 된다.
		//따라서 다른 방식을 사용한다. (redirect일때는 requestScope를 쓸 수 없다.)
		// 방법1. Session 사용 (session을 이용, 살짝 담겼다 없어짐 )
		
		// request가 초기화되기전에 request에 담긴 key 값인 name=한동석을 담아둔다.
		//그러고 b에 도착하면 초기화된상태인데 그대 session에서 다시 b에 추가를 시켜준다.
		//따라서 b에서 name=한동석을 쓸 수 있다.
		
		//방법2. queryString
		//url에 데이터가 붙어있기 때문에 마지막까지 유지가된다.
		
		//이 2가지를 다룰 수 있는게 RedirectAttributes rttr 이다.
		}
	
	@GetMapping({"/read", "/modify"})
	public void read(Criteria criteria, Long bno, HttpServletRequest request ,Model model){//Getmapping 이기 때문에 redirect로 안보냄
		//어떤요청이 들어왔는지 궁금하다? requset객체 필요
		//request객체에는 사용자가 요청한 url이 들어있다. 그게 뭔지를 알아야 변수로 쓸 수있다.
		//HttpServletRequest request 매개변수에 담아준다.
		String url = request.getRequestURI();
		log.info(url.substring(url.lastIndexOf("/")) + " : " +  bno);
		//마지막이 read 인지 modify인지 궁금한것이기 때문에 url.substring(url.lastIndexOf("/")) 이렇게 잘라준다.
		model.addAttribute("board", boardService.get(bno));
	}
	//수정페이지로 이동을 할 때는 기본적으로 작성된 정보가 있어야한다.
	// read와 같기 때문에 read와 같이 써준다.
	//같이쓸때는 {}안에 넣어준다.
	
	@GetMapping("/remove")
	public String remove(Long bno, RedirectAttributes rttr) {
		log.info("/remove : " + bno);
		
		if(boardService.remove(bno)) {
			rttr.addFlashAttribute("result", "success"); //안넘겨도되는데 성공 보여주고싶어서 넘김
		}
		
		return "redirect:/board/list";
	}
	
	@PostMapping("/modify")
	public String modify(Criteria criteria, BoardVO boardVO, RedirectAttributes rttr) {
		log.info("/modify : " + boardVO);
		if(boardService.modify(boardVO)) {
			rttr.addFlashAttribute("result", "success");
		}
		/*
		 * rttr.addAttribute("pageNum", criteria.getPageNum());
		 * rttr.addAttribute("type", criteria.getType()); rttr.addAttribute("keyword",
		 * criteria.getKeyword());
		 */
		return "redirect:/board/list" + criteria.getParams();
		// 2~3번째 부터 넘어가는 값은 add를 썼을 때 잘 안될 수 도 있으므로 무조건 되는 getParams를 사용
		// 뒤에 이어서 경로를 직접 입력
	}
	
	@GetMapping("/register")
	public void register() { //get post에 따라 처리가 다르다.
		//get으로 받기 때문에 /register.jsp로 이동하고 그 후 등록처리가 완료가되면 post register로간다.		
	}
}
