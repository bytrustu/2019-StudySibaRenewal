<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta charset="UTF-8">
    <title>스터디시바 : 온라인 스터디 그룹</title>

    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.32.2/dist/sweetalert2.min.js"></script>
    <script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
    <script src="/js/sociallogin.js"></script>
    <script src="/js/main.js"></script>

    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@7.32.2/dist/sweetalert2.min.css">
    <link rel="stylesheet" href="/css/main.css">

    <!--	소셜 세션	-->
    <c:if test="${sessionScope.socialInfo ne null }">
        <c:remove var="socialInfo" scope="session" />
    </c:if>
    <!--	메세지 세션	-->
    <c:if test="${sessionScope.message ne null}">
        <script>
            sessionMessage();
    	function sessionMessage(){
    		var message = "${sessionScope.message}";
    		if ( message != '' ) {
    			alert(message);
    		}
    	}
        </script>
        <c:remove var="message" scope="session" />
    </c:if>

</head>

<body>

    <div class="warp">

        <div class="layout-topheader">
        </div>

        <nav class="navbar navbar-expand-sm navbar-dark fixed-top layout-menu">
            <!-- Brand -->
            <a class="navbar-brand menu-title" href="/">
                <span class="menu-pre">스터디</span><span class="menu-last">시바</span>
            </a>
            <!-- Links -->
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="#">
                        <i class="fa fa-home menu-icon"></i>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">
                        <i class="fa fa-edit menu-icon"></i>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">
                        <i class="fa fa-envelope menu-icon" data="message"></i>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">
                        <i class="fas fa-book menu-icon"></i>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">
                        <i class="fa fa-user-plus menu-icon"></i>
                    </a>
                </li>
            </ul>

            <ul class="nav navbar-nav menu-infobtn">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle menu-dropicon" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expaneded="false">
                        <i class="fa fa-cog fa-spin fa-fw menu-icon"></i>
                    </a>
                    <div class="dropdown-menu menu-dropmenu">
                        <a class="dropdown-item modal_open" data="loginModal" href="#">로그인</a>
                        <a class="dropdown-item modal_open" data="joinModal" href="#">회원가입</a>
                    </div>
                </li>
            </ul>
        </nav>

        <div class="layout-content">
            <div class="content_top">
                <div class="content_text">
                    <p class="context_pretext">온라인 스터디그룹</p>
                    <p class="context_lasttext">STUDY SIBA</p>
                </div>
                <div class="content_main">
                    <img class="content_mainimage" src="/images/main/content_siba.png">
                </div>
            </div>

            <div class="content_bottom">

                <div class="content_container content_first">
                    <div class="content_titletext">
                        <img class="content_icon" src="/images/main/catalog.png">
                        <span class="content_toptext">사이트 현황</span>
                    </div>
                    <div class="content_body">
                    </div>
                </div>

                <div class="content_container">
                    <div class="content_titletext">
                        <img class="content_icon" src="/images/main/thumb-up.png">
                        <span class="content_toptext">실시간</span>
                        <span class="content_toptext content_textmiddle">인기</span>
                        <span class="content_toptext">게시글</span>
                    </div>
                    <div class="content_body">

                        <div class="content_rank">
                            <img class="content_ranknum" src="/images/main/rank1.png">
                            <img class="content_rankimage" src="/images/main/kakao-1.png">
                            <img class="content_like" src="/images/main/like.png">
                            <span class="content_liketext">15</span>
                            <span class="content_boardtitle">월요일싫어요월요...</span>
                        </div>

                        <div class="content_rank content_rank2">
                            <img class="content_ranknum" src="/images/main/rank2.png">
                            <img class="content_rankimage" src="/images/main/kakao-2.png">
                            <img class="content_like" src="/images/main/like.png">
                            <span class="content_liketext">8</span>
                            <span class="content_boardtitle">화요일싫어요화요...</span>
                        </div>

                        <div class="content_rank content_rank3">
                            <img class="content_ranknum" src="/images/main/rank3.png">
                            <img class="content_rankimage" src="/images/main/kakao-3.png">
                            <img class="content_like" src="/images/main/like.png">
                            <span class="content_liketext">5</span>
                            <span class="content_boardtitle">수요일싫어요수요...</span>
                        </div>

                    </div>
                </div>

                <div class="content_container">
                    <div class="content_titletext">
                        <img class="content_icon" src="/images/main/location.png">
                        <span class="content_toptext">접속중 회원</span>
                        <span class="content_toptext content_textmiddle">3</span>
                        <span class="content_toptext">명</span>
                    </div>
                    <div class="content_connectbody">
                        <div class="content_connect">
                            <img class="content_user" src="/images/main/kakao-1.png">
                            <span>관리자</span>
                            <img class="content_connecticon" src="/images/main/friendship.png">
                            <img class="content_connecticon" src="/images/main/mail.png">
                        </div>

                        <div class="content_connect">
                            <img class="content_user" src="/images/main/kakao-2.png">
                            <span>복실이</span>
                            <img class="content_connecticon" src="/images/main/friendship.png">
                            <img class="content_connecticon" src="/images/main/mail.png">
                        </div>

                        <div class="content_connect">
                            <img class="content_user" src="/images/main/kakao-3.png">
                            <span>하하호호</span>
                            <img class="content_connecticon" src="/images/main/friendship.png">
                            <img class="content_connecticon" src="/images/main/mail.png">
                        </div>

                        <div class="content_connect">
                            <img class="content_user" src="/images/main/kakao-2.png">
                            <span>하하호호</span>
                            <img class="content_connecticon" src="/images/main/friendship.png">
                            <img class="content_connecticon" src="/images/main/mail.png">
                        </div>

                        <div class="content_connect">
                            <img class="content_user" src="/images/main/kakao-3.png">
                            <span>관리자</span>
                            <img class="content_connecticon" src="/images/main/friendship.png">
                            <img class="content_connecticon" src="/images/main/mail.png">
                        </div>


                    </div>
                </div>

                <div class="content_container">
                    <div class="content_info">
                        <div class="content_infotext">
                            <p>스터디에 참가하려면 ?</p>
                        </div>
                        <div class="btn btn-primary content_loginbtn modal_open" data="loginModal">
                            <span class="content_textbtn">스터디시바 로그인</span>
                        </div>
                    </div>
                    <div class="content_login">
                        <div class="content_logintext">
                            <span class="content_pretext modal_open" data="joinModal">회원가입</span>
                            <span class="content_middletext">｜</span>
                            <span class="content_lasttext">비밀번호찾기</span>
                        </div>
                        <div class="content_socials">
                            <a href="${google_url }"><img class="content_socialicon" data="google" src="images/main/google.png"></a>
                            <img class="content_socialicon" data="facebook" src="images/main/facebook.png">
                            <a href="#"><img class="content_socialicon" data="naver" src="images/main/naver.png"></a>
                            <img class="content_socialicon" data="kakao" src="images/main/kakao.png">
                        </div>
                    </div>
                </div>

            </div>



            <div class="content_bottom">
                <div class="content_point">
                    <img src="/images/main/tourist.png">
                    <p>진행중인 스터디</p>
                </div>
                <div class="content_allview">
                    <button class="btn btn-danger">전체보기</button>
                </div>
                <div class="content_container content_first content_marginfix">
                    <div class="content_titletext content_meettitle test1">
                        <div class="content_date">
                            <p class="content_month">12월</p>
                            <p class="content_day">5일</p>
                        </div>
                    </div>
                    <div class="content_meetbody">
                        <p class="content_meetsubject">씨샾 스터디 개발자 모임</p>
                        <div class="content_meetinfo">
                            <img class="rounded-circle" src="/images/main/kakao-2.png">
                            <div class="content_groupleader">
                                <span>그룹장 : </span> <span>복실이</span>
                            </div>
                            <div class="content_groupname">
                                <span>그룹명 : </span> <span>씨샾시바</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="content_container content_marginfix">
                    <div class="content_titletext content_meettitle test2">
                        <div class="content_date">
                            <p class="content_month">12월</p>
                            <p class="content_day">13일</p>
                        </div>
                    </div>
                    <div class="content_meetbody">
                        <p class="content_meetsubject">영어 스터디 정기 모임</p>
                        <div class="content_meetinfo">
                            <img class="rounded-circle" src="/images/main/kakao-2.png">
                            <div class="content_groupleader">
                                <span>그룹장 : </span> <span>복실이</span>
                            </div>
                            <div class="content_groupname">
                                <span>그룹명 : </span> <span>영어마스터즈</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="content_container content_marginfix">
                    <div class="content_titletext content_meettitle test3">
                        <div class="content_date">
                            <p class="content_month">12월</p>
                            <p class="content_day">18일</p>
                        </div>
                    </div>
                    <div class="content_meetbody">
                        <p class="content_meetsubject">오라클 스터디 개발자 모임</p>
                        <div class="content_meetinfo">
                            <img class="rounded-circle" src="/images/main/kakao-2.png">
                            <div class="content_groupleader">
                                <span>그룹장 : </span> <span>복실이</span>
                            </div>
                            <div class="content_groupname">
                                <span>그룹명 : </span> <span>오라클시바</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="content_container content_marginfix">
                    <div class="content_titletext content_meettitle test4">
                        <div class="content_date">
                            <p class="content_month">12월</p>
                            <p class="content_day">24일</p>
                        </div>
                    </div>
                    <div class="content_meetbody">
                        <p class="content_meetsubject">자바 스터디 개발자 모임</p>
                        <div class="content_meetinfo">
                            <img class="rounded-circle" src="/images/main/kakao-2.png">
                            <div class="content_groupleader">
                                <span>그룹장 : </span> <span>복실이</span>
                            </div>
                            <div class="content_groupname">
                                <span>그룹명 : </span> <span>자바시바</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <!-- 소셜 데이터 폼 -->
        <form id="socialForm" method="POST" action="<c:url value='/login/facebookSignInCallback'/>">
            <input type="hidden" name="sId">
            <input type="hidden" name="eMail">
        </form>


        <div class="clear-fix"></div>

        <div class="layout-footer">
            <div class="footer_left">
                <img class="footer_logo" src="/images/main/studysiba-logo.png">
                <p class="footer_name">STUDY SIBA</p>
                <img class="footer_line" src="/images/main/footer-line.png">
            </div>
            <div class="footer_center">
                <p>Copyright © 2018 by bytrustu. Some Rights Reserved.</p>
            </div>
            <div class="footer_right">
                <i id="mail" class="fas fa-envelope footer-icons"></i>
                <i id="fb" class="fab fa-facebook-square footer-icons"></i>
                <i id="git" class="fab fa-github footer-icons"></i>
            </div>
        </div>
    </div>




    <!--
                [   모달 창 영역   ]
-->

    <!-- 로그인 모달 -->
    <div class="modal fade loginmodal_warp" id="loginModal">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-body loginmodal_body">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <img class="rounded-circle loginmodal_image" src="/images/main/siba_login.gif">
                    <p class="loginmodal_title">들어와..시바</p>
                    <form method="POST" action="/member/login" id="loginForm">
                        <input class="loginmodal_loginid" type="text" name="id" placeholder="아이디 입력">
                        <input class="loginmodal_loginpass" type="password" name="pass" placeholder="비밀번호 입력">
                        <button class="btn btn-primary loginmodal_loginbtn" type="submit">로그인</button>
                        <div class="content_modalsocialwarp">
                            <a href="${google_url }"><img class="content_socialicon content_modalsocial" data="google" src="images/main/google.png"></a>
                            <img class="content_socialicon" data="facebook" src="images/main/facebook.png">
                            <a href="#"><img class="content_socialicon" data="naver" src="images/main/naver.png"></a>
                            <img class="content_socialicon" data="kakao" src="images/main/kakao.png">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- 회원가입 모달 -->
    <div class="modal fade joinmodal_warp" id="joinModal">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-body joinmodal_body">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <img class="joinmodal_choose" src="/images/main/rotate.png">
                    <img class="rounded-circle joinmodal_image" name="proFile" src="">
                    <p class="joinmodal_title">올꺼지..시바</p>
                    <form id="joinForm" method="POST" action="<c:url value='/member/join' />">
                    	<input type="hidden" name="proFile">
                        <div>
                            <input class="joinmodal_joinid social_input" type="text" name="id" id="socialJoinId" placeholder="아이디 입력">
                            <div class="btn btn-warning validation_false" id="idChecker" data="false">미입력</div>
                        </div>
                        <div>
                            <input class="joinmodal_joinpass social_input validation_readonly" type="password" name="pass" id="socialJoinPass" placeholder="비밀번호 입력" readonly="readonly">
                            <div class="btn btn-warning validation_false" id="passChecker" data="false">미입력</div>
                        </div>
                        <div>
                            <input class="joinmodal_joinid social_input validation_readonly" type="text" name="nick" id="socialJoinNick" placeholder="닉네임 입력" readonly="readonly">
                            <div class="btn btn-warning validation_false" id="nickChecker" data="false">미입력</div>
                        </div>
                        <div>
                            <input class="joinmodal_joinid social_input validation_readonly" type="email" name="eMail" id="socialJoinEmail" placeholder="EXAMPLE@GMAIL.COM" readonly="readonly">
                            <div class="btn btn-warning validation_false" id="eMailChecker" data="false">미입력</div>
                        </div>
                        <div>
                            <button class="btn btn-danger joinmodal_joinbtn" type="button">회원가입</button>
                            <div class="btn btn-danger validation_final" data="false">불가</div>
                        </div>
                        <input type="hidden" id="joinType" value="normal">
                    </form>
                </div>
            </div>
        </div>
    </div>

</body>

</html>
