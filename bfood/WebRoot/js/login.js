$(document).ready(function(){
	var postStr = {
		'params.login_name':'',
		'params.login_pass':''
	};
	var selfCenterBtn = $("#selfCenterBtn");
	var loginOutBtn = $("#loginOutBtn");
	var loginInBtn = $("#loginInBtn");
	var regBtn = $("#regBtn");
	var infoField = $("#infoField");
	var loginField = $("#loginField");
	var login_name = $("#login_name");
	var login_pass = $("#login_pass");
	
	loginOutBtn.bind('click',function(){
		$.post('LoginOutSystem.action',null,
			function(responseObj) {
					if(responseObj.success) {	
						 alert('退出成功！');
						 window.location.href="page_index.action";
					}else  if(responseObj.err.msg){
						 alert('退出异常：'+responseObj.err.msg);
					}else{
						 alert('退出异常：服务器异常！');
					}	
		 },'json');
	});
	
	loginInBtn.bind('click',function(){
		if(login_name.val()==''||login_pass.val()==''){
			alert("用户名和密码不能为空！")
			return;
		}
		postStr['params.login_name'] = login_name.val();
		postStr['params.login_pass'] = login_pass.val();
		
		$.post('LoginInSystem.action',postStr,
			function(responseObj) {
					if(responseObj.success) {	
						 alert('登录成功！');
						 window.location.reload();
					}else  if(responseObj.err.msg){
						 alert('登录异常：'+responseObj.err.msg);
					}else{
						 alert('登录异常：服务器异常！');
					}	
		 },'json');
	});
	
	regBtn.bind('click',function(){
		 window.location.href="page_reg.action";
	});
	
	selfCenterBtn.bind('click',function(){
		 window.location.href="page_myInfo.action";
	});
});