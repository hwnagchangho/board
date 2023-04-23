package com.example.board.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // 기본생성자
public class PageDTO {
	private int startPage; //현재 페이지 기준 시작 페이지
	private int endPage; // 현재 페이지 기준 마지막 페이지
	private int realEnd; // 실제 마지막 페이지 
	// amount를 10을 주면 10,20,30, 으로 늘어난다.
	//근데 마지막 게시물이 126번이면 몇페이지에 뜰까?
	// 01~10게시글이 이 1페이지 11~20게시글이 2페이지 21~30게시글이 3페이지 
	// 그랬을 때 앞의 자리를 보면 시작 단계를 알 수 있다.
	// 0+1 = 1, 1+1=2, 2+1=3 => 즉 126번게시물은 12+1 = 13 
	//13페이지라는 것을 알 수 있다.121번~130번게시글
	//근데 13페이지가 끝이어야 되는데 보여지는 페이지 개수가 10개씩 일 경우 11~20 페이지
	//즉, 14~20은 게시글이 없는데도 출력이된다. 
	//따라서 이러한 상황을 막아주기 위해 realEnd를 쓰는데 (전체 게시글 개수/amount)를 해주면된다.
	
	//이전 다음 버튼 유무검사
	private boolean prev; 
	private boolean next;
	
	//전체 게시글 수
	private int total; // realEnd를 구하기 위해선 게시글의 전체개수가 필요
	private Criteria criteria; // pageNum, amount가 필요하기 때문에 criteria를 여기서 써준다.
	// 따라서 화면쪽에 pageDTO만 전달해도 Criteria도 같이 전달이 되기 때문에 pageNum, amount 둘다 사용 가능하다.
	//pageDTO.criteria.amount 이런식으로 사용가능
	
	public PageDTO(int total, Criteria criteria) {// 두개만 외부에서 받아온다.
		//데이터베이스에서 조회한 전체게수를 dto에 전달해주면 realEnd를 구할 수 있다.
		this.total = total;
		this.criteria = criteria;
		//criteria는 boardController에서 이미 외부에서 전달을 받는다.
		// 그대로 여기로 전달
		
//		Math클래스  => 여러가지 수학적인 부분의 메소드들이 만들어져있음
		//매번 객체화를 해서 작업하기가 불편하기 때문에 싱글톤패턴으로 설정해놓음
		// static메소드라 Math. 클래스로 바로 접근 가능
		// 페이징 처리시 올림 메서드 필요
		//게시글이 126개면 12페이지에 120번까지 들어가고 6개가 남기때문에 13페이지가 필요하다.
		// 126/10.0 = 12.6 => 여기서 올림을 해준다. 그럼 13
		
//		ceil(실수 값) : 올림 처리, 페이지에 게시글이 한개라도 있다면, 올림을 하여 해당 페이지를 표시하기 위함
		this.endPage = (int)(Math.ceil(criteria.getPageNum() / (double)criteria.getAmount())) * criteria.getAmount();
		// 현재페이지 7 / 10 = 0.7 => ceil로인해 올림 => 1 * 10 => 끝페이지 10
		//현재페이지 17 / 10 = 1.7 => ceil로인해 올림 => 2 * 10 => 끝페이지 20
		this.startPage = endPage - (criteria.getAmount() - 1);
		// 10 - (10-1) = 1
		// 20 - (10 -1) = 11, 30 - 9 = 21

//		실제 마지막 페이지는 전체 게시글 개수에서 amount를 나눠주게 된다. 하지만 1개의 게시글이 있더라도 페이지를 표현해야 하기 때문에
//		실수로 나누어 준 다음 소수점을 올림하여 페이지를 + 1 해준다.
		this.realEnd = (int)Math.ceil(total * 1.0 / criteria.getAmount());
		
//		endPage는 amount가 10이므로 10의 배수이다. 그렇게 때문에 realEnd보다 더 커지면 realEnd를 넣어준다.
		if(this.realEnd < this.endPage) {
			this.endPage = this.realEnd;
		}
		
//		1~10 : 1단위
//		11~20 : 2단위
//		...
//		이전 단위가 있는 경우
		this.prev = this.startPage > 1;
//		다음 단위가 있는 경우
		this.next = this.endPage < this.realEnd;
	}
	
	
}
