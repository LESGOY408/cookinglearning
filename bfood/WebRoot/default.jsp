<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
$(function(){
	var ggIndex = 1;
	setInterval(function(){
		ggIndex++;
		if(ggIndex>3)ggIndex=1;
		$(".gg1,.gg2,.gg3").hide();
		$(".gg"+ggIndex).show();
	},2000);
})
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
		  <s:iterator value="#attr.foods" id="food">
		  <div class="info_pic">
		  <s:a href="page_queryFood.action?paramsFood.food_id=%{#food.food_id}" target="_blank" title="%{#food.food_name}">
		  	<img src="images/foods/<s:property value='#food.food_pic'/>" style="border:0px"/>
		  </s:a>
		  </div>
		  <div class="info_con1">
			<s:a href="page_queryFood.action?paramsFood.food_id=%{#food.food_id}" target="_blank" title="%{#food.food_name}">
				<s:property value="#food.food_name.length()>25?#food.food_name.substring(0,24)+'...':#food.food_name"/>
			</s:a>
			<div class="time">
				发布时间：<s:property value="#food.food_date.substring(0,10)"/>
				<br/>
				创作人：<s:property value="#food.food_author"/>
			</div>
		  </div>
		  <div class="info_con2">
		  		<s:property value="#food.food_descShow" escape="false"/>
		  </div>
		  <div class="info_sep">&nbsp;</div>
		  </s:iterator>
	</div>
	
	<div id="middle_right">
		  <div class="info_title">❥ 猜你喜欢</div>
		  <div class="info_pic gg1">
		  <s:a href="http://www.meishichina.com/Health/Baby/201609/182038.html" target="_blank" title="宝宝成长必需的营养素 你家娃补对了吗？">
		  	<img src="images/foods/gg1.png"  style="border:0px"/>
		  </s:a>
		  </div>
		  <div class="info_con1 gg1">
			<s:a href="http://www.meishichina.com/Health/Baby/201609/182038.html" target="_blank" title="宝宝成长必需的营养素 你家娃补对了吗？">
				宝宝成长必需的营养素
			</s:a>
		  </div>
		  
		  <div class="info_pic gg2" style="display:none">
		  <s:a href="http://www.meishichina.com/Health/Baby/201609/182035.html" target="_blank" title="橙子营养丰富 孕妇适量服用好处多">
		  	<img src="images/foods/gg2.png"  style="border:0px"/>
		  </s:a>
		  </div>
		  <div class="info_con1 gg2" style="display:none">
			<s:a href="http://www.meishichina.com/Health/Baby/201609/182035.html" target="_blank" title="橙子营养丰富 孕妇适量服用好处多">
				橙子营养丰富 孕妇适量服用
			</s:a>
		  </div>
		  
		  <div class="info_pic gg3" style="display:none">
		  <s:a href="http://www.meishichina.com/Health/Baby/201609/181843.html" target="_blank" title="儿童健康发育 莫要拒绝脂肪">
		  	<img src="images/foods/gg3.png"  style="border:0px"/>
		  </s:a>
		  </div>
		  <div class="info_con1 gg3" style="display:none">
			<s:a href="http://www.meishichina.com/Health/Baby/201609/181843.html" target="_blank" title="儿童健康发育 莫要拒绝脂肪">
				儿童健康发育 莫要拒绝脂肪
			</s:a>
		  </div>
		  
	      <div class="info_title">❥ 热门菜谱</div>
		  <s:iterator value="#attr.foods2" id="food">
		  <div class="info_pic">
		  <s:a href="page_queryFood.action?paramsFood.food_id=%{#food.food_id}" target="_blank" title="%{#food.food_name}">
		  	<img src="images/foods/<s:property value='#food.food_pic'/>"  style="border:0px"/>
		  </s:a>
		  </div>
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