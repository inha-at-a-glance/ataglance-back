<%--
  Created by IntelliJ IDEA.
  User: minji
  Date: 2024-11-21
  Time: 오전 1:20
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Image Viewer</title>
</head>
<body>
<h1>Uploaded Image</h1>
<div>
    <img src="${imageUrl}" alt="Uploaded Image" style="max-width:50%; height:auto;">
</div>
<div>
    <p>Image URL: <a href="${imageUrl}" target="_blank">${imageUrl}</a></p>
</div>
</body>
</html>
