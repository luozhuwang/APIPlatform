<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增变量页面</title>
<link rel="stylesheet" href="${basePath}css/mail/table.css" />
<link rel="stylesheet" href="${basePath}css/table-td.css" />
<script type="text/javascript" src="${basePath}jQuery/2.1.4/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${basePath}bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${basePath}bootstrap/3.3.7/css/bootstrap-datepicker3.min.css">
<script type="text/javascript" src="${basePath}bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="all">
		<sf:form action="${ctx}/Var/addAction" method="post" modelAttribute="envVarModel">
			<div class="whole">
				<div class="littletitle">添加变量</div>
				变量名：<input type="text" placeholder="变量名" value="${envVarModel.varName}" name="varName" id="varName" style="width: 190px; height: 30px">
				<sf:errors path="varName" cssClass="errorMsg" cssStyle="color:red"></sf:errors>	 				
				变量值：<input type="text" placeholder="变量值" value="${envVarModel.varValue}" name="varValue" id="varValue" style="width: 190px; height: 30px">
				<sf:errors path="varValue" cssClass="errorMsg" cssStyle="color:red"></sf:errors>	
				<button type="submit" class="btn btn-success">保存</button>
				<a type="button" class="btn btn-default" href="${ctx}/Var/list">返回</a>							
			</div>			
		</sf:form>				
	</div>
</body>
</html>

