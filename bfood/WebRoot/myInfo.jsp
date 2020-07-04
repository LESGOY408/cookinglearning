<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>修改个人信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/store.css">
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
	var user_id="${userFront.user_id}";
	if(user_id==null || user_id==''){
		alert("请先登录！");
		window.location.href="page_login.action";
	}
</script>
<style type="text/css">
 body,td,div
 {
   font-size:12px;
 }
</style>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div id="middle">
	 <div id="product_menu">
	 	 <jsp:include page="leftMenu.jsp"></jsp:include>
	 </div>
	 <!--  购物车 -->
	 <div id="product_info">
			<div class="title">个人中心  &gt;&gt;  修改个人信息</div>
			<div style="margin-top:5px">
				 <form id="info" name="info" action="page_saveUserFront.action" method="post">    
				 <input type="hidden" name="paramsUser.user_id" value="${userFront.user_id}"/>
				 <input type="hidden" name="paramsUser.user_pic" id="paramsUser.user_pic" value='${userFront.user_pic}'/>
				 <table class="ptable" style="margin-bottom:5px;">
					<tr>
			          <td width="15%" align="right" style="padding-right:5px">用户名：</td>
			          <td width="35%">${userFront.login_name}</td>
			          <td colspan="2" rowspan="3" >
			          	<img id="userImg" src="images/head/<s:property value='#attr.userFront.user_pic' />" width="120" height="100" style="border:0px;"/>
				        &nbsp;<span id="op" style="display:none"><img src="images/loading004.gif"  height="80" /></span>
			          </td>
			        </tr> 
			        <tr>
			          <td align="right" style="padding-right:5px"><font color="red">*</font> 姓名：</td>
			          <td>
			            <input type="text" id="paramsUser.real_name" name="paramsUser.real_name" value="${userFront.real_name}"/>
			          </td> 
			        </tr> 
			         <tr>
			          <td align="right" style="padding-right:5px"><font color="red">*</font> 昵称：</td>
			          <td>
			            <input type="text" id="paramsUser.nick_name" name="paramsUser.nick_name" value="${userFront.nick_name}"/>
			          </td> 
			        </tr> 
			        <tr>
			          <td align="right" style="padding-right:5px"><font color="red">*</font> 联系电话：</td>
			          <td>
			            <s:textfield id="paramsUser.user_mobile" name="paramsUser.user_mobile" value="%{#attr.userFront.user_mobile}"/>
			          </td> 
			          <td colspan="2">
			          	<iframe name="uploadPage" src="uploadImg.jsp" width="100%" height="50" marginheight="0" marginwidth="0" scrolling="no" frameborder="0"></iframe>
			          </td> 
			        </tr> 
			        <tr>
			          <td width="15%" align="right" style="padding-right:5px">联系邮箱：</td>
			          <td  colspan="3">
			             <input type="text" id="paramsUser.user_mail" name="paramsUser.user_mail" value="${userFront.user_mail}"/>
			          </td>
			        </tr>
			         <tr>
			          <td width="15%" align="right" style="padding-right:5px">联系地址：</td>
			          <td colspan="3">
			             <input style="width:350px" type="text" id="paramsUser.user_lxdz" name="paramsUser.user_lxdz" value="${userFront.user_lxdz}"/>
			          </td>
			        </tr> 
			        <tr class="">
			          <td align="center" height="30" colspan="4">
			            <input type="button" id="saveBtn" Class="btnstyle" value="修 改"/>&nbsp;
			            <span style="color:red">${tip }</span>
			          </td>
			        </tr>
				 </table>
				 </form>
			</div>
		</div>
	 <!--  购物车 -->
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script language="javascript" type="text/javascript">
	$(document).ready(function(){
		 var num = /^\d+(\.\d+)?$/;
		 $("#saveBtn").bind('click',function(){
			if($("#paramsUser\\.real_name").val()==''){
				alert('姓名不能为空');
				return;
			}
			if($("#paramsUser\\.nick_name").val()==''){
				alert('昵称不能为空');
				return;
			}
			if($("#paramsUser\\.user_mobile").val()==''){
				alert('联系电话不能为空');
				return;
			}
			$("#info").submit();
			 
		 });
		
	});	 
</script>
</body>
</html>