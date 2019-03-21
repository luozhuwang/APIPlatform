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
<link rel="stylesheet" type="text/css" href="${basePath}bootstrap/3.3.7/css/bootstrap-select.css">
<link rel="stylesheet" type="text/css" href="${basePath}bootstrap/3.3.7/css/bootstrap.min.css">
<script type="text/javascript" src="${basePath}bootstrap/3.3.7/js/bootstrap-select.js"></script>
<script type="text/javascript" src="${basePath}bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}bootstrap/3.3.7/js/bootstrap-paginator.js"></script>
<script>
function APIlist(){
	var element = $("#page");
	var AapiName=$("#AapiName").val();	
	$.ajax({  
        type : "post",  
        url : "../Interface/jsonlist",  
        data:{apiName:AapiName,page:1},  
        dataType: "json",       
        cache: false,
        success : function(result) {  
        	var json = eval(result.list); //数组   
        	 var div="",s3_Method;
        	var jsonlenth=json.length;    
        	if(jsonlenth==0){  
        		div+="<tr><td colspan='4'>无</td></tr>"        		
        	}else{
        		 for (var i = 0; i <jsonlenth; i++) {
	                  if(result.list[i].apiMethod=="post1"){
	                 	s3_Method="post加密";
	                 }else{
	                 	s3_Method=result.list[i].apiMethod;
	                 }
        			  div+="<tr id='row"+result.list[i].id+"'><td id='case_APIId' style='display:none'>"+result.list[i].id+"</td>"+
		                  	"<td  style='display:none' id='case_APIHost'>"+result.list[i].apiHost+"</td>"+
			                "<td title='"+result.list[i].apiName+"' id='case_APIName'>"+result.list[i].apiName+"</td>"+
			                "<td title='"+result.list[i].apiUrl+"' id='case_APIUrl'>"+result.list[i].apiUrl+"</td>"+ 
			                "<td  style='display:none' id='case_Data'>"+result.list[i].apiParams+"</td>"+
			                "<td id='case_APIMethod'>"+s3_Method+"</td>"+
			                "<td><a class='btn btn-warning' id='choose' onclick='chooseAPI("+result.list[i].id+")'>选择</a></td></tr>";                          
        		};
       
        	}   
        	 $("#group_one").html(div);	 
   		   	$("#count").html("共计"+jsonlenth+"条数据"); 
   		   	var currentPage=result.page;
   		 	var totalPages=result.totalPage;
   		 	var numberOfPages=result.pageSize
   		 	if(totalPages<=0){
   		 		totalPages=1;
   		 	}
   		 var options = {
   				 bootstrapMajorVersion:3, //bootstrap的版本要求
                 currentPage:currentPage,//当前页数
                 totalPages:totalPages,//总页数
                 numberOfPages:numberOfPages,//每页记录数                    
                 itemTexts : function(type, page, current) {//设置分页按钮显示字体样式
                     switch (type) {
                     case "first":
                         return "首页";
                     case "prev":
                         return "上一页";
                     case "next":
                         return "下一页";
                     case "last":
                         return "末页";
                     case "page":
                         return page;
                     }
                 },
                 onPageClicked:function(event,originalEvent,type,page){//分页按钮点击事件
                	 $.ajax({//根据page去后台加载数据
                     	type : "post",  
                         url : "../Interface/jsonlist",  
                         data:{apiName:AapiName,page:page},  
                         dataType: "json",
                         cache: false,
	                         success:function(result){
	                        	 var json = eval(result.list); //数组   
	                        	 var div,s3_Method;
	                        	var jsonlenth=json.length;    
	                        	if(jsonlenth==0){  
	                        		div+="<tr><td colspan='4'>无</td></tr>"        		
	                        	}else{
	                        		 for (var i = 0; i <jsonlenth; i++) {
	                	                  if(result.list[i].apiMethod=="post1"){
	                	                 	s3_Method="post加密";
	                	                 }else{
	                	                 	s3_Method=result.list[i].apiMethod;
	                	                 }
	                        			  div+="<tr id='row"+result.list[i].id+"'><td id='case_APIId' style='display:none'>"+result.list[i].id+"</td>"+
	                		                  	"<td  style='display:none' id='case_APIHost'>"+result.list[i].apiHost+"</td>"+
	                			                "<td title='"+result.list[i].apiName+"' id='case_APIName'>"+result.list[i].apiName+"</td>"+
	                			                "<td title='"+result.list[i].apiUrl+"' id='case_APIUrl'>"+result.list[i].apiUrl+"</td>"+ 
	                			                "<td  style='display:none' id='case_Data'>"+result.list[i].apiParams+"</td>"+
	                			                "<td id='case_APIMethod'>"+s3_Method+"</td>"+
	                			                "<td><a class='btn btn-warning' id='choose' onclick='chooseAPI("+result.list[i].id+")'>选择</a></td></tr>";                          
	                        		};
	                       
	                        	}   
	                        	 $("#group_one").html(div);	 
	                   		   	$("#count").html("共计"+jsonlenth+"条数据"); 
	                         }
                         })                 
                 }
   		 }
   		//初始化分页框
         element.bootstrapPaginator(options);
		  	//模态弹出框
		  	$('#addUserModal').modal();
		  	$("#btn-invest").removeAttr("disabled");
		
        } 
    }); 
	 
}
</script>
<script>
function chooseAPI(id){
	$("#APIId").attr("value",$("#row"+id).find("#case_APIId").html());
	$("#apiName").attr("value",$("#row"+id).find("#case_APIName").html());
	$("#apiHost").attr("value",$("#row"+id).find("#case_APIHost").html());
	$("#apiUrl").attr("value",$("#row"+id).find("#case_APIUrl").html());
	$("#apiMethod").attr("value",$("#row"+id).find("#case_APIMethod").html());
	$("#caseData").html($("#row"+id).find("#case_Data").html());
	$('#addUserModal').modal('hide');
}
</script>
<script>
function saveRunCase(){
	var aa=$("#para_table");
	var bb=$("#table3");
	var caseName=$("#caseName").val();
	var APIId=$("#APIId").val();
	var caseAssert="";
	var caseParam="";
	var remark=$("#remark").val();
	var caseData=$("#caseData").val();
	
	if(caseName==null||caseName==undefined||caseName==""){
		 alert("请输入用例名称");
		 return false;
	}if(APIId==null||APIId==undefined||APIId==""){
		 alert("请选择接口");
		 return false;
	}
	
	var length1=aa.find("tr#row").length;
	if(length1>0){
		caseAssert=changeTbodyToDetail(aa);
	}else{
		alert("请添加断言");
		return false;
	}
	var length2=bb.find("tr#row").length;
	if(length2>0){
		caseParam=changeTbodyToDetail(bb);
	}
	
	$.ajax({  
        type : "post",  
        url : "addAction",  
        data:{caseData:caseData,caseName:caseName,APIId:APIId,caseAssert:caseAssert,caseParam:caseParam,remark:remark},  
        dataType: "text",       
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
<ul id="myTab" class="nav nav-tabs" >
    <li class="active"><a href="#ChooseAPI" data-toggle="tab">选择接口</a></li>
   <li><a href="#assertion" data-toggle="tab">断言</a></li>
   <li><a href="#saveData" data-toggle="tab">数据提取</a></li>
</ul>
<div id="myTabContent" class="tab-content" >
	<div class="tab-pane fade in active" id="ChooseAPI">
		<div class="col-md-2" align="center">
			<a type="button" class="btn btn-success"  data-toggle="modal" onclick="APIlist();">选择接口</a>
		</div>

     <div class="panel panel-primary" style="width: 100%; height: 560px">
				<div class="panel-heading">
					<h3 class="panel-title">基本信息</h3>
				</div>
				<div class="panel-body">
					<label class="col-sm-1 control-label">用例名称</label>
					<div class="col-sm-2">
						<input class="form-control" id="caseName" name="caseName" value="${APIcase.caseName}" type="text" placeholder="用例名称">
					</div>
					<label class="col-sm-1 control-label">接口名称</label>
					<div class="col-sm-2">
						<input class="form-control" id="apiName" name="apiName" readonly="readonly" value="" type="text" placeholder="接口名称">
						<input class="form-control" id="APIId" name="APIId"  value="${APIcase.APIId}" type="text" style="display:none" >
					</div>
				</div>
				<!-- 如果fieldset设置了disabled属性，整个域都会处于被禁用状态 -->
				 <fieldset disabled>
				<div class="panel-body">
					<label class="col-sm-1 control-label">请求环境</label>
					<div class="col-sm-2">
						<input class="form-control" id="apiHost" type="text" name="apiHost"   value="" placeholder="请求环境">
					</div>
				
					<label class="col-sm-1 control-label">请求地址</label>
					<div class="col-sm-4">
						<input class="form-control" id="apiUrl" type="text" name="apiUrl"   value="" placeholder="请求地址">
					</div>
				</div>
				
				<div class="panel-body" >
						<label class="col-sm-1 control-label">请求方式</label>
						<div class="col-sm-2">
							<input class="form-control" id="apiMethod" type="text" name="apiMethod"   value=""  placeholder="请求方式">	
						</div>							
				</div>
				</fieldset>
				<div class="panel-body" >
					<label class="col-sm-1 control-label">用例数据</label>
					<a  data-toggle="tooltip" title="临时变量(测试用例保存的变量)：\#{temp}#
获取APP-serverIP:\#\#{env_ip}##
初始化变量：\${name}
随机20位数据:%{Random}%">参数帮助</a>
					<div class="panel-body" >
						<textarea class="form-control" rows="4" style="resize: none;" id="caseData" value=""  name="caseData" placeholder="用例数据">${APIcase.caseData}</textarea>
					</div>
				</div>
				<div class="panel-body" >
					<label class="col-sm-1 control-label">用例描述</label>
					<div class="panel-body" >
						<textarea class="form-control" rows="4" style="resize: none;" id="remark" name="remark" placeholder="用例描述">${APIcase.remark}</textarea>
					</div>
				</div>
			</div>
	</div>
	<div class="tab-pane fade" id="assertion" >
		   <div  class="col-md-2"  align="center">
				<button type="button" class="btn btn-success" onclick="addtable1tr()">添加断言</button>
				<a data-toggle="tooltip" data-placement="bottom" title="状态码只支持'等于'和'不等于'">断言帮助</a>
		   </div>
			<table class="table  table-bordered" id="para_table">
				<tr>
					<th style="text-align:center" width="20">断言项</th>
<!-- 					<th style="text-align:center" width="10%">实际值</th> -->
					<th style="text-align:center" width="10">比较方式</th>
					<th style="text-align:center" width="20">期望值</th>
					<th style="text-align:center" width="5">操作</th>
				</tr>
			</table>
	</div>
	<div class="tab-pane fade" id="saveData" >
		<div class="col-md-3" align="center">
			<button type="button" class="btn btn-success" onclick="addtable2tr()">添加数据</button>
			<a data-toggle="tooltip" data-placement="bottom" 
title="json数据注意事项:
1.数组或者多层大括号无法解析
2.参数名和规则填写一样即可
常用正则表达式
身份证:(^\d{18}$)|(^\d{15}$)">数据帮助</a>
		</div>
				<table class="table  table-bordered" id="table3">
					<tr>
						<th style="text-align:center" width="20">参数名</th>
						<th style="text-align:center" width="10">取值方式</th>
						<th style="text-align:center" width="20">规则</th>
						<th style="text-align:center" width="5">操作</th>
					</tr>
				</table>
	</div>
	<div align="center">	
				<button type="button" class="btn btn-success btn-lg" onclick="saveRunCase();">保存</button>
				<a type="button" class="btn btn-default btn-lg" href="${ctx}/Case/list">返回</a>
	</div>
	
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

