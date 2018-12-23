<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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
    <link rel="stylesheet" href="/css/sub.css">

    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.32.2/dist/sweetalert2.min.js"></script>
    <script src="/js/main.js"></script>

<!--	메세지 세션	-->
    <c:if test="${sessionScope.message ne null}">
        <script>
        $(document).ready(function(){
        	sessionMessage();
        	function sessionMessage(){
        		var message = "${sessionScope.message}";
        		if ( message != '' ) {
        			Swal('알림', message, 'success');
        		}
        	}
        });
        <c:remove var="message" scope="session" />
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
                <c:if test="${userSession ne null }">
                    <li class="nav-item">
                        <a class="nav-link" href='<c:url value="/group/list"/>'>
                            <i class="fa fa-user-plus menu-icon"></i>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <i class="fa fa-envelope menu-icon modal_open" data=messageModal></i>
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
                        <a class="dropdown-item modal_open modal_menu" data="loginModal" href="#">로그인</a>
                        <a class="dropdown-item modal_open modal_menu" data="joinModal" href="#">회원가입</a>
                        <a class="dropdown-item modal_open modal_menu" data="modifyModal" href="#">정보수정</a>
                        <a class="dropdown-item modal_open modal_menu" data="passModal" href="#">비밀번호변경</a>
                        <a class="dropdown-item modal_open modal_menu" data="logoutModal" href="<c:url value='/member/logout'/>">로그아웃</a>
                        <a class="dropdown-item modal_open modal_menu" data="secessionModal" href="#">회원탈퇴</a>
                    </div>
                </li>
            </ul>
        </nav>
        
        <div class="layout-body">


        	<div class="content">