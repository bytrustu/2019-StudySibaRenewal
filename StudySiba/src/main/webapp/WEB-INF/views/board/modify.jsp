<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>

<script>
    $(document).ready(function(){
	$('#summernote').summernote({
	    placeholder: '',
	    tabsize: 100,
	    height: 400,
	    width : 820
	  });
});
</script>

<div class="content_subject">
    <div class="content_subjectleft">
        <img src="/images/sub/studying.png"> <span>자유게시판</span>
    </div>
</div>

<div class="clear-fix"></div>

<div class="content_body">
    <div class="board_wrtiewarp">
        <div class="board_writesubject">
            <div class="board_writesubtop">
                <span>제목</span> <input type="text" id="board_subjecttext" value="${view.title }">
            </div>
            <div class="board_writesubbottom">
                <span>욕설, 도배, 비방 루머등 운영정책에 어긋나는 게시물 등록시에는 이용 제한 등 불이익을 받으실 수
                    있습니다.</span>
            </div>
        </div>

        <div id="summernote">
            ${view.content }
        </div>
        <input type="hidden" class="board_contenttext">
        <input type="hidden" id="view_no" value="${view.no }">
        <input type="hidden" id="view_gNo" value="${view.gNo }">
        <input type="hidden" id="view_step" value="${view.step }">
        <input type="hidden" id="view_indent" value="${view.indent }">

        <button class="btn btn-danger boardBtn" id="board_wrtiecancel" data="board_cancel">취소</button>
        <button class="btn btn-primary boardBtn" id="board_modifybtn" data="board_modify">확인</button>
    </div>
</div>
