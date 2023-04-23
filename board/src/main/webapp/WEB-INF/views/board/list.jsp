<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Board</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="/resources/assets/css/main.css" />
		<style>
			body {transform: scale(0.8);}
			
			.big-width {display: block;}
			.small-width {display: none;}
			.table-wrapper{overflow-x : hidden;}
			select{
				width:15%;
				display:inline;
			}
			input[name='keyword']{
				display:inline;
				width:60%;
			}
			.search{	
				width:20%;
			}
			
			@media(max-width: 918px){
				body {transform: scale(1); overflow-x: hidden;}
				.writer {display: none;}
				.regDate {display: none;}
				.updateDate {display: none;}
				.big-width {display: none;}
				.small-width {display: block;}
				select{
				width:100%;
				}
				input[name='keyword']{
					width:100%;
				}
				.search{	
					width:100%;
				}
			}
			
		</style>
	</head>
	<body class="is-preload">
		<!-- Main -->
		<div id="main">
			<div class="wrapper">
				<div class="inner">
	
					<!-- Elements -->
					<header class="major">
						<h1>Board</h1>
						<p>게시판 목록</p>
					</header>
					<!-- Table -->
					<h3><a href="/board/register" class="button small">글 등록</a></h3>
					<div class="table-wrapper">
						<table>
							<thead>
								<tr class="tHead">
									<th class="bno">번호</th>
									<th class="title">제목</th>
									<th class="writer">작성자</th>
									<th class="regDate">작성일</th>
									<th class="updateDate">수정일</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="board" items="${boardList}">
									<tr class="tBody">
										<td class="bno">${board.bno}</td>
										<td class="title"><a href="/board/read${pageDTO.criteria.params}&bno=${board.bno}">${board.title}</a></td>
										<td class="writer">${board.writer}</td>
										<td class="regDate">${board.regDate}</td>
										<td class="updateDate">${board.updateDate}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						
						<form action="/board/list" name="searchForm">
							<div class="fields">
								<div class="field" style="text-align : center">
									<select name="type">
										<option value="" ${pageDTO.criteria.type == null ? 'selected' : ''}>검색 기준</option>
										<option value="TCW" ${pageDTO.criteria.type == 'TC' ? 'selected' : ''}>전체</option>
										<option value="T" ${pageDTO.criteria.type == 'T' ? 'selected' : ''}>제목</option>
										<option value="C" ${pageDTO.criteria.type == 'C' ? 'selected' : ''}>내용</option>
										<option value="W" ${pageDTO.criteria.type == ' W' ? 'selected' : ''}>작성자</option>
										<option value="TW" ${pageDTO.criteria.type == 'TW' ? 'selected' : ''}>제목 또는 작성자</option>
										<option value="TC" ${pageDTO.criteria.type == 'TC' ? 'selected' : ''}>제목 또는 내용</option>
									</select>
									<input type="text" name="keyword" value="${pageDTO.criteria.keyword}"/><!-- 외부에서 들어온 값이 value로 전달되어야지 그전에 입력된 값이 기억이된다.  -->
									<input type="hidden" name="pageNum" value="${pageDTO.criteria.pageNum}"/>
									<!-- <a href="javascript:send()" class="search button primary icon solid fa-search">검색</a> -->
									<!-- 강사는 이렇게 해놨는데 나는 onclick으로 함 -->
									<button type="button" id="searchBtn" onclick="send()">검색</button>
								</div>
							</div>
						</form>
						
						<div style="text-align:center;" class="big-width">
							<c:if test="${pageDTO.prev}">
							<!-- a태그는 똑같은 클래스로 묶을거다 -->
							<!-- 현재페이지를 기준으로 구해진 startpage -1  -->
							<!-- 현재페이지 17이면 startpage=11 여기서 -1 하면 10 => 1~10으로 이동 -->
								<a class="changePage" href="${realEnd - (realEnd - 1)}"><code>&lt;&lt;</code></a> <!-- &lt; 왼쪽방향 화살표 -->
								<!-- href="$pageDTO.criteria.pageNum = 1" 이렇게하면 11페이지부터 클릭된 페이지 표시가 안됨 realEnd-(realEnd-1)도 마찬가지-->
								<a class="changePage" href="${pageDTO.startPage - 1}"><code>&lt;</code></a> <!-- &lt; 왼쪽방향 화살표 -->
							</c:if>
						<!-- 빠른 for문도 가능하지만 시작과 끝을 정해서 돌릴 수 도 있따.  -->
							<c:forEach var="num" begin="${pageDTO.startPage}" end="${pageDTO.endPage}">
								<c:choose>
									<c:when test="${pageDTO.criteria.pageNum == num}"> 
										<code><c:out value="${num}"></c:out></code> 
									</c:when>
									<c:otherwise>
										<!-- code? 기존에 페이지버튼이 디자인 되있는 태그 -->
										<a class="changePage" href="${num}"><code><c:out value="${num}"></c:out></code></a>
										<!-- a태그를 js에서 클릭이벤트로 뺄거기 때문에 href는 디폴트값 num을 넣어준다. -->
										<!-- 자바스크립트에서 board/list로 보낼 때  필요로하는것은 사용자가 이동하고싶은 페이지-->
										<!-- 그래서 href에는 사용자가 지금 이동하고싶은 페이지의 값을 적어준다. -->
										<!-- js에서 몇페이지로 이동하냐 물어보면 href에 값이 있어 라고 알려준다 -->
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:if test="${pageDTO.next}">
								<a class="changePage" href="${pageDTO.endPage + 1}"><code>&gt;</code></a> <!-- &gt; 오른쪽방향 화살표 -->
								<a class="changePage" href="${pageDTO.realEnd}"><code>&gt;&gt;</code></a> <!-- &gt; 오른쪽방향 화살표 -->
							</c:if>						
						</div>
						
						<div style="text-align: center;" class="small-width">
							<c:if test="${pageDTO.criteria.pageNum > 1}">
								<a class="changePage" href="${realEnd - (realEnd - 1)}"><code>&lt;&lt;</code></a> <!-- &lt; 왼쪽방향 화살표 -->
								<a class="changePage" href="${pageDTO.criteria.pageNum - 1}"><code>&lt;</code></a>
							</c:if>
							<code><c:out value="${pageDTO.criteria.pageNum}"/></code>
							<c:if test="${pageDTO.realEnd > pageDTO.criteria.pageNum}">
								<a class="changePage" href="${pageDTO.criteria.pageNum + 1}"><code>&gt;</code></a>
								<a class="changePage" href="${pageDTO.realEnd}"><code>&gt;&gt;</code></a> <!-- &gt; 오른쪽방향 화살표 -->
							</c:if>
						</div>
						<form name="pageForm" action="/board/list">
							<input type="hidden" name="pageNum" value="${pageDTO.criteria.pageNum}"/>
							<!-- js에서 a태그를 클릭하면 value값이 사용자가 가고싶은 페이지 값으로 바뀌어야함 -->
							<input type="hidden" name="type" value="${pageDTO.criteria.type}"/> <!-- 페이지 이동을 할 때에도 기억하기 위해선 type하고 keyword가 필요함  -->
							<input type="hidden" name="keyword" value="${pageDTO.criteria.keyword}"/>
						</form>
					</div>
				</div>
			</div>
		</div> 
	</body>
	<!-- Scripts -->
	<script src="/resources/assets/js/jquery.min.js"></script>
	<script src="/resources/assets/js/jquery.dropotron.min.js"></script>
	<script src="/resources/assets/js/browser.min.js"></script>
	<script src="/resources/assets/js/breakpoints.min.js"></script>
	<script src="/resources/assets/js/util.js"></script>
	<script src="/resources/assets/js/main.js"></script>
	
	<script>
	//페이지 이동을 하기 위해 a태그를 클릭하면 이벤트 실행
	/*  e.preventDefault(); 기존이벤트 막아줌 */
		$("a.changePage").on("click", function(e){
			/* alert('들어옴'); 안들어올 때 확인 e.~~ 쓰려면 function에 e를 받아와야한다.*/
			e.preventDefault();
			/* console.log("페이지 이동!"); */
			//form태그 가져오기
			let $form = $(document.pageForm);
			//form 태그 자식 요소 중 pageNum이 이름인 input태그 가져오기
			//기존의 value값을 사용자가 이동하고자 하는 페이지 번호로 변경하기
			$form.find("input[name='pageNum']").val($(this).attr("href"));/* jquery객체기때문에 $ 붙혀줌/ find는 자식태그 찾을때  */
			/* pageNum의 value에 a태그 href를 넣어줌 / attr 속성값찾을때 => 즉 href라는 속성을 넣는다는 의미  */
			$form.submit();
			
		});
		
		function send(){
			let $form = $(document.searchForm);
			if(!$form.find("option:selected").val()){
				alert('검색 종류를 선택하세요');
				return;
				}
			if(!$form.find("input[name='keyword']").val()){
				alert('키워드를 입력하세요');
				return;
				}
			$form.submit();
			}
		
	</script>
</html>