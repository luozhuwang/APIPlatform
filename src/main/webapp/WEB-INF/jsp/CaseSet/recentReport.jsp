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
<script type="text/javascript" src="${basePath}jQuery/2.1.4/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${basePath}bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${basePath}bootstrap/3.3.7/css/bootstrap-datepicker3.min.css">
<script type="text/javascript" src="${basePath}bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript"  src="${basePath}echarts-2.2.7/echarts.js"></script>
<script>
function getresultList(batchNo){
	var div,tdStatus;
	$.ajax({
		  type: "POST",
		  url: "../CaseSet/batch/"+batchNo,
		  dataType: "json", 
		  success : function(result) {
			  var json = eval(result); //数组   
			  var jsonlenth=json.length;    
	        	if(jsonlenth==0){  
	        		div+="<tr><td colspan='5'>无</td></tr>"        		
	        	}else{
	        		for (var i = 0; i <jsonlenth; i++) {
	        			var responseStatus=result[i].resultStatus;
	        			if(responseStatus=="SUCCESS"){
	        				tdStatus="<td style='color:green'>"+result[i].resultStatus+"</td>"
	        			}else if(responseStatus=="FAILURE"){
	        				tdStatus="<td style='color:red'>"+result[i].resultStatus+"</td>"
	        			}else{
	        				tdStatus="<td style='color:orange'>"+result[i].resultStatus+"</td>"
	        			}
	        			div+="<tr id='row_"+result[i].caseId+"'><td id='batch' style='display: none'>"+batchNo+"</td><td id='caseId' style='display: none'>"+result[i].caseId+"</td>"+
	        			"<td title='"+result[i].caseName+"' id='caseName'>"+result[i].caseName+"</td>"+
	        			"<td title='"+result[i].startTime+"'>"+result[i].startTime+"</td>"+
	        			"<td title='"+result[i].endTime+"' >"+result[i].endTime+"</td>"+tdStatus+
	        			"<td title='"+result[i].costTime+"'>"+result[i].costTime+"ms</td>"
// 	        			+"<td><button  class='btn btn-success'>查看接口结果</button></td></tr>"
	        		}
	        	}
	        	$("#caseList").html(div);
	        },error:function(result){			 
				alert("用例集获取用例结果异常");
			}  
		});
}

function getPie(batchNo,textTitle) {
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
        	$.ajax({
      		  type: "POST",
      		  url: "../CaseSet/batchReport/"+batchNo,
      		  dataType: "json",  
      		  success : function(result) {  	    	        
      			pie_data.setOption({
      				title:{
                		show:true,
                		x:'center',
                		text:textTitle                		
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
      					alert("请求后台报表异常");
      			}
      		});
        	
        }
    )
}
</script>
<script>
$(function() {
    // 路径配置
    require.config({
        paths: {
        	 echarts: '<%=basePath%>echarts-2.2.7'
			}
		});
		//动态加载饼状图
		require(
				[ 
				  'echarts', 'echarts/chart/line', 
				  'echarts/chart/pie',// 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
					'echarts/chart/bar' ],
				function(ec) {
					// 基于准备好的dom，初始化echarts图表
					var pie_last = ec.init(document.getElementById('pie_last'));
					var succTotal = 0, failTotal = 0, skipTotal = 0, batchNo = 0, xAxisData
					var succStatus, failStatus, skipStatus
					var legends = [];// 准备存放图例数据
					$.ajax({
						type : "POST",
						url : "../CaseSet/recentData",
						dataType : "json",
						success : function(result) {
							var json = eval(result); //数组            			 
							succStatus = json[0].resultStatus;
							failStatus = json[1].resultStatus;
							skipStatus = json[2].resultStatus;
							succTotal = json[0].totalArray;
							failTotal = json[1].totalArray;
							skipTotal = json[2].totalArray;
							batchNo = json[3].totalArray;
							xAxisData = json[4].totalArray;
							legends.push(succStatus);// 将每一项的图例名称也放到图例的数组中
							legends.push(failStatus);// 将每一项的图例名称也放到图例的数组中
							legends.push(skipStatus);// 将每一项的图例名称也放到图例的数组中  	
										
							if(batchNo==0){
								pie_last.setOption({									
									noDataLoadingOption: {
										text: '暂无数据',
										effect: 'bubble',
										effectOption: {
											effect: {
												n: 0
											}
										},
										textStyle : {
											fontSize : 18,
											fontWeight : 'bolder',
											color : '#333'
										}
									},			
				                    series : [
				                        {
				                            name:'执行用例',
				                            type:'pie',
				                            radius : '55%',
				                            center: ['50%', '60%'],
				                            data:[]
				                        }
				                    ]
									
								})
							}
							else{
							pie_last.setOption({							
									title : {
										show : true,
										text : "历史运行结果图",
										x : 'left',
										textStyle : {
											fontSize : 18,
											fontWeight : 'bolder',
											color : '#333'
										}
									},
									tooltip : {
										trigger : "axis"
									},
									toolbox: {
				                        show : true,
				                        feature : {
				                            dataView : {show: true, readOnly: false},
				                            magicType : {
				                                show: true,
				                                type: ['line','bar'],
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
									//x轴显示
									xAxis : {
										data : xAxisData,
										splitLine : {
											show : false
										}
									},
									//y轴显示
									yAxis : {
										splitLine : {
											show : false
										}
									},
									legend : {
										data : legends
									},
									series : [ 
										{
											name : succStatus,
											type : "bar",
											stack : "业务",//折叠显示
											data : succTotal,
											barWidth : 38,
											//显示颜色
											itemStyle : {
												normal : {
													color : "#FF8849"
												}
											}
										}, 
										{
											name : failStatus,
											type : "bar",
											stack : "业务",
											data : failTotal,
											barWidth : 38,
											itemStyle : {
												normal : {
													color : "#87CEFA"
												}
											}
										}, 
										{
											name : skipStatus,
											type : "bar",
											stack : "业务",
											data : skipTotal,
											barWidth : 38,
											itemStyle : {
												normal : {
													color : "#DA70D6"
												}
											}
										} 
									]
								});
										
							}
							getresultList(batchNo[0]);
							getPie(batchNo[0], "最近一次");						
						},
						error : function(result) {
							alert("请求后台异常");
						}

					});
					//柱子点击事件
					var ecConfig = require('echarts/config');
					function eConsole(param) {
						if (typeof param.seriesIndex != 'undefined') {
							switch (param.dataIndex) {
							case 0:
								getresultList(batchNo[0]);
								getPie(batchNo[0], param.name);
								break;
							case 1:
								getresultList(batchNo[1]);
								getPie(batchNo[1], param.name);
								break;
							case 2:
								getresultList(batchNo[2]);
								getPie(batchNo[2], param.name);
								break;
							case 3:
								getresultList(batchNo[3]);
								getPie(batchNo[3], param.name);
								break;
							case 4:
								getresultList(batchNo[4]);
								getPie(batchNo[4], param.name);
								break;
							case 5:
								getresultList(batchNo[5]);
								getPie(batchNo[5], param.name);
								break;
							case 6:
								getresultList(batchNo[6]);
								getPie(batchNo[6], param.name);
								break;
							default:
								break;
							}
						}
					}
					pie_last.on(ecConfig.EVENT.CLICK, eConsole);
				})
	});
</script>
</head>
<body>
<div id="pie_last" class="col-sm-6" style="height:400px;"></div>
<div id="pie_data" class="col-sm-6" style="height:400px;"></div>
<div id="caseResult">
	<table id="cs_table" class="data-table" border="1" style="table-layout:fixed;">
					<thead >
						<tr class="head" style="background: green;">
							<td style="width:20%;font-weight:bold;">用例名称</td>
							<td style="width:15%;font-weight:bold;">开始时间</td>
							<td style="width:15%;font-weight:bold;">结束时间</td>
							<td style="width:10%;font-weight:bold;">结果</td>
							<td style="width:10%;font-weight:bold;">耗时</td>
<!-- 							<td style="width:10%;font-weight:bold;">操作</td> -->
						</tr>
					</thead>
					<tbody id="caseList" >						
					</tbody>
	</table>
</div>
</body>
</html>