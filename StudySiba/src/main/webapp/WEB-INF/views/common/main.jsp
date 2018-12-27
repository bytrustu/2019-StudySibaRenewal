<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>

<head>
    <meta charset="UTF-8">
    <title>스터디시바 : 온라인 스터디 그룹</title>

    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@7.32.2/dist/sweetalert2.min.css">
    <link rel="stylesheet" href="/css/messenger.css">
    <link rel="stylesheet" href="/css/main.css">

    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.32.2/dist/sweetalert2.min.js"></script>
    <script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
    <script src="/js/sociallogin.js"></script>
    <script src="/js/main.js"></script>
    <script src="/js/notice.js"></script>

    <!--	소셜 세션	-->
    <c:if test="${sessionScope.socialInfo ne null }">
        <c:remove var="socialInfo" scope="session" />
    </c:if>
    <!--	메세지 세션	-->
    <c:if test="${sessionScope.message ne null}">
        <script>
            $(document).ready(function(){
        	sessionMessage();
    		function sessionMessage(){
    			var message = "${sessionScope.message}";
    			if ( message != '' ) {
    				Swal({
    					  position: 'top-end',
    					  type: 'success',
    					  title: message,
    					  showConfirmButton: false,
    					  timer: 1500
    					})
    			}
    		}
        });
        </script>
        <c:remove var="message" scope="session" />
    </c:if>

    <!--	에러 세션	-->
    <c:if test="${sessionScope.error ne null}">
        <script>
            $(document).ready(function(){
        	sessionError();
        	function sessionError(){
        		var error = "${sessionScope.error}";
        		if ( error != '' ) {
        			Swal({
  					  position: 'top-end',
  					  type: 'error',
  					  title: error,
  					  showConfirmButton: false,
  					  timer: 1500
  					})
        		}
        	}
        });
        <c:remove var="error" scope="session" />
        </script>
    </c:if>
    
    <!-- 접속현황 -->
    <c:if test="${sessionScope.userSession ne null }">
    	<script>
    		$(document).ready(function(){
    			statusConnect();
    		});
    	</script>
    </c:if>
    <c:if test="${sessionScope.userSession eq null }">
    	<script>
    		$(document).ready(function(){
    			clearInterval(connectRepeat);
    		});
    	</script>
    </c:if>


</head>

<body>

    <div class="warp">

        <div class="layout-topheader">
        </div>

        <nav class="navbar navbar-expand-sm navbar-dark fixed-top layout-menu">
            <!-- Brand -->
            <a class="navbar-brand menu-title" href='<c:url value="/"/>'>
                <span class="menu-pre">스터디</span><span class="menu-last">시바</span>
            </a>
            <!-- Links -->
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href='<c:url value="/"/>'>
                        <i class="fa fa-home menu-icon"></i>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href='<c:url value="/board/list"/>'>
                        <i class="fa fa-edit menu-icon"></i>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href='<c:url value="/study/list"/>'>
                        <i class="fas fa-book menu-icon"></i>
                    </a>
                </li>
                
                    <li class="nav-item">
                        <a class="nav-link" href='<c:url value="/group/list"/>'>
                            <i class="fa fa-user-plus menu-icon"></i>
                        </a>
                    </li>
                    <c:if test="${userSession ne null }">
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <i class="fa fa-envelope menu-icon modal_open" data=messageModal></i>
                            <div class="message_counter"></div>
                        </a>
                    </li>
                </c:if>
            </ul>

            <ul class="nav navbar-nav menu-infobtn">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle menu-dropicon" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expaneded="false">
                        <i class="fa fa-cog fa-spin fa-fw menu-icon"></i>
                    </a>
                    <div class="dropdown-menu menu-dropmenu">
                        <c:choose>
                            <c:when test="${sessionScope.userSession eq null }">
                                <a class="dropdown-item modal_open modal_menu" data="loginModal" href="#">로그인</a>
                                <a class="dropdown-item modal_open modal_menu" data="joinModal" href="#">회원가입</a>
                            </c:when>
                            <c:otherwise>
                                <a class="dropdown-item modal_open modal_menu" data="modifyModal" href="#">정보수정</a>
                                <a class="dropdown-item modal_open modal_menu" data="passModal" href="#">비밀번호변경</a>
                                <a class="dropdown-item modal_open modal_menu" data="logoutModal" href="<c:url value='/member/logout'/>">로그아웃</a>
                                <a class="dropdown-item modal_open modal_menu" data="secessionModal" href="#">회원탈퇴</a>
                            </c:otherwise>
                        </c:choose>
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
                    <img class="content_mainimage" src="/images/main/background_siba.png">
                </div>
            </div>

            <div class="content_bottom">

                <div class="content_container content_first">
                    <div class="content_titletext">
                        <img class="content_icon" src="/images/main/catalog.png">
                        <span class="content_toptext">사이트 현황</span>
                    </div>
                    <div class="content_body">
                    	<div id="chart_div" style="width: 280px; height: 200px;"></div>
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

						<c:forEach items="${like }" var="like" varStatus="i">
                        	<div class="content_rank" data="${like.no }">
                            	<img class="content_ranknum" src="/images/main/rank${i.count }.png">
                            	<img class="content_rankimage" src="/local_upload/profile/${like.proFile }">
                            	<img class="content_like" src="/images/main/like.png">
                            	<span class="content_liketext">${like.count }</span>
                            	<span class="content_boardtitle">${like.title }</span>
                        	</div>
                        </c:forEach>


                    </div>
                </div>

                <div class="content_container">
                    <div class="content_titletext">
                        <img class="content_icon" src="/images/main/location.png">
                        <span class="content_toptext">접속중 회원</span>
                        <span class="content_toptext content_textmiddle">${fn:length(connect) }</span>
                        <span class="content_toptext">명</span>
                    </div>
                    <div class="content_connectbody">
                    
                    	<c:forEach items="${connect }" var="connect">
                        	<div class="content_connect">
                            	<img class="content_user" src="/local_upload/profile/${connect.proFile }">
                            	<span>${connect.nick }</span>
                            	<c:if test="${sessionScope.userSession ne null }">
	                            	<img class="content_connecticon modal_open" id="friendBtn" src="/images/main/friendship.png" data="messageModal">
	                            	<img class="content_connecticon modal_open" id="messageBtn" src="/images/main/mail.png" data="messageModal">
                            	</c:if>
                        	</div>
                        </c:forEach>

                    </div>
                </div>

                <c:choose>
                    <c:when test="${sessionScope.userSession eq null }">
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
                                    <a href="${google_url }"><img class="content_socialicon" data="google" src="/images/main/google.png"></a>
                                    <img class="content_socialicon" data="facebook" src="/images/main/facebook.png">
                                    <a href="#"><img class="content_socialicon" data="naver" src="/images/main/naver.png"></a>
                                    <img class="content_socialicon" data="kakao" src="/images/main/kakao.png">
                                </div>
                            </div>
                        </div>
                    </c:when>

                    <c:when test="${sessionScope.userSession ne null }">
                        <div class="content_container">
                            <div class="logininfo_top">
                                <div class="logininfo_profile">
                                    <img class="logininfo_image" src="/local_upload/profile/${sessionScope.userSession.proFile }">
                                    <span id="logininfo_nick">${sessionScope.userSession.nick }</span>
                                    <span>님</span>
                                </div>
                                <div class="logininfo_connect">
                                    <span>최종접속시간 : </span>
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
                            <div class="logininfo_middle">
                                <div class="logininfo_details">
                                    <img src="/images/main/login_visit.png">
                                    <span>출석수</span>
                                    <span>${sessionScope.userSession.visitCount }</span>
                                    <span>회</span>
                                </div>
                                <div class="logininfo_details">
                                    <img src="/images/main/login_boardwrite.png">
                                    <span>게시글 작성수</span>
                                    <span>${sessionScope.userSession.boardCount }</span>
                                    <span>회</span>
                                </div>
                                <div class="logininfo_details">
                                    <img src="/images/main/login_commentwrite.png">
                                    <span>댓글 작성수</span>
                                    <span>${sessionScope.userSession.commentCount }</span>
                                    <span>회</span>
                                </div>
                            </div>
                            <div class="logininfo_bottom">
                                <div class="logininfo_mywrite">
                                    <a href="#">내가 쓴 글</a>
                                </div>
                                <div class="logininfo_myinfo">
                                    <a class="modal_open" data="modifyModal" href="#">정보 수정</a>
                                </div>
                            </div>
                        </div>
                    </c:when>
                </c:choose>

            </div>



            <div class="content_bottom">
                <div class="content_point">
                    <img src="/images/main/tourist.png">
                    <p>진행중인 스터디</p>
                </div>
                <div class="content_allview">
                    <button class="btn btn-danger main_moveStudy">전체보기</button>
                </div>
                
                <c:forEach items="${study }" var="study" varStatus="i">
                	
                		<div class="content_container 
                			<c:if test="${i.count eq '1' }">
                				content_first 
                			</c:if>
                		content_marginfix main_studylist" data="${study.no }">
                	
	                    <div class="content_titletext content_meettitle" style="background-image: url('/local_upload/study/${study.fileName}');background-size: cover;">
	                        <div class="content_date">
	                            <p class="content_month">${study.rDate }</p>
	                            <p class="content_day">${study.sDate }</p>
	                        </div>
	                    </div>
	                    <div class="content_meetbody">
	                        <p class="content_meetsubject">${study.title }</p>
	                        <div class="content_meetinfo">
	                            <img class="rounded-circle" src="/local_upload/profile/${study.proFile }">
	                            <div class="content_groupleader">
	                                <span>그룹장 : </span> <span>${study.nick }</span>
	                            </div>
	                            <div class="content_groupname">
	                                <span>그룹명 : </span> <span>${study.gName }</span>
	                            </div>
	                        </div>
	                    </div>
	                </div>
                </c:forEach>

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
                <p>본 웹사이트는 개인 프로젝트용 데모 사이트 입니다.</p>
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
        <div class="modal-dialog modal-dialog-centered modal-margin">
            <div class="modal-content loginmodal_resize">
                <div class="modal-body loginmodal_body">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <img class="rounded-circle loginmodal_image" src="/images/main/siba_login.gif">
                    <p class="loginmodal_title">들어와..시바</p>
                    <form method="POST" action="/member/login" id="loginForm">
                        <input class="loginmodal_loginid" type="text" name="id" placeholder="아이디 입력">
                        <input class="loginmodal_loginpass" type="password" name="pass" placeholder="비밀번호 입력">
                        <button class="btn btn-primary loginmodal_loginbtn" type="submit">로그인</button>
                        <div class="content_modalsocialwarp">
                            <a href="${google_url }"><img class="content_socialicon content_modalsocial" data="google" src="/images/main/google.png"></a>
                            <img class="content_socialicon" data="facebook" src="/images/main/facebook.png">
                            <a href="#"><img class="content_socialicon" data="naver" src="/images/main/naver.png"></a>
                            <img class="content_socialicon" data="kakao" src="/images/main/kakao.png">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- 회원가입 모달 -->
    <div class="modal fade joinmodal_warp" id="joinModal">
        <div class="modal-dialog modal-dialog-centered modal-margin">
            <div class="modal-content loginmodal_resize">
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

    <!-- 정보수정 모달 -->
    <div class="modal fade modifymodal_warp" id="modifyModal">
        <div class="modal-dialog modal-dialog-centered modifymodal_retop">
            <div class="modal-content modifymodal_resize">
                <div class="modal-body loginmodal_body">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <img class="rounded-circle loginmodal_image" id="profileImage" src="/local_upload/profile/${sessionScope.userSession.proFile }">
                    <p class="modifymodal_title">변경할 사진을 원안으로 넣어 주세요</p>
                    <form method="POST" id="nickForm" action="<c:url value='/member/changeNick'/>">
                        <input type="hidden" name="id" value="${sessionScope.userSession.id }">
                        <input type="hidden" name="type" value="nick">
                        <input class="modifymodal_nick" type="text" name="nick" maxlength="12" placeholder="닉네임 입력">
                        <button class="btn btn-primary modifymodal_modifybtn" type="button">변경</button>
                    </form>
                </div>
            </div>
        </div>
    </div>


    <!-- 메신저 모달 -->
    <div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-labelledby="modal" aria-hidden="true">
        <div class="modal-dialog" id="message_modal_layout">
            <div class="modal-content" style="background-color: transparent; border: 1px solid transparent">
                <div class="modal-body">
                    <div id="message_container">
                        <div style="width: 520px">
                            <!-- 메세지 목록 부분 -->
                            <div class="card mr-2 float-left" style="width: 125px;">
                                <div class="card-header bg-custom text-white" id="message_listtitle">
                                    <span>목록</span>
                                </div>
                                <div class="card-body" id="message_list">

                                </div>

                                <div class="card-footer" id="memberList_footer" ondragover="return false;" ondragenter="return false;" ondrop="drop(this, event);">
                                    <div class="message_waste"></div>
                                </div>
                            </div>
                            <!-- 메세지함 부분 -->
                            <div class="card">
                                <div class="card-header bg-custom text-white" id="message_title">
                                    <div class="message_userInfo">
                                        <!-- <img class="rounded-circle message_userimage" src="/images/profile/kakao/kakao-5.png"> -->
                                        <span id="message_title_text">메세지시바</span>
                                    </div>
                                    <div class="message_functionbtn">
                                        <img src="/images/main/find-my-friend.png" class="modal_open" data="searchModal">
                                        <img src="/images/main/friendship.png" class="modal_open" data="friendModal">
                                    </div>
                                </div>

                                <div class="card-body" id="message_body">

                                </div>
                                <div class="card-footer" id="message_footer">
                                    <input type="text" class="messenger_input" id="message_input" data="message">
                                    <button class="btn btn-warning messenger_btn" id="message_btn" data="message">전송</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 닉네임 검색 모달 -->
    <div class="modal fade" id="searchModal" tabindex="-1" role="dialog" aria-labelledby="modal" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content col-sm-8">
                <div class="modal-header">
                    <h4 class="modal-title" id="modal" style="font-weight: bold;">닉네임 검색</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>닉네임</label> <input type="text" id="search_text" class="form-control messenger_input" maxlength="15" data="search">
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-danger messenger_btn" id="message_search" data-dismiss="modal" aria-label="Close" data="search">검색</button>
                        <button class="btn btn-default" type="button" data-dismiss="modal" aria-label="Close">취소</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 친구신청 모달 -->
    <div class="modal fade" id="friendModal" tabindex="-1" role="dialog" aria-labelledby="modal" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content col-sm-8">
                <div class="modal-header">
                    <h4 class="modal-title" id="modal" style="font-weight: bold;">친구 추가</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>닉네임</label> <input type="text" name="searchText" id="friend_text" class="form-control messenger_input" maxlength="15" data="friend">
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-danger friend_apply messenger_btn" id="message_friend" data-dismiss="modal" aria-label="Close" data="friend">신청</button>
                        <button class="btn btn-default" data-dismiss="modal" aria-label="Close">취소</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 비밀번호변경 모달 -->
    <div class="modal fade" id="passModal" tabindex="-1" role="dialog" aria-labelledby="modal" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content col-sm-8 passmodal_locate">
                <div class="modal-header">
                    <h4 class="modal-title" id="modal" style="font-weight: bold;">비밀번호 변경</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>현재비밀번호</label> <input type="password" name="currPassword" id="currPassword" class="form-control messenger_input" maxlength="15" data="currPass">
                    </div>
                    <div class="form-group">
                        <label>변경비밀번호</label> <input type="password" name="changePassword" id="changePassword" class="form-control messenger_input" maxlength="15" data="changePass">
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary messenger_btn" id="pass_btn" type="button" aria-label="Close" data="changePass">변경</button>
                        <button class="btn btn-danger" data-dismiss="modal" aria-label="Close">취소</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    
    
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawVisualization);

      function drawVisualization() {
        // Some raw data (not necessarily accurate)
        /* var data = google.visualization.arrayToDataTable([
          ['일자', '방문자수', '가입자수'],
          ['2004/05',  165,      938],
          ['2005/06',  135,      1120],
          ['2006/07',  157,      1167],
          ['2007/08',  139,      1110],
          ['2008/09',  136,      691]
        ]); */
        
        var data = new google.visualization.DataTable();
        data.addColumn('date', '날짜'); 
        data.addColumn('number', '방문자수');
        data.addColumn('number', '가입자수');
        data.addRows(7);
        $.ajax({
        	type : 'POST',
        	url : 'getTotalList',
        	dataType : 'json',
        	async : false,
        	success : function(response){
        		$.each(response.result, function(index,item){
        			data.setCell(index,0,new Date(item.date));
        			data.setCell(index,1,item.vCount);
            		data.setCell(index,2,item.mCount);
        		});
        		var options = {
        		 seriesType: 'bars',
        		 chartArea:{left:10,top:20,width:"100%",height:"100%"},
        		 series: {5: {type: 'line'}}
        		 };

        		 var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
        		 chart.draw(data, options);
        	}
        });

        
      }
    </script>

</body>

</html>
