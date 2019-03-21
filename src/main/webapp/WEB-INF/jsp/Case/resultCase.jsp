<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用例运行结果</title>
<link rel="stylesheet" href="${basePath}css/mail/table.css" />
<link rel="stylesheet" href="${basePath}css/table-td.css" />
<script type="text/javascript" src="${basePath}jQuery/2.1.4/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${basePath}bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${basePath}bootstrap/3.3.7/css/bootstrap-datepicker3.min.css">
<script type="text/javascript" src="${basePath}bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<a href="${ctx}/Case/list" class="btn btn-success">返回</a>
	<div  style="width: 70%; height: 160px">
		<div class="panel-body">
			<label class="col-sm-1 control-label">用例名称</label>
					<div class="col-sm-2">
						${APIcase.caseName}
					</div>				
			<label class="col-sm-1 control-label">用例结果</label>					
				<c:if test="${caseResult.resultStatus eq 'SUCCESS'}"><div class="col-sm-2" style="color:green ">SUCCESS</div></c:if>
				<c:if test="${caseResult.resultStatus eq 'FAILURE'}" ><div class="col-sm-2" style="color:red">FAILURE</div></c:if>
				<c:if test="${caseResult.resultStatus eq 'SKIP'}" ><div class="col-sm-2" style="color:orange">SKIP</div></c:if>				
			<label class="col-sm-1 control-label">开始时间</label>
					<div class="col-sm-2">
						${caseResult.startTime}
					</div>
				
			<label class="col-sm-1 control-label">结束时间</label>
					<div class="col-sm-2">
						${caseResult.endTime}
					</div>
		</div>
		<div class="main">
				<table id="cs_table" class="data-table" style="table-layout:fixed;" border="2">
					<thead >
						<tr class="head" style="background: green;">
							<td style="width:20%;">接口名称</td>
							<td style="width:20%;">请求URL</td>
							<td style="width:35%;">参数</td>
							<td style="width:35%;">响应</td>
							<td style="width:30%;">断言内容</td>
							<td style="width:10%;">消耗时间</td>
						</tr>
					</thead>
					<tbody id="group_one">
							<tr>
								<td title="${API.apiName}">${API.apiName}</td>
								<td title="${API.apiUrl}">${API.apiUrl}</td>								
									<c:choose>
											<c:when test="${caseResult.request eq null}">
									    <td >无</td>     
									   </c:when>
									   <c:otherwise> 
									   	<td title=${caseResult.request}>${caseResult.request}</td>
									   </c:otherwise>
									</c:choose>				 
								<td title=${caseResult.response}>${caseResult.response}</td>
								<td title=${APIcase.caseAssert}>${APIcase.caseAssert}</td>
								<td title="${caseResult.costTime}ms">${caseResult.costTime}ms</td>
							</tr>
					</tbody>

				</table>
			</div>
	</div>
</body>
</html>

