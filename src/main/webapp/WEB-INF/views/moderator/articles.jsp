<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Управление статьями</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
    <div class="container">
        <div class="header">
            <a href="${pageContext.request.contextPath}/moderator">← Назад</a>
            <a href="${pageContext.request.contextPath}/moderator/articles?action=new" class="btn btn-primary">Новая статья</a>
        </div>

        <h2>Статьи</h2>

        <table>
            <tr>
                <th>ID</th>
                <th>Заголовок</th>
                <th>Просмотры</th>
                <th>Действия</th>
            </tr>
            <c:forEach var="article" items="${articles}">
                <tr>
                    <td>${article.id}</td>
                    <td>${article.title}</td>
                    <td>${article.viewsCount}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/moderator/articles?action=edit&id=${article.id}" class="btn btn-warning">Редактировать</a>
                        <form method="post" action="${pageContext.request.contextPath}/moderator/articles" style="display: inline;">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="${article.id}">
                            <button type="submit" class="btn btn-danger">Удалить</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>