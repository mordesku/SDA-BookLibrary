<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book list</title>
</head>
<body>
<table style="border: solid 1px; margin: auto">
        <thead>
        <tr>
            <th>id</th>
            <th>title</th>
            <th>author</th>
            <th>actions</th>
        </tr>
        </thead>
    <tbody>
    <c:forEach items="${books}" var="book">
        <tr style="border: solid 1px">
            <td style="border: solid 1px"><c:out value="${book.id}"/></td>
            <td style="border: solid 1px"><c:out value="${book.title}"/></td>
            <td style="border: solid 1px"><c:out value="${book.author.name}"/></td>
            <td style="border: solid 1px"><a href="/sda/book/delete/<c:out value="${book.id}"/>">delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
