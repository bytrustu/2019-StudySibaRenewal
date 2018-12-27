<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

</div>

<!-- 정보수정 모달 -->
<div class="modal fade modifymodal_warp" id="modifyModal">
    <div class="modal-dialog modal-dialog-centered modifymodal_retop">
        <div class="modal-content modifymodal_resize">
            <div class="modal-body loginmodal_body">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <img class="rounded-circle loginmodal_image" id="profileImage" src="/images/main/siba_login.gif">
                <p class="modifymodal_title">사진을 원안으로 넣어 주세요</p>
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


<!-- 스터디룸 모달 -->
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



<!-- 업로드 모달 -->
<div class="modal fade" id="uploadModal" tabindex="-1" role="dialog" aria-labelledby="modal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content col-sm-8">
            <div class="modal-header">
                <h4 class="modal-title" id="modal" style="font-weight: bold;">첨부파일</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="POST" action="/group/upload" id="groupUpload" enctype="multipart/form-data">
                    <div class="form-group">
                        <label>내용</label> <input type="text" name="comment" class="form-control" maxlength="15">
                    </div>
                    <div class="form-group">
                        <label>첨부파일</label> <input type="file" name="file" id="groupUploadFile" class="form-control messenger_input" maxlength="15" data="friend">
                    </div>
                    <div class="modal-footer">
                    	<input type="hidden" name="gNo" id="modal_gNo">
                        <button class="btn btn-danger boardBtn" data-dismiss="modal" aria-label="Close" data="groupUpload">등록</button>
                        <button class="btn btn-default" data-dismiss="modal" aria-label="Close">취소</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


</div>


</div>
</body>

</html>
