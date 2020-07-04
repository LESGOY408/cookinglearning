<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:if test="#attr.user!=null && #attr.user.user_id!=0">编辑</s:if><s:else>添加</s:else>用户信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	 var num = /^\d+(\.\d+)?$/;
	 $("#addBtn").bind('click',function(){
		if($("#paramsUser\\.login_name").val()==''){
			alert('用户名不能为空');
			return;
		}
		if($("#paramsUser\\.login_pass").val()==''){
			alert('密码不能为空');
			return;
		}
		if($("#paramsUser\\.real_name").val()==''){
			alert('姓名不能为空');
			return;
		}
		if($("#paramsUser\\.nick_name").val()==''){
			alert('昵称不能为空');
			return;
		}
		if($("#paramsUser\\.user_mobile").val()==''){
			alert('电话不能为空');
			return;
		}
		 
		$("#paramsUser\\.user_id").val(0);
		$("#info").attr('action','Admin_addUser.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
		if($("#paramsUser\\.real_name").val()==''){
			alert('姓名不能为空');
			return;
		}
		if($("#paramsUser\\.nick_name").val()==''){
			alert('昵称不能为空');
			return;
		}
		if($("#paramsUser\\.user_mobile").val()==''){
			alert('电话不能为空');
			return;
		}
		$("#info").attr('action','Admin_saveUser.action').submit();
			 
	});
	
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">注册用户管理&gt;&gt;<s:if test="#attr.user!=null && #attr.user.user_id!=0">编辑</s:if><s:else>添加</s:else>用户</span>
</div>
<form id="info" name="info" action="Admin_addUser.action" method="post">   
<s:hidden id="paramsUser.user_id" name="paramsUser.user_id" value="%{#attr.user.user_id}" /> 
<input type="hidden" name="paramsUser.user_pic" id="paramsUser.user_pic" value='${admin.user_pic}'/>
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle"><s:if test="#attr.user!=null && #attr.user.user_id!=0">编辑</s:if><s:else>添加</s:else>用户</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
        </TABLE>
     </td>
   </tr>
   <tr>
     <td height="1" bgcolor="#8f8f8f"></td>
   </tr>
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
       <tr>
          <td width="12%" align="right" style="padding-right:5px"><font color="red">*</font> 用户名：</td>
          <td width="38%" >
          	<s:if test="#attr.user!=null && #attr.user.user_id!=0"><s:property value="#attr.user.login_name" /></s:if>
          	<s:else><s:textfield name="paramsUser.login_name" id="paramsUser.login_name" value="%{#attr.user.login_name}"/> </s:else>
          </td>
          <td width="12%" rowspan="3" align="right" style="padding-right:5px">头像：</td>
          <td width="38%" rowspan="3" >
          	<img id="userImg" src="../images/head/<s:property value='#attr.user.user_pic' />" width="120" height="100" style="border:0px;"/>
	        &nbsp;<span id="op" style="display:none"><img src="images/loading004.gif"  height="80" /></span>
          </td>
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 密码：</td>
          <td>
          	<s:if test="#attr.user!=null && #attr.user.user_id!=0">
          	<s:password name="paramsUser.login_pass" id="paramsUser.login_pass" value=""  showPassword="true"/>
          	</s:if>
          	<s:else>
          	<s:password name="paramsUser.login_pass" id="paramsUser.login_pass" value="111111"  showPassword="true"/>
          	<span id="passTip" style="color:red;">初始密码：111111</span>
          	</s:else>
          </td>
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 昵称：</td>
          <td>
            <s:textfield name="paramsUser.nick_name" id="paramsUser.nick_name" value="%{#attr.user.nick_name}"/>
          </td>
        </tr>   
        <tr>
         <td align="right" style="padding-right:5px"><font color="red">*</font> 姓名：</td>
          <td>
            <s:textfield name="paramsUser.real_name" id="paramsUser.real_name" value="%{#attr.user.real_name}"/>
          </td>
          <td align="right" style="padding-right:5px"></td>
          <td>
          	<iframe name="uploadPage" src="uploadImg.jsp" width="100%" height="50" marginheight="0" marginwidth="0" scrolling="no" frameborder="0"></iframe>
          </td> 
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px">邮箱：</td>
          <td>
            <s:textfield id="paramsUser.user_mail" name="paramsUser.user_mail" value="%{#attr.user.user_mail}"/>
          </td> 
           <td align="right" style="padding-right:5px"><font color="red">*</font> 电话：</td>
          <td>
            <s:textfield id="paramsUser.user_mobile" name="paramsUser.user_mobile" value="%{#attr.user.user_mobile}"/>
          </td> 
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px">联系地址：</td>
          <td colspan="3">
            <s:textfield id="paramsUser.user_lxdz" name="paramsUser.user_lxdz" value="%{#attr.user.user_lxdz}" cssStyle="width:350px"/>
          </td> 
        </tr>
     </table>
     </td>
   </tr>  
   <tr>
     <td>
       <table width="100%" align="center" cellpadding="0" cellspacing="0" class="editbody">
        <tr class="editbody">
          <td align="center" height="30">
          	<s:if test="#attr.user!=null && #attr.user.user_id!=0">
          	<input type="button" id="editBtn" Class="btnstyle" value="编 辑"/> 
          	</s:if>
          	<s:else>
          	<input type="button" id="addBtn" Class="btnstyle" value="添 加" />
          	</s:else>
            &nbsp;<label style="color:red">${tip}</label>
          </td>
        </tr>
      </table>
     </td>
   </tr>
</table>
</form>
</body>
</html>