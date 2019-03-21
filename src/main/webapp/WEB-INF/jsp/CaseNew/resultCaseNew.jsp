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
	<a href="${ctx}/CaseNew/list" class="btn btn-primary">返回</a>
	<div  style="width: 90%; height: 160px">
		<div class="panel-body" style="color:blue">
					<div class="col-sm-2">
						用例名称：&nbsp;${caseResult.caseName}
					</div>
				<c:if test="${caseResult.resultStatus eq 'SUCCESS'}"><div class="col-sm-2" style="color:green">用例结果：&nbsp;SUCCESS</div></c:if>
				<c:if test="${caseResult.resultStatus eq 'FAILURE'}" ><div class="col-sm-2" style="color:red">用例结果：&nbsp;FAILURE</div></c:if>
				<c:if test="${caseResult.resultStatus eq 'SKIP'}" ><div class="col-sm-2" style="color:orange">用例结果：&nbsp;SKIP</div></c:if>				
					<div class="col-sm-2">
							运行环境：&nbsp;${caseResult.runEnv}
					</div>
					<div class="col-sm-2">
						开始时间：&nbsp;${caseResult.startTime}
					</div>
					<div class="col-sm-2">
						结束时间：&nbsp;${caseResult.endTime}
					</div>
					<div class="col-sm-2">
							耗时：&nbsp;${caseResult.costTime}ms
					</div>
		</div>
		<div class="main">
				<table id="cs_table" class="data-table" style="table-layout:fixed;" border="2">
					<thead >
						<tr class="head" style="background: green;">
							<td style="width:20%;">接口名称</td>
							<td style="width:20%;">请求URL</td>
							<td style="width:35%;">参数</td>
							<td style="width:10%;">状态码</td>
							<td style="width:35%;">响应</td>
							<td style="width:30%;">断言内容</td>
							<td style="width:10%;">消耗时间</td>
						</tr>
					</thead>
					<tbody id="group_one">
						<c:if   test="${fn:length(resultModels) <= 0}">
							<tr>								
									<td colspan="7">无</td>									
							</tr>
						</c:if>
						<c:forEach items="${resultModels}" var="e2">
							<tr>
								<td title="${e2.apiName}">${e2.apiName}</td>
								<td title="${e2.apiUrl}">${e2.apiUrl}</td>		
								<c:choose>
									<c:when test="${e2.request eq null}">
									    <td >无</td>     
									</c:when>
									<c:otherwise> 
									   	<td title=${e2.request}>${e2.request}</td>
									</c:otherwise>
								</c:choose>	
								<td title=${e2.statusCode}>${e2.statusCode}</td>		 
								<td title=${e2.response}>${e2.response}</td>	
								<td title=${e2.caseAssert}>${e2.caseAssert}</td>												   																
								<td title=${e2.costTime}ms>${e2.costTime}ms</td>
							</tr>
						</c:forEach>
					</tbody>

				</table>
			</div>
	</div>
</body>
</html>

