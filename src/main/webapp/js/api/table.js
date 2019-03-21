function htmlEscape(text){ 
  return text.replace(/[<>"&]/g, function(match, pos, originalText){
    switch(match){
    case "\"":return "\\\""; 
  } 
  }); 
}

function addtable1tr(ModalLabel){
		var modal=$("div#"+ModalLabel);
	    var table = modal.find("#para_table");
			var tr= $("<tr id='row'>" +
			"<td><select id='assertItem' name='assertItem' class='selectpicker show-tick form-control' ><option value='状态码'>状态码</option><option value='响应报文'>响应报文</option></select>"+"</td>"+
//	        "<td><input type='text' id='assertItem' name='assertItem' value='' class='form-control' placeholder='断言项' />"+"</td>" +
//	        "<td><input type='text' id='actual' name='actual' value='' class='form-control' placeholder='实际值' />"+"</td>" +
			"<td><select class='selectpicker show-tick form-control' id='compare' name='compare' ><option value='包含'>包含</option><option value='等于'>等于</option><option value='不等于'>不等于</option></select>"+"</td>" +
	        "<td><input type='text' id='expect' name='expect' value='' class='form-control' placeholder='期望值' />"+"</td>" +
	        "<td  align='center' onclick='deletetable1tr(this)'><button type='button'  class='btn btn-danger' >"+"删除"+"</button></td></tr>");
	    table.append(tr);
	}
	function deletetable1tr(tdobject){
	    var td=$(tdobject);
	    td.parents("tr").remove();
	}

	function addtable2tr(ModalLabel){
		var modal=$("div#"+ModalLabel);
	    var table = modal.find("#table3");
			var tr= $("<tr id='row'>" +
	        "<td><input type='text' id='param'  name='param' value='' class='form-control' placeholder='参数名' />"+"</td>" +
	        "<td><select id='method' name='method' class='selectpicker show-tick form-control' ><option value='JSON'>JSON格式</option><option value='Regular'>正则表达式</option></select>"+"</td>" +
			"<td><input type='text' id='rule'  name='rule' value=''  class='form-control' placeholder='规则'/>"+"</td>" +
	        "<td  align='center' onclick='deletetable2tr(this)'><button type='button'  class='btn btn-danger' >"+"删除"+"</button></td></tr>");
	    table.append(tr);
	}
	function deletetable2tr(tdobject){
	    var td=$(tdobject);
	    td.parents("tr").remove();
	}
	
	function deletetableAlltr(){
		$("#para_table>tbody>tr[id='row']").remove();
		$("#table3>tbody>tr[id='row']").remove();
	}
	
	//从一个tbody中获取数据并置入隐藏域
	function changeTbodyToDetail(tbody) {
			var result = "[";
	        tbody.find("tr#row").each(function () {
	            var b = changeTrToDetail($(this));
	            result = result + b + ",";
	        });
	        result = result.substring(0, result.length - 1)+"]";
			 return result
	}
	 
	 
	//从一个tr中获取数据
	function changeTrToDetail(tr) {
	        var result = "{";
	        tr.find("td").each(function () {
	            var a = changeTdToDetail($(this));
	            if (a!=""){
	                result = result+ a + ",";
	            }
	        });
	        result = result.substring(0, result.length - 1) + "}";
	        return result;
	}
	 
	//从一个td中获取一个数据
	function changeTdToDetail(id) {
			var result="";
	        var input = id.find("input[type='text']");
	        if (input.length > 0) {
	            var name = input.prop("name");
	            var val = input.val();
	            result = result = "\"" +name + "\"" + ":\"" + htmlEscape(val) + "\"";
	            return result;
	        }
	        else{
	            var select = id.find("select");
	            var name = select.prop("name");
	            var val = select.val();
	            if(name==undefined){
	                return "";
	            }else{
	                result = "\"" +name + "\"" + ":\"" + htmlEscape(val) + "\"";
	                return result;
	            }
	        }
	}
	
	//将断言显示在页面
	function GeneratelAssert(caseAssert){		
		if(caseAssert==null||caseAssert==undefined||caseAssert==""){
			 console.info("断言内容为空")
			 return false;
		}else{
			var objs = eval(caseAssert);
					$("div#editInterface table#para_table>tbody>tr[id='row']").remove();
					for(var i = 0;i<objs.length;i++){
						addtable1tr('editInterface');  
						var assertItem=$("#editInterface select#assertItem:eq("+i+")");
						var content1=objs[i].assertItem
						assertItem.val(content1)
						
						var compare=$("#editInterface select#compare:eq("+i+")");
						var content2=objs[i].compare; 
						compare.val(content2);
						
						var expect=$("#editInterface input#expect:eq("+i+")");
						var content3=objs[i].expect; 
						expect.val(content3);
					}   
		}
		   
	}	
	
	//将数据提取显示在页面
	function GeneratelParam(caseParam){
		if(caseParam==null||caseParam==undefined||caseParam==""){
			 console.info("数据提取内容为空")
			 return false;
		}else{
			var objs = eval(caseParam);		
				$("div#editInterface table#table3>tbody>tr[id='row']").remove();
				   for(var i = 0;i<objs.length;i++){
					addtable2tr('editInterface');  
					var param=$("#editInterface input#param:eq("+i+")");
					var content1=objs[i].param
					param.val(content1)
					
					var method=$("#editInterface select#method:eq("+i+")");
					var content2=objs[i].method; 
					method.val(content2);
					
					var rule=$("#editInterface input#rule:eq("+i+")");
					var content3=objs[i].rule; 
					rule.val(content3);
				  }		
		}
		   
	}