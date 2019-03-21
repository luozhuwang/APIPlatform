//获取当前项目路径 
function  getPath(){
	var pathName = document.location.pathname;
	var index = pathName.substr(1).indexOf("/");
	var result = pathName.substr(0,index+1);
	return result;
}


$(function getReport() {
	var basePath=getPath();
    // 路径配置
    require.config({
        paths: {
        	 echarts: basePath+"/echarts-2.2.7"
        }
    });
    //动态加载饼状图
    require(
        [
             'echarts',  
//                 'echarts/chart/line',
                'echarts/chart/pie',
//                 'echarts/chart/bar'   
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var pie_data = ec.init(document.getElementById('pie_data'));
            
        	$.ajax({
      		  type: "POST",
      		  url: basePath+"/CaseSet/setReport",
      		  dataType: "json",  
      		  success : function(result) {  	    	        
      			pie_data.setOption({
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient : 'vertical',
                        x : 'left',
                        // data:['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
                        data:result
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            magicType : {
                                show: true,
                                type: ['pie', 'funnel'],
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
                            name:'访问来源',
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
        	
            // 为echarts对象加载数据
            pie_data.setOption(option_pie);
        }
    )
});