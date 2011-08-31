<%@page contentType="text/html" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@taglib uri="/WEB-INF/tlds/post.tld" prefix="post" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Page</title>
	</head>
	<body>
	
	<sql:setDataSource var="dataSource" scope="application" dataSource="jdbc/Database" />
	
		<c:if test="${param.pid != null}">
			
			<c:catch var="e">
				<c:out value="Exception: ${e}" />
			</c:catch>
    
			<post:load id="${param.pid}" var="post" dataSource="${dataSource}" />
			<c:out value="${post.title}" /><br/>
			<c:out value="${post.content}" />
    
		</c:if>
	
	</body>
</html>
