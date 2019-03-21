1.下载插件：
https://github.com/uxsolutions/bootstrap-datepicker
2.加载插件：
<script type="text/javascript" src="<%=basePath%>jQuery/2.1.4/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>bootstrap/3.3.7/css/bootstrap-datepicker3.min.css">
<script type="text/javascript" src="<%=basePath%>bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>bootstrap/3.3.7/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="<%=basePath%>bootstrap/3.3.7/js/bootstrap-datepicker.zh-CN.min.js"></script>
 
 3.添加标签：
 上线日期：<input type="text" style="width: 190px; height: 30px" value="${online_date}" name="online_date" id="online_date">
 4.增加函数：
<script>
$(function () {
    $("#online_date").datepicker({
    	format: 'yyyy-mm-dd', //日期格式
    	language:'zh-CN',//中文
    	autoclose:true//选择后自动关闭
    });
});
</script>


5.select 标签使用
<table class="table  table-bordered" id="table3">
   <tbody>
    <tr> 
     <th style="text-align:center" width="200">参数名</th> 
     <th style="text-align:center" width="200">取值方式</th> 
     <th style="text-align:center" width="25">规则</th> 
     <th style="text-align:center" width="25">操作</th> 
    </tr>
    <tr>
     <td><input type="text" id="assert" value="" class="form-control" placeholder="参数名" /></td>
     <td><select id="getValue" name="getValue" class="selectpicker show-tick form-control"><option value="JSON">JSON格式</option><option value="Regular">正则表达式</option></select></td>
     <td><input type="text" id="rule" value="" class="form-control" placeholder="规则" /></td>
     <td align="center" onclick="deletetr2(this)"><button type="button" class="btn btn-danger">删除</button></td>
    </tr>
   </tbody>
  </table>
  