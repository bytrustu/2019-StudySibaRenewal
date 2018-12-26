<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="content_subject">
    <div class="content_subjectleft">
        <img src="/images/sub/search.png">
        <span>스터디그룹</span>
    </div>
</div>

<div class="content_group">

	<c:forEach items="${list }" var="list">
	
    <div class="form-row contentgroup_list">
        <div class="form-gorup col-sm-3 contentgroup_groupimg">
            <img src="/local_upload/study/${list.fileName }">
        </div>
        <div class="form-group col-sm-2 contentgroup_divide">
        	<div class="form-row">
	            <img src="/images/sub/books.png">
	            <span>${list.divide }</span>
        	</div>
        	<div class="form-row">
	            <img src="/images/sub/startup.png">
	            <span>${list.gName }</span>
        	</div>
        </div>
        <div class="form-group col-sm-4 contentgroup_info">
            <div class="form-row">
                <img src="/images/sub/global.png">
                <span>${list.address }</span>
            </div>
            <div class="form-row">
                <img src="/images/sub/calendar.png">
                <span>${list.toPer } ~ ${list.fromPer }</span>
            </div>
            <div class="form-row">
                <img src="/images/sub/circular-clock.png">
                <span>${list.toTime } ~ ${list.fromTime }</span>
            </div>
        </div>
        <div class="<form-group></form-group> col-sm-2 contentgroup_joiner">
            <img src="/images/sub/networking.png">
            <span>${list.count }명 / 
            <c:if test="${list.person eq '99' }">
            	무제한
            </c:if>
            <c:if test="${list.person ne '99' }">
            	${list.person }명
            </c:if>  
            </span>
        </div>
    </div>
    
    </c:forEach>
    
    <div class="content_pagenation ">
        <ul class="pagination">
            <c:if test="${page.startPage > page.pageBlock }">
                <li class="page-item"><a class="page-link" href="/group/list?pageNum=${page.startPage-1 }">이전</a></li>
            </c:if>
            <c:forEach var="i" begin="${page.startPage }" end="${page.endPage }" step="1">
                <c:if test="${page.pageNum eq i }">
                    <li class="page-item active"><a class="page-link" href="/group/list?pageNum=${i }">${i }</a></li>
                </c:if>
                <c:if test="${page.pageNum ne i }">
                    <li class="page-item"><a class="page-link" href="/group/list?pageNum=${i }">${i }</a></li>
                </c:if>
            </c:forEach>
            <c:if test="${page.endPage < page.pageCount }">
                <li class="page-item"><a class="page-link" href="/group/list?pageNum=${page.startPage+page.pageBlock }">다음</a></li>
            </c:if>
        </ul>
    </div>

</div>
