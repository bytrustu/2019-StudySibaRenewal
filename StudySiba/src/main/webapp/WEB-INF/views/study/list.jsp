<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css" rel="stylesheet">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="/js/googlemap.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyByjX-fIiEVgNTofLuWWpxgGqQADaoNSWk&libraries=places&callback=initAutocomplete" async defer></script>

<script>
    $(document).ready(function(){
		 $('#study_contenttext').summernote({
		    placeholder: '',
		    tabsize: 100,
		    height: 150,
		    width : 940
		  });
		 $.datepicker.setDefaults({
		        dateFormat: 'yy-mm-dd',
		        prevText: '이전 달',
		        nextText: '다음 달',
		        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
		        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
		        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
		        showMonthAfterYear: true,
		        yearSuffix: '년'
		    });
		 $('.datepickter').datepicker();
	});
</script>


<div class="content_subject">
    <div class="content_subjectleft">
        <img src="/images/sub/reading.png">
        <span>스터디룸</span>
    </div>
    <div class="content_subjectright">
        <form method="GET" action="/study/search">
            <select name="searchType" class="form-control board_searchselect">
                <option value="all">전체</option>
                <option value="programming">프로그래밍</option>
                <option value="development">자기계발</option>
                <option value="certificate">자격증</option>
                <option value="language">외국어</option>
                <option value="hobby">취미</option>
                <option value="job">취업</option>
                <option value="etc">기타</option>
            </select>
            <input class="board_searchtext board_searchtext" type="text" name="searchText" class="form-control" placeholder="내용을 입력하세요">
            <button class="btn btn-primary board_searchbtn">검색</button>
        </form>
        <button class="btn btn-warning board_writebtn modal_open" data="studyModal">글쓰기</button>
    </div>
</div>

<div class="clear-fix"></div>

<div class="content_body">

    <div class="study_listwarp">

        <c:forEach items="${list }" var="list">
            <div class="study_list">
                <div class="study_header">
                    <div class="study_divide">
                        <span>${list.divide }</span>
                    </div>

                    <div class="study_subject">
                        <span>${list.title }</span>
                        <span>(${list.nick })</span>
                    </div>

                    <div class="study_restats">
                        <img src="/images/sub/refresh-page-option.png">
                    </div>

                </div>
                <div class="study_body">
                    <div class="study_bodyleft">
                        <div>
                            <img src="/images/sub/startup.png">
                            <span>${list.gName }</span>
                        </div>
                        <div>
                            <img src="/images/sub/networking.png">
                            <span>최대인원</span>
                            <span>(</span>
                            <span>
                                <c:if test="${list.person eq '99' }">
                                    제한없음
                                </c:if>
                                <c:if test="${list.person ne '99' }">
                                    ${list.person }
                                </c:if>
                            </span>
                            <span>)</span>
                        </div>

                    </div>
                    <div class="study_bodycenter">
                        <div>
                            <img src="/images/sub/global.png">
                            <span>${list.address }</span>
                        </div>
                        <div>
                            <img src="/images/sub/cooperation.png">
                            <span>참여인원</span>
                            <span>(</span>
                            <span>${list.count }</span>
                            <span>)</span>
                        </div>
                    </div>
                    <div class="study_bodyright">
                        <div>
                            <img src="/images/sub/circular-clock.png">
                            <span>[기간] ${list.toPer }~${list.fromPer } &nbsp; [모임시간] ${list.toTime }~${list.fromTime }</span>
                        </div>
                        <div class="study_contentlink">
                            <a href="/study/view?no=${list.no }">[ 자세히보기 ]</a>
                        </div>
                    </div>
                    <div class="clear-fix"></div>
                </div>
            </div>
        </c:forEach>




        <div class="clear-fix"></div>
        <div class="content_pagenation ">
            <ul class="pagination">
                <c:if test="${page.startPage > page.pageBlock }">
                    <li class="page-item"><a class="page-link" href="/study/list?pageNum=${page.startPage-1 }">이전</a></li>
                </c:if>
                <c:forEach var="i" begin="${page.startPage }" end="${page.endPage }" step="1">
                    <c:if test="${page.pageNum eq i }">
                        <li class="page-item active"><a class="page-link" href="/study/list?pageNum=${i }">${i }</a></li>
                    </c:if>
                    <c:if test="${page.pageNum ne i }">
                        <li class="page-item"><a class="page-link" href="/study/list?pageNum=${i }">${i }</a></li>
                    </c:if>
                </c:forEach>
                <c:if test="${page.endPage < page.pageCount }">
                    <li class="page-item"><a class="page-link" href="/study/list?pageNum=${page.startPage+page.pageBlock }">다음</a></li>
                </c:if>
            </ul>
        </div>



    </div>
</div>