<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>关于我们</title>
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
		   <img src="images/author.jpg" style="margin-top:15px"/>
		   <div style="padding:10px 0 10px 0;font-size:14px;color:#555555;line-height:22px;">
			  <p>美食除了最基本的维持生命功能之外，更多的是一种健康积极的生活方式和生活态度；是心情的调节器，建立良好人际关系的润滑剂；更是人的某种记忆，某种心情以及对生活的感悟和体察。这是美食网告诉我们的道理。</p>

			  <p>亲自下厨本身就是在追求一种积极的生活方式，是另一种方式的创作。做人和做菜一样，水平高低往往由心态决定。良好的心情生成良好的态度，创造良好的心情，建立一种正确的生活方式，用美食来转变心情，甚至能改变人生。</p>
			
			  <p>说它是心情的调节器，建立良好人际关系的润滑剂，是因为只有真正懂美食、懂生活的人，才能学会与食物交流，与自己的内心交流。幸福的时候，孤独的时候，甜蜜的时候，疲惫的时候……即使面对的是同一样食材，心情不同，品尝出的味道就不同。日子苦了，加点儿蜜；生活淡了，酸甜苦辣咸都试试，不断变换的餐点，就像是在经历着各色的人生。</p>
			
			  <p>美食能建立良好的人际关系，是因为它能让自己和别人在胃口大开的同时，增加别人对你的好感，从而使你的运势改变。比如：当凝结了你爱心的饭菜摆上饭桌，看到亲朋好友品尝后满意的笑容，我想那就是给你最美好的礼物。而那些初为人父的男士，如果能在爱妻坐月子时亲自下厨，做一份营养滋补又美味可口的“台湾麻油鸡”，亲手送到床边，看着她美滋滋地吃完。我相信，这位刚刚经历人生巨大转变的妻子，一定会对他增添一份信任和亲情。而微妙复杂的婆媳关系，也可能因为一道味道可口的美食改善并建立良好的婆媳关系。</p>
			
			  <p>美食之所以是人的某种记忆，某种心情以及对生活的感悟和体察，是因为这些感悟和体察，能让人更加看清楚自己。因为食物和人一样也是有情绪，有生命，有灵性的。食客有什么样的心情，就能尝出什么样的味道——幸福的味道、失落的味道、坚强的味道、留恋的味道……美食的故事，同时也是食客自己的故事。因为有不同的情感参与其中，食物于是就有了记忆，并因为这种记忆在人心中便有了独一无二的意义。</p>
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