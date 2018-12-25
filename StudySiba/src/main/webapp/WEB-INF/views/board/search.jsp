<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="content_subject">
    <div class="content_subjectleft">
        <img src="/images/sub/studying.png">
        <span>자유게시판</span>
    </div>
    <div class="content_subjectright">
        <form method="GET" action="/board/search">
            <select name="searchType" class="form-control board_searchselect">
                <option value="all">전체</option>
                <option value="title">제목</option>
                <option value="content">내용</option>
                <option value="id">작성자</option>
            </select>
            <input class="board_searchtext board_searchtext" type="text" name="searchText" class="form-control" placeholder="내용을 입력하세요">
            <button class="btn btn-primary board_searchbtn boardBtn" data="board_search">검색</button>
        </form>
        <button class="btn btn-warning board_writebtn boardBtn" data="board_movewrite">글쓰기</button>
    </div>
</div>

<div class="clear-fix"></div>

<div class="content_body">

    <div class="content_listwarp">

        <c:forEach items="${list }" var="list">
            <c:choose>
                <c:when test="${list.available eq '0' }">
                    <div class="content_list">
                        <div class="content_userimg">
                            <div class="content_layoutimg">
                                <img src="/images/profile/kakao/${list.proFile }">
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
                                <span>[ ${list.commentCount } ]</span>
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
                </c:when>
                <c:otherwise>

                    <div class="content_list">
                        <div class="content_userimg">
                            <div class="content_layoutimg">
                                <img src="/images/profile/kakao/siba-default.png">
                            </div>
                        </div>
                        <div class="content_boardview">
                            <div class="content_boardtext">
                                <c:if test="${list.indent > 0 }">
                                    <c:set var="wid" value="${list.indent*10 }"></c:set>
                                    <img src='<c:url value="/images/sub/level.png"/>' width="${wid }">
                                    <img src='<c:url value="/images/sub/list-re.png"/>'>
                                </c:if>
                                <span data="${list.no }">삭제 된 게시글 입니다.</span>
                                <span></span>
                            </div>
                            <div class="content_boardinfotext">
                                <span>확인불가</span>
                                <span></span>
                                <span></span>
                            </div>
                        </div>
                        <div class="content_boardinfo">
                            <div class="content_searchicon">
                                <img src="/images/sub/board_search.png">
                                <span>-</span>
                            </div>
                            <div class="content_totalicon">
                                <img src="/images/sub/board_like.png">
                                <span>-</span>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </c:forEach>


    </div>

    <div class="clear-fix"></div>
    <div class="content_pagenation ">
        <ul class="pagination">
            <c:if test="${page.startPage > page.pageBlock }">
                <li class="page-item"><a class="page-link" href="/board/search?pageNum=${page.startPage-1 }&searchType=${search.searchType}&searchText=${search.searchText}">이전</a></li>
            </c:if>
            <c:forEach var="i" begin="${page.startPage }" end="${page.endPage }" step="1">
                <c:if test="${page.pageNum eq i }">
                    <li class="page-item active"><a class="page-link" href="/board/search?pageNum=${i }&searchType=${search.searchType}&searchText=${search.searchText}">${i }</a></li>
                </c:if>
                <c:if test="${page.pageNum ne i }">
                    <li class="page-item"><a class="page-link" href="/board/search?pageNum=${i }&searchType=${search.searchType}&searchText=${search.searchText}">${i }</a></li>
                </c:if>
            </c:forEach>
            <c:if test="${page.endPage < page.pageCount }">
                <li class="page-item"><a class="page-link" href="/board/search?pageNum=${page.startPage+page.pageBlock }&searchType=${search.searchType}&searchText=${search.searchText}">다음</a></li>
            </c:if>
        </ul>

    </div>

</div>
