<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

</div>
<div class="rightmenu">
    <div class="rightmenu_nav">

        <div class="rightmenu_infowarp">
            <div class="rightmenu_info">
                <img class="rightmenu_img" src="/local_upload/profile/${sessionScope.userSession.proFile }">
                <div class="rightmenu_user">
                    <p>${sessionScope.userSession.nick }</p>
                    <p>${sessionScope.userSession.id }</p>
                    <p>
                        <c:if test="${sessionScope.userSession.auth eq 'admin' }">
                            관리자
                        </c:if>
                        <c:if test="${sessionScope.userSession.auth eq 'normal' }">
                            일반유저
                        </c:if>
                    </p>
                </div>
            </div>
            <div class="rightmenu_connect">
                <span>최종접속기록</span>
                <span>
                    <c:choose>
                        <c:when test="${sessionScope.userSession.cDate eq null}">
                            처음 접속한 회원 입니다. ^^
                        </c:when>
                        <c:otherwise>
                            ${sessionScope.userSession.cDate}
                        </c:otherwise>
                    </c:choose>
                </span>
            </div>
        </div>

        <ul>
            <li class="rightmenu_list" data="freeboard"><i class="fas fa-angle-right"></i><span>자유게시판</span></li>
            <li class="rightmenu_list" data="study"><i class="fas fa-angle-right"></i><span>스터디룸</span></li>
            <c:if test="${userSession ne null }">
                <li class="rightmenu_list" data="group"><i class="fas fa-angle-right"></i><span>스터디그룹</span></li>
            </c:if>
        </ul>

        <div class="rightmenu_community">
            <p>커뮤니티활동</p>
            <div class="rightmenu_commwarp">
                <div class="rightmenu_comm">
                    <p>${sessionScope.userSession.visitCount }</p>
                    <p>방문한수</p>
                </div>
                <div class="rightmenu_comm">
                    <p>${sessionScope.userSession.boardCount }</p>
                    <p>작성한글</p>
                </div>
                <div class="rightmenu_comm">
                    <p>${sessionScope.userSession.commentCount }</p>
                    <p>댓글단글</p>
                </div>
            </div>
        </div>
    </div>
</div>
