<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<div id="top">
<div id="nav">
	<div class="navTitle">❥ 食谱集享</div>
	<ul>
	   <li style="background-image:none"><a href="page_index.action">  首 页</a></li>
	   <li><a href="page_listFoods.action">美食展览</a></li>
       <li><a href="page_aboutUs.action">关于我们</a></li>
       <li><a href="page_contactUs.action">联系我们</a></li>
	   <c:if test="${userFront!=null}">
       <li onmouseover="showmenu('user')" onmouseout="hidemenu('user')"><a href="#">用 户</a>  
            <ul id="user" class="dropMenu">  
       			<li><a href="page_myInfo.action">用户中心</a></li>  
                <li><a id="loginOutBtn" href="javascript:void(0)">退出网站</a></li>  
            </ul>  
        </li> 
        </c:if>
        <c:if test="${userFront==null}">
       	<li><a href="login.jsp">登 录</a></li>  
       	<li><a href="reg.jsp">注 册</a></li>  
        </c:if>
	</ul>
</div>
</div>
<hr class="sep"/>
<script type="text/javascript" src="js/top.js"></script>
