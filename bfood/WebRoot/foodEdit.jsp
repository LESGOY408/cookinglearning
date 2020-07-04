<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><s:if test="#attr.food!=null && #attr.food.food_id!=0">编辑</s:if><s:else>发布</s:else>菜谱信息</title>
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
<script language="javascript" type="text/javascript" src="admin/My97DatePicker/WdatePicker.js"></script>
<script charset="utf-8" src="admin/editor2/kindeditor-all-min.js"></script>
<script charset="utf-8" src="admin/editor2/lang/zh-CN.js"></script>
<script language="javascript" type="text/javascript"> 
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
			<div class="title">个人中心  &gt;&gt;  <s:if test="#attr.food!=null && #attr.food.food_id!=0">编辑</s:if><s:else>发布</s:else>菜谱信息</div>
			<div style="margin-top:5px">
				 <form id="info" name="info" action="page_saveFood.action" method="post" style="width:100%;height:100%">    
				 <input type="hidden" name="paramsFood.user_id" id="paramsFood.user_id" value="${userFront.user_id }"/>
				 <input type="hidden" name="paramsFood.food_id" id="paramsFood.food_id" value="<s:property value='#attr.food.food_id'/>"/>
				 <input type="hidden" name="paramsFood.food_pic" id="paramsFood.food_pic" value="<s:property value='#attr.food.food_pic'/>"/>
				 <table class="ptable" style="margin-bottom:5px;">
					<tr>
			          <td width="15%" align="right" style="padding-right:5px"><span style="color:red">*</span> 菜谱名称：</td>
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
					    <img id="userImg" src="images/foods/<s:property value='#attr.food.food_pic'/>" width="70" height="80" style="border:0px;"/>
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
			          <td class="KEEdit">
			          	<textarea style="width:500px;height:300px" name="paramsFood.food_desc" id="noticeContent"><s:property value="#attr.food.food_desc" escape="false"/></textarea>
			          </td>
			        </tr>
			        <tr class="">
			          <td align="center" height="30" colspan="4">
			            <s:if test="#attr.food!=null && #attr.food.food_id!=0">
			          	<input type="button" id="editBtn" Class="btnstyle" value="编 辑"/> 
			          	</s:if>
			          	<s:else>
			          	<input type="button" id="addBtn" Class="btnstyle" value="发 布" />
			          	</s:else>
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
		var num=/^\d+?$/;
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
			var aQuery = $("#info").serialize();  
			$.post('page_addFood.action',aQuery,
				function(responseObj) {
						if(responseObj.success) {	
							 alert('发布成功！');
							 window.location.href="page_listMyFoods.action";
						}else  if(responseObj.err.msg){
							 alert('发布失败：'+responseObj.err.msg);
						}else{
							 alert('发布失败：服务器异常！');
						}	
			},'json');
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
			var aQuery = $("#info").serialize();  
			$.post('page_saveFood.action',aQuery,
				function(responseObj) {
						if(responseObj.success) {	
							 alert('编辑成功！');
							 window.location.href="page_listMyFoods.action";
						}else  if(responseObj.err.msg){
							 alert('编辑失败：'+responseObj.err.msg);
						}else{
							 alert('编辑失败：服务器异常！');
						}	
			},'json');
		 });
		
	});	 
	
	KindEditor.ready(function(K) {
		window.editor = K.create('#noticeContent',{
			width : '95%',
			items:[
				'fullscreen','|','justifyleft', 'justifycenter', 'justifyright','justifyfull',
				'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
				'italic', 'underline','anchor', 'link', 'unlink','image'
			],
			uploadJson : 'admin/editor2/jsp/upload_json.jsp',
	        fileManagerJson : 'admin/editor2/jsp/file_manager_json.jsp',
	        allowFileManager : true
		});
	});
</script>
</body>
</html>