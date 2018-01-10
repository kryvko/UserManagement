<%@ page import="org.apache.log4j.Logger" %>
<%@ page import="org.apache.log4j.Level" %>
<% Logger logger=Logger.getLogger("error"); %>
<html>
<body>
<%
    logger.log(Level.ERROR, "log message from TestLog JSP-page");
%>
Log4j test JSP-page
</body>
</html>