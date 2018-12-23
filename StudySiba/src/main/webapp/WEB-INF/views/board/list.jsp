<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>





<div class="content_subject">

    <div class="content_subjectleft">
        <img src="/images/sub/studying.png">
        <span>자유게시판</span>
    </div>
    <div class="content_subjectright">
        <select name="board_search" class="form-control board_searchselect">
            <option value="전체">전체</option>
            <option value="제목">제목</option>
            <option value="내용">내용</option>
            <option value="작성자">작성자</option>
        </select>
        <input class="board_searchtext board_searchtext" type="text" name="search" class="form-control" placeholder="내용을 입력하세요">
        <button class="btn btn-primary board_searchbtn boardBtn" data="board_search">검색</button>
        <button class="btn btn-warning board_writebtn boardBtn" data="board_movewrite">글쓰기</button>
    </div>
</div>

<div class="clear-fix"></div>

<div class="content_body">

    <div class="content_listwarp">

		<c:forEach items="${list }" var="list">
        <div class="content_list">
            <div class="content_userimg">
                <div class="content_layoutimg">
                    <img src="/images/profile/kakao/kakao-1.png">
                </div>
            </div>
            <div class="content_boardview">
                <div class="content_boardtext">
                	<c:if test="${list.indent > 0 }">
                		<c:set var="wid" value="${list.indent*10 }"></c:set>
                		<img src='<c:url value="/images/sub/level.png"/>' width="${wid }">
                		<img src='<c:url value="/images/sub/list-re.png"/>'>
                	</c:if>
                    <span data="${list.no }">${list.title }</span>
                    <span>[5]</span>
                </div>
                <div class="content_boardinfotext">
                    <span>${list.nick }</span>
                    <span>[${list.id }]</span>
                    <span> - ${list.bDate }</span>
                </div>
            </div>
            <div class="content_boardinfo">
                <div class="content_searchicon">
                    <img src="/images/sub/board_search.png">
                    <span>${list.count }</span>
                </div>
                <div class="content_totalicon">
                    <img src="/images/sub/board_like.png">
                    <span>${list.likeCount }</span>
                </div>
            </div>
        </div>
		</c:forEach>
        


    </div>

    <div class="clear-fix"></div>
    <div class="content_pagenation ">
        <ul class="pagination">
            <li class="page-item"><a class="page-link" href="#">이전</a></li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item active"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item"><a class="page-link" href="#">다음</a></li>
        </ul>

    </div>

</div>
