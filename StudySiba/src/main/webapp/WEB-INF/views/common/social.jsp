<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>스터디시바 : 온라인 스터디 그룹</title>

    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <script src="/js/main.js"></script>
    <script src="/js/social.js"></script>

    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css">
    <link rel="stylesheet" href="/css/main.css">
    
</head>


<body>
    <div class="social_warp">
        <div class="sociallogin_warp">
            <div class="joinmodal_warp">
                <div class="joinmodal_body">
                    <button type="button" class="close social-close">&times;</button>
                    <img class="social_choose" src="/images/main/rotate.png">
                    <img class="rounded-circle joinmodal_image" src="">
                    <p class="joinmodal_title">☆소셜★...가즈아</p>
                    <form id="joinForm" method="POST" action="<c:url value='/member/join'/>">
                    	<input type="hidden" name="proFile">
                        <div>
                            <input class="joinmodal_joinid" type="text" name="id" placeholder="아이디 입력">
                            <div class="btn btn-warning validation_false">미입력</div>
                        </div>
                        <div>
                            <input class="joinmodal_joinid" type="text" name="nick" placeholder="닉네임 입력">
                            <div class="btn btn-warning validation_false">미입력</div>
                        </div>
                        <c:if test="${sessionScope.socialInfo.type eq 'google' || sessionScope.socialInfo.type eq 'kakao'  }">
                        <div>
                            <input class="joinmodal_joinid" type="email" name="eMail" placeholder="EXAMPLE@GMAIL.COM">
                            <div class="btn btn-warning validation_false">미입력</div>
                        </div>
                        </c:if>
                        <div>
                            <button class="btn btn-danger joinmodal_joinbtn" data="social">회원가입</button>
                            <div class="btn btn-warning validation_final">불가</div>
                        </div>
                    </form>
                </div>
            </div>
        </div>


    </div>

</body>
</html>