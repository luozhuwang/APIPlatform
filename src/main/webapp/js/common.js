//参考文献：https://www.cnblogs.com/lideqiang/p/5667576.html
function get_url(url){
	$.ajax( {
        type : "get",   	
        success :function(response) {  
        } 
    });  
    window.open(url);	
}
//post成功打开一个新的浏览器窗口
function post_submit(action,PARAMTERS) {	    
	 var temp_form = document.createElement("form");    
	 temp_form.id = "form1";  
	 temp_form.name = "form1"; 	
     temp_form .action = action;      
     temp_form .target = "_blank";
     temp_form .method = "post";      
     temp_form .style.display = "none";    
	 
	//添加参数
     for (var item in PARAMTERS) {
         var opt = document.createElement("input");
         opt.type="hidden";
         opt.name = PARAMTERS[item].name;
         opt.value = PARAMTERS[item].value;
         temp_form.appendChild(opt);
     }
     document.body.appendChild(temp_form);      
     temp_form .submit();     
}
