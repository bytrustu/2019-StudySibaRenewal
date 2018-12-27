$(document).ready(function () {
    groupMessageBtn();
    groupInputEnter();
    viewGroupMessage();
    setInterval(function () {
        viewGroupMessage();
    }, 5000);
});

// 그룹 메세지 버튼 클릭시
function groupMessageBtn() {
    $('.groupMessageBtn').on('click', function () {
        var gNo = $('#group_gNo').val();
        var content = $('.groupMessageText').val();
        sendGroupMessage(gNo, content)
    });
}

// 그룹 메세지창 엔터 입력시
function groupInputEnter() {
    $('.groupMessageText').on('keyup', function (e) {
        if (e.which == 13) {
            var gNo = $('#group_gNo').val();
            var content = $('.groupMessageText').val();
            sendGroupMessage(gNo, content);
        }
    });
}

// 그룹 메세지 조회
function viewGroupMessage() {
    $('.messenger_body').html('');
    var gNo = $('#group_gNo').val();
    $.ajax({
        type: 'POST',
        url: '/group/viewGroupMessage',
        data: {
            gNo: gNo
        },
        dataType: 'json',
        success: function (response) {
            $.each(response.result, function (index, item) {
                addGroupMessage(item.proFile, item.nick, item.content, item.gDate);
                $('.messenger_body').scrollTop($('.messenger_body')[0].scrollHeight);
            });
        },
        error: function () {},
        complete: function () {
            $('.messenger_body').scrollTop($('.messenger_body')[0].scrollHeight);
        }
    });
}

// 그룹메세지 템플릿
function addGroupMessage(proFile, nick, content, gDate) {
    $('.messenger_body').append(
        '<div class="messenger_message">' +
        '<img class="rounded-circle" src="/local_upload/profile/' + proFile + '">' +
        '<div class="messeger_messagewarp">' +
        '<div class="messenger_messagetop">' +
        '<div>' + nick + '</div>' +
        '<div>' + gDate + '</div>' +
        '</div>' +
        '<div class="clear-fix"></div>' +
        '<div class="messenger_messagebottom">' + content + '</div>' +
        '</div>' +
        '</div>'
    );
}


//그룹 메세지 전송
function sendGroupMessage(gNo, content) {
	if ( content == '' ) {
		Swal({
            position: 'top-end',
            type: 'error',
            title: '메세지를 입력해 주세요.',
            showConfirmButton: false,
            timer: 1500
        })
        return false;
	}
    var check = false;
    $('.groupMessageText').val('');
    $('.groupMessageBtn').html('전송중');
    $('.groupMessageBtn').removeClass('btn-primary');
    $('.groupMessageBtn').addClass('btn-danger');
    $.ajax({
        type: 'POST',
        url: '/group/sendGroupMessage',
        data: {
            gNo: gNo,
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
            viewGroupMessage();
            if (check == true) {
                setTimeout(function () {
                    $('.groupMessageBtn').html('전송');
                    $('.groupMessageBtn').removeClass('btn-danger');
                    $('.groupMessageBtn').addClass('btn-primary');
                }, 1000);
            } else {
                setTimeout(function () {
                    errorAlert('관리자에게 문의 바랍니다.');
                    $('.groupMessageBtn').html('전송');
                    $('.groupMessageBtn').removeClass('btn-danger');
                    $('.groupMessageBtn').addClass('btn-primary');
                }, 1000);
            }
        }
    });
}