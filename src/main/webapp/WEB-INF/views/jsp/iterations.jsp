<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <c:forEach items="${items}" var="item">
        <tr>
            <td><c:out value="${item}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
