<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
</div>


</div>
</body>

</html>
