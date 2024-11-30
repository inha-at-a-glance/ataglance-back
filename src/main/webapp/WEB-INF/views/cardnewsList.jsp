<%--
  Created by IntelliJ IDEA.
  User: minji
  Date: 2024-11-22
  Time: 오전 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>전체 뉴스</title>
</head>
<body>
<h1>전체 뉴스</h1>
<ul>
    <c:forEach var="news" items="${newsList}">
        <li>
            <a href="/api/cardnews/news_id?newsId=${news.newsId}">${news.title}</a> - ${news.category}
        </li>
    </c:forEach>
</ul>
</body>
</html>

