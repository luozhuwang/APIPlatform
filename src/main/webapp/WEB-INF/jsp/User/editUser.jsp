<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>编辑用户页面</title>
<link rel="stylesheet" href="${basePath}css/mail/table.css" />
<link rel="stylesheet" href="${basePath}css/table-td.css" />
<script type="text/javascript" src="${basePath}jQuery/2.1.4/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${basePath}bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${basePath}bootstrap/3.3.7/css/bootstrap-datepicker3.min.css">
<script type="text/javascript" src="${basePath}bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div  class="widget-content nopadding">
		<sf:form action="${ctx}/User/editAction" method="post" modelAttribute="userModel">
			<div>
				<div class="panel-body">
					<label class="col-sm-1">登陆名</label>
					<div class="col-sm-2">
						<input  id="id" name="id" type="hidden" value="${userModel.id}">
						<input class="form-control" id="user" name="user" readonly="readonly" type="text" value="${userModel.user}" style="width: 190px; height: 30px">
					</div>
				</div>
				<div class="panel-body">
					<label class="col-sm-1">用户名</label>
					<div class="col-sm-2">
						<input type="text" placeholder="用户名" value="${userModel.userName}" name="userName" id="userName" style="width: 190px; height: 30px"> 				
						<sf:errors path="userName" cssClass="errorMsg" cssStyle="color:red"></sf:errors>
					</div>
				</div>
				<div class="panel-body">
					<label class="col-sm-1">密码</label>
					<div class="col-sm-2">
						<input type="text" placeholder="密码" value="${userModel.userPwd}" name="userPwd" id="userPwd" style="width: 190px; height: 30px"> 				
						<sf:errors path="userPwd" cssClass="errorMsg" cssStyle="color:red"></sf:errors>	 
					</div>
				</div>
				<div class="panel-body">
					<label class="col-sm-1">邮箱</label>
					<div class="col-sm-2">
						<input type="text" placeholder="邮箱" value="${userModel.email}" name="email" id="email" style="width: 190px; height: 30px">
						<sf:errors path="email" cssClass="errorMsg" cssStyle="color:red"></sf:errors>	 
					</div>
				</div>
			</div>
			<br>
			<div align="center" class="col-sm-2">
					<button type="submit" class="btn btn-success">保存</button>
					<a type="button" class="btn btn-default" href="${ctx}/User/list">返回</a>
			</div>							
		</sf:form>				
	</div>
</body>
</html>

