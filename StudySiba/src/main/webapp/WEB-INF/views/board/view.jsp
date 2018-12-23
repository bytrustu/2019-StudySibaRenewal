<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="content_subject">

    <div class="content_subjectleft">
        <img src="/images/sub/studying.png">
        <span>자유게시판</span>
    </div>
</div>

<div class="clear-fix"></div>

<div class="content_body">

    <div class="content_warp">
        <div class="content_view">
            <div class="view_header">
                <div class="view_subject">
                    <span>${view.title }</span>
                </div>
                <div class="view_contentinfo">
                    <div class="view_infoleft">
                        <span>[${view.nick }]</span>
                    </div>
                    <div class="view_inforight">
                        <span>${view.bDate }</span>
                        <span>｜</span>
                        <span>조회 ${view.count }</span>
                    </div>
                </div>
            </div>
            <div class="view_body">
                <input type="hidden" id="view_no" value="${view.no }">
                <input type="hidden" id="view_gNo" value="${view.gNo }">
                <input type="hidden" id="view_step" value="${view.step }">
                <input type="hidden" id="view_indent" value="${view.indent }">
                <div class="view_content">
                    ${view.content }
                </div>
                <c:choose>
                    <c:when test="${like.check eq 'true' }">
                        <div class="view_like" data="like">
                            <img src="/images/sub/like.png">
                            <span>좋아요</span>
                            <span>${like.count }</span>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="view_like" data="unlike">
                            <img src="/images/sub/unlike.png">
                            <span>좋아요</span>
                            <span>${like.count }</span>
                        </div>
                    </c:otherwise>
                </c:choose>

            </div>
            <div class="view_footer">
                <div class="view_info">
                    <img src="/images/profile/kakao/${view.proFile }">
                    <span>${view.nick }</span>
                    <span>[ ${view.id } ]</span>
                </div>
                <div class="view_contact">
                    <div class="view_message">
                        <img src="/images/main/mail.png">
                        <span>메세지</span>
                    </div>
                    <div class="view_friend">
                        <img src="/images/main/friendship.png">
                        <span>친구추가</span>
                    </div>
                </div>
            </div>

            <div class="view_button">
                <button class="btn btn-primary boardBtn" data="content_rewrite">답글쓰기</button>
                <button class="btn btn-primary boardBtn" data="board_list">목록</button>
            </div>

            <div class="view_comment">
                <div class="comment_subject">
                    <span>10</span>
                    <span>개의 댓글이 있습니다.</span>
                </div>
                <div class="comment_inputwapr">
                    <input type="text">
                    <button class="btn btn-danger">작성</button>
                </div>



                <div class="comment_list">


                    <div class="comment_content">
                        <img src="/images/profile/kakao/kakao-1.png">
                        <div class="comment_info">
                            <p>
                                <span>[국비캐궁금]</span>
                                <span>쟁비서바보ㅋㅋㅋ</span>
                            </p>
                            <p>2018.12.23 15:58</p>
                        </div>
                        <div class="comment_button btn btn-info" data="close">
                            답글작성
                        </div>
                    </div>

                    <div class="comment_content">
                        <img src="/images/profile/kakao/kakao-2.png">
                        <div class="comment_info">
                            <p>
                                <span>[쟁비서]</span>
                                <span>캄캄바보</span>
                            </p>
                            <p>2018.12.23 15:58</p>
                        </div>
                        <div class="comment_button btn btn-info">
                            답글작성
                        </div>
                    </div>

                    <div class="comment_content">
                        <img src="/images/profile/kakao/kakao-3.png">
                        <div class="comment_info">
                            <p>
                                <span>[캄캄32]</span>
                                <span>부왕바보</span>
                            </p>
                            <p>2018.12.23 15:58</p>
                        </div>
                        <div class="comment_button btn btn-info">
                            답글작성
                        </div>
                    </div>

                    <div class="comment_content">
                        <img src="/images/profile/kakao/kakao-4.png">
                        <div class="comment_info">
                            <p>
                                <span>[부왕]</span>
                                <span>88실종사태발발</span>
                            </p>
                            <p>2018.12.23 15:58</p>
                        </div>
                        <div class="comment_button btn btn-info">
                            답글작성
                        </div>
                    </div>

                    <div class="comment_content">
                        <img src="/images/profile/kakao/kakao-5.png">
                        <div class="comment_info">
                            <p>
                                <span>[88.방장.com]</span>
                                <span>오늘목욕할예정</span>
                            </p>
                            <p>2018.12.23 15:58</p>
                        </div>
                        <div class="comment_button btn btn-info">
                            답글작성
                        </div>
                    </div>


                </div>

            </div>

        </div>
    </div>


</div>