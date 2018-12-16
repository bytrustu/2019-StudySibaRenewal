$(document).ready(function () {
    //상단 아이콘 HOVER 색상 반응
    iconDecoration();
    //푸터 아이콘 클릭 처리
    footerIconClick();
    //모달창 보이기
    modalShow();
    //회원가입 이미지 변경 버튼 클릭시
    chooseBtn();
    //회원가입 이미지 변경 로직
    chooseProfile();
    //소셜 로그인 버튼 클릭
    socialLogin();
    //회원가입 처리
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
});

//selector 캐싱
var $c = function (qr, force) {
    if (!$c.dt) {
        $c.dt = {};
    }
    if (!$c.dt[qr] || force) {
        $c.dt[qr] = $(qr);
    }
    return $c.dt[qr];
};

/*  console.time('t1');
      for (let i = 0; i < 100000; i++) {
         $c;
      }
      console.timeEnd('t1');*/

//페이지 이동
function movePath(path) {
    location.href = path;
}

//새창 페이지 이동
function moveNewPath(path) {
    window.open(path, '_blank');
}

//상단 아이콘 HOVER 색상 반응
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

//푸터 아이콘 클릭 처리
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

//모달창 보이기
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
        } else if (value == 'modifyModal') {
            $('.modifymodal_nick').focus();
        } else if (value == 'messageModal') {
            messengerOpen();
            getMessengerUserList();
        } else if (value == 'searchModal') {
            $('#search_text').val('');
            setTimeout(function () {
                $('#search_text').focus();
            }, 500);
        } else if (value == 'friendModal') {
            setTimeout(function () {
                $('#friend_text').focus();
            }, 500);
        }
    });
}

//모달종료시 액션
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

//로그인 버튼
function loginBtn() {
    $c('.loginmodal_loginbtn').on('click', function () {
        $('#loginForm').submit();
    });
}


//회원가입 이미지 변경 아이콘 움직임
function swing() {
    $c('.joinmodal_choose').animate({
        'top': '4%',
        'left': '57%'
    }, 600).animate({
        'top': '7%',
        'left': '52%'
    }, 600, swing);
}

//소셜 회원가입 이미지 변경 아이콘 움직임
function socialSwing() {
    $c('.social_choose').animate({
        'top': '22%',
        'left': '51%'
    }, 600).animate({
        'top': '20%',
        'left': '52%'
    }, 600, socialSwing);
}

//회원가입 이미지 변경 버튼 클릭시
function chooseBtn() {
    $c('.joinmodal_choose, .social_choose').on('click', function () {
        chooseProfile();
    });
}

//회원가입 이미지 변경 로직
function chooseProfile() {
    var path = '/images/profile/kakao/'
    var result = Math.floor(Math.random() * 40) + 1;
    var profile = 'kakao-' + result + '.png';
    $c('.joinmodal_image').attr('src', path + profile);
    $('input[name=proFile]').val(profile);
}

//소셜 로그인 버튼 클릭
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

//회원가입 처리
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

//소셜 회원가입 입력값 확인
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


//확인버튼 : 중복확인
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

//확인버튼 : 불가능
function changeFalseView(selector) {
    selector.removeClass('btn-success');
    selector.addClass('btn-warning');
    selector.attr('data', 'false');
    selector.html('불가능');
}

//중복확인 로직
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


//입력값 중복 확인값 리턴
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

//닉네임변경
function nickCheck() {
    $('.modifymodal_modifybtn').on('click', function () {
        var nick = $('.modifymodal_nick').val();
        $.ajax({
            type: 'POST',
            url: '/member/checkNick',
            data: {
                nick: nick
            },
            success: function (callback) {
                if (callback == 'false') {
                    $('#nickForm').submit();
                } else if (callback == 'true') {
                    Swal('사용불가', '이미 사용중인 닉네임 입니다.', 'error');
                } else if (callback == 'emptyValue') {
                    Swal('사용불가', '닉네임을 입력 해 주세요.', 'error');
                }
            }
        });
    });
}

//드래그앤드롭 이미지
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

//파일 업로드
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
        success: function (callback) {
            $("#profileImage").attr('src', '/local_upload/' + type + '/' + callback);
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
        '<div class="row mb-3" id="message_line">' +
        '<div class="col-12 message_defaulttext">메세지를 보낼 대상을 선택 해 주세요 !</div>' +
        '</div>'
    );
    $('#message_input').val('');
    setTimeout(function () {
        $('#message_input').focus();
    }, 500);

    //messengerBtn();
}

// 메신져 버튼 별 처리 구분
function messengerBtn() {
    // 결국 여기가 창 뜰때마다 실행되니까
    // 이벤트가 창 띄울때마다 추가되고

    $c('.messenger_btn').on('click', function () {
        var data = $(this).attr('data');
        if (data == 'message') {
            var nick = $('#message_title_text').attr('data');
            var content = $('#message_input').val();
            var type = data;
            sendMessage(nick, type, content);

        } else if (data == 'search') {
            var searchVal = $('#search_text').val();
            console.log(searchVal);
            findUser(searchVal);
        } else if (data == 'friend') {
            var friendVal = $('#friend_text').val();
        }
    });

    $c('.messenger_input').on('keyup', function (e) {
        var data = $(this).attr('data');
        var value = $(this).val();
        if (e.which == 13) {
            if (data == 'message') {

            } else if (data == 'search') {
                findUser(value);

            } else if (data == 'friend') {
                $('#friendModal').modal('hide');
            }
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
        success: function (callback) {
            if (callback == 'true') {
                Swal('검색 성공', value + '님과 대화를 시작 합니다.', 'success');
                $('#searchModal').modal('hide');
                successSearch(value);
            } else if (callback == 'false') {
                Swal('검색 실패', '존재하지 않는 닉네임 입니다.', 'error');
                return;
            } else if (callback == 'equal') {
                Swal('검색 실패', '본인과는 대화를 연결 할 수 없습니다.', 'error');
                return;
            } else if (callback == 'error') {
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
        success: function (callback) {
            $c('.message_userInfo').html(
                '<img class="rounded-circle message_userimage" src="/local_upload/profile/' + callback + '">' +
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
    }
    $.ajax({
        type: 'POST',
        url: '/messenger/sendMessage',
        data: {
            nick: nick,
            type: type,
            content: content
        },
        success: function (callback) {
            if (callback == 'true') {

            } else if (callback == 'false') {
                Swal('오류', '입력사항을 다시 확인 해 주세요.', 'error');
            }
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
        success: function (callback) {
            $('#message_body').html('');
            $.each(callback.result, function (index, item) {
                if (item.myNick === item.nick) {
                    myMessage(item.nick, item.myProfile, item.content, item.mDate);
                } else {
                    fromMessage(item.nick, item.myProfile, item.content, item.mDate);
                }
            });
        },
        complete: function () {
            $('#message_body').scrollTop($('#message_body')[0].scrollHeight);
        }
    });
}

// 메신져 유저 리스트 조회
function getMessengerUserList() {
    $.ajax({
        type: 'POST',
        url: '/messenger/getMessengerUserList',
        dataType: 'json',
        success: function (callback) {
            console.log(callback);
            $('#message_list').html('');
            $.each(callback.result, function (index, item) {
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
function userClick(){
	$('.message_listimage').on('click', function(){
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
    if (connect == 'off') {
        result = red;
    } else {
        result = green;
    }
    $('#message_list').append(
        '<div class="ml-2 mb-3 message_profile">' +
        '<img class="rounded-circle message_listimage" src="/local_upload/profile/' + proFile + '" draggable="true" ondragstart="drag(this, event)">' +
        result +
        '<div id="message_readcount"><span id="message_readtext">' + unRead + '</span></div>' +
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
function inviteMessage() {

}
