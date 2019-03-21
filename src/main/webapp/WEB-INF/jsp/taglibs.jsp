<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String LoginuserName= (String)request.getSession().getAttribute("LoginuserName");
	request.setAttribute("ctx", request.getContextPath());
	request.setAttribute("basePath",basePath);
	request.setAttribute("LoginuserName",LoginuserName);
%>