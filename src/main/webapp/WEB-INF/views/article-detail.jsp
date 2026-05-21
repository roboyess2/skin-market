<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${article.title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
    <div class="container">
        <div class="header">
            <a href="${pageContext.request.contextPath}/articles">← Все статьи</a>
        </div>

        <h1>${article.title}</h1>
        <p style="color: #b0b0b0;">
            Опубликовано: ${article.createdAt}
        </p>

        <div class="article-content">
            ${article.content}
        </div>
    </div>
</body>
</html>