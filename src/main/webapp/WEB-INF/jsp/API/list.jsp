<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>
<!DOCTYPE "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>接口列表管理</title>
<link rel="stylesheet" href="${basePath}css/mail/table.css" />
<link rel="stylesheet" href="${basePath}css/table-td.css" />
<script type="text/javascript" src="${basePath}jQuery/2.1.4/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${basePath}bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${basePath}bootstrap/3.3.7/css/bootstrap-datepicker3.min.css">
<script type="text/javascript" src="${basePath}bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
function clean() {
	$("#id").val("");
	$("#apiName").val("");
	$("#apiMethod").val("");
	$("#apiStatus").val("");
}
function del(case_id){
	var id=case_id;
	var r=confirm("是否刪除?");
	if(r==true){
		$.ajax({
			  type: "POST",
			  url: "del/"+id,
			  success : function(result) {  	    	        
	                  alert("数据已成功删除！");
	                  window.location.reload();
		        },error:function(result){			 
					alert("接口被用例已引用，删除失败");
				}  
			});
	}
}
</script>
</head>
<body>
	<div class="all">
		<form action="${ctx}/Interface/list" method="post">
			<div class="whole">
				<div class="littletitle">接口列表</div>
				接口名称：<input type="text" placeholder="接口名称" value="${apiName}" name="apiName" id="apiName" style="width: 190px; height: 30px"> 
				请求方法：<select id="apiMethod"  class="selectpicker" name="apiMethod" style="width: 80px;height: 30px">
					<option value="">全部</option>
					<option value="get"
						<c:if test="${apiMethod eq 'get'}">selected="selected"</c:if>>get</option>
					<option value="post"
						<c:if test="${apiMethod eq 'post'}">selected="selected"</c:if>>post</option>
					<option value="post1"
						<c:if test="${apiMethod eq 'post1'}">selected="selected"</c:if>>post加密</option>
				</select>
				状态：<select id="apiStatus"  class="selectpicker" name="apiStatus" style="width: 80px;height: 30px">
					<option value="">全部</option>
					<option value="0"
						<c:if test="${apiStatus eq '0'}">selected="selected"</c:if>>启用</option>
					<option value="1"
						<c:if test="${apiStatus eq '1'}">selected="selected"</c:if>>暂停</option>
				</select>
				<button type="submit" class="btn btn-primary">查询</button>
				<button type="button" class="btn btn-default" onclick="clean();">重置</button>
				<a class="btn btn-success" href="${ctx}/Interface/addAPI" >新增</a>
			</div>
			<div class="main">
				<table id="cs_table" class="data-table" style="table-layout:fixed;" border="1">
					<thead>
						<tr class="head">
							<td style="width:5%;">编号</td>
							<td style="width:20%;">接口名称</td>
							<td style="width:20%;">请求URL</td>
							<td style="width:5%;">请求方法</td>
							<td style="width:30%;">参数</td>
							<td style="width:5%;">状态</td>
							<td style="width:15%;">操作</td>
						</tr>
					</thead>
					<tbody id="group_one">
					<c:if   test="${fn:length(APIList.list) <= 0}">
							<tr>								
									<td colspan="7">无</td>									
							</tr>
					</c:if>	
						<c:forEach items="${APIList.list}" var="e2" varStatus="status">
							<tr>
								<td>${status.index + 1}</td>
								<td title="${e2.apiName}">${e2.apiName}</td>
								<td title="${e2.apiHost}${e2.apiUrl}">${e2.apiHost}${e2.apiUrl}</td>
								<td>
									<c:if test="${e2.apiMethod eq 'get'}">get</c:if>
									<c:if test="${e2.apiMethod eq 'post'}">post</c:if>
									<c:if test="${e2.apiMethod eq 'post1'}">post加密</c:if>
								</td>
								<c:choose>
									   <c:when test="${e2.apiParams eq ''}">  
									    <td >无</td>     
									   </c:when>
									   <c:otherwise> 
									   	<td title=${e2.apiParams}>${e2.apiParams}</td>
									   </c:otherwise>
								</c:choose>
								<td>
									<c:if test="${e2.apiStatus eq '0'}">启用</c:if>
									<c:if test="${e2.apiStatus eq '1'}">暂停</c:if>
								</td>									
								<td>
<%-- 									<a class="btn btn-warning"  href="run/${e2.id}" target="_blank">测试</a> --%>
									<a class="btn btn-info" href="editAPI/${e2.id}" >编辑</a>
									<button  class="btn btn-danger" type="button"  onclick="del(${e2.id});" >删除</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>

				</table>
				<hr>
			</div>
		</form>
				<div align='right'>
			<!-- 分页文字信息 ：拿到控制器处理请求时封装在pageInfo里面的分页信息-->
			<div class="col-md-6">
				当前${APIList.pageNum}页,共${APIList.pages }页,总${APIList.total }条记录
			</div>
			<!-- 分页码 -->
			<div class="col-md-6">
				<ul class="pagination">
					<!-- 
                        1.pageContext.request.contextPath表示当前项目路径，采用的是绝对路径表达方式。一般为http:localhost:8080/项目名 。
                        2.首页，末页的逻辑：pn=1访问第一次，pn=${pageInfo.pages}访问最后一页
                      -->
					<li><a
						href="${pageContext.request.contextPath}/Interface/list?apiName=${apiName}&apiMethod=${apiMethod}&pagenum=1">首页</a>
					</li>
					<!-- 如果还有前页就访问当前页码-1的页面， -->
					<c:if test="${APIList.hasPreviousPage}">
						<li><a
							href="${pageContext.request.contextPath}/Interface/list?apiName=${apiName}&apiMethod=${apiMethod}&pagenum=${APIcaseList.pageNum-1}">
								<span>上一页</span>
						</a></li>
					</c:if>
					<li>
						<!--遍历所有导航页码，如果遍历的页码页当前页码相等就高亮显示，如果相等就普通显示  --> <c:forEach
							items="${APIList.navigatepageNums }" var="page_Nums">
							<c:if test="${page_Nums==APIList.pageNum }">
								<li class="active"><a href="#">${page_Nums}</a></li>
							</c:if>
							<c:if test="${page_Nums!=APIList.pageNum }">
								<li><a
									href="${pageContext.request.contextPath}/Interface/list?apiName=${apiName}&apiMethod=${apiMethod}&pagenum=${page_Nums}">${page_Nums}</a></li>
							</c:if>
						</c:forEach>
					</li>
					<!-- 如果还有后页就访问当前页码+1的页面， -->
					<c:if test="${APIList.hasNextPage}">
						<li><a
							href="${pageContext.request.contextPath}/Interface/list?apiName=${apiName}&apiMethod=${apiMethod}&pagenum=${APIList.pageNum+1}">
								<span>下一页</span>
						</a></li>
					</c:if>
					<li><a
						href="${pageContext.request.contextPath}/Interface/list?apiName=${apiName}&apiMethod=${apiMethod}&pagenum=${APIList.pages}">末页</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>

