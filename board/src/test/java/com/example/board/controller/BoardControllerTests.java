package com.example.board.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration //was가 돌아가는 부분에 대해서 여기를 들렸다 가게끔 해준다.
@ContextConfiguration({ // WAS가 설정파일을 참고해서 돌아가는데 webapp이 돌아갈때 필요한 파일의 경로
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})//여러개 쓸때는 중괄호를 써주어야 한다.
@Log4j
public class BoardControllerTests {
//컨트롤러는 요청과 응답 파라미터등이 있기 때문에 그동안 사용했던 단위테스와는 다르다.
//	was라는 환경 즉, webapp이라는 친구를 사용해서 환경을 구축해주고 was가 돌아가듯이 단위테스트인 junit이라는api를 사용해서 돌린다.
	@Autowired
	private WebApplicationContext webApplicationContext;
	//context라는 영역을 써준다.
	//bean들을 등록할 수 있는 container 즉, context라는 영역을 만들어준다.
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {//매번 먼저 사용
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	//WebApplicationContext를 불러온 이유는 ContextConfiguration의 참조된 경로를 주입을받고 주입된객체를 setup에 전달을해주면
	//build를 통해 실제 요청과 응답을 보낼 수 있는 mockMvc라는 객체를 생성 할 수 있다.
	
	@Test
	public void listTest() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
				.param("pageNum", "1")
				.param("amount", "10")
				.param("type", "TC")
				.param("keyword", "새"))
				.andReturn().getModelAndView().getModelMap());
		//preform메소드 안에서  parameter들이 일어난다.
		//MockMvcRequestBuilders가 요청을 할 수 있게끔 만들어주고
		//get(url)방식으로 보내겠다.(post도 있음)
		//perform을 통해 andReturn 요청을 하고나서 결과를 가져오는 객체
		//getModelAndView()를 통해 getModelMap() 현재 모델이라는 데이터전달자 안에 무엇이들었는지 map으로 보겠다.
		// 이렇게하고 model이라는 데이터 전달자 안에 보내려했떤 list가 들어가있으면된다.
		//throws Exception을 통해 전체 exception을 잡아준다.
	}
	
//	@Test
//	public void registerTest() throws Exception {
//		log.info(mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
//					.param("title", "테스트 새 글 제목")
//					.param("content", "테스트 새 글 내용")
//					.param("writer", "hgd0000")
//				
//				).andReturn().getFlashMap());
////				외부에서 파라미터를 전달 받아야할 때는 이렇게 사용한다.
////				post라는 메소드에서 parm을 전달할 수 있기때문에 여기서;로 끊어준다..
////				FlashAttribute를 썼을때 flash담겨있는 데이터를 가져와야하기때문에
////				modelMap이 아닌 FlashMap을 써준다.
//	}
	
//	@Test
//	public void readTest() throws Exception {
//		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/read")
//					.param("bno", "22")// 파싱이될때 데이터타입을 일반타입으로 쓰면 파싱이 안될수 있으니 클래스 타입으로 써주자(꿀팁)
//				
//				).andReturn().getModelAndView().getViewName());
//		//getViewName() => 연산이 끝나고 실제로 이동하게될 페이지 / 경로알고싶을때
//		//그게아니라 전달자가 보고싶으면 getModelMap()을 대신 써주면된다.
//	}
//	
//	@Test
//	public void removeTest() throws Exception{
//		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/remove")
//				.param("bno", "3")
//				).andReturn().getFlashMap());
//	}
//	@Test
//	public void modifyTest() throws Exception{
//		log.info(mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
//				.param("bno", "8")
//				.param("title", "수정한 게시글 제목")
//				.param("content", "수정한 게시글 내용")
//				.param("writer", "bugbugcoding")
//				).andReturn().getFlashMap());
//	}
	
//	@Test
//	public void goModifyTest() throws Exception{
//		mockMvc.perform(MockMvcRequestBuilders.get("/board/modify").param("bno", "9"));
//	}
}
