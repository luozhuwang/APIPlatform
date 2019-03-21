<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>编辑用例集</title>
<link rel="stylesheet" href="${basePath}css/mail/table.css" />
<script type="text/javascript" src="${basePath}jQuery/2.1.4/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${basePath}bootstrap/3.3.7/css/bootstrap-select.css">
<link rel="stylesheet" type="text/css" href="${basePath}bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${basePath}bootstrap/3.3.7/css/bootstrap-datepicker3.min.css">
<script type="text/javascript" src="${basePath}bootstrap/3.3.7/js/bootstrap-select.js"></script>
<script type="text/javascript" src="${basePath}bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">  
$(window).on('load', function () {
	var obj =jQuery("#caseRelation");
	var str=obj.attr("value");
	var arr = str.split(',');
	obj.selectpicker('val', arr);
});
</script>
<script type="text/javascript">  
function setEditAction(){
	var id=$("#id").val();
	var remark=$("#remark").val();
	var caseSetName=$("#caseSetName").val();	
	if(caseSetName==null||caseSetName==undefined||caseSetName==""){
		 alert("用例集名称不能为空");
		 return false;
	}
	//获取所选执行的用例
	var Relations=$('#caseRelation').selectpicker('val');
// 	var Relations=$('#caseRelation').find("option:selected").attr('val')
	if(Relations==null||Relations==undefined||Relations==""){
		 alert("请关联用例");
		 return false;
	}
	var caseRelation=Relations.join(",");
	$.ajax({  
        type : "post",  
        url : "../editSetAction",  
        data:{id:id,caseSetName:caseSetName,caseRelation:caseRelation,remark:remark},  
        dataType: "text",       
        cache: false,
        success: function(result){
        	alert("编辑用例集成功");
        	window.location.href="../list";
        },
		error:function(result){			 
			alert("编辑用例集异常");
		}
	})
}
</script>
</head>
<body>
	<div class="widget-content nopadding">
				<div class="panel-body">
					<label class="col-sm-1 control-label">用例集名称</label>
					<div class="col-sm-2">
						<input class="form-control" id="id" name="id" type="hidden" value="${caseSet.id}">
						<input class="form-control" id="caseSetName" name="caseSetName" type="text" value="${caseSet.caseSetName}" placeholder="用例集名称" readonly="readonly">
					</div>								 
				</div>
				<div class="panel-body">
					<label for="id_select" class="col-sm-1 control-label">关联用例</label>
					<div>
						<select id="caseRelation" name="caseRelation" class="selectpicker bla bla bli col-sm-8" multiple data-live-search="true" value="${caseSet.caseRelation}" title="请选择用例(支持多选)">
							<c:forEach items="${APIcaseList}" var="r">
								<option value="${r.id }">${r.caseName }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="panel-body">
					<label class="col-sm-1 control-label">用例集描述</label>
					<div  class="col-sm-8" >
						<textarea class="form-control" rows="4" style="resize: none;" id="remark" name="remark" placeholder="用例集描述">${caseSet.remark}</textarea>
					</div>					  
				</div>
			<div align="center" class="col-sm-10">				
				<button  class="btn btn-success btn-lg" value="保存" onclick="setEditAction();">保存</button>
				<a type="button" class="btn btn-default btn-lg" href="${ctx}/CaseSet/list">返回</a>
			</div>
	</div>
</body>
</html>

