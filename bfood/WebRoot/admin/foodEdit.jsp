<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:if test="#attr.food!=null && #attr.food.food_id!=0">编辑</s:if><s:else>添加</s:else>菜谱信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script charset="utf-8" src="editor2/kindeditor-all-min.js"></script>
<script charset="utf-8" src="editor2/lang/zh-CN.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	
	 var num = /^\d+(\.\d+)?$/;
	 var num2 = /^\d+$/;
	 $("#addBtn").bind('click',function(){
		editor.sync();
		if($("#paramsFood\\.food_name").val()==''){
			alert('菜谱名称不能为空');
			return;
		}
		if($("#paramsFood\\.food_type_id").val()=='0'){
			alert('菜谱类型不能为空');
			return;
		}
		if($("#paramsFood\\.food_pic").val()==''){
			alert('菜谱图片不能为空');
			return;
		}
		if($("#noticeContent").val()==''){
			alert('菜谱介绍不能为空');
			return;
		}
		$("#paramsFood\\.food_id").val(0);
		$("#info").attr('action','Admin_addFood.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
		editor.sync();
		if($("#paramsFood\\.food_name").val()==''){
			alert('菜谱名称不能为空');
			return;
		}
		if($("#paramsFood\\.food_type_id").val()=='0'){
			alert('菜谱类型不能为空');
			return;
		}
		if($("#paramsFood\\.food_pic").val()==''){
			alert('菜谱图片不能为空');
			return;
		}
		if($("#noticeContent").val()==''){
			alert('菜谱介绍不能为空');
			return;
		}
		$("#info").attr('action','Admin_saveFood.action').submit();
		 
	});
	
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">菜谱管理&gt;&gt;<s:if test="#attr.food!=null && #attr.food.food_id!=0">编辑</s:if><s:else>添加</s:else>菜谱</span>
</div>
<form id="info" name="info" action="Admin_addFood.action" method="post">   
<s:hidden id="paramsFood.food_id" name="paramsFood.food_id" value="%{#attr.food.food_id}" /> 
<s:if test="#attr.food!=null && #attr.food.food_id!=0">
<s:hidden id="paramsFood.user_id" name="paramsFood.user_id" value="%{#attr.food.user_id}" /> 
</s:if>
<s:else>
<s:hidden id="paramsFood.user_id" name="paramsFood.user_id" value="%{#attr.admin.user_id}" /> 
</s:else>
<input type="hidden" name="paramsFood.food_pic" id="paramsFood.food_pic" value='<s:property value="#attr.food.food_pic"/>'/>
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle"><s:if test="#attr.food!=null && #attr.food.food_id!=0">编辑</s:if><s:else>添加</s:else>菜谱信息</TD>
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
          <td width="150" align="right" style="padding-right:5px"><font color="red">*</font> 菜谱名称：</td>
          <td width="*">
          	<s:textfield name="paramsFood.food_name" id="paramsFood.food_name" value="%{#attr.food.food_name}" cssStyle="width:300px"/>
          </td>
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 菜谱类型：</td>
          <td>
          	<s:select id="paramsFood.food_type_id" name="paramsFood.food_type_id" value="%{#attr.food.food_type_id}" 
	      		list="#attr.foodTypes" listKey="food_type_id" listValue="food_type_name" 
	      		cssStyle="width:155px;" headerKey="0" headerValue="请选择">
	        </s:select>
          </td>
        </tr> 
        <tr>
		  <td align="right" style="padding-right:5px">菜谱图片：</td>
		  <td align="left">
		    <img id="userImg" src="../images/foods/<s:property value='#attr.food.food_pic'/>" width="70" height="80" style="border:0px;"/>
	        &nbsp;<span id="op" style="display:none"><img src="images/loading004.gif"  height="80" /></span>
	      </td>
	    </tr>
	     <tr>
		  <td align="right" style="padding-right:5px"></td>
	      <td align="left">
	          <iframe name="uploadPage" src="uploadImg2.jsp" width="100%" height="50" marginheight="0" marginwidth="0" scrolling="no" frameborder="0"></iframe>            
	       </td>
	    </tr>
        <tr>
          <td align="right" style="padding-right:5px">菜谱作者：</td>
          <td>
          	<s:textfield name="paramsFood.food_author" id="paramsFood.food_author" value="%{#attr.food.food_author}"/>
          </td>
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 菜谱介绍：</td>
          <td>
          	<textarea style="width:500px;height:300px" name="paramsFood.food_desc" id="noticeContent"><s:property value="#attr.food.food_desc" escape="false"/></textarea>
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
          	<s:if test="#attr.food!=null && #attr.food.food_id!=0">
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
<script>        
KindEditor.ready(function(K) {
	window.editor = K.create('#noticeContent',{
		width : '95%',
		items:[
			'fullscreen','|','justifyleft', 'justifycenter', 'justifyright','justifyfull',
			'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
			'italic', 'underline','anchor', 'link', 'unlink','image'
		],
		uploadJson : 'editor2/jsp/upload_json.jsp',
        fileManagerJson : 'editor2/jsp/file_manager_json.jsp',
        allowFileManager : true
	});
});
	   
</script>
</body>
</html>