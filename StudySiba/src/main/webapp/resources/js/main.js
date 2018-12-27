$(document).ready(function () {
    // 상단 아이콘 HOVER 색상 반응
    //iconDecoration();
    // 푸터 아이콘 클릭 처리
    footerIconClick();
    // 모달창 보이기
    modalShow();
    // 회원가입 이미지 변경 버튼 클릭시
    chooseBtn();
    // 회원가입 이미지 변경 로직
    chooseProfile();
    // 소셜 로그인 버튼 클릭
    socialLogin();
    // 회원가입 처리
    joinFunction();
    // 입력값 validation 체크
    inputValueCheck();
    // 모달종료시 액션
    closeJoinModal();
    // 닉네임변경
    nickCheck();
    // 드래그앤드롭 이미지
    drageImage();
    // 메신져 버튼 구분 처리
    messengerBtn();
    // 메세지 휴지통 드래그시 모션 처리
    deleteMotion();
    // 접속목록 버튼 클릭시
    connecticonBtn();
    // 접속시간 갱신
    //statusConnect();
    // 게시판에 따른 active 효과
    viewActive();
    // 우측 메뉴 링크 이동
    menuLink();
    // 게시판 별 버튼 구분
    boardBtn();
    // 자유게시판 제목 클릭시 이동
    boardSelect();
    // 자유게시판 좋아요
    likeFunc();
    // 코멘트 버튼 생성
    commentBtn();
    // 뷰 메신저 버튼 클릭시
    viewMessengerBtn();
    // 스터디룸 스터디장 버튼 클릭시
    groupLeaderBtn();
    // 스터디룸 참여 회원 클릭시
    groupJoinerBtn();
    // 스터디룸 참여 버튼 클릭시
    groupJoinBtnFunc();
    // 스터디그룹 목록 클릭시
    moveGroupView();
    // 스터디그룹 탈퇴
    groupOutBtn();
});



// selector 캐싱
var $c = function (qr, force) {
    if (!$c.dt) {
        $c.dt = {};
    }
    if (!$c.dt[qr] || force) {
        $c.dt[qr] = $(qr);
    }
    return $c.dt[qr];
};

/*
 * console.time('t1'); for (let i = 0; i < 100000; i++) { $c; }
 * console.timeEnd('t1');
 */

// 페이지 이동
function movePath(path) {
    location.href = path;
}

// 새창 페이지 이동
function moveNewPath(path) {
    window.open(path, '_blank');
}

// 상단 아이콘 HOVER 색상 반응
function iconDecoration() {
    $c('.menu-icon, .menu-dropicon, .menu-pre').hover(
        function () {
            $(this).css('color', 'white');
            if ($(this).attr('data') == 'messageModal') {
                $(this).removeClass('fa fa-envelope');
                $(this).addClass('fas fa-envelope-open');
            }
        },
        function () {
            $(this).css('color', '#9a9da0');
            if ($(this).attr('data') == 'messageModal') {
                $(this).removeClass('fas fa-envelope-open');
                $(this).addClass('fa fa-envelope');
            }
        });
}

// 푸터 아이콘 클릭 처리
function footerIconClick() {
    $c('.footer-icons').on('click', function () {
        var path = '';
        var value = $(this).attr('id');
        if (value == 'mail') {
            path = 'mailto:bytrustu@gmail.com';
        } else if (value == 'fb') {
            path = 'https://www.facebook.com/choddol';
        } else if (value == 'git') {
            path = 'https://github.com/bytrustu';
        }
        movePath(path);
    });
}

// 모달창 보이기
function modalShow() {
    $('body, nav, .modal').addClass('modal-nopadding');
    $('body, .modal').addClass('modal-nooverflow');
    $(".joinmodal_choose").css({
        "top": "-50%",
        "left": "290%"
    });
    $c('.modal_open').on('click', function () {
        var value = $(this).attr('data');
        $('#' + value).modal('show');
        if (value == 'joinModal') {
            $('.joinmodal_joinid').focus();
            swing();
        } else if (value == 'loginModal') {
            setTimeout(function () {
                $('.loginmodal_loginid').focus();
            }, 500);
        } else if (value == 'modifyModal') {
            setTimeout(function () {
                $('.modifymodal_nick').focus();
            }, 500);
        } else if (value == 'messageModal') {
            messengerOpen();
            getMessengerUserList();
        } else if (value == 'searchModal') {
            $('#search_text').val('');
            setTimeout(function () {
                $c('#search_text').focus();
            }, 500);
        } else if (value == 'friendModal') {
            setTimeout(function () {
                $c('#friend_text').focus();
            }, 500);
        } else if (value == 'passModal') {
            $c('#currPassword').val('');
            $c('#changePassword').val('');
            setTimeout(function () {
                $c('#currPassword').focus();
            }, 500);
        } else if (value == '"uploadModal"') {

        }
    });
}

// 모달종료시 액션
function closeJoinModal() {
    $('#joinModal').on('hidden.bs.modal, hide.bs.modal', function (e) {
        e.stopImmediatePropagation();
        $('.joinmodal_choose').stop();
        $('.joinmodal_choose').css({
            'top': '-50%',
            'left': '290%'
        });
    });
}

// 로그인 버튼
function loginBtn() {
    $c('.loginmodal_loginbtn').on('click', function () {
        $('#loginForm').submit();
    });
}


// 회원가입 이미지 변경 아이콘 움직임
function swing() {
    $c('.joinmodal_choose').animate({
        'top': '4%',
        'left': '57%'
    }, 600).animate({
        'top': '7%',
        'left': '52%'
    }, 600, swing);
}

// 소셜 회원가입 이미지 변경 아이콘 움직임
function socialSwing() {
    $c('.social_choose').animate({
        'top': '22%',
        'left': '51%'
    }, 600).animate({
        'top': '20%',
        'left': '52%'
    }, 600, socialSwing);
}

// 회원가입 이미지 변경 버튼 클릭시
function chooseBtn() {
    $c('.joinmodal_choose, .social_choose').on('click', function () {
        chooseProfile();
    });
}

// 회원가입 이미지 변경 로직
function chooseProfile() {
    var path = '/images/profile/kakao/'
    var result = Math.floor(Math.random() * 40) + 1;
    var profile = 'kakao-' + result + '.png';
    $c('.joinmodal_image').attr('src', path + profile);
    $('input[name=proFile]').val(profile);
}

// 소셜 로그인 버튼 클릭
function socialLogin() {
    $c('.content_socialicon').on('click', function () {
        var value = $(this).attr('data');

        if (value == 'google' || value == 'naver') {
            return;
        } else if (value == 'facebook') {
            FB.login(function (response) {
                checkFacebookLoginStatus(response);
            });
        } else if (value = 'kakao') {
            kakaoLogin();
        }
    });
}

// 모달 X버튼 클릭시
function socialClose() {
    $c('.social-close').on('click', function () {
        movePath("/");
    });
}

// 회원가입 처리
function joinFunction() {
    $c('.joinmodal_joinbtn').on('click', function () {

        var div = $(this).attr('data');
        if ($('.validation_final').attr('data') == 'false') {
            Swal({
                type: 'error',
                title: '회원가입실패',
                text: '입력사항을 확인 해주세요.'
            })
            return;
        }
        $('#joinForm').attr('action', '/member/join');
        $('#joinForm').submit();
    });
}

// 소셜 회원가입 입력값 확인
function inputValueCheck() {

    var exp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
    var idSelector = $('#idChecker');
    var passSelector = $('#passChecker');
    var nickSelector = $('#nickChecker');
    var eMailSelector = $('#eMailChecker');
    var passwordSelector = $('#passChecker');
    var passComfirmSelector = $('#passConfirmChecker');
    var idInput = $('#socialJoinId');
    var passInput = $('#socialJoinPass');
    var nickInput = $('#socialJoinNick');
    var eMailInput = $('#socialJoinEmail');

    $('#socialJoinId').focus();
    $('.social_input').keyup(function () {

        var inputName = $(this).attr('name');
        var inputLength = new Object();

        if ($(this).attr('name') == 'id') {
            inputLength.id = $('#socialJoinId').val().length;
        } else if ($(this).attr('name') == 'pass') {
            inputLength.pass = $('#socialJoinPass').val().length;
        } else if ($(this).attr('name') == 'nick') {
            inputLength.nick = $('#socialJoinNick').val().length;
        }


        if (inputName == 'id') {
            changeCheckView(inputName, 3, inputLength.id, idSelector);
        } else if (inputName == 'pass') {
            changeCheckView(inputName, 3, inputLength.pass, passSelector);
        } else if (inputName == 'nick') {
            changeCheckView(inputName, 0, inputLength.nick, nickSelector);
        } else if (inputName == 'eMail') {
            changeCheckView(inputName, 0, 0, eMailSelector);
        }

        function changeCheckView(type, index, length, selector) {
            if (type == 'eMail') {
                if (inputName == type && exp.test($('#socialJoinEmail').val()) == true && selector.attr('data') == 'false') {
                    changeTrueView(selector);
                } else if (inputName == type && exp.test($('#socialJoinEmail').val()) == false) {
                    changeFalseView(selector);
                }
            } else {
                if (inputName == type && length > index && (selector.attr('data') == 'false' || selector.attr('data') == 'true')) {
                    changeTrueView(selector);
                } else if (inputName == type && length <= index) {
                    changeFalseView(selector);
                }
            }
        }

    });

    if ($('#joinType').val() == 'normal') {
        idSelector.on('click', function () {
            checkRedu(idSelector, idInput, passInput, '아이디', 'id', $('#socialJoinId').val());
        });
        passSelector.on('click', function () {
            checkRedu(passSelector, passInput, nickInput, '비밀번호', 'pass', $('#socialJoinPass').val());
        });
        nickSelector.on('click', function () {
            checkRedu(nickSelector, nickInput, eMailInput, '닉네임', 'nick', $('#socialJoinNick').val());
        });
        eMailSelector.on('click', function () {
            checkRedu(eMailSelector, eMailInput, null, '이메일', 'eMail', $('#socialJoinEmail').val());
        });
    } else {
        idSelector.on('click', function () {
            checkRedu(idSelector, idInput, nickInput, '아이디', 'id', $('#socialJoinId').val());
        });
        nickSelector.on('click', function () {
            checkRedu(nickSelector, nickInput, eMailInput, '닉네임', 'nick', $('#socialJoinNick').val());
        });
        eMailSelector.on('click', function () {
            checkRedu(eMailSelector, eMailInput, null, '이메일', 'eMail', $('#socialJoinEmail').val());
        });
    }
}


// 확인버튼 : 중복확인
function changeTrueView(selector) {
    selector.removeClass('btn-warning');
    selector.addClass('btn-success');
    selector.attr('data', 'true');
    if (selector.attr('id') == 'passChecker') {
        selector.html('변경확인');
    } else {
        selector.html('중복확인');
    }
}

// 확인버튼 : 불가능
function changeFalseView(selector) {
    selector.removeClass('btn-success');
    selector.addClass('btn-warning');
    selector.attr('data', 'false');
    selector.html('불가능');
}

// 중복확인 로직
function checkRedu(btnSelector, preSeletor, lastSelector, text, type, value) {

    var result = '';
    if (type != 'pass') {
        result = validationCheck(type, value);
    } else {
        result = 'false';
    }

    if (result == 'false') {

        if (btnSelector.attr('data') == 'true') {
            Swal({
                title: '사용가능',
                text: text + '를 확정 하시겠습니까?',
                type: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: '네, 확인!',
                cancelButtonText: '아니요'
            }).then((result) => {
                if (result.value) {
                    Swal(
                        '승인',
                        text + ' 지정 되었습니다.',
                        'success'
                    )
                    btnSelector.attr('data', 'success');
                    btnSelector.removeClass('btn-success');
                    btnSelector.addClass('btn-primary');
                    btnSelector.html('승인');
                    preSeletor.addClass('validation_readonly');
                    preSeletor.attr('readOnly', 'true');
                    if (lastSelector != null) {
                        lastSelector.removeAttr('readOnly');
                        lastSelector.removeClass('validation_readonly');
                        lastSelector.focus();
                    }

                    if ($c('#joinType').val() == 'facebook') {
                        if ($('#idChecker').attr('data') == 'success' && $('#nickChecker').attr('data') == 'success') {
                            final();
                        }
                    } else if ($c('#joinType').val() == 'google' || $c('#joinType').val() == 'kakao') {
                        if ($('#idChecker').attr('data') == 'success' && $('#nickChecker').attr('data') == 'success' && $('#eMailChecker').attr('data') == 'success') {
                            final();
                        }
                    } else if ($c('#joinType').val() == 'normal') {
                        if ($('#idChecker').attr('data') == 'success' && $('#passChecker').attr('data') == 'success' && $('#nickChecker').attr('data') == 'success' && $('#eMailChecker').attr('data') == 'success') {
                            final();
                        }
                    }

                    function final() {
                        $c('.validation_final').removeClass('btn-danger');
                        $c('.validation_final').addClass('btn-primary');
                        $c('.validation_final').attr('data', 'true');
                        $c('.validation_final').html('승인');
                        $('.joinmodal_joinbtn').removeClass('btn-danger');
                        $('.joinmodal_joinbtn').addClass('btn-primary');
                    }
                }
            })
        }
        // end if

    } else if (result == 'true') {
        Swal(
            '사용불가',
            '이미 사용중인 ' + text + ' 입니다.',
            'error'
        )
    }
    // end on
}


// 입력값 중복 확인값 리턴
function validationCheck(type, value) {
    var result = '';
    $.ajax({
        type: 'POST',
        url: '/member/validationCheck',
        data: {
            type: type,
            value: value
        },
        async: false,
        success: function (data) {
            result = data;
        },
        error: function () {
            console.log('valdation function error');
        }
    });
    return result;
}

// 닉네임변경
function nickCheck() {
    $('.modifymodal_modifybtn').on('click', function () {
        var nick = $('.modifymodal_nick').val();
        $.ajax({
            type: 'POST',
            url: '/member/checkNick',
            data: {
                nick: nick
            },
            success: function (response) {
                if (response == 'false') {
                    $('#nickForm').submit();
                } else if (response == 'true') {
                    Swal('사용불가', '이미 사용중인 닉네임 입니다.', 'error');
                } else if (response == 'emptyValue') {
                    Swal('사용불가', '닉네임을 입력 해 주세요.', 'error');
                }
            }
        });
    });
}

// 드래그앤드롭 이미지
function drageImage() {
    $(function () {
        var obj = $("#profileImage");
        var image = '';

        obj.on('dragenter', function (e) {
            e.stopPropagation();
            e.preventDefault();
            image = $(this).attr('src');
            $(this).attr('src', '/images/main/loading.gif');
        });

        obj.on('dragleave', function (e) {
            e.stopPropagation();
            e.preventDefault();
            obj.attr('src', image);
            $(this).css('border', '1px solid transparent');
        });

        obj.on('dragover', function (e) {
            e.stopPropagation();
            e.preventDefault();
        });

        obj.on('drop', function (e) {
            e.preventDefault();
            $(this).css('border', '1px solid transparent');

            var files = e.originalEvent.dataTransfer.files;
            var file = files[0];
            uploadFile(file, "profile");
        });
    });
}

// 파일 업로드
function uploadFile(file, type) {
    var formData = new FormData();
    formData.append("file", file);
    formData.append("type", type);
    $.ajax({
        type: 'POST',
        url: '/upload',
        data: formData,
        dataType: 'text',
        processData: false,
        contentType: false,
        success: function (response) {
            $("#profileImage").attr('src', '/local_upload/' + type + '/' + response);
            Swal('정보변경', '프로필 사진이 변경 되었습니다.', 'success');
        }
    });
}



// 메신저 모달 호출 시
function messengerOpen() {

    $c('.message_userInfo').html(
        '<span id="message_title_text">메세지시바</span>'
    );
    $c('#message_body').html('');
    $c('#message_body').append(
        '<div class="row mb-3">' +
        '<div class="col-12 message_defaulttext">메세지를 보낼 대상을 선택 해 주세요 !</div>' +
        '</div>'
    );
    $('#message_input').val('');
    setTimeout(function () {
        $('#message_input').focus();
    }, 500);
}

// 메신져 버튼 별 처리 구분
function messengerBtn() {

    $('.messenger_btn').on('click', function () {
        var data = $(this).attr('data');
        if (data == 'message') {
            var nick = $c('#message_title_text').attr('data');
            var content = $c('#message_input').val();
            var type = data;
            sendMessage(nick, type, content);
        } else if (data == 'search') {
            var searchVal = $c('#search_text').val();
            findUser(searchVal);
        } else if (data == 'friend') {
            var friendVal = $c('#friend_text').val();
            checkFriendStatus(friendVal);
        } else if (data == 'changePass') {
            var currPass = $('#currPassword').val();
            var changePass = $('#changePassword').val();
            changPasswrod(currPass, changePass);
        }
    });

    $('.messenger_input').on('keyup', function (e) {
        var data = $(this).attr('data');
        var value = $(this).val();
        if (e.which == 13) {
            if (data == 'message') {
                var nick = $('#message_title_text').attr('data');
                var content = $('#message_input').val();
                var type = data;
                sendMessage(nick, type, content);
            } else if (data == 'search') {
                findUser(value);
            } else if (data == 'friend') {
                checkFriendStatus(value);
                $('#friendModal').modal('hide');
            } else if (data == 'currPass') {
                $c('#changePassword').focus();
            } else if (data == 'changePass') {
                var currPass = $('#currPassword').val();
                var changePass = $('#changePassword').val();
                changPasswrod(currPass, changePass);
            }
        }
    });
}

function functionBtn(nick) {
    $('.messenger_btn').on('click', function () {
        var data = $(this).attr('data');
        if (data == 'friend_refuse') {
            var no = $(this).parent('div').children('#frined_messageno').html();
            refuseFriend(no, nick);
        } else if (data == 'friend_accept') {
            var no = $(this).parent('div').children('#frined_messageno').html();
            acceptFriend(no, nick);
        }
    });
}


// 닉네임 조회
function findUser(value) {
    $.ajax({
        type: 'POST',
        url: '/messenger/findUser',
        data: {
            nick: value
        },
        success: function (response) {
            if (response == 'true') {
                Swal('검색 성공', value + '님과 대화를 시작 합니다.', 'success');
                $('#searchModal').modal('hide');
                successSearch(value);
            } else if (response == 'false') {
                Swal('검색 실패', '존재하지 않는 닉네임 입니다.', 'error');
                return;
            } else if (response == 'equal') {
                Swal('검색 실패', '본인과는 대화를 연결 할 수 없습니다.', 'error');
                return;
            } else if (response == 'error') {
                Swal('검색 실패', '닉네임을 입력 해 주세요.', 'error');
                return;
            }
        }
    });
}

// 닉네임 조회 성공시 메신져 상단 변화
function successSearch(value) {
    $.ajax({
        type: 'POST',
        url: '/messenger/getImage',
        data: {
            nick: value
        },
        success: function (response) {
            $c('.message_userInfo').html(
                '<img class="rounded-circle message_userimage" src="/local_upload/profile/' + response + '">' +
                '<span id="message_title_text" data="' + value + '">' + value + '</span>'
            );
            viewMessage(value);
        }
    });
}

// 메세지 전송
function sendMessage(nick, type, content) {
    if (nick == '' || type == '' || content == '') {
        Swal('오류', '입력사항을 다시 확인 해 주세요.', 'error');
        return;
    }
    $c('#message_input').val('');
    $c('#message_input').focus();
    $c('#message_btn').html('전송중');
    $c('#message_btn').removeClass('btn-warning');
    $c('#message_btn').addClass('btn-primary');
    $c('#message_btn').attr('disabled', 'disabled');
    $.ajax({
        type: 'POST',
        url: '/messenger/sendMessage',
        data: {
            nick: nick,
            type: type,
            content: content
        },
        success: function (response) {
            if (response == 'true') {

            } else if (response == 'false') {
                Swal('오류', '입력사항을 다시 확인 해 주세요.', 'error');
            }
        },
        error: function () {
            Swal('메세지오류', '관리자에게 문의 해 주세요.', 'error');
        },
        complete: function () {
            viewMessage(nick);
            getMessengerUserList();
            setTimeout(function () {
                $c('#message_btn').html('전송');
                $c('#message_btn').removeClass('btn-primary');
                $c('#message_btn').addClass('btn-warning');
                $c('#message_btn').removeAttr('disabled');
            }, 500);
        }
    });
}

// 메세지 조회
function viewMessage(nick) {
    $.ajax({
        type: 'POST',
        url: '/messenger/viewMessage',
        dataType: 'json',
        data: {
            nick: nick
        },
        success: function (response) {
            $('#message_body').html('');
            $.each(response.result, function (index, item) {
                if (item.type == 'message') {

                    if (item.myNick === item.nick) {
                        myMessage(item.nick, item.myProfile, item.content, item.mDate);
                    } else {
                        fromMessage(item.nick, item.myProfile, item.content, item.mDate);
                    }

                } else if (item.type == 'friend') {
                    inviteMessage(item.no, item.myNick, item.nick, item.toNick, item.myProfile, item.toProfile);
                }
            });
        },
        complete: function () {
            $('#message_body').scrollTop($('#message_body')[0].scrollHeight);
            functionBtn(nick);
        }
    });
}


// 메신져 유저 리스트 조회
function getMessengerUserList() {
    $.ajax({
        type: 'POST',
        url: '/messenger/getMessengerUserList',
        dataType: 'json',
        success: function (response) {
            $('#message_list').html('');
            $.each(response.result, function (index, item) {
                userList(item.nick, item.connect, item.proFile, item.unRead);
            });
        },
        error: function () {
            Swal('오류', '[유저리스트] 관리자에게 문의 해 주세요.', 'error');
        },
        complete: function () {
            userClick();
        }
    });
}

// 목록 유저 선택
function userClick() {
    $('.message_listimage').on('click', function () {
        var nick = $(this).parent('.message_profile').children('.message_nickname').find('span').html();
        viewMessage(nick);
        successSearch(nick);
    });
}

// 유저리스트 설정
function userList(nick, connect, proFile, unRead) {
    var red = '<div class="background_red" id="message_state"></div>';
    var green = '<div class="background_green" id="message_state"></div>';
    var result = '';
    var newElement = '';
    if (connect == 'off') {
        result = red;
    } else {
        result = green;
    }
    if (unRead > 0) {
        newElement = '<div class="message_alarm"><span class="badge badge-warning">New</span></div>';
        if (unRead > 9) {
            unRead = '!?';
        }
    }
    $('#message_list').append(
        '<div class="ml-2 mb-3 message_profile">' +
        '<img class="rounded-circle message_listimage" id="' + nick + '" src="/local_upload/profile/' + proFile + '"  draggable="true" ondragstart="drag(this, event)">' +
        result +
        '<div id="message_readcount"><span id="message_readtext">' + unRead + '</span></div>' +
        newElement +
        '<div class="message_nickname">' +
        '<span>' + nick + '</span>' +
        '</div></div>'
    );
}

// 내가 보내는 메세지 설정
function myMessage(nick, profile, content, mDate) {
    $('#message_body').append(
        '<div class="message_commentwarp">' +
        '<div class="message_imgwarp_me">' +
        '<img class="rounded-circle" id="message_img" src="/local_upload/profile/' + profile + '">' +
        '</div>' +
        '<div class="message_commentbox_me">' +
        '<div class="message_nick_me ">' +
        '<p>' + nick + '</p>' +
        '</div>' +
        '<div class="clear-fix"></div>' +
        '<div class="message_baloon_me">' +
        '<p>' + content + '</p>' +
        '</div>' +
        '<div class="message_date_me">' +
        '<p>' + mDate + '</p>' +
        '</div></div></div>' +
        '<div class="clear-fix message_padding"></div>'
    );
}

// 받은 메세지 설정
function fromMessage(nick, profile, content, mDate) {
    $('#message_body').append(
        '<div class="message_commentwarp">' +
        '<div class="message_imgwarp">' +
        '<img class="rounded-circle" id="message_img" src="/local_upload/profile/' + profile + '">' +
        '</div>' +
        '<div class="message_commentbox_other">' +
        '<div class="message_nick">' +
        '<p>' + nick + '</p>' +
        '</div>' +
        '<div class="clear-fix"></div>' +
        '<div class="message_baloon">' +
        '<p>' + content + '</p>' +
        '</div>' +
        '<div class="message_date">' +
        '<p>' + mDate + '</p>' +
        '</div></div></div>' +
        '<div class="clear-fix message_padding"></div>'
    );
}

// 초대 메세지 설정
function inviteMessage(no, myNick, nick, toNick, myProfile, toProfile) {
    if (myNick == nick) {
        $('#message_body').append(
            '<div class="row mb-3" id="message_line">' +
            '<div class="col-11" style="text-align: center; margin-left:6%;">' +
            '<div id="frined_messageno" style="visibility: hidden; ">' + no + '</div>' +
            '<img src="/local_upload/profile/' + toProfile + '" id="friend_img" class="rounded-circle">' +
            '<p>' + toNick + '님에게 친구 요청 중...' + '</p>' +
            '<button class="btn btn-danger messenger_btn" data="friend_refuse">취소</button></div>' +
            '</div>'
        );
    } else {
        $('#message_body').append(
            '<div class="row mb-3" id="message_line">' +
            '<div class="col-11" style="text-align: center; margin-left:6%;">' +
            '<div id="frined_messageno"style="visibility: hidden; ">' + no + '</div>' +
            '<img src="/local_upload/profile/' + myProfile + '" id="friend_img" class="rounded-circle">' +
            '<p>' + nick + '님의 친구신청</p>' +
            '<button class="btn btn-primary messenger_btn" data="friend_accept" style="margin-right : 2%;">수락</button>' +
            '<button class="btn btn-danger messenger_btn" data="friend_refuse">거절</button></div>' +
            '</div>'
        );
    }
}

//드래그 시작시 호출 할 함수
function drag(target, profile) {
    profile.dataTransfer.setData('memberList', target.id);
};

// 드롭시 호출 할 함수
function drop(target, profile) {
    var id = profile.dataTransfer.getData('memberList');
    target.appendChild(document.getElementById(id));
    profile.preventDefault();
    var nick = $('#memberList_footer').find('.message_listimage').attr('id');
    $('#memberList_footer').html('<div class="message_waste"></div>');
    deleteMessage(nick);
}

// 유저 이미지를 푸터로 드래그 시 모션변화
function deleteMotion() {
    $(function () {
        var obj = $("#memberList_footer");
        obj.on('dragenter', function (e) {
            e.stopPropagation();
            e.preventDefault();
            $(this).css('border', '3px solid #5272A0');
        });
        obj.on('dragleave', function (e) {
            e.stopPropagation();
            e.preventDefault();
            $(this).css('border', '1px solid #dddddd');
        });
        obj.on('dragover', function (e) {
            e.stopPropagation();
            e.preventDefault();
        });
        obj.on('drop', function (e) {
            e.preventDefault();
            $(this).css('border', '1px solid #dddddd');
        });
    });
}

// 메세지 삭제
function deleteMessage(nick) {
    $.ajax({
        type: 'POST',
        url: '/messenger/deleteMessage',
        data: {
            nick: nick
        },
        success: function (response) {
            Swal('메세지삭제', response + '개 메세지가 삭제 되었습니다.', 'success');
        },
        error: function () {
            Swal('삭제오류', '관리자에게 문의 해 주세요.', 'error');
        },
        complete: function () {
            getMessengerUserList();
            messengerOpen();
        }
    });
}

// 친구 관계 상태 확인 및 초대
function checkFriendStatus(nick) {
    $.ajax({
        type: 'POST',
        url: '/messenger/checkFriendStatus',
        data: {
            nick: nick
        },
        success: function (response) {
            if (response == 'emptyValue') {
                Swal('친구오류', '닉네임을 입력 해 주세요.', 'error');
            } else if (response == 'false') {
                Swal('친구오류', nick + '님은 존재하지 않는 사용자 입니다.', 'error');
            } else if (response == 2) {
                Swal('친구추가', nick + '님은 이미 등록 된 친구 입니다.', 'error');
            } else if (response == 1) {
                Swal('친구추가', nick + '님은 이미 친구신청 진행 중 입니다.', 'error');
            } else if (response == 0) {
                applyFriend(nick);
            }
        },
        error: function () {
            Swal('친구초대오류', '관리자에게 문의 해 주세요.', 'error');
        },
        complete: function () {
            $c('#friend_text').val('');
        }
    });
}

// 친구초대
function applyFriend(nick) {
    var check = false;
    $.ajax({
        type: 'POST',
        url: '/messenger/applyFriend',
        data: {
            nick: nick
        },
        success: function (response) {
            if (response == '2') {
                check = true;
                let timerInterval
                Swal({
                    title: '친구신청 중...',
                    html: '친구 신청까지 <strong></strong> 남았습니다',
                    timer: 1000,
                    onBeforeOpen: () => {
                        Swal.showLoading()
                        timerInterval = setInterval(() => {
                            Swal.getContent().querySelector('strong')
                                .textContent = Swal.getTimerLeft()
                        }, 100)
                    },
                    onClose: () => {
                        clearInterval(timerInterval)
                    }
                }).then((result) => {
                    if (
                        result.dismiss === Swal.DismissReason.timer
                    ) {
                        Swal('친구신청', nick + '님에게 친구신청 되었습니다.', 'success');
                    }
                })
            } else {
                Swal('친구오류', nick + '님에게 전송한 친구신청이 오류가 발생했습니다.', 'error');
            }
        },
        error: function () {
            Swal('친구오류', '관리자에게 문의 해 주세요.', 'error');
        },
        complete: function () {
            if (check) {
                setTimeout(function () {
                    sendMessage(nick, 'friend', '친구신청메세지');
                    successSearch(nick);
                }, 1500);
            }
        }
    });
}

// 친구 초대 거절
function refuseFriend(no, nick) {
    $.ajax({
        type: 'POST',
        url: '/messenger/refuseFriend',
        data: {
            no: no,
            nick: nick
        },
        success: function (response) {
            if (response > 0) {
                Swal('친구거절', nick + '님의 친구신청을 거절 하였습니다.', 'success');
            } else {
                Swal('친구오류', nick + '님에게 전송한 친구거절이 오류가 발생했습니다.', 'error');
            }
        },
        error: function () {
            Swal('친구오류', '관리자에게 문의 해 주세요.', 'error');
        },
        complete: function () {
            getMessengerUserList();
            viewMessage(nick);
        }
    });
}

// 친구 초대 수락
function acceptFriend(no, nick) {
    $.ajax({
        type: 'POST',
        url: '/messenger/acceptFriend',
        data: {
            no: no,
            nick: nick
        },
        success: function (response) {
            if (response == '1') {
                Swal('친구수락', nick + '님의 친구신청을 수락 하였습니다.', 'success');
            } else {
                Swal('친구오류', nick + '님에게 전송한 친구수락이 오류가 발생했습니다.', 'error');
            }
        },
        error: function () {
            Swal('친구오류', '관리자에게 문의 해 주세요.', 'error');
        },
        complete: function () {
            getMessengerUserList();
            viewMessage(nick);
        }
    });
}

// 친구 변경 로직
function changPasswrod(currPass, changePass) {
    var type = false;
    $.ajax({
        type: 'POST',
        url: '/member/changPasswrod',
        data: {
            currPass: currPass,
            changePass: changePass
        },
        dataType: 'json',
        success: function (response) {
            if (response == 'success') {
                type = true;
            }
            passwordAlert(response);
        },
        error: function () {
            Swal('비밀번호변경오류', '관리자에게 문의 해 주세요.', 'error');
        },
        complete: function () {
            if (type) {
                $('#passModal').modal('hide');
            } else {
                $c('#currPassword').val('');
                $c('#changePassword').val('');
                $c('#currPassword').focus();
            }
        }
    });
}

// 패스워드 변경 처리에 따른 alert
function passwordAlert(response) {
    if (response == 'success') {
        Swal('비밀번호변경', '비밀번호가 변경 되었습니다.', 'success');
    } else if (response == 'empty') {
        Swal('비밀번호변경오류', '비밀번호를 입력 해 주세요.', 'error');
    } else if (response == 'length') {
        Swal('비밀번호변경오류', '비밀번호는 4글자이상 필수 사항 입니다.', 'error');
    } else if (response == 'equal') {
        Swal('비밀번호변경오류', '현재 비밀번호와 동일 합니다.', 'error');
    } else if (response == 'currpass') {
        Swal('비밀번호변경오류', '현재 비밀번호가 올바르지 않습니다.', 'error');
    } else {
        Swal('비밀번호변경오류', '관리자에게 문의 해 주세요.', 'error');
    }
}

// 접속 회원 목록 버튼 클릭시
function connecticonBtn() {
    $('.content_connecticon').on('click', function () {
        var data = $(this).attr('id');
        var nick = $(this).parent('.content_connect').children('span').html();
        setTimeout(function () {
            if (data == 'messageBtn') {
                findUser(nick);
            } else if (data == 'friendBtn') {
                viewMessage(nick);
                checkFriendStatus(nick);
            }
        }, 500);
    });
}

// 접속 기록 55초마다 갱신
function statusConnect() {
    addConnect();
    setInterval(function () {
        addConnect();
    }, 55000);
}

// 접속 기록 갱신
function addConnect() {
    $.ajax({
        type: 'POST',
        url: '/member/addConnect',
        dataType: 'json',
        success: function (response) {
            if (response == 'true') {
                //console.log('접속 시간 갱신');
            }
        },
        error: function () {
            Swal('접속로그오류', '관리자에게 문의 해 주세요.', 'error');
        },
        complete: function () {}
    });
}

// 우측 메뉴바 메뉴 클릭시 이동 경로
function viewActive() {
    var subject = $c('.content_subjectleft').children('span').html();
    if (subject == '자유게시판') {
        $('.fa-edit').addClass('menu_active');
        $('.rightmenu_list:nth-of-type(1)').addClass('rightmenu_active');
    } else if (subject == '스터디룸') {
        $('.fa-book').addClass('menu_active');
        $('.rightmenu_list:nth-of-type(2)').addClass('rightmenu_active');
    } else if (subject == '스터디그룹') {
        $('.fa-user-plus').addClass('menu_active');
        $('.rightmenu_list:nth-of-type(3)').addClass('rightmenu_active');
    }
}

// 상단 메뉴바 아이콘 클릭시 이동 경로
function menuLink() {
    $('.rightmenu_list').on('click', function () {
        var value = $(this).attr('data');
        var path = '';
        if (value == 'freeboard') {
            path = '/board/list';
        } else if (value == 'study') {
            path = '/study/list';
        } else if (value == 'group') {
            path = '/group/list';
        } else if (value == 'home') {
            return;
        }
        location.href = path;
    });
}

// 자유게시판 버튼 클릭시 로직
function boardBtn() {
    $('.boardBtn').on('click', function () {
        var value = $(this).attr('data');
        var text = $(this);
        var check = false;
        if (value == 'board_movewrite') {
            movePath('/board/write');
        } else if (value == 'board_write') {
            var content = $('#summernote').summernote('code');
            var title = $('#board_subjecttext').val();
            var gNo = $('#view_gNo').val();
            var step = $('#view_step').val();
            var indent = $('#view_indent').val();
            var path = '/board/list';
            contentWriteAction(title, content, gNo, step, indent, path);
        } else if (value == 'content_rewrite') {
            var gNo = $('#view_gNo').val();
            var step = $('#view_step').val();
            var indent = $('#view_indent').val();
            location.href = '/board/rewrite?gNo=' + gNo + '&&step=' + step + '&&indent=' + indent;
        } else if (value == 'board_cancel') {
            history.back();
        } else if (value == 'comment_write') {
            var no = $('#view_no').val();
            var content = $('.comment_text').val();
            commentWriteAction(no, content);
        } else if (value == 'board_list') {
            location.href = '/board/list';
        } else if (value == 'board_modify') {
            var no = $('#view_no').val();
            var title = $('#board_subjecttext').val();
            var content = $('#summernote').summernote('code');
            contentModifyAction(no, title, content);
        } else if (value == 'content_modify') {
            var no = $('#view_no').val();
            location.href = '/board/modify?no=' + no;
        } else if (value == 'content_delete') {
            var no = $('#view_no').val();
            contentDeleteAction(no);
        } else if (value == 'study_register') {
            var content = $('#study_contenttext').summernote('code');
            $('.study_contenttext').val(content);
            var gName = $('.inputs_gName').val();
            var toPer = $('.inputs_toPer').val();
            var fromPer = $('.inputs_fromPer').val();
            var lat = $('.inputs_lat').val();
            var lng = $('.inputs_lng').val();
            var title = $('.inputs_title').val();
            var text = '';
            var check = false;
            var file = $('.inputs_file').val();

            if (gName == '' || gName == null) {
                text = '스터디명을 입력 해 주세요.';
            } else if (toPer == '' || toPer == null || fromPer == '' || fromPer == null) {
                text = '일자를 확인 해 주세요.';
            } else if (lat == '' || lng == null || lat == '' || lng == null) {
                text = '스터디 위치를 검색 해 주세요.';
            } else if (title == '' || title == null || content == '' || content == null) {
                text = '세부사항을 입력 해 주세요.';
            } else if (file == '' || file == null) {
                text = '그룹사진은 필수조건 입니다.';
            } else {
                check = true;
            }

            if (check == false) {
                errorAlert(text);
            } else {
                $('#studyForm').submit();
            }
        } else if (value == 'refreshStudy') {
            var no = $(this).parent('.study_restats').attr('data');
            refreshStudy(no);
        } else if (value == 'groupUpload') {
            var gNo = $('#group_gNo').val();
            var fileName = $('#groupUploadFile').val();
            $('#modal_gNo').val(gNo);
            $('#groupUpload').submit();
        }
    });
}


// 게시글 작성 시 
function contentWriteAction(title, content, gNo, step, indent, path) {
    var check = false;
    $.ajax({
        type: 'POST',
        url: '/board/write',
        data: {
            title: title,
            content: content,
            gNo: gNo,
            step: step,
            indent: indent
        },
        dataType: 'json',
        success: function (response) {
            if (response == 1) {
                check = true;
            }
        },
        error: function () {
            check = false;
        },
        complete: function () {
            $('.board_wrtiebtn').html('등록중');
            completeAlert(check, '게시판 글 등록', '작성하신 게시글이 등록 중 입니다.', path);
        }
    });
}

// 댓글 작성 시
function commentWriteAction(no, content) {
    var check = false;
    $.ajax({
        type: 'POST',
        url: '/board/writeComment',
        data: {
            fNo: no,
            content: content
        },
        dataType: 'json',
        success: function (response) {
            if (response == 1) {
                check = true;
            }
        },
        error: function () {
            check = false;
        },
        complete: function () {
            if (check == true) {
                location.reload();
            } else {
                Swal('오류', '관리자에게 문의 해 주세요.', 'error');
            }
        }
    });
}

// 게시글 수정 시
function contentModifyAction(no, title, content) {
    var check = false;
    $.ajax({
        type: 'POST',
        url: '/board/modify',
        data: {
            title: title,
            content: content,
            no: no
        },
        dataType: 'json',
        success: function (response) {
            if (response == 1) {
                check = true;
            }
        },
        error: function () {
            check = false;
        },
        complete: function () {
            if (check == true) {
                location.href = '/board/view?no=' + no;
            } else {
                Swal('오류', '관리자에게 문의 해 주세요.', 'error');
            }
        }
    });
}


// 게시글 삭제 시
function contentDeleteAction(no) {
    var check = false;
    $.ajax({
        type: 'POST',
        url: '/board/delete',
        data: {
            no: no
        },
        success: function (response) {
            check = true;
        },
        error: function () {
            check = false;
        },
        complete: function () {
            if (check == true) {
                location.href = '/board/list';
            } else {
                Swal('오류', '관리자에게 문의 해 주세요.', 'error');
            }
        }
    });
}



// 자유게시판 제목 클릭시 글 조회
function boardSelect() {
    $('.content_boardtext').on('click', function () {
        var no = $(this).find('span:nth-of-type(1)').attr('data');
        location.href = '/board/view?no=' + no;
    });
}

// 좋아요 처리 로직
function likeFunc() {
    $('.view_like').on('click', function () {
        var no = $('#view_no').val();
        var check = false;
        var data = $(this).attr('data');
        $.ajax({
            type: 'POST',
            url: '/board/likeFunc',
            data: {
                no: no,
                type: data
            },
            dataType: 'json',
            success: function (response) {
                if (response > 0) {
                    check = true;
                }
            },
            error: function () {
                check = false;
            },
            complete: function () {
                if (check == true && data == 'unlike') {
                    $('.view_like').attr('data', 'like');
                    $('.view_like').find('img').attr('src', '/images/sub/like.png');
                    getLike(no);
                    sAlert('좋아요가 등록 되었습니다.');
                } else if (check == true && data == 'like') {
                    getLike(no);
                    $('.view_like').attr('data', 'unlike');
                    $('.view_like').find('img').attr('src', '/images/sub/unlike.png');
                    sAlert('좋아요가 삭제 되었습니다.')
                } else {
                    Swal('오류', '관리자에게 문의 해 주세요.', 'error');
                }
            }
        });
    });
}

// 좋아요 수 조회
function getLike(no) {
    var count;
    var check = false;
    $.ajax({
        type: 'POST',
        url: '/board/getLike',
        data: {
            no: no
        },
        dataType: 'json',
        success: function (response) {
            if (response >= 0) {
                count = response;
                check = true;
            }
        },
        error: function () {
            check = false;
        },
        complete: function () {
            if (check == true) {
                $('.view_like').find('span:nth-of-type(2)').html(count);
            } else {
                Swal('오류', '관리자에게 문의 해 주세요.', 'error');
            }
        }
    });
}

// 댓글작성 버튼 클릭시 댓글 입력창 생성
function commentBtn() {
    $('.comment_button').on('click', function () {
        var data = $(this).attr('data');
        if (data == 'close') {
            $(this).attr('data', 'open');
            $(this).parent('.comment_content').children('.comment_rewrite').css('margin-top', '10px');
            $(this).parent('.comment_content').children('.comment_rewrite').append(
                '<div class="comment_inputwarp">' +
                '<input type="text" class="comment_retext">' +
                '<button class="btn btn-danger comment_rewrite" onclick="reWriteComment()">작성</button>' +
                '</div>'
            );
        } else {
            $(this).attr('data', 'close');
            $(this).parent('.comment_content').children('.comment_rewrite').css('margin-top', '0');
            $(this).parent('.comment_content').children('.comment_rewrite').html('');
        }
    });
}


// 대댓글 작성 로직
function reWriteComment() {
    $('.comment_rewrite').on('click', function () {
        var fNo = $('#view_no').val();
        var type = $(this).parent('.comment_content').children('#comment_type').val();
        var preId = $(this).parent('.comment_content').children('#comment_id').val();
        var gNo = $(this).parent('.comment_content').children('#comment_gNo').val();
        var step = $(this).parent('.comment_content').children('#comment_step').val();
        var indent = $(this).parent('.comment_content').children('#comment_indent').val();
        var content = $('.comment_retext').val();
        reWriteCommentAction(fNo, type, preId, gNo, step, indent, content);
    });
}

//  대댓글 작성 등록
function reWriteCommentAction(fNo, type, preId, gNo, step, indent, content) {
    var check = false;
    $.ajax({
        type: 'POST',
        url: '/board/reWriteComment',
        data: {
            fNo: fNo,
            type: type,
            preId: preId,
            gNo: gNo,
            step: step,
            indent: indent,
            content: content
        },
        dataType: 'json',
        success: function (response) {
            if (response == 1) {
                check = true;
            }
        },
        error: function () {
            check = false;
        },
        complete: function () {
            if (check == true) {
                location.reload();
            } else {
                Swal('오류', '관리자에게 문의 해 주세요.', 'error');
            }
        }
    });
}

// 게시글 메신저 버튼 클릭시
function viewMessengerBtn() {
    $('.view_messenger').on('click', function () {
        var data = $(this).attr('id');
        var nick = $('#view_nick').html();
        setTimeout(function () {
            if (data == 'messageBtn') {
                findUser(nick);
            } else if (data == 'friendBtn') {
                viewMessage(nick);
                checkFriendStatus(nick);
            }
        }, 500);
    });
}

// 스터디룸 그룹 참여 버튼 클릭시
function groupJoinBtnFunc() {
    $('.roomheader_group').on('click', function () {
        var value = $(this).attr('data');
        if (value == 'close') {
            errorAlert('참여인원수를 초과하여 참여가 불가 합니다.');
        } else if (value == 'join') {
            errorAlert('이미 참여 중인 스터디 입니다.');
        } else if (value == 'unjoin') {
            var no = $('#room_no').val();
            var check = false;
            $.ajax({
                type: 'POST',
                url: '/study/joinGroup',
                data: {
                    gNo: no
                },
                dataType: 'json',
                success: function (response) {
                    if (response > 0) {
                        check = true;
                    }
                },
                error: function () {
                    check = false;
                },
                complete: function () {
                    if (check == true) {
                        timerAlert('참가신청중', '해당 스터디에 참여 신청 중 입니다.', '/study/view?no=' + no);
                    } else {
                        errorAlert('관리자에게 문의 바랍니다.');
                    }
                }
            });
        }
    });
}


// 스터디 재등록
function refreshStudy(no) {
    Swal({
        title: '재등록',
        text: "해당 스터디룸을 재등록 하시겠습니까 ?",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '네',
        cancelButtonText: '아니요'

    }).then((result) => {
        if (result.value) {
            var check = false;
            $.ajax({
                type: 'POST',
                url: '/study/refreshStudy',
                data: {
                    no: no
                },
                dataType: 'json',
                success: function (response) {
                    if (response = 1) {
                        check = true;
                    }
                },
                error: function () {
                    check = false;
                },
                complete: function () {
                    if (check == true) {
                        timerAlert('재등록', '해당 스터디룸을 재등록 중입니다', '/study/list');
                    } else {
                        errorAlert('관리자에게 문의 바랍니다.');
                    }
                }
            })
        }
    })
}

// 스터디그룹 리스트 클릭시 페이지 이동
function moveGroupView() {
    $('.contentgroup_list').on('click', function () {
        gNo = $(this).attr('data');
        movePath('/group/view?gNo=' + gNo);
    });
}


//스터디룸 상단 아이콘 클릭시
function groupLeaderBtn() {
    $('.roomheader_message').on('click', function () {
        var nick = $(this).parent('.roomheader_leader').children('span').html();
        var data = $(this).attr('id');
        setTimeout(function () {
            if (data == 'messageBtn') {
                findUser(nick);
            } else if (data == 'friendBtn') {
                viewMessage(nick);
                checkFriendStatus(nick);
            }
        }, 500);
    });
}

// 스터디룸 참여 회원 클릭시
function groupJoinerBtn() {
    $('.roomfooter_info').on('click', function () {
        var nick = $(this).children('p').html();
        setTimeout(function () {
            findUser(nick);
        }, 500);
    });
}

// 그룹 탈퇴 버튼 클릭시
function groupOutBtn(){
	var gNo = $('#group_gNo').val();
	var type='';
	var text='';
	$('.group_outBtn').on('click',function(){
		type='out';
		text='탈퇴';
		groupAlert(type,text);
	});
	$('.group_deleteBtn').on('click',function(){
		type='delete';
		text='삭제';
		groupAlert(type,text);
	});
	function groupAlert(type,text){
		Swal({
	        title: '그룹'+text,
	        text: "해당 스터디를 "+ text +" 하시겠습니까 ?",
	        type: 'warning',
	        showCancelButton: true,
	        confirmButtonColor: '#3085d6',
	        cancelButtonColor: '#d33',
	        confirmButtonText: '네',
	        cancelButtonText: '아니요'

	    }).then((result) => {
	        if (result.value) {
	        	if ( type == 'out' ) {
	        		groupOutFunc(gNo);
	        	} else if ( type == 'delete' ) {
	        		groupDeleteFunc(gNo);
	        	}
	        }
	    })
	}
}


function groupOutFunc(gNo){
	var check = false;
    $.ajax({
        type: 'POST',
        url: '/group/secession',
        data: {
            gNo: gNo
        },
        dataType: 'json',
        success: function (response) {
            if (response = 1) {
                check = true;
            }
        },
        error: function () {
            check = false;
        },
        complete: function () {
            if (check == true) {
                timerAlert('그룹탈퇴', '해당 스터디를 탈퇴 중입니다', '/group/list');
            } else {
                errorAlert('관리자에게 문의 바랍니다.');
            }
        }
    })
}

function groupDeleteFunc(gNo){
	var check = false;
    $.ajax({
        type: 'POST',
        url: '/group/delete',
        data: {
            gNo: gNo
        },
        dataType: 'json',
        success: function (response) {
            if (response = 1) {
                check = true;
            }
        },
        error: function () {
            check = false;
        },
        complete: function () {
            if (check == true) {
                timerAlert('그룹삭제', '해당 스터디를 삭제 중입니다', '/group/list');
            } else {
                errorAlert('관리자에게 문의 바랍니다.');
            }
        }
    })
}


//ajax complete 로직
function completeAlert(check, title, content, path) {
    if (check == true) {
        timerAlert(title, content, path);
    } else {
        Swal('오류', '관리자에게 문의 해 주세요.', 'error');
    }
}

// SweetAlert 우측상단 메세지
function sAlert(text) {
    Swal({
        position: 'top-end',
        type: 'success',
        title: text,
        showConfirmButton: false,
        timer: 1500
    })
}

function errorAlert(text) {
    Swal({
        position: 'top-end',
        type: 'error',
        title: text,
        showConfirmButton: false,
        timer: 1500
    })
}

// SweetAlert 타이머메세지
function timerAlert(title, content, path) {
    let timerInterval
    Swal({
        title: title,
        html: content,
        timer: 1500,
        onBeforeOpen: () => {
            Swal.showLoading()
            timerInterval = setInterval(() => {
                /*Swal.getContent().querySelector('strong')
                  .textContent = Swal.getTimerLeft()*/
            }, 100)
        },
        onClose: () => {
            clearInterval(timerInterval)
        }
    }).then((result) => {
        if (
            // Read more about handling dismissals
            result.dismiss === Swal.DismissReason.timer
        ) {
            movePath(path);
        }
    })
}
