<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>我的菜谱信息</title>
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
			<div class="title">个人中心  &gt;&gt;  我的菜谱信息</div>
			<div style="margin-top:5px">
			     <form id="info" name="info" action="page_listMyFoods.action" method="post" style="width:100%;height:100%">
				 <input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>    
				 <table class="ptable" style="margin-bottom:5px;">
				    <tr>
					     <td colspan="8" align="right">
					            菜谱名称：
					      <input type="text" id="paramsFood.food_name" name="paramsFood.food_name" value="${paramsFood.food_name}" class="inputstyle" Style="width:100px;"/>&nbsp;
					            菜谱类型：
					      <s:select id="paramsFood.food_type_id" name="paramsFood.food_type_id" value="%{#attr.paramsFood.food_type_id}" 
					      		list="#attr.foodTypes" listKey="food_type_id" listValue="food_type_name" 
					      		cssClass="inputstyle" cssStyle="width:100px;" headerKey="0" headerValue="请选择">
					      </s:select>&nbsp;
					      <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
					      <input type="button" value="新增" onclick="window.location.href='page_addFoodShow.action';" class="btnstyle"/>
					     </td>
					</tr>
					<tr class="head_text">
					     <td width="" align="center">菜谱名称</td>
					     <td width="" align="center">菜谱类型</td>
					     <td width="" align="center">菜谱作者</td>
					     <td width="" align="center">上架时间</td>
					     <td width="" align="center">点击次数</td>
					     <td width="" align="center">操作</td>
					</tr>
					<s:if test="#attr.foods!=null && #attr.foods.size()>0">
					   <s:iterator value="#attr.foods" id="food" status="status">
					   <tr> 
					     <td width="200" align="center"><s:property value="#food.food_name"/></td>
					     <td width="" align="center"><s:property value="#food.food_type_name"/></td>
					     <td width="" align="center"><s:property value="#food.food_author"/>&nbsp;</td>
					     <td width="" align="center"><s:property value="#food.food_date"/></td>
					     <td width="" align="center"><s:property value="#food.food_click"/></td>
					     <td width="" align="center">
					        <s:a id="delFood_%{#food.food_id}" href="javascript:void(0)">删除</s:a>&nbsp;
					        <s:a href="page_editFood.action?paramsFood.food_id=%{#food.food_id}">编辑</s:a>
					     </td>
					   </tr> 
					   </s:iterator>
					</s:if>
				    <s:else>
				    <tr>
				      <td height="60" colspan="8" align="center"><b>&lt;不存在我的菜谱信息&gt;</b></td>
				    </tr>
				    </s:else>
				 </table>
				 </form>
			</div>
			<div class="pages">
				<jsp:include page="page.jsp"></jsp:include>
			</div>
		</div>
	 <!--  购物车 -->
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script language="javascript" type="text/javascript">
	function serch()
	{
	   document.info.submit();
	}
	function GoPage()
	{
	  var pagenum=document.getElementById("goPage").value;
	  var patten=/^\d+$/;
	  if(!patten.exec(pagenum))
	  {
	    alert("页码必须为大于0的数字");
	    return false;
	  }
	  document.getElementById("pageNo").value=pagenum;
	  document.info.submit();
	}
	function ChangePage(pagenum)
	{
	    document.getElementById("pageNo").value=pagenum;
		document.info.submit();
	}
	$(document).ready(function(){
		$("a[id^='delFood']").bind('click',function(){
		    if(confirm('确认删除吗!?'))
		    {
		    	var food_id=$(this).attr('id').split('_')[1];
		    	var aQuery = {
						'paramsFood.ids':food_id
				};  
				$.post('page_delFood.action',aQuery,
					function(responseObj) {
							if(responseObj.success) {	
								 alert('删除成功！');
								 window.location.reload();
							}else  if(responseObj.err.msg){
								 alert('删除失败：'+responseObj.err.msg);
							}else{
								 alert('删除失败：服务器异常！');
							}	
				},'json');
		    }
		 });
	});
</script>
</body>
</html>