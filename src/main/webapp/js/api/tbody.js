var trs;

function editAPI(tdobject){
	//将所有数据清空
	cleanAll();	
//	 var tr=$(tdobject).parents("tr");
	 trs=$(tdobject).parents("tr");
//	 console.info(trs.find("td#APIName").html());
	 var APIId=trs.find("td#APIId").html();
	 var APIName=trs.find("td#APIName").html();
	 var apiHost=trs.find("td#apiHost").html();
	 var apiMethod=trs.find("td#apiMethod").html();
	 var apiUrl=trs.find("td#apiUrl").html();
	 var caseData=trs.find("td#caseData").html();
	 var caseAssert=trs.find("td#caseAssert").html();
	 var caseParam=trs.find("td#caseParam").html();
	 
	 $("div#editInterface input#ChooseAPIId").attr("value",APIId);
	 $("div#editInterface input#ChooseAPIName").attr("value",APIName);
	 $("div#editInterface input#ChooseAPIHost").attr("value",apiHost);
	 $("div#editInterface input#ChooseAPIMethod").attr("value",apiMethod);
	 $("div#editInterface input#ChooseAPIUrl").attr("value",apiUrl);
	 $("div#editInterface textarea#ChooseAPIData").html(caseData);
	 $("div#editInterface textarea#ChooseAPIData").val(caseData);

	 //断言和数据提取都需要特殊处理
	 GeneratelAssert(caseAssert);
	 GeneratelParam(caseParam);
}
function editAPISave(){
	var aa=$("div#editInterface table#para_table");
	var bb=$("div#editInterface table#table3");
	var AAPIName=$("div#editInterface input#ChooseAPIName").val();
	var AAPIId=$("div#editInterface input#ChooseAPIId").val();
	var AapiMethod=$("div#editInterface input#ChooseAPIMethod").val();
	var AapiHost=$("div#editInterface input#ChooseAPIHost").val();
	var AapiUrl=$("div#editInterface input#ChooseAPIUrl").val();
	
	var AcaseAssert="";
	var AcaseParam="";
	var AcaseData=$("div#editInterface textarea#ChooseAPIData").val();

	if(APIName==null||APIName==undefined||APIName==""){
		 alert("请选择接口");
		 return false;
	}
	
	var length2=bb.find("tr#row").length;
	if(length2>0){
		AcaseParam=changeTbodyToDetail(bb);
	}	
	var length1=aa.find("tr#row").length;
	if(length1>0){
		AcaseAssert=changeTbodyToDetail(aa);
	}else{
		alert("请添加断言");
		return false;
	}

	
	trs.find("td#APIId").html(AAPIId);
	trs.find("td#APIName").attr("title",AAPIName);
	trs.find("td#APIName").html(AAPIName);
	trs.find("td#apiHost").html(AapiHost);
	trs.find("td#apiMethod").html(AapiMethod);
	trs.find("td#apiMethod").attr("title",AapiMethod);
	trs.find("td#apiUrl").html(AapiUrl);
	trs.find("td#apiUrl").attr("title",AapiUrl);
	trs.find("td#caseData").html(AcaseData);
	trs.find("td#caseData").attr("title",AcaseData);
	trs.find("td#caseAssert").html(AcaseAssert);
	trs.find("td#caseParam").html(AcaseParam);
	 
	 $(".close").click();
}


function addApi(){
	var tbody=$("#API_table>tbody");
	var aa=$("div#addInterface table#para_table");
	var bb=$("div#addInterface table#table3");
	var APIName=$("#ChooseAPIName").val();
	var APIId=$("#ChooseAPIId").val();
	var apiMethod=$("#ChooseAPIMethod").val();
	var apiHost=$("#ChooseAPIHost").val();
	var apiUrl=$("#ChooseAPIUrl").val();
	
	var caseAssert="";
	var caseParam="";
	var caseData=$("#ChooseAPIData").val();

	if(APIName==null||APIName==undefined||APIName==""){
		 alert("请选择接口");
		 return false;
	}
	
	var length2=bb.find("tr#row").length;
	if(length2>0){
		caseParam=changeTbodyToDetail(bb);
	}	
	var length1=aa.find("tr#row").length;
	if(length1>0){
		caseAssert=changeTbodyToDetail(aa);
	}else{
		alert("请添加断言");
		return false;
	}
	
	var tr="<tr id='row_api'>"+
					"<td  id='APIId' style='display:none'>"+APIId+"</td>"+
					"<td  id='APIName'  title='"+APIName+"'>"+APIName+"</td>"+
					"<td  id='apiHost' style='display:none'>"+apiHost+"</td>"+
					"<td  id='apiUrl'  title='"+apiUrl+"'>"+apiUrl+"</td>"+
					"<td  id='apiMethod'  title='"+apiMethod+"'>"+apiMethod+"</td>"+
					"<td  id='caseData' title='"+caseData+"'>"+caseData+"</td>"+
					"<td  id='caseAssert' style='display:none'>"+caseAssert+"</td>"+
					"<td  id='caseParam' style='display:none'>"+caseParam+"</td>"+
					"<td  align='center'><button  class='btn btn-info' data-toggle='modal'  data-target='#editInterface' onclick='editAPI(this)'>"+"编辑"+"</button><button  class='btn btn-primary up' onclick='moveUp(this)'>"+"上移"+"</button><button  class='btn btn-danger' onclick='deletetbody(this)'>"+"删除"+"</button><button  class='btn btn-primary up' onclick='moveDown(this)'>"+"下移"+"</button></td>"
					
	tbody.append(tr)
	$(".close").click();
	cleanAll();
}
//将所有数据清空
function cleanAll(){
	$("#ChooseAPIName").attr("value","");
	$("#ChooseAPIData").val("");
	$("#ChooseAPIMethod").attr("value","");
	$("#ChooseAPIHost").attr("value","");
	$("#ChooseAPIUrl").attr("value","");
	deletetableAlltr();
}
function deletetbody(tdobject){
	    var td=$(tdobject);
	    td.parents("tr").remove();
	    deletetableAlltr();
	   
}

function moveUp(tdobject){
    var td=$(tdobject);
    var $tr = td.parents("tr");
    if ($tr.index() != 0) {
		$tr.fadeOut().fadeIn();
		$tr.prev().before($tr);		
    }else{
    	alert("首行不能向上移动")
    }
}
function moveDown(tdobject){
	var maxIndex=$(".down").parent().length-2;
    var td=$(tdobject);
    var $tr = td.parents("tr");
    
	$tr.fadeOut().fadeIn();
	$tr.next().after($tr);
    
}
function deletetableAlltr(){
	$("#para_table>tbody>tr[id='row']").remove();
	$("#table3>tbody>tr[id='row']").remove();
}
