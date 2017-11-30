<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Facebook Login JavaScript Example</title>
<meta charset="UTF-8">

</head>
<body>


<form action="<%=request.getContextPath()%>/front/realtor/realtor.do" method="post">
<div id="fb-root"></div>

<script>
	function statusChangeCallback(response) {
		alert('statusChangeCallback');
		alert(response);
		if (response.status === 'connected') {
			fblogin();
		} else if (response.status === 'not_authorized') {
			document.getElementById('status').innerHTML = 'Please log '
					+ 'into this app.';
		} else {
			document.getElementById('status').innerHTML = 'Please log '
					+ 'into Facebook.';
		}
	}
	function checkLoginState() {
		FB.getLoginStatus(function(response) {
			statusChangeCallback(response);
		});
	}


	window.fbAsyncInit = function() {
	    FB.init({
	      appId      : '193615331198785',
	      xfbml      : true,
	      version    : 'v2.11'
	    });
	    FB.AppEvents.logPageView();
	  };

	  (function(d, s, id){
	     var js, fjs = d.getElementsByTagName(s)[0];
	     if (d.getElementById(id)) {return;}
	     js = d.createElement(s); js.id = id;
	     js.src = "https://connect.facebook.net/en_US/sdk.js";
	     fjs.parentNode.insertBefore(js, fjs);
	   }(document, 'script', 'facebook-jssdk'));

	function testAPI() {
		console.log('Welcome!  Fetching your information.... ');
		FB.api('/me',function(response) {
			console.log('Successful login for: '+ response.name);
			document.getElementById('status').innerHTML = 'Thanks for logging in, '	+ response.name+ response.email  +response.id+'!';});
		//讀照片必備	  
		FB.api('/me/picture?type=large', function(response) {
			document.getElementById('status1').innerHTML = response.data.url;
		});
	}

		

		function fblogin(){
			  //===實作(填入程式碼)
			  var xhr=new XMLHttpRequest();
			  xhr.onreadystatechange=function(){
				  if(xhr.readyState==4){
					  if(xhr.status==200){
						  document.getElementById("IdShowPanel").innerHTML=xhr.responseText;
					  } 
					  else{
						  alert(xhr.status);
						  } 
					  }
				  }
			  //取得使用者資訊，還可以取很多個
			  FB.api('/me', 'GET', {"fields":"id,name,email"},function(response) {
				  name = response.name;
				  email = response.email;
				  fbid = response.id;
			
			      //建立好Get連接與送出請求
				  var url="<%=request.getContextPath()%>/front/realtor/realtor.do?action=FBLogin&name="+name+"&email="+email+"&id="+fbid;
				  xhr.open("Get", url, true);
				  xhr.send(null);
			   });
		}
</script>

		<fb:login-button scope="public_profile,email"
			onlogin="checkLoginState();" data-size="large">
		</fb:login-button>
		<div id="IdShowPanel"></div>

		<div id="status"></div>
	</form>
</body>
</html>