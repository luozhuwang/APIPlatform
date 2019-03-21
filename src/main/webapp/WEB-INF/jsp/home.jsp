<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>在线接口测试平台</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${basePath}css/mainframe.css" />
<script type="text/javascript"  src="${basePath}jQuery/2.1.1/jquery.js"></script>
<script  type="text/javascript" src="${basePath}jQuery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}js/fastLiveFilter.js"></script>
<link rel="stylesheet" type="text/css" href="${basePath}bootstrap/3.3.7/css/bootstrap.min.css"></link>
<script type="text/javascript" src="${basePath}bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
$(".nav li").click(function() {
    $(".active").removeClass('active');
    $(this).addClass("active");
});
</script>
<script>
$(function() {
        $('#search_input').fastLiveFilter('.nav.nav-list.collapse.secondmenu');
});
</script>
</head>
<body>
<!--顶部导航栏部分-->
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" title="在线接口测试平台" href="http://www.xiaoniu88.com"><font color="white">在线接口测试平台</font></a>
         </div>
<!--         <div class="collapse navbar-collapse"> -->
<!--            <ul class="nav navbar-nav navbar-right"> -->
<!--                <li role="presentation"> -->
<%--                    <a class="glyphicon glyphicon-user"><font color="white">用户：${LoginuserName}</font></a> --%>
<!--                </li> -->
<!--                <li> -->
<!--                    <a href="outLogin"  class="glyphicon glyphicon-log-out"> -->
<!--                          <font color="white">退出登录</font></a> -->
<!--                 </li> -->
<!--             </ul> -->
<!--        </div>       -->
    </div>      
</nav>
<!-- 中间主体内容部分 -->
<div class="pageContainer">
     <!-- 左侧导航栏 -->
     <div class="pageSidebar">
     <!-- 菜单过滤，待完善 -->
<!--          <input type="text"  class="form-control" id="search_input" name="search_input" placeholder="Search ..." /> -->
         <ul id="main-nav" class="nav nav-tabs nav-stacked" style="Display:inline">
         			<li>
         				<a  class="glyphicon glyphicon-home" id="links" target="iframename" href="${basePath}CaseSet/Recent">首页</a>
         			</li>
					<li ><a href="#FirstMenu" class="nav-header collapsed"
						data-toggle="collapse"><span class="glyphicon glyphicon-align-justify" aria-hidden="true"></span>接口管理 <span
							class="pull-right glyphicon glyphicon-chevron-down"></span>
					</a>
						<ul id="FirstMenu" class="nav nav-list collapse secondmenu" >
							<li><a  class="glyphicon glyphicon-star" aria-hidden="true" id="links" target="iframename" href="${ctx}/Interface/list">接口管理</a></li>
							<li><a class="glyphicon glyphicon-star" aria-hidden="true" id="links" target="iframename" href="${ctx}/CaseNew/list">用例管理</a></li>
							<li><a class="glyphicon glyphicon-star" aria-hidden="true" id="links" target="iframename" href="${ctx}/CaseSet/list">用例集管理</a></li>
						</ul></li>	
					<li>
						<a href="#SecondMenu" class="nav-header collapsed"
							data-toggle="collapse"><span class="glyphicon glyphicon-align-justify" aria-hidden="true"></span>基础信息管理 <span
								class="pull-right glyphicon glyphicon-chevron-down"></span>
						</a>
						<ul id="SecondMenu" class="nav nav-list collapse secondmenu" >
							<li><a class="glyphicon glyphicon-star" aria-hidden="true" id="links" target="iframename" href="${ctx}/Var/list">初始化变量管理</a></li>	
							<li><a class="glyphicon glyphicon-star" aria-hidden="true" id="links" target="iframename" href="${ctx}/User/list">用户管理</a></li>							
						</ul>
					</li>
					<li>
						<span style="color:red">&nbsp;&nbsp;&nbsp;&nbsp;运行环境</span>
						<select id="envTitle" name="envTitle" class="selectpicker form-control" value="">
							<c:forEach items="${EnvList}" var="r">
								<option value="${r.envTitle}">${r.envTitle}</option>
							</c:forEach>
						</select>
					</li>			
				</ul>
     </div>
     <!-- 左侧导航和正文内容的分隔线 -->
     <div class="splitter"></div>
     <!-- 正文内容部分 -->
     <div class="pageContent">
<%--          <iframe src="${basePath}html/welcome.html" id="iframename" name="iframename" frameborder="0" width="100%"  height="100%" frameBorder="0" ></iframe> --%>
				<iframe src="${basePath}CaseSet/Recent" id="iframename" name="iframename" frameborder="0" width="100%"  height="100%" frameBorder="0" ></iframe>
     </div>
 </div>
 <!-- 底部页脚部分 -->
 <div class="footer">
	 <div>
		 	<ul>
	     		<li>
	                <a target="_blank" href="http://172.20.20.160:8580/xiaoniu_web_tm/IPManager/ip_info/query?ip=172.20.17.69" class="til">★IP查询信息工具</a>
	                <a target="_blank" href="http://172.20.20.113:8280/xn-springmvc-prj/prjcheck.html">★测试开发组项目在线审阅</a>
	                <a target="_blank" href="http://172.20.20.160:9999/admin/plugin/user_data">★测试数据构造工具</a>
	
	            </li>
	        </ul>
	     <p class="text-center">
	         2018 &copy; Luojuwang
	     </p>
	 </div>
 </div>
</body>
</html>