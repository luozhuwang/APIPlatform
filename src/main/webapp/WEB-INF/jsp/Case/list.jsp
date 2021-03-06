<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用例列表管理</title>
<link rel="stylesheet" href="${basePath}css/mail/table.css" />
<link rel="stylesheet" href="${basePath}css/table-td.css" />
<script type="text/javascript" src="${basePath}jQuery/2.1.4/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${basePath}bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${basePath}bootstrap/3.3.7/css/bootstrap-datepicker3.min.css">
<script type="text/javascript" src="${basePath}bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
function clean() {
	$("#caseName").val("");
	$("#APIId").val("");
}
function delerun(run_id) {
	var id=run_id;
	var r=confirm("是否刪除?");
	if(r==true){
		$.ajax({
			  type: "POST",
			  url: "del/"+id,
			  success : function(result) {  	    	        
	                  alert("数据已成功删除！");
	                  window.location.reload();
		        },error:function(result){			 
					alert("用例被用例集已引用，删除失败");
				}  
			});
	}
}
</script>
<script>
function RunCase(run_id){
	id=run_id;
	var envTitle=parent.jQuery("#envTitle").val();
	$("#run"+run_id).text("执行中");		
	$("#run"+run_id).attr("disabled","true");
	$.ajax({
		  type: "POST",
		  url: "run",
		  data:{id:id,envTitle:envTitle},
		  dataType: "text",  
		  success : function(result) {  	    	        
                $("#run"+run_id).text("执行");
                alert(result);	
                $("#run"+run_id).removeAttr("disabled")
	        },
			error:function(result){
				 $("#run"+run_id).text("执行");
				alert("用例执行异常");
				$("#run"+run_id).removeAttr("disabled")
			}
		});
}

</script>
</head>
<body>
	<div class="all">
		<form action="${ctx}/Case/list" method="post">
			<div class="whole">
				<div class="littletitle">测试用例列表</div>
				用例名称：<input type="text" placeholder="用例名称" value="${caseName}" name="caseName" id="caseName" style="width: 190px; height: 30px">
				接口名称:
					<select id="APIId" name="APIId" class="selectpicker" value="${APIId}" style="width: 190px; height: 30px">
						<option value="">全部</option>
						<c:forEach items="${APIList}" var="r">
							<option value="${r.id }" <c:if test="${APIId eq r.id}">selected="selected"</c:if>>${r.apiName }</option>
						</c:forEach>
					</select> 				
				<button type="submit" class="btn btn-primary">查询</button>
				<button type="button" class="btn btn-default" onclick="clean();">重置</button>
				<a class="btn btn-success" href="${ctx}/Case/addCase" >新增</a>	
			</div>
			<div class="main">
				<table id="cs_table" class="data-table" border="1" style="table-layout:fixed;">
					<thead>
						<tr class="head">
							<td style="width:5%;">ID</td>
							<td style="width:20%;">用例名称</td>
							<td style="width:20%;">用例描述</td>
							<td style="width:15%;">操作</td>
						</tr>
					</thead>
					<tbody id="group_one">
					<c:if   test="${fn:length(CaseList.list) <= 0}">
							<tr>								
									<td colspan="4">无</td>									
							</tr>
					</c:if>	
						<c:forEach items="${CaseList.list}" var="e2" varStatus="status">
							<tr>
								<td>${status.index + 1}</td>
								<td id="caseName" title="${e2.caseName}">${e2.caseName}</td>								
								<c:choose>
									   <c:when test="${e2.remark eq ''}">  
									    <td >无</td>     
									   </c:when>
									   <c:otherwise> 
									   	<td id="remark" title="${e2.remark}">${e2.remark}</td>
									   </c:otherwise>
								</c:choose>	
								<td>
									<a class="btn btn-warning" id="run${e2.id}" onclick="RunCase(${e2.id});">执行</a>
									<a class="btn btn-info" href="edit/${e2.id}" >编辑</a>
									<a class="btn btn-success" href="result/${e2.id}" >查看结果</a>
<!-- 									<a class="btn btn-success" href="" >结果统计</a> -->
<!-- 									<button  class="btn btn-default" type="button" >复制</button> -->
									<button  class="btn btn-danger" type="button"  onclick="delerun(${e2.id});" >删除</button>
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
				当前${CaseList.pageNum}页,共${CaseList.pages }页,总${CaseList.total }条记录
			</div>
			<!-- 分页码 -->
			<div class="col-md-6">

				<ul class="pagination">
					<!-- 
                        1.pageContext.request.contextPath表示当前项目路径，采用的是绝对路径表达方式。一般为http:localhost:8080/项目名 。
                        2.首页，末页的逻辑：pn=1访问第一次，pn=${pageInfo.pages}访问最后一页
                      -->
					<li><a
						href="${pageContext.request.contextPath}/Case/list?caseName=${caseName}&pagenum=1">首页</a>
					</li>
					<!-- 如果还有前页就访问当前页码-1的页面， -->
					<c:if test="${CaseList.hasPreviousPage}">
						<li><a
							href="${pageContext.request.contextPath}/Case/list?caseName=${caseName}&pagenum=${CaseList.pageNum-1}">
								<span>上一页</span>
						</a></li>
					</c:if>
					<li>
						<!--遍历所有导航页码，如果遍历的页码页当前页码相等就高亮显示，如果相等就普通显示  --> <c:forEach
							items="${CaseList.navigatepageNums }" var="page_Nums">
							<c:if test="${page_Nums==CaseList.pageNum }">
								<li class="active"><a href="#">${page_Nums}</a></li>
							</c:if>
							<c:if test="${page_Nums!=CaseList.pageNum }">
								<li><a
									href="${pageContext.request.contextPath}/Case/list?caseName=${caseName}&pagenum=${page_Nums}">${page_Nums}</a></li>
							</c:if>
						</c:forEach>
					</li>
					<!-- 如果还有后页就访问当前页码+1的页面， -->
					<c:if test="${CaseList.hasNextPage}">
						<li><a
							href="${pageContext.request.contextPath}/Case/list?caseName=${caseName}&pagenum=${CaseList.pageNum+1}">
								<span>下一页</span>
						</a></li>
					</c:if>
					<li><a
						href="${pageContext.request.contextPath}/Case/list?caseName=${caseName}&pagenum=${CaseList.pages}">末页</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>

