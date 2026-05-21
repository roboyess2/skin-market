<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Панель модератора</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
    <div class="container">
        <div class="header">
            <a href="${pageContext.request.contextPath}/home">← На главную</a>
        </div>

        <h2>Панель модератора</h2>

        <div style="display: flex; gap: 20px; margin-top: 30px;">
            <div style="background-color: #2a2a2a; padding: 30px; border-radius: 5px; flex: 1;">
                <h3>Статьи</h3>
                <a href="${pageContext.request.contextPath}/moderator/articles" class="btn btn-primary">Управление статьями</a>
            </div>

            <div style="background-color: #2a2a2a; padding: 30px; border-radius: 5px; flex: 1;">
                <h3>Объявления</h3>
                <a href="${pageContext.request.contextPath}/moderator/listings" class="btn btn-primary">Управление объявлениями</a>
            </div>
        </div>
    </div>
</body>
</html>