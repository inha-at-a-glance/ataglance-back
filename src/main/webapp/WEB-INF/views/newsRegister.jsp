
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>news register</title>
</head>
<body>
<h3>뉴스 원본 등록</h3>

<form action="/api/news/register" method="post">
    <table border="1">
        <tr>
            <td>링크</td>
            <td><input type="text" name="sourceUrl"></td>
        </tr>
        <tr>
            <td>출처</td>
            <td>
                <label><input type="radio" name="sourceBc" value=1>MBC</label>
                <label><input type="radio" name="sourceBc" value=2>SBS</label>
                <label><input type="radio" name="sourceBc" value=3>KBS</label>
            </td>
        </tr>
        <tr>
            <td>카테고리</td>
            <td>
                <label><input type="radio" name="category" value=1>경제</label>
                <label><input type="radio" name="category" value=2>사회</label>
                <label><input type="radio" name="category" value=3>생활</label>
                <label><input type="radio" name="category" value=4>IT</label>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="right"><input type="submit" value="등록"></td>
        </tr>
    </table>
</form>

</body>
</html>
