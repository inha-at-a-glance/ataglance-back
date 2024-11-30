<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>news list</title>
</head>
<body>
<h3>뉴스 등록 결과</h3>

<p>
    news_ID: ${news.newsId}     <br>
    제목: ${news.title}	<br>
    URL: ${news.sourceUrl}	<br>
    출처: ${news.sourceBc}	<br>
    카테고리: ${news.category}	<br>
</p>

<a href="/api/news/register">등록하기</a>

</body>
