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

        <div class="content_list">
            <div class="content_userimg">
                <div class="content_layoutimg">
                    <img src="/images/profile/kakao/kakao-1.png">
                </div>
            </div>
            <div class="content_boardview">
                <div class="content_boardtext">
                    <span>월요일싫어요</span>
                    <span>[5]</span>
                </div>
                <div class="content_boardinfotext">
                    <span>복실복실1</span>
                    <span>[bytrustu1]</span>
                    <span>- 방금 전</span>
                </div>
            </div>
            <div class="content_boardinfo">
                <div class="content_searchicon">
                    <img src="/images/sub/board_search.png">
                    <span>222</span>
                </div>
                <div class="content_totalicon">
                    <img src="/images/sub/board_like.png">
                    <span>22</span>
                </div>
            </div>
        </div>

        <div class="content_list">
            <div class="content_userimg">
                <div class="content_layoutimg">
                    <img src="/images/profile/kakao/kakao-2.png">
                </div>
            </div>
            <div class="content_boardview">
                <div class="content_boardtext">
                    <span>화요일싫어요</span>
                    <span>[5]</span>
                </div>
                <div class="content_boardinfotext">
                    <span>복실복실2</span>
                    <span>[bytrustu2]</span>
                    <span>- 10분 전</span>
                </div>
            </div>
            <div class="content_boardinfo">
                <div class="content_searchicon">
                    <img src="/images/sub/board_search.png">
                    <span>55</span>
                </div>
                <div class="content_totalicon">
                    <img src="/images/sub/board_like.png">
                    <span>5</span>
                </div>
            </div>
        </div>

        <div class="content_list">
            <div class="content_userimg">
                <div class="content_layoutimg">
                    <img src="/images/profile/kakao/kakao-3.png">
                </div>
            </div>
            <div class="content_boardview">
                <div class="content_boardtext">
                    <span>수요일싫어요</span>
                    <span>[32]</span>
                </div>
                <div class="content_boardinfotext">
                    <span>복실복실3</span>
                    <span>[bytrustu3]</span>
                    <span>- 15분 전</span>
                </div>
            </div>
            <div class="content_boardinfo">
                <div class="content_searchicon">
                    <img src="/images/sub/board_search.png">
                    <span>52</span>
                </div>
                <div class="content_totalicon">
                    <img src="/images/sub/board_like.png">
                    <span>3</span>
                </div>
            </div>
        </div>

        <div class="content_list">
            <div class="content_userimg">
                <div class="content_layoutimg">
                    <img src="/images/profile/kakao/kakao-4.png">
                </div>
            </div>
            <div class="content_boardview">
                <div class="content_boardtext">
                    <span>목요일싫어요</span>
                    <span>[52]</span>
                </div>
                <div class="content_boardinfotext">
                    <span>복실복실4</span>
                    <span>[bytrustu4]</span>
                    <span>- 22분 전</span>
                </div>
            </div>
            <div class="content_boardinfo">
                <div class="content_searchicon">
                    <img src="/images/sub/board_search.png">
                    <span>5</span>
                </div>
                <div class="content_totalicon">
                    <img src="/images/sub/board_like.png">
                    <span>5</span>
                </div>
            </div>
        </div>

        <div class="content_list">
            <div class="content_userimg">
                <div class="content_layoutimg">
                    <img src="/images/profile/kakao/kakao-5.png">
                </div>
            </div>
            <div class="content_boardview">
                <div class="content_boardtext">
                    <span>금요일싫어요</span>
                    <span>[21]</span>
                </div>
                <div class="content_boardinfotext">
                    <span>복실복실5</span>
                    <span>[bytrustu]</span>
                    <span>- 33분 전</span>
                </div>
            </div>
            <div class="content_boardinfo">
                <div class="content_searchicon">
                    <img src="/images/sub/board_search.png">
                    <span>52</span>
                </div>
                <div class="content_totalicon">
                    <img src="/images/sub/board_like.png">
                    <span>9</span>
                </div>
            </div>
        </div>

        <div class="content_list">
            <div class="content_userimg">
                <div class="content_layoutimg">
                    <img src="/images/profile/kakao/kakao-6.png">
                </div>
            </div>
            <div class="content_boardview">
                <div class="content_boardtext">
                    <span>토요일싫어요</span>
                    <span>[2]</span>
                </div>
                <div class="content_boardinfotext">
                    <span>복실복실6</span>
                    <span>[bytrustu6]</span>
                    <span>- 1시간 전</span>
                </div>
            </div>
            <div class="content_boardinfo">
                <div class="content_searchicon">
                    <img src="/images/sub/board_search.png">
                    <span>56</span>
                </div>
                <div class="content_totalicon">
                    <img src="/images/sub/board_like.png">
                    <span>5</span>
                </div>
            </div>
        </div>

        <div class="content_list">
            <div class="content_userimg">
                <div class="content_layoutimg">
                    <img src="/images/profile/kakao/kakao-7.png">
                </div>
            </div>
            <div class="content_boardview">
                <div class="content_boardtext">
                    <span>일요일싫어요</span>
                    <span>[2]</span>
                </div>
                <div class="content_boardinfotext">
                    <span>복실복실7</span>
                    <span>[bytrustu7]</span>
                    <span>- 3시간 전</span>
                </div>
            </div>
            <div class="content_boardinfo">
                <div class="content_searchicon">
                    <img src="/images/sub/board_search.png">
                    <span>56</span>
                </div>
                <div class="content_totalicon">
                    <img src="/images/sub/board_like.png">
                    <span>5</span>
                </div>
            </div>
        </div>


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
