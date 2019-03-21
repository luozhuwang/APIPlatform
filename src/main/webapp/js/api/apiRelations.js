function htmlEscape(text){ 
  return text.replace(/[<>"&]/g, function(match, pos, originalText){
    switch(match){
    case "\"":return "\\\""; 
  } 
  }); 
}
function getAPIRelations(){
var rows=$("#API_table tr[id='row_api']");
var APIRelations="[";
var lengths=rows.length;
rows.each(function (index){
var api="{";
//	console.info();$(this).find("td#APIName").html();
	var APIId=$(this).find("td#APIId").html();
	api=api+"\"APIId\":\""+APIId+"\",";
	var APIName=$(this).find("td#APIName").html();
	api=api+"\"APIName\":\""+APIName+"\",";
	var caseData=$(this).find("td#caseData").html();
	//判断特殊字符进行转义
	api=api+"\"caseData\":\""+htmlEscape(caseData)+"\",";
	var caseAssert=$(this).find("td#caseAssert").html();
	api=api+"\"caseAssert\":\""+htmlEscape(caseAssert)+"\",";
	var caseParam=$(this).find("td#caseParam").html();
	api=api+"\"caseParam\":\""+htmlEscape(caseParam)+"\"}";
	
	//如果是最后1个不需要加,号
	if(index !=(lengths-1)){
		APIRelations=APIRelations+api+",";
	}else{
		APIRelations=APIRelations+api;
	}
});
APIRelations=APIRelations+"]";
//console.info(APIRelations);
return APIRelations
}