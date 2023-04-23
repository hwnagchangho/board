package com.example.board.domain.vo;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class Criteria {
	private int pageNum; //현재페이지
	private int amount; // 해당 페이지에 뿌려질 갯수 
	private String type;
	private String keyword;

	public Criteria() {
		this(1, 10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public Criteria(int pageNum, int amount, String type, String keyword) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
		this.type = type;
		this.keyword = keyword;
	}
	//기본 생성자를 정하는 이유는 데이터가 안들어올 때 default값을 정하기 위함이다.
	//this(pageNum, amount) => 즉, 1페이지에 10개씩
	// board/list를 조회할 때 this(1, 10)에 맞춰서 가져와야하기때문에
	//getList()라는 메소드에서는 Criteria를 전달받아서 현재페이지와 갯수를 가지고 와야한다.
	
	public String getParams() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("type", this.type)
				.queryParam("keyword", this.keyword);
//	fromPath("") => 가장 최상위 경로를 뭘로 할거냐? 최상위 경로에 항상 뭔가를 붙힐 필요는 없으므로 우리는 그대로 한다.
//	queryParam("pageNum", this.pageNum); pageNum이라는 파라미터를 this,pageNum으로 계속해서 전달 하겠다 라는뜻
		return builder.toUriString();
//	builder.toUriString() 이렇게 전달해주면 ?"pageNum"=this.pageNum(value값)으로 들어간다.
//		getParams는 위의 값을 리턴하는 메소드
//		get붙어있으니 사용할때는 params로 사용하면 된다. 그러면 자동으로 getter를 찾는다.
//		이 메소드의 리턴값은 항상 물음표부터 시작하기때문에 list.jsp에서 ? 값을 직접 써주면 안된다. 
//		/board/read?bno=${board.bno}를 /board/read${pageDTO.criteria.params}&bno=${board.bno}이렇게 써주어야 한다.
	}
	public String[] getTypes(){
		//"AbC".split("") --> 3칸 배열([A][B][C]) => 다중 항목 검색 처리 할 메소드
		return type == null ? new String[] {} : type.split("");
	}
}
