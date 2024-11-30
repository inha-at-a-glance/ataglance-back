<%--
  Created by IntelliJ IDEA.
  User: minji
  Date: 2024-11-22
  Time: 오전 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>뉴스 상세</title>
</head>
<body>
<h1>${news.title}</h1>
<div>
<img src="${imageUrl}" alt="Uploaded Image" style="max-width:50%; height:auto;">
</div>
<div>
    <p>Image URL: <a href="${imageUrl}" target="_blank">${imageUrl}</a></p>
</div>
<ul>
    <c:forEach var="sentence" items="${summarySentences}">
        <li>${sentence}</li>
    </c:forEach>
</ul>

<p>카테고리: ${news.category}</p>
<p>출처: ${news.sourceBc}</p>
<p>원본 영상 경로: <a href="${news.sourceUrl}">${news.sourceUrl}</a></p>
</body>
</html>
