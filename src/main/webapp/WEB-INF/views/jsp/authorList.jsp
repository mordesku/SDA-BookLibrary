<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Author list</title>
</head>
<body>
<table style="border: solid 1px; margin: auto">
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>actions</th>
        </tr>
        </thead>
    <tbody>
    <c:forEach items="${authors}" var="book">
        <tr style="border: solid 1px">
            <td style="border: solid 1px"><c:out value="${book.id}"/></td>
            <td style="border: solid 1px"><c:out value="${book.name}"/></td>
            <td style="border: solid 1px"><a href="/author/delete/<c:out value="${book.id}"/>">delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
