<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                    <span id="view_nick">${view.nick }</span>
                    <span>[ ${view.id } ]</span>
                </div>
                <div class="view_contact">
                    <div class="view_message">
                        <img src="/images/main/mail.png" class="modal_open view_messenger" id="messageBtn" data="messageModal">
                        <span class="modal_open view_messenger" id="messageBtn" data="messageModal">메세지</span>
                    </div>
                    <div class="view_friend">
                        <img src="/images/main/friendship.png" class="modal_open view_messenger" id="friendBtn" data="messageModal">
                        <span class="modal_open view_messenger" id="friendBtn" data="messageModal">친구추가</span>
                    </div>
                </div>
            </div>

            <div class="view_button">
                <c:if test="${sessionScope.userSession.id eq view.id}">
                    <button class="btn btn-primary boardBtn" data="content_modify">수정</button>
                    <button class="btn btn-primary boardBtn" data="content_delete">삭제</button>
                </c:if>

                <button class="btn btn-primary boardBtn" data="content_rewrite">답글쓰기</button>
                <button class="btn btn-primary boardBtn" data="board_list">목록</button>
            </div>

            <div class="view_comment">
                <div class="comment_subject">
                    <span>${commentCount }</span>
                    <span>개의 댓글이 있습니다.</span>
                </div>
                <div class="comment_inputwarp">
                    <input type="text" class="comment_text">
                    <button class="btn btn-danger boardBtn" data="comment_write">작성</button>
                </div>

                <div class="comment_list">

                    <c:forEach items="${comment }" var="comment">
                        <div class="comment_content">
                            <c:if test="${comment.indent > 0 }">
                                <c:set var="wid" value="${comment.indent*15 }"></c:set>
                                <img src='<c:url value="/images/sub/level.png"/>' style="width: ${wid}px; margin-right:0;">
                                <img src='<c:url value="/images/sub/comment.png"/>' style="width: 23px; height: 23px;">
                            </c:if>
                            <img src="/images/profile/kakao/${comment.proFile }">
                            <div class="comment_info">
                                <p>
                                    <span>[${comment.nick }]</span>
                                    <c:if test="${comment.preId ne 'default' }">
                                        <span class="comment_preId">${comment.preId }</span>
                                    </c:if>
                                    <span>${comment.content }</span>
                                </p>
                                <p>${comment.cDate }</p>
                            </div>
                            <div class="comment_button btn btn-info" data="close">
                                답글작성
                            </div>
                            <input type="hidden" id="comment_type" value="${comment.type }">
                            <input type="hidden" id="comment_id" value="${comment.id }">
                            <input type="hidden" id="comment_gNo" value="${comment.gNo }">
                            <input type="hidden" id="comment_step" value="${comment.step }">
                            <input type="hidden" id="comment_indent" value="${comment.indent }">
                            <div class="comment_rewrite"></div>
                        </div>
                    </c:forEach>

                </div>
            </div>
        </div>
    </div>
</div>
