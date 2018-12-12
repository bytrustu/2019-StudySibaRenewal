/*  
 *			페이스북 연동
*/
function statusChangeCallback(response) {
  /*console.log('statusChangeCallback');*/
  /*console.log(response);*/
  if (response.status === 'connected') {
    testAPI();
  } else {
    document.getElementById('status').innerHTML = 'Please log ' +
      'into this app.';
  }
}

function checkLoginState() {
  FB.getLoginStatus(function(response) {
    statusChangeCallback(response);
  });
}

var checkFacebookLoginStatus = function(response) {
 /* console.log(response);*/
  if (response.status === 'connected') {
    FB.api('/me', {
      fields : 'name,email'
    }, function(response) {
      userinfo = response;
      $('#socialForm input[name=sId]').val(userinfo.id);
      $('#socialForm input[name=eMail]').val(userinfo.email);
      $('#socialForm').submit();
    });
  }
}

window.fbAsyncInit = function() {
  FB.init({
    appId : '489217858222608',
    cookie : true,
    xfbml : true,
    version : 'v3.1'
  });
};

(function(d, s, id) {
  var js,
    fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s);
  js.id = id;
  js.src = "https://connect.facebook.net/en_US/sdk.js";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

function testAPI() {
  /*console.log('Welcome!  Fetching your information.... ');*/
  FB.api('/me', function(response) {
    /*console.log('Successful login for: ' + response.name);*/
    document.getElementById('status').innerHTML = 'Thanks for logging in, ' + response.name + '!';
  });
}


/*  
 *			카카오톡 연동
*/

Kakao.init('d26477e534365eee96ce785fe0918730');

function kakaoLogin() {
    Kakao.Auth.login({
        success: function (authObj) {
            console.log('success');
            Kakao.API.request({
                url: '/v2/user/me',
                success: function (res) {
                    /* console.log(JSON.stringify(res)); */
                	
                    $('#socialForm input[name=sId]').val(res.id);
                    $('#socialForm').attr('action', '/login/kakaoSignInCallback');
                    $('#socialForm').submit();
                },
                fail: function (error) {
                    /*alert(JSON.stringify(error));*/
                    alert('로그인 실패 했습니다.');
                }
            });
        },
        fail: function (err) {
            /*console.log(JSON.stringify(err));*/
            alert('로그인 실패 했습니다.');
        }
    });
}

/*$('.kakao-login-btn').on('click', function(){
});*/
