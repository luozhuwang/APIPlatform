<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户列表管理</title>
<link rel="stylesheet" href="${basePath}css/mail/table.css" />
<link rel="stylesheet" href="${basePath}css/table-td.css" />
<script type="text/javascript" src="${basePath}jQuery/2.1.4/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${basePath}bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${basePath}bootstrap/3.3.7/css/bootstrap-datepicker3.min.css">
<script type="text/javascript" src="${basePath}bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
function clean() {
	$("#userName").val("");
}
function del(userId){
	var id=userId;
	var r=confirm("是否刪除?");
	if(r==true){
		$.ajax({
			  type: "POST",
			  url: "del/"+id,
			  success : function(result) {  	    	        
	                  alert("删除用户成功！");
	                  window.location.reload();
		      },error:function(result){			 
					alert("当前用户为登陆用户，不可删除");
			  }		      
			});
	}
}
</script>
</head>
<body>
	<div class="all">
		<form action="${ctx}/User/list" method="post">
			<div class="whole">
				<div class="littletitle">用户列表</div>
				用户名：<input type="text" placeholder="用户名" value="${userName}" name="userName" id="userName" style="width: 190px; height: 30px"> 				
				<button type="submit" class="btn btn-primary">查询</button>
				<button type="button" class="btn btn-default" onclick="clean();">重置</button>				
			</div>
			<div class="main">
				<table id="cs_table" class="data-table" style="table-layout:fixed;" border="1">
					<thead>
						<tr class="head">
							<td style="width:5%;">编号</td>
							<td style="width:20%;">登陆名</td>
							<td style="width:20%;">用户名</td>
							<td style="width:20%;">注册IP</td>
							<td style="width:20%;">邮箱</td>							
							<td style="width:15%;">操作</td>
						</tr>
					</thead>
					<tbody id="group_one">
					<c:if   test="${fn:length(userList.list) <= 0}">
							<tr>								
									<td colspan="6">无</td>									
							</tr>
					</c:if>	
						<c:forEach items="${userList.list}" var="e2" varStatus="status">
							<tr>
								<td>${status.index + 1}</td>
								<td title="${e2.user}">${e2.user}</td>
								<td title="${e2.userName}">${e2.userName}</td>
								<td title="${e2.userIp}">${e2.userIp}</td>
								<td title="${e2.email}">${e2.email}</td>																							
								<td>
									<a class="btn btn-info" href="editUser/${e2.id}" >编辑</a>
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
				当前${userList.pageNum}页,共${userList.pages }页,总${userList.total }条记录
			</div>
			<!-- 分页码 -->
			<div class="col-md-6">

				<ul class="pagination">
					<!-- 
                        1.pageContext.request.contextPath表示当前项目路径，采用的是绝对路径表达方式。一般为http:localhost:8080/项目名 。
                        2.首页，末页的逻辑：pn=1访问第一次，pn=${pageInfo.pages}访问最后一页
                      -->
					<li><a
						href="${pageContext.request.contextPath}/User/list?userName=${userName}&pagenum=1">首页</a>
					</li>
					<!-- 如果还有前页就访问当前页码-1的页面， -->
					<c:if test="${userList.hasPreviousPage}">
						<li><a
							href="${pageContext.request.contextPath}/User/list?userName=${userName}&pagenum=${userList.pageNum-1}">
								<span>上一页</span>
						</a></li>
					</c:if>
					<li>
						<!--遍历所有导航页码，如果遍历的页码页当前页码相等就高亮显示，如果相等就普通显示  --> <c:forEach
							items="${userList.navigatepageNums }" var="page_Nums">
							<c:if test="${page_Nums==userList.pageNum }">
								<li class="active"><a href="#">${page_Nums}</a></li>
							</c:if>
							<c:if test="${page_Nums!=userList.pageNum }">
								<li><a
									href="${pageContext.request.contextPath}/User/list?userName=${userName}&pagenum=${page_Nums}">${page_Nums}</a></li>
							</c:if>
						</c:forEach>
					</li>
					<!-- 如果还有后页就访问当前页码+1的页面， -->
					<c:if test="${userList.hasNextPage}">
						<li><a
							href="${pageContext.request.contextPath}/User/list?userName=${userName}&pagenum=${userList.pageNum+1}">
								<span>下一页</span>
						</a></li>
					</c:if>
					<li><a
						href="${pageContext.request.contextPath}/User/list?userName=${userName}&pagenum=${userList.pages}">末页</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>

