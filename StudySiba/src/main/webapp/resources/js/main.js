$(document).ready(function () {
    iconDecoration();
    footerIconClick();
    modalShow();
    chooseBtn();
    chooseProfile();
});


// 페이지 이동 ( 새창 )
function movePath(path) {
    location.href = path;
}

// 상단 아이콘 HOVER 색상 반응
function iconDecoration() {
    $('.menu-icon, .menu-dropicon, .menu-pre').hover(
        function () {
            $(this).css('color', 'white');
        },
        function () {
            $(this).css('color', '#9a9da0');
        });
}

// 새창 페이지 이동
function movePath(path) {
    window.open(path, '_blank');
}

// 푸터 아이콘 클릭 처리
function footerIconClick() {
    $('.footer-icons').on('click', function () {
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
    $('.modal_open').on('click', function () {
        var value = $(this).attr('data');
        $('#' + value).modal('show');
        if ( value == 'joinModal' ) {
            swing();
        }
    });
}

// 회원가입 이미지 변경 아이콘 움직임
function swing() {
    $('.joinmodal_choose').animate({
        'top': '50px',
        'left': '270px'
    }, 600).animate({
        'top': '40px',
        'left': '280px'
    }, 600, swing);
}

// 회원가입 이미지 변경 버튼 클릭시
function chooseBtn(){
    $('.joinmodal_choose').on('click',function(){
        chooseProfile();
    });
}

// 회원가입 이미지 변경 로직
function chooseProfile(){
    var path = '/images/profile/kakao/'
    var result = Math.floor(Math.random() * 40) + 1;
    $('.joinmodal_image').attr('src',path+'kakao-'+result+'.png');
}