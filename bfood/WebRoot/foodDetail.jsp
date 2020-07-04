<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜谱详情</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/product.css">
<link rel="stylesheet" type="text/css" href="css/message.css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<style type="text/css">
 body,td,div
 {
   font-size:12px;
 }
 #product_info .productShow .picBig{
	width:900px;
	height:460px;
	text-align:center;
	overflow:hidden;
}
</style>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div id="middleBg">
	<!--  产品检索介绍 -->
	 <div id="product_info">
			<div class="productShow">
					<div class="pictext" style="height:460px;margin:0 auto;margin-top:5px;">
							<div class="picBig"><img src="images/foods/<s:property value='#attr.food.food_pic'/>" width="890px" height="450px"/></div>
					</div>
					<div class="typehr"></div>
					<div class="title"><s:property value="#attr.food.food_name"/></div>
					<div class="typehr"></div>
					<div class="intro">
						菜谱类型：<span style="color:black"><s:property value="#attr.food.food_type_name"/></span>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;菜谱作者：<span style="color:black"><s:property value='#attr.food.food_author'/></span>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上架时间：<span style="color:black"><s:property value="#attr.food.food_date.substring(0,10)"/></span>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点击次数：<span style="color:black"><s:property value='#attr.food.food_click'/>次</span>
					</div>
					
					<div class="typehr"></div>
					<div class="title">菜谱步骤介绍</div>
					<div class="typehr"></div>
					<div class="intro">
						<s:property value="#attr.food.food_descShow" escape="false"  />
					</div>
					
					<div class="typehr"></div>
					<div class="title">用户评论信息</div>
					
					 <!-- 信息开始 -->
					 <s:if test="#attr.sblogs!=null && #attr.sblogs.size() > 0">
					 <div style="width:100%;max-height:350px;overflow:auto">
					 <s:iterator value="#attr.sblogs" id="sblog">
					 <div class="messages2" style="width:900px;margin:0px auto;margin-top:5px">
						 <div class="messages_left" style="width:165px">
							<div class="nickName"><s:property value="#sblog.nick_name" /></div>
							<div class="headphoto"><img class="circle"  src="images/head/<s:property value='#sblog.user_pic' />"/></div>
						</div>
				
						<div class="messages_right" style="min-height:100px;width:720px;">
							<div class="time">
								评论时间：<s:property value="#sblog.sblog_date.substring(0,19)" />　
								&nbsp;<img src="images/quote.gif" vlign="middle" style="vertical-align:middle"/>
								<s:a href="#link" id="reply_%{#sblog.sblog_id}_%{#sblog.nick_name}">回复</s:a>
							</div>
				
							<div class="sblog_title">
								 <s:property value="#attr.sblog.sblog_content" />
							</div>
							
							<s:if test="#sblog.replys != null && #sblog.replys.size() >0">
					 		<s:iterator value="#sblog.replys" id="sblogReply">
							<div class="reply">
								 <div class="user"><s:property value="#sblogReply.nick_name" />回复：</div>
								 <div class="reply_con">
									<s:property value="#sblogReply.sblog_content" />
								 </div>
								 <div class="reply_time">回复时间：<s:property value="#sblogReply.sblog_date.substring(0,19)" /></div>
							</div>
							<hr/>
							</s:iterator>
					 		</s:if>
						</div>
					 </div>
					 </s:iterator>
					 </div>
					 </s:if>
					<!-- 信息结束 -->
					
					<!-- 发布留言 -->
					 <div id="addSblogReply" style="width:900px;margin:0 auto;display:block">
					 <form name="info2" id="info2" action="page_addSblog.action" method="post">
					 <input type="hidden" name="paramsSblog.user_id" id="paramsSblog.user_id" value="<s:property value='#attr.userFront.user_id' />"/>
					 <input type="hidden" name="paramsSblog.nick_name" id="paramsSblog.nick_name" value="<s:property value='#attr.userFront.nick_name' />"/>
					 <input type="hidden" name="paramsSblog.nick_name2" id="paramsSblog.nick_name2" value=""/>
					 <input type="hidden" name="paramsSblog.sblog_id2" id="paramsSblog.sblog_id2" value="0"/>
					 <input type="hidden" name="paramsSblog.user_pic" id="paramsSblog.user_pic" value="<s:property value='#attr.userFront.user_pic' />"/>
					 <input type="hidden" name="paramsSblog.food_id" id="paramsSblog.food_id" value="<s:property value='#attr.food.food_id' />"/>
					 <table class="reply_add">
						<tr>
							<td class="theme" colspan="2">评论内容：</td>
						</tr>
						<tr>
							<td id="replyUser" align="left" colspan="2" style="padding-left:10px">发表评论</td>
						</tr>
						<tr>
							<td align="left" colspan="2" style="padding-left:10px">
								<textarea name="paramsSblog.sblog_content" id="noticeContent" style="width:600px;height:100px" class="inputstyle"></textarea>
							</td>
						</tr>
						<tr>
							<td align="left" colspan="2" style="padding-left:10px">
								<img src="Random.jsp" width="50" valign="middle" style="cursor:pointer;vertical-align:middle" title="换一张" id="safecode" border="0" onClick="reloadcode()"/>
								<input type="text" id="random" name="paramsSblog.random" style="width:80px;" class="inputstyle"/>&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" id="addBtn" class="btnstyle" value="提交"/>&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" id="resetBtn" class="btnstyle" value="清空"/>
							</td>
						</tr>
					 </table>
					 </form>
					 </div>
					 <a name="link"></a>
			</div>

			 
			
	 </div>
	 <!--  产品检索 -->
	 
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script type="text/javascript">
var food_id = "<s:property value='#attr.food.food_id' />";
function GoPage()
{
  var pagenum=document.getElementById("goPage").value;
  var patten=/^\d+$/;
  if(!patten.exec(pagenum))
  {
    alert("页码必须为大于0的数字");
    return false;
  }
  window.location.href="page_queryFood.action?paramsFood.food_id="+food_id;
}
function ChangePage(pagenum)
{
	window.location.href="page_queryFood.action?paramsFood.food_id="+food_id;
}

var user_id = "<s:property value='#attr.userFront.user_id' />";
$(document).ready(function(){
	$("a[id^='reply']").bind("click",function(){
		var sblog_id2 = $(this).attr("id").split("_")[1];
		var nick_name2 = $(this).attr("id").split("_")[2];
		$("#replyUser").html("回复："+nick_name2);
		$("#paramsSblog\\.sblog_id2").val(sblog_id2);
		$("#paramsSblog\\.nick_name2").val(nick_name2);
	});
	
	$("#resetBtn").bind("click",function(){
		$("#info2")[0].reset();
		$("#replyUser").html("发表评论");
	});
	
	$("#addBtn").bind("click",function(){
		if(user_id==''){
			alert('请先登录后在进行发表评论！')
			return;
		}
		if($("#noticeContent").val()==''){
			alert('评论内容不能为空！')
			return;
		}
		if($("#random").val()==''){
			alert('验证码不能为空！')
			return;
		}
		
		var aQuery = $("#info2").serialize();  
		$.post('page_addSblog.action',aQuery,
			function(responseObj) {
					if(responseObj.success) {	
						 alert('评论成功！');
						 window.location.reload();
					}else  if(responseObj.err.msg){
						 alert('评论失败：'+responseObj.err.msg);
					}else{
						 alert('评论失败：服务器异常！');
					}	
		},'json');
	});
	
});
</script>
</body>
</html>