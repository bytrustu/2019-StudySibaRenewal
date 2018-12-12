$(document).ready(function () {
    iconDecoration();
    footerIconClick();
    modalShow();
    chooseBtn();
    chooseProfile();
    socialLogin();
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
     },
     function () {
         $(this).css('color', '#9a9da0');
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
 $c('.modal_open').on('click', function () {
     var value = $(this).attr('data');
     $('#' + value).modal('show');
     if (value == 'joinModal') {
         swing();
     }
 });
}

//회원가입 이미지 변경 아이콘 움직임
function swing() {
 $c('.joinmodal_choose').animate({
     'top': '50px',
     'left': '270px'
 }, 600).animate({
     'top': '40px',
     'left': '280px'
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
 var profile = 'kakao-'+ result + '.png';
 $c('.joinmodal_image').attr('src', path + profile);
 $('input[name=proFile]').val(profile);
}

//소셜 로그인 버튼 클릭
function socialLogin() {
 $c('.content_socialicon').on('click', function () {
     var value = $(this).attr('data');
     console.log(value);
     if ( value == 'google' || value == 'naver' ) {
         return;
     } else if ( value == 'facebook' ) {
    	 FB.login(function(response){
    		 checkFacebookLoginStatus(response);
         });
     } else if ( value = 'kakao' ) {
    	 kakaoLogin();
     }
 });
}

function socialClose() {
 $c('.social-close').on('click', function () {
     movePath("/");
 });
}

//회원가입 처리
function joinFunction() {
 $c('.joinmodal_joinbtn').on('click', function () {
     var div = $(this).attr('data');
     if (div == 'social') {
         $c('#joinForm').submit();
     } else if (div == 'normal') {
         var formData = $c('#joinForm').serialize();
         $.ajax({
             type: 'POST',
             url: '/member/join',
             data: formData,
             contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
             dataType: 'html',
             success: function (data) {
                 console.log(data);
             }
         });
     }
 });
}

