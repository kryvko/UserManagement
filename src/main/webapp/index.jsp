<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.apache.log4j.Logger" %>
<%@ page import="org.apache.log4j.Level" %>
<% Logger logger=Logger.getLogger("error"); %>
<html>
<body>
    <a href="<c:url value="/roles"/>">Roles</a>
    <a href="<c:url value="/users"/>">Users</a>
    <a href="<c:url value="/pages/addRole.jsp"/>" >AddRole</a>
</body>
</html>