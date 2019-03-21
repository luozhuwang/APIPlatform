<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传文件</title>
</head>
<body>
        <h3>上传1个文件</h3>
        <!-- 上传1个文件 -->
    <form action="<%=basePath%>upload.do" method="post" enctype="multipart/form-data">
        <input type="hidden" name="tuzi" value="tuzi">
        上传文件：<input type="file" name="uploadfile">
         <input type="submit" value="上传">
    </form>
    
        <!-- 上传多个文件 -->
        <div>
			<h3>上传多个文件</h3>
	        <form action="<%=basePath%>uploads.do" method="post" enctype="multipart/form-data">
	        <input type="hidden" name="tuzi" value="tuzi">
	        上传文件1：<input type="file" name="uploadfile">
	         <input type="hidden" name="tuzi" value="tuzi">
	        上传文件2：<input type="file" name="uploadfile">         
	         <input type="hidden" name="tuzi" value="tuzi">
	        上传文件3：<input type="file" name="uploadfile">
	         <input type="submit" value="上传">
	    </form>
    </div>
</body>
</html>