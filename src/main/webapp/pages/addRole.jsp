<%--
  Created by IntelliJ IDEA.
  User: artem
  Date: 17.01.18
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>jQuery.post demo</title>
</head>
<body>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
    jQuery.ajax({
        url: '/roles',
        data : JSON.stringify({id: "3", name: "MANAGER"}),
        contentType : 'application/json',
        type : 'POST'
    });
</script>

added role

</body>
</html>