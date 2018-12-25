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
                        <img src="/images/sub/refresh.png">
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
                <li class="page-item"><a class="page-link" href="/study/search?pageNum=${page.startPage-1 }&searchType=${search.searchType}&searchText=${search.searchText}">이전</a></li>
            </c:if>
            <c:forEach var="i" begin="${page.startPage }" end="${page.endPage }" step="1">
                <c:if test="${page.pageNum eq i }">
                    <li class="page-item active"><a class="page-link" href="/study/search?pageNum=${i }&searchType=${search.searchType}&searchText=${search.searchText}">${i }</a></li>
                </c:if>
                <c:if test="${page.pageNum ne i }">
                    <li class="page-item"><a class="page-link" href="/study/search?pageNum=${i }&searchType=${search.searchType}&searchText=${search.searchText}">${i }</a></li>
                </c:if>
            </c:forEach>
            <c:if test="${page.endPage < page.pageCount }">
                <li class="page-item"><a class="page-link" href="/study/search?pageNum=${page.startPage+page.pageBlock }&searchType=${search.searchType}&searchText=${search.searchText}">다음</a></li>
            </c:if>
            </ul>
        </div>



    </div>

</div>






<div class="modal fade" id="studyModal" tabindex="-1" role="dialog" aria-labelledby="modal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content study_modallayout">
            <div class="modal-header">
                <img src="/images/sub/study_modal.png" class="study_modalimg">
                <h4 class="modal-title study_modaltitle">스터디 등록</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body study_modalbody">
                <form method="POST" action="/study/write" enctype="multipart/form-data" id="studyForm">
                    <div class="study_bodytop">
                        <div class="bodytop_inputs">

                            <div class="form-row">
                                <div class="form-group col-sm-4">
                                    <label>분류</label>
                                    <select name="divide" class="form-control">
                                        <option value="프로그래밍">프로그래밍</option>
                                        <option value="외국어">외국어</option>
                                        <option value="자격증">자격증</option>
                                        <option value="취미">취미</option>
                                        <option value="자기계발">자기계발</option>
                                        <option value="기타">기타</option>
                                    </select>
                                </div>
                                <div class="form-group col-sm-8">
                                    <label>스터디명</label>
                                    <input type="text" class="form-control inputs_gName" name="gName">
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-sm-4">
                                    <label>지역</label>
                                    <select name="area" class="form-control">
                                        <option value="서울">서울</option>
                                        <option value="경기">경기</option>
                                        <option value="강원">강원</option>
                                        <option value="충북">충북</option>
                                        <option value="전남">전남</option>
                                        <option value="경북">경북</option>
                                        <option value="경남">경남</option>
                                        <option value="부산">부산</option>
                                        <option value="제주">제주</option>
                                        <option value="기타">기타</option>
                                    </select>
                                </div>
                                <div class="form-group col-sm-4">
                                    <label>시작일자</label>
                                    <input type="text" class="form-control datepickter inputs_toPer" name="toPer">
                                </div>
                                <div class="form-group col-sm-4">
                                    <label>종료일자</label>
                                    <input type="text" class="form-control datepickter inputs_fromPer" name="fromPer">
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-sm-4">
                                    <label>참여인원</label>
                                    <select name="person" class="form-control">
                                        <option value="1">1명</option>
                                        <option value="2">2명</option>
                                        <option value="3">3명</option>
                                        <option value="4">4명</option>
                                        <option value="5">5명</option>
                                        <option value="5">6명</option>
                                        <option value="5">7명</option>
                                        <option value="5">8명</option>
                                        <option value="5">9명</option>
                                        <option value="99">제한없음</option>
                                    </select>
                                </div>
                                <div class="form-group col-sm-4">
                                    <label>시작시간</label>
                                    <select name="toTime" class="form-control">
                                        <c:forEach begin="1" end="24" step="1" var="i">
                                            <c:if test="${i < 10 }">
                                                <option value="0${i }:00">0${i }:00</option>
                                            </c:if>
                                            <c:if test="${i >= 10 }">
                                                <option value="${i }:00">${i }:00</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group col-sm-4">
                                    <label>종료시간</label>
                                    <select name="fromTime" class="form-control">
                                        <c:forEach begin="1" end="24" step="1" var="i">
                                            <c:if test="${i < 10 }">
                                                <option value="0${i }:00">0${i }:00</option>
                                            </c:if>
                                            <c:if test="${i >= 10 }">
                                                <option value="${i }:00">${i }:00</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-sm-9">
                                    <label>
                                        그룹사진
                                    </label>
                                    <input type="file" class="inputs_file" name="file">
                                </div>
                            </div>
                            <!-- end bodytop_inputs -->
                        </div>

                        <div class="bodytop_locate">
                            <div class="form-row">
                                <div class="form-group col-sm-4">
                                    <label>위도</label>
                                    <input type="text" class="form-control inputs_lat" name="lat" readonly="true" style="background-color: #ffffff;">
                                </div>
                                <div class="form-group col-sm-4">
                                    <label>경도</label>
                                    <input type="text" class="form-control inputs_lng" name="lng" readonly="true" style="background-color: #ffffff;">
                                </div>
                            </div>
                            <input type="text" class="form-control" id="pac-input" name="address" style="width: 192px;">
                            <div id="map"></div>

                        </div>

                        <!-- end study_bodytop -->
                    </div>

                    <div class="study_bodybottom">

                        <div class="form-row mt-sm-2 mb-sm-2">
                            <label>세부사항</label>
                            <input type="text" class="form-control inputs_title" name="title" class="form-control">
                        </div>
                        <div class="form-row">
                            <div id="study_contenttext"></div>
                            <input type="hidden" class="study_contenttext" name="content">
                        </div>

                    </div>
                </form>
                <!-- end study_modalbody -->
            </div>

            <div class="modal-footer">
                <button class="btn btn-primary boardBtn" data="study_register">등록</button>
            </div>
        </div>
    </div>
</div>
