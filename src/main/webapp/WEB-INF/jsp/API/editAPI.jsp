<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>
<!DOCTYPE "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>编辑接口</title>
<link rel="stylesheet" href="${basePath}css/mail/table.css" />
<script type="text/javascript"
	src="${basePath}jQuery/2.1.4/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${basePath}bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${basePath}bootstrap/3.3.7/css/bootstrap-datepicker3.min.css">
<script type="text/javascript"
	src="${basePath}bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="widget-content nopadding" align="center">
		<sf:form  action="${ctx}/Interface/editAPIAction" method="post" class="form-horizontal" modelAttribute="APImodel">
			<div class="panel panel-primary" style="width: 70%; height: 260px">
				<div class="panel-heading">
					<h3 class="panel-title">基本信息</h3>
				</div>
				<div class="panel-body">
					<label class="col-sm-1 control-label">接口名称
					</label>
					<div class="col-sm-5">
						<input class="form-control" id="id" name="id" type="hidden" value="${APImodel.id}">
						<input class="form-control" id="apiName" name="apiName" type="text" value="${APImodel.apiName}" placeholder="接口名称">
						<sf:errors path="apiName" cssClass="errorMsg" cssStyle="color:red"></sf:errors>
					</div>
				</div>
				<div class="panel-body">
					<label class="col-sm-1 control-label">请求环境
						<a  data-toggle="tooltip" title="www.testproject.com映射固定IP'172.20.20.160',配置为http,需要带端口
www.xiaoniu88.com映射环境NginxIP',配置为https,不需要带端口" style="color:red">域名帮助</a>
					</label>
					<div class="col-sm-3">
						<input class="form-control" id="apiHost" type="text" name="apiHost"  value="${APImodel.apiHost}" placeholder="请求环境">
						<sf:errors path="apiHost" cssClass="errorMsg" cssStyle="color:red"></sf:errors>
					</div>
				
					<label class="col-sm-1 control-label">请求地址</label>
					<div class="col-sm-4">
						<input class="form-control" id="apiUrl" type="text" name="apiUrl" value="${APImodel.apiUrl}" placeholder="请求地址">
						<sf:errors path="apiUrl" cssClass="errorMsg" cssStyle="color:red"></sf:errors>
					</div>
				</div>
				<div class="panel-body">
						<label class="col-sm-1 control-label">请求方式</label>
						<div class="col-sm-2">
							<select id="apiMethod" name="apiMethod"  class="selectpicker form-control" style="height: 35px">
								<option value="get"
									<c:if test="${APImodel.apiMethod eq 'get'}">selected="selected"</c:if>>get</option>
								<option value="post"
									<c:if test="${APImodel.apiMethod eq 'post'}">selected="selected"</c:if>>post</option>
								<option value="post1"
									<c:if test="${APImodel.apiMethod eq 'post1'}">selected="selected"</c:if>>post加密</option>
							</select>
						</div>							
						<label class="col-sm-1 control-label">状态</label>
						<div class="col-sm-2">						
							<label class="radio-inline">
								<input type="radio" name="apiStatus" id="apiStatus" value="0" <c:if test="${APImodel.apiStatus eq '0'}">checked</c:if>> 启用
							</label>
							<label class="radio-inline">
								<input type="radio" name="apiStatus" id="apiStatus"  value="1" <c:if test="${APImodel.apiStatus eq '1'}">checked</c:if>>暂停
							</label>
						</div>
				</div>
			</div>
			<!-- 
			<div class="panel panel-info" style="width: 70%; height: 160px">
				<div class="panel-heading">
						<h3 class="panel-title">请求头</h3>
				</div>
				<div class="panel-body">
					<textarea class="form-control" rows="4" style="resize: none;" id="apiHeaders" name="apiHeaders"   placeholder="请求头">${API.apiHeaders}</textarea>
				</div>
			</div>
			 -->
			<div class="panel panel-warning" style="width: 70%; height: 160px">
					<div class="panel-heading">
						<h3 class="panel-title">请求参数</h3>
					</div>
					<div class="panel-body">
						<textarea class="form-control" rows="4" style="resize: none;" id="apiParams" name="apiParams"  placeholder="请求参数">${APImodel.apiParams}</textarea>
<%-- 						<sf:errors path="apiParams" cssClass="errorMsg" cssStyle="color:red"></sf:errors> --%>
					</div>
			</div>
			<div class="panel panel-danger" style="width: 70%; height: 160px">
					<div class="panel-heading">
						<h3 class="panel-title">备注</h3>
					</div>
					<div class="panel-body" >
						<textarea class="form-control" rows="4" style="resize: none;" id="remark" name="remark"  placeholder="备注">${APImodel.remark}</textarea>
					</div>
			</div>
			<input type="submit" class="btn btn-success btn-lg" value="保存"/>
			<a type="button" class="btn btn-default btn-lg" href="${ctx}/Interface/list">返回</a>
		</sf:form>
	</div>
</body>
</html>

