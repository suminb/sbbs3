<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register a post</title>
    </head>
    <body>

    <h1>Register a post</h1>
    
    <form method="post" action="Post/Register">
		
		<table>
            <tr>
				<td><input name="post_title" type="text" style="width:600px;" /></td>
            </tr>
			<tr>
				<td><textarea name="post_content" style="width:600px; height:300px;"></textarea></td>
			</tr>
			<tr>
				<td><input type="submit" value="Register" /></td>
			</tr>
    </form>
    
    </body>
</html>
