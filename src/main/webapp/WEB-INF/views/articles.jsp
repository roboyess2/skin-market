<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Статьи</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
    <div class="container">
        <div class="header">
            <div class="nav">
                <a href="${pageContext.request.contextPath}/home">Главная</a>
                <a href="${pageContext.request.contextPath}/listings">Скины</a>
                <a href="${pageContext.request.contextPath}/articles">Статьи</a>
            </div>
        </div>

        <h2>Статьи</h2>

        <c:forEach var="article" items="${articles}">
            <div style="background-color: #2a2a2a; padding: 20px; border-radius: 5px; margin-bottom: 15px;">
                <h3>
                    <a href="${pageContext.request.contextPath}/articles?id=${article.id}">${article.title}</a>
                </h3>
                <p style="color: #b0b0b0;">Просмотров: ${article.viewsCount}</p>
            </div>
        </c:forEach>

        <c:if test="${empty articles}">
            <p>Статей пока нет</p>
        </c:if>
    </div>
</body>
</html>