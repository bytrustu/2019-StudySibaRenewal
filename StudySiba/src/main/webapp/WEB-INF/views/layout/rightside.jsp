<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

</div>
<div class="rightmenu">
    <div class="rightmenu_nav">

        <div class="rightmenu_infowarp">
            <div class="rightmenu_info">
                <img class="rightmenu_img" src="/images/profile/kakao/kakao-1.png">
                <div class="rightmenu_user">
                    <p>하하호호하하</p>
                    <p>bytrustu</p>
                    <p>일반유저</p>
                </div>
            </div>
            <div class="rightmenu_connect">
                <span>최종접속기록</span>
                <span>2018월12월12일 22시 51분</span>
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
                    <p>113</p>
                    <p>방문한수</p>
                </div>
                <div class="rightmenu_comm">
                    <p>53</p>
                    <p>작성한글</p>
                </div>
                <div class="rightmenu_comm">
                    <p>555</p>
                    <p>댓글단글</p>
                </div>
            </div>
        </div>
    </div>
</div>
