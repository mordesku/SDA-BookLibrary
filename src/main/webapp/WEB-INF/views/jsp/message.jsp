<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<h2>${title}</h2>
<p>${message}</p>
<p>${requestScope['javax.servlet.forward.request_uri']}</p>


</body>
</html>