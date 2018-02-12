<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome ${name} in Spring MVC</title>
</head>
<body>
    <h2>Welcome ${name} in Spring MVC</h2>
    <p>${date}</p>
    <p>${requestScope['javax.servlet.forward.request_uri']}</p>
    <spring:url value="/upload"/>
    <c:if test="${name != 'dziadek'}">
        dupa
    </c:if>
    <form action="<spring:url value="/upload"/>" method="post" enctype="multipart/form-data">
        <input name="file" type="file">
        <input type="submit">
    </form>
</body>
</html>