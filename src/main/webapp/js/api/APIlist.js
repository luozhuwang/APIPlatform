//获取当前项目路径 
function  getPath(){
	var pathName = document.location.pathname;
	var index = pathName.substr(1).indexOf("/");
	var result = pathName.substr(0,index+1);
	return result;
}
function APIlist(modal){
	if(modal==null||modal==undefined||modal==""){
		modal=$(".modal.fade.in").attr("id");
	}
	var ctx=getPath();
	var element = $("#page");
	var AapiName=$("#AapiName").val();	
	$.ajax({  
        type : "post",  
        url : ctx+"/Interface/jsonlist",  
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
			                "<td  style='display:none' id='case_APIData'>"+result.list[i].apiParams+"</td>"+
			                "<td id='case_APIMethod'>"+s3_Method+"</td>"+
			                "<td><a class='btn btn-warning' id='choose' onclick='chooseAPI(\""+modal+"\","+result.list[i].id+")'>选择</a></td></tr>";                          
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
                         url : ctx+"/Interface/jsonlist",  
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
	                			                "<td  style='display:none' id='case_APIData'>"+result.list[i].apiParams+"</td>"+
	                			                "<td id='case_APIMethod'>"+s3_Method+"</td>"+
	                			                "<td><a class='btn btn-warning' id='choose' onclick='chooseAPI(\""+modal+"\","+result.list[i].id+")'>选择</a></td></tr>";                          
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

function chooseAPI(modal,id){
	var ModalLabel=$("#"+modal);
	ModalLabel.find("#ChooseAPIId").attr("value",$("#row"+id).find("#case_APIId").html());
	ModalLabel.find("#ChooseAPIName").attr("value",$("#row"+id).find("#case_APIName").html());
	ModalLabel.find("#ChooseAPIHost").attr("value",$("#row"+id).find("#case_APIHost").html());
	ModalLabel.find("#ChooseAPIUrl").attr("value",$("#row"+id).find("#case_APIUrl").html());
	ModalLabel.find("#ChooseAPIMethod").attr("value",$("#row"+id).find("#case_APIMethod").html());
	ModalLabel.find("#ChooseAPIData").html($("#row"+id).find("#case_APIData").html());
	ModalLabel.find("#ChooseAPIData").val($("#row"+id).find("#case_APIData").html());
	$('#addUserModal').modal('hide');
}