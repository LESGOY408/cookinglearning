<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>联系我们</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
</script>
<style type="text/css">
 body,td,div
 {
   font-size:12px;
 }
 #infoField,#loginField{
 	line-height:35px;
 }
</style>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div id="middle">
	<div style="padding:10px 0 10px 0;font-size:28px;font-weight:bold;text-align:center;">
		看到你中意的食谱只需轻轻一点，选择你最喜欢的食谱和烹饪技巧。在美食网尽情享受美味吧。
	</div>
</div>
<hr class="sep2"/>
<div id="middle">	
	<div id="middle_left">
		   <img src="images/contactUs.jpg" style="margin-top:15px"/>
		   <div style="padding:10px 0 10px 0;font-size:18px;color:#555555;line-height:50px;">
		   		<strong>联系电话：</strong>0311-88888888
		   		<br/><strong>联系邮箱：</strong>meishiwang@163.com
		   		<br/><strong>联系地址：</strong>泉州市鲤城区南环路金龙街道媒人桥2号
		   </div>
	</div>
	
	<div id="middle_right">
	      <div class="info_title">热门菜谱</div>
		  <s:iterator value="#attr.foods2" id="food">
		  <div class="info_pic"><img src="images/foods/<s:property value='#food.food_pic'/>" /></div>
		  <div class="info_con1">
			<s:a href="page_queryFood.action?paramsFood.food_id=%{#food.food_id}" target="_blank" title="%{#food.food_name}">
				<s:property value="#food.food_name.length()>25?#food.food_name.substring(0,24)+'...':#food.food_name"/>
			</s:a>
		  </div>
		  </s:iterator>
	</div>
	
</div>
<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>