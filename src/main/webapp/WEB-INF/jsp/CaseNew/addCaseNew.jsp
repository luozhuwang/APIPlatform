<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增测试用例</title>
<link rel="stylesheet" href="${basePath}css/mail/table.css" />
<link rel="stylesheet" href="${basePath}css/table-td.css" />
<script type="text/javascript" src="${basePath}jQuery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}js/api/table.js"></script>
<script type="text/javascript" src="${basePath}js/api/APIlist.js"></script>
<script type="text/javascript" src="${basePath}js/api/tbody.js"></script>
<script type="text/javascript" src="${basePath}js/api/apiRelations.js"></script>
<link rel="stylesheet" type="text/css" href="${basePath}bootstrap/3.3.7/css/bootstrap-select.css">
<link rel="stylesheet" type="text/css" href="${basePath}bootstrap/3.3.7/css/bootstrap.min.css">
<script type="text/javascript" src="${basePath}bootstrap/3.3.7/js/bootstrap-select.js"></script>
<script type="text/javascript" src="${basePath}bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}bootstrap/3.3.7/js/bootstrap-paginator.js"></script>
<script>
function saveCase(){
	var caseName=$("#caseName").val();
	var remark=$("#remark").val();
	var dependCaseId=$("#dependCaseId").val();
	var caseRelations=getAPIRelations();
	
	if(caseName==null||caseName==undefined||caseName==""){
		 alert("请输入用例名称");
		 return false;
	}
	if(caseRelations==null||caseRelations==undefined||caseRelations=="[]"){
		 alert("请添加API");
		 return false;
	}
	$.ajax({  
        type : "post",  
        url : "addNewAction",  
        contentType: "application/json",
        data: JSON.stringify({
        	dependCaseId:dependCaseId,
        	caseName:caseName,
    		remark:remark,
    		caseRelations:caseRelations,
        }),
        cache: false,
        success: function(result){
        	//将返回的内容重新显示在页面，但是url不会变 
//         	document.write(result);
        	alert("新增用例成功");
        	window.location.href="list";
        },
		error:function(result){			 
			alert("新增用例异常");
		}
	})
}
</script>
</head>
<body>
	<div class="panel panel-warning">
			<div class="panel-heading">
					<h3 class="panel-title" align="center">用例基本信息</h3>
			</div>
			<div class="panel-body">
<!-- 				<label class="col-sm-1 control-label">依赖用例</label> -->
<!-- 				<div class="col-sm-2"> -->
<%-- 						<select id="dependCaseId" name="dependCaseId" class="selectpicker" value="${dependCaseId}" class="selectpicker"  style="width: 190px; height: 30px"> --%>
<!-- 							<option value="0">无</option> -->
<%-- 							<c:forEach items="${APIcaseList}" var="r"> --%>
<%-- 								<option value="${r.id }" <c:if test="${dependCaseId eq r.id}">selected="selected"</c:if>>${r.caseName }</option> --%>
<%-- 							</c:forEach> --%>
<!-- 						</select> -->
<!-- 				</div>	 -->
				<label class="col-sm-1 control-label">用例名称</label>
				<div class="col-sm-2">
						<input class="form-control" id="caseName" name="caseName" value="" type="text" placeholder="用例名称">
				</div>			
			</div>
			<div class="panel-body">
				<label class="col-sm-1 control-label">用例描述</label>
					<div class="panel-body" >
						<textarea class="form-control" rows="4" style="resize: none;" id="remark" name="remark" placeholder="用例描述"></textarea>
					</div>		
			</div>
			<div class="panel-body" align="center">
					<button class="btn btn-success"  data-toggle="modal" data-target="#addInterface">添加API</button>			
				<div class="main" align="center">
						<table id="API_table" class="data-table" border="1" style="table-layout:fixed;">
							<thead>
								<tr class="head">
									<td style="width:5%;">接口名称</td>
									<td style="width:10%;">请求URL</td>
									<td style="width:5%;">请求方式</td>
									<td style="width:20%;">请求数据</td>									
									<td style="width:10%;">操作</td>
								</tr>
							</thead>	
							<tbody>
							</tbody>				
						</table>
					<hr>
				</div>
			</div>
				<div align="center">	
							<button type="button" class="btn btn-success btn-lg" onclick="saveCase();">保存</button>
							<a type="button" class="btn btn-default btn-lg" href="${ctx}/CaseNew/list">返回</a>
				</div>
				<br>
		</div>
		
	<!-- 模态框：添加API -->
	<div class="modal fade" id="addInterface" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 80%">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
	            		×
	          		</button>
					<ul id="myTab" class="nav nav-tabs" >
					    <li class="active"><a href="#ChooseAPI" data-toggle="tab">选择接口</a></li>
					   <li><a href="#assertion" data-toggle="tab">断言</a></li>
					   <li><a href="#saveData" data-toggle="tab">数据提取</a></li>
					</ul>
				</div>
				
				<div id="myTabContent" class="tab-content">
					<div class="tab-pane fade in active" id="ChooseAPI" align="center">
						<div class="col-md-2" align="center">
							<a type="button" class="btn btn-success"  data-toggle="modal" onclick="APIlist('addInterface');">选择接口</a>
						</div>

					     <div class="panel panel-primary" style="width: 99%; height:360px">
									<div class="panel-heading">
										<h3 class="panel-title">基本信息</h3>
									</div>
									<div class="panel-body">
										<label class="col-sm-1 control-label">接口名称</label>
										<div class="col-sm-2">
											<input class="form-control" id="ChooseAPIName" name="apiName" readonly="readonly" value="" type="text" placeholder="接口名称">
											<input class="form-control" id="ChooseAPIId" name="APIId"  value="" type="text" style="display:none" >
										</div>
										<label class="col-sm-1 control-label">请求方式</label>
											<div class="col-sm-2">
												<input class="form-control" id="ChooseAPIMethod" type="text" name="apiMethod" readonly="readonly"  value=""  placeholder="请求方式">	
											</div>	
									</div>
									<!-- 如果fieldset设置了disabled属性，整个域都会处于被禁用状态 -->
									 <fieldset disabled>
									<div class="panel-body">
										<label class="col-sm-1 control-label">请求环境</label>
										<div class="col-sm-2">
											<input class="form-control" id="ChooseAPIHost" type="text" name="apiHost"   value="" placeholder="请求环境">
										</div>
									
										<label class="col-sm-1 control-label">请求地址</label>
										<div class="col-sm-4">
											<input class="form-control" id="ChooseAPIUrl" type="text" name="apiUrl"   value="" placeholder="请求地址">
										</div>
									</div>
									</fieldset>
									<div class="panel-body" >
										<label class="col-sm-2 control-label" align="left">用例数据
										<a style="color:red"  data-toggle="tooltip" title="临时变量(测试用例保存的变量)：\#{temp}#
获取APP-serverIP:\#\#{env_ip}##
初始化变量：\${name}
随机20位数据:%{Random}%">参数帮助</a>
										</label>										
										<div class="panel-body" >
											<textarea class="form-control" rows="4" style="resize: none;" id="ChooseAPIData" value=""  name="caseData" placeholder="用例数据"></textarea>
										</div>
									</div>
								</div>
						</div>
						<div class="tab-pane fade" id="assertion" >
						   <div  class="col-md-2"  align="center">
								<button type="button" class="btn btn-success" onclick="addtable1tr('addInterface')">添加断言</button>
								<a data-toggle="tooltip" data-placement="bottom" style="color:red"  
title="状态码只支持'等于'和'不等于'">断言帮助</a>
						   </div>
						   <div style="width: 80%;">
								<table class="table  table-bordered" id="para_table" >
									<tr>
										<th style="text-align:center" width="10px">断言项</th>
					<!-- 					<th style="text-align:center" width="10%">实际值</th> -->
										<th style="text-align:center" width="10px">比较方式</th>
										<th style="text-align:center" width="20px">期望值</th>
										<th style="text-align:center" width="5px">操作</th>
									</tr>
								</table>
							</div>
						</div>
						<div class="tab-pane fade" id="saveData" >
							<div class="col-md-3" align="center">
								<button type="button" class="btn btn-success" onclick="addtable2tr('addInterface')">添加数据</button>
								<a style="color:red"  data-toggle="tooltip" data-placement="bottom" 
title="json数据注意事项:
1.数组或者多层大括号无法解析
2.参数名和规则填写一样即可
常用正则表达式
身份证:(^\d{18}$)|(^\d{15}$)">数据帮助</a>
							</div>
							<div style="width: 80%;">
									<table class="table  table-bordered" id="table3">
										<tr>
											<th style="text-align:center" width="20%">参数名</th>
											<th style="text-align:center" width="10%">取值方式</th>
											<th style="text-align:center" width="20%">规则</th>
											<th style="text-align:center" width="5%">操作</th>
										</tr>
									</table>
							</div>
						</div>					
						<div align="center">	
							<button type="button" class="btn btn-success btn-lg" onclick="addApi();">保存</button>
<%-- 							<a type="button" class="btn btn-default btn-lg" href="${ctx}/Case/list">返回</a> --%>
						</div>
						<br>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
		
	<!-- 模态框：编辑API -->
	<div class="modal fade" id="editInterface" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 80%">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
	            		×
	          		</button>
					<ul id="myTab" class="nav nav-tabs" >
					    <li class="active"><a href="#editChooseAPI" data-toggle="tab">选择接口</a></li>
					   <li><a href="#editassertion" data-toggle="tab">断言</a></li>
					   <li><a href="#editsaveData" data-toggle="tab">数据提取</a></li>
					</ul>
				</div>
				
				<div id="myTabContent" class="tab-content">
					<div class="tab-pane fade in active" id="editChooseAPI" align="center">
						<div class="col-md-2" align="center">
							<a type="button" class="btn btn-success"  data-toggle="modal" onclick="APIlist('editInterface');">选择接口</a>
						</div>

					     <div class="panel panel-primary" style="width: 99%; height:360px">
									<div class="panel-heading">
										<h3 class="panel-title">基本信息</h3>
									</div>
									<div class="panel-body">
										<label class="col-sm-1 control-label">接口名称</label>
										<div class="col-sm-2">
											<input class="form-control" id="ChooseAPIName" name="apiName" readonly="readonly" value="" type="text" placeholder="接口名称">
											<input class="form-control" id="ChooseAPIId" name="APIId"  value="" type="text" style="display:none" >
										</div>
										<label class="col-sm-1 control-label">请求方式</label>
											<div class="col-sm-2">
												<input class="form-control" id="ChooseAPIMethod" type="text" name="apiMethod" readonly="readonly"  value=""  placeholder="请求方式">	
											</div>	
									</div>
									<!-- 如果fieldset设置了disabled属性，整个域都会处于被禁用状态 -->
									 <fieldset disabled>
									<div class="panel-body">
										<label class="col-sm-1 control-label">请求环境</label>
										<div class="col-sm-2">
											<input class="form-control" id="ChooseAPIHost" type="text" name="apiHost"   value="" placeholder="请求环境">
										</div>
									
										<label class="col-sm-1 control-label">请求地址</label>
										<div class="col-sm-4">
											<input class="form-control" id="ChooseAPIUrl" type="text" name="apiUrl"   value="" placeholder="请求地址">
										</div>
									</div>
									</fieldset>
									<div class="panel-body" >
										<label class="col-sm-2 control-label" align="left">用例数据
										<a style="color:red"  data-toggle="tooltip" title="临时变量(测试用例保存的变量)：\#{temp}#
获取APP-serverIP:\#\#{env_ip}##
初始化变量：\${name}
随机20位数据:%{Random}%">参数帮助</a>
										</label>										
										<div class="panel-body" >
											<textarea class="form-control" rows="4" style="resize: none;" id="ChooseAPIData" value=""  name="caseData" placeholder="用例数据"></textarea>
										</div>
									</div>
								</div>
						</div>
						<div class="tab-pane fade" id="editassertion" >
						   <div  class="col-md-2"  align="center">
								<button type="button" class="btn btn-success" onclick="addtable1tr('editInterface')">添加断言</button>
								<a data-toggle="tooltip" data-placement="bottom" style="color:red"  
title="状态码只支持'等于'和'不等于'">断言帮助</a>
						   </div>
						   <div style="width: 80%;">
								<table class="table  table-bordered" id="para_table" >
									<tr>
										<th style="text-align:center" width="10px">断言项</th>
					<!-- 					<th style="text-align:center" width="10%">实际值</th> -->
										<th style="text-align:center" width="10px">比较方式</th>
										<th style="text-align:center" width="20px">期望值</th>
										<th style="text-align:center" width="5px">操作</th>
									</tr>
								</table>
							</div>
						</div>
						<div class="tab-pane fade" id="editsaveData" >
							<div class="col-md-3" align="center">
								<button type="button" class="btn btn-success" onclick="addtable2tr('editInterface')">添加数据</button>
								<a style="color:red"  data-toggle="tooltip" data-placement="bottom" 
title="json数据注意事项:
1.数组或者多层大括号无法解析
2.参数名和规则填写一样即可
常用正则表达式
身份证:(^\d{18}$)|(^\d{15}$)">数据帮助</a>
							</div>
							<div style="width: 80%;">
									<table class="table  table-bordered" id="table3">
										<tr>
											<th style="text-align:center" width="20%">参数名</th>
											<th style="text-align:center" width="10%">取值方式</th>
											<th style="text-align:center" width="20%">规则</th>
											<th style="text-align:center" width="5%">操作</th>
										</tr>
									</table>
							</div>
						</div>					
						<div align="center">	
							<button type="button" class="btn btn-success btn-lg" onclick="editAPISave();">保存</button>
<%-- 							<a type="button" class="btn btn-default btn-lg" href="${ctx}/Case/list">返回</a> --%>
						</div>
						<br>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	
	<div class="modal fade" id="addUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	      <div class="modal-content">
	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
	            ×
	          </button>
	          <h4 class="modal-title" id="myModalLabel">
	            		请选择接口
	          </h4>
	        </div>
				<div>
						<div class="whole">
							接口名称：<input type="text" placeholder="接口名称" value="" name="AapiName" id="AapiName" style="width: 180px; height: 30px"> 
							<a type="submit" class="btn btn-primary" onclick="APIlist();">查询</a>
							<a type="submit" class="btn btn-default" onclick="javascript:$('#AapiName').val('')">清空</a>
						</div>
					<div class="main">
						<table id="cs_table" class="data-table" border="1" style="table-layout:fixed;">						
							<thead>
								<tr class="head">
									<td width="20%">接口名称</td>
									<td width="40%">接口地址</td>
									<td width="15%">请求方式</td>
									<td  width="15%">操作</td>
								</tr>
							</thead>
							<tbody id="group_one">	
							</tbody>
						</table>
						<div id="count" class='col-md-4'></div>
					</div>
				</div>
				<div class="container" >
					<ul id="page"></ul>
	      		</div>
	    </div>
	</div>
  </div>
</body>
</html>

