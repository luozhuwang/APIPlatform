<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用例集结果</title>
<link rel="stylesheet" href="${basePath}css/mail/table.css">
<link rel="stylesheet" href="${basePath}css/table-td.css" />
<script type="text/javascript" src="${basePath}js/api/APIlist.js"></script>
<script type="text/javascript"  src="${basePath}echarts-2.2.7/echarts.js"></script>
<script type="text/javascript" src="${basePath}jQuery/2.1.4/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${basePath}bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${basePath}bootstrap/3.3.7/css/bootstrap-datepicker3.min.css">
<script type="text/javascript" src="${basePath}bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
function APIALLResults(tdobject){
	var ele=$(tdobject).parent().parent();
	var setId=ele.find("td#setId").attr("value");
	var caseId=ele.find("td#caseId").attr("value");
		$.ajax({
			  type: "POST",
			  url: "../APIResults",
			  data:{"setId":setId,"caseId":caseId},
			  dataType: "json", 
			  success : function(result) { 
				  var div;
				  var json = eval(result); //数组   
				  var jsonlenth=json.length;    
		        	if(jsonlenth==0){  
		        		div+="<tr><td colspan='7'>无</td></tr>"        		
		        	}else{
		        		for (var i = 0; i <jsonlenth; i++) {
		        			div+="<tr id='row"+result[i].apiid+"'><td id='apiName' title='"+result[i].apiName+"'>"+result[i].apiName+"</td>"+
		        			 "<td title='"+result[i].apiUrl+"' id='apiUrl'>"+result[i].apiUrl+"</td>"+
		        			 "<td title='"+result[i].request+"' id='request'>"+result[i].request+"</td>"+ 
		        			 "<td title='"+result[i].statusCode+"' id='statusCode'>"+result[i].statusCode+"</td>"+ 
		        			 "<td title='"+result[i].response+"' id='response'>"+result[i].response+"</td>"+ 
		        			 "<td title='"+result[i].caseAssert+"' id='caseAssert'>"+result[i].caseAssert+"</td>"+ 
		        			 "<td title='"+result[i].costTime+"ms' id='costTime'>"+result[i].costTime+"ms</td>"
		        		}
		        	}
		        	$("#interfaceResult").html(div);	 
		        },error:function(result){			 
					alert("用例集获取接口结果异常");
				}  
			});

}

</script>
<script>
$(function() {
    // 路径配置
    require.config({
        paths: {
        	 echarts: '<%=basePath %>echarts-2.2.7'  
        }
    });
    //动态加载饼状图
    require(
        [
             'echarts',  
             'echarts/chart/line',
             'echarts/chart/pie',// 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
             'echarts/chart/bar'   
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var pie_data = ec.init(document.getElementById('pie_data'));
            var setId=$("#caseSetId").attr("value");
            console.info(setId);
        	$.ajax({
      		  type: "POST",
      		  url: "../Report/"+setId,
      		  dataType: "json",  
      		  success : function(result) {  	    	        
      			pie_data.setOption({
      				title:{
                		show:true,
                		x:'center',
                		text:"运行结果图表"                		
                	},
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient : 'vertical',
                        x : 'left',
                        data:result
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            magicType : {
                                show: true,
                                type: ['pie'],
                                option: {
                                    funnel: {
                                        x: '25%',
                                        width: '50%',
                                        funnelAlign: 'left',
                                        max: 1548
                                    }
                                }
                            },
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    calculable : true,
                    series : [
                        {
                            name:'执行用例',
                            type:'pie',
                            radius : '55%',
                            center: ['50%', '60%'],
                            data:result
                        }
                    ]
                });
      	        },
      			error:function(result){
      					alert("请求后台异常");
      			}
      		});
        	
        }
    )
});
</script>
</head>
<body>
	<a href="${ctx}/CaseSet/list" class="btn btn-primary">返回</a>
	<div class="main">
		<div class="panel-body" >
			<div class="col-sm-6" >
				<form class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-2 text-navy">用例集名称</label>
						<div class="col-sm-4">
							<input id="caseSetId" style="display: none" value="${caseSetId}" />
							<span>${caseSetResult.caseSetName}</span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2  text-navy">总用例数</label>
						<div class="col-sm-2">
							<span>${fn:length(allResult)}</span>
						</div>
					</div>
					<div class="form-group text-success">
						<label class="col-sm-2 text-navy">成功用例</label>
						<div class="col-sm-1">
							<span>${count_succ}</span>
						</div>
					</div>
					<div class="form-group text-danger">
						<label class="col-sm-2 text-navy">失败用例</label>
						<div class="col-sm-1">
							<span>${count_fail}</span>
						</div>
					</div>
					<div class="form-group text-warning">
						<label class="col-sm-2 text-navy">跳过用例</label>
						<div class="col-sm-1">
							<span>${count_skip}</span>
						</div>
					</div>
					<div class="form-group" style="color:blue">
						<label class="col-sm-2 text-navy">运行环境</label>
						<div class="col-sm-4">
							<span>${caseSetResult.runEnv}</span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 text-navy">开始时间</label>
						<div class="col-sm-4">
							<span>${caseSetResult.startTime}</span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 text-navy">结束时间</label>
						<div class="col-sm-4">
							<span>${caseSetResult.endTime}</span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 text-navy">运行时间</label>
						<div class="col-sm-4">
							<span>${caseSetResult.costTime}ms</span>
						</div>
					</div>
				</form>
			</div>
			<div class="col-sm-6" id="pie_data" style="height: 350px"></div>
		</div>
		<table id="cs_table" class="data-table" border="1" style="table-layout:fixed;">
					<thead >
						<tr class="head" style="background: green;">
							<td style="width:20%;font-weight:bold;">用例名称</td>
							<td style="width:15%;font-weight:bold;">开始时间</td>
							<td style="width:15%;font-weight:bold;">结束时间</td>
							<td style="width:10%;font-weight:bold;">结果</td>
							<td style="width:10%;font-weight:bold;">耗时</td>
							<td style="width:10%;font-weight:bold;">操作</td>
						</tr>
					</thead>
					<tbody id="group_one" >
						<c:if   test="${fn:length(allResult) <= 0}">
							<tr>								
									<td colspan="6">无</td>									
							</tr>
						</c:if>	
						<c:forEach items="${allResult}" var="e2">
							<tr>
								<td id="setId" value="${e2.setId}" style="display: none" />
								<td id="caseId" value="${e2.caseId}" style="display: none" />
								<td title="${e2.caseName}">${e2.caseName}</td>
								<td title="${e2.startTime}">${e2.startTime}</td>	
								<td title="${e2.endTime}">${e2.endTime}</td>		
									<c:choose>
										<c:when test="${e2.resultStatus eq 'SUCCESS'}">  
									    	<td style="color:green">SUCCESS</td>     
									   </c:when>
									   <c:when test="${e2.resultStatus eq 'FAILURE'}">  
									    	<td style="color:red">FAILURE</td>     
									   </c:when>
									   <c:when test="${e2.resultStatus eq 'SKIP'}">  
									    	<td style="color:orange">SKIP</td>     
									   </c:when>									
									</c:choose>									   																
								<td title=${e2.costTime}ms>${e2.costTime}ms</td>
								<td><button  class="btn btn-success" data-toggle="modal" data-target="#resultModal" onclick="APIALLResults(this);">查看接口结果</button></td>
							</tr>
						</c:forEach>
					</tbody>

				</table>
				<hr>
			</div>
			
	<div class="modal fade" id="resultModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog" style="width: 80%">
	      <div class="modal-content">
	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
	            ×
	          </button>
	          <h4 class="modal-title" id="myModalLabel" style="color:red">
	            		接口结果显示
	          </h4>
	        </div>
				<div>
					<div class="main">
						<table id="cs_table" class="data-table" border="1" style="table-layout:fixed;">						
							<thead>
								<tr class="head" style="color:blue;font-weight:bold;">
									<td style="width:15%;">接口名称</td>
									<td style="width:20%;">请求URL</td>
									<td style="width:40%;">参数</td>
									<td style="width:10%;">状态码</td>
									<td style="width:35%;">响应</td>
									<td style="width:30%;">断言内容</td>
									<td style="width:10%;">消耗时间</td>
								</tr>
							</thead>
							<tbody id="interfaceResult">	
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

