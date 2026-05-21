<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Управление объявлениями</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
    <div class="container">
        <div class="header">
            <a href="${pageContext.request.contextPath}/moderator">← Назад</a>
        </div>

        <h2>Все объявления</h2>

        <table>
            <tr>
                <th>ID</th>
                <th>Название</th>
                <th>Цена</th>
                <th>Тип продавца</th>
                <th>Статус</th>
                <th>Действия</th>
            </tr>
            <c:forEach var="listing" items="${listings}">
                <tr>
                    <td>${listing.id}</td>
                    <td>${listing.title}</td>
                    <td>$ ${listing.price}</td>
                    <td>${listing.sellerType}</td>
                    <td>
                        <span class="status status-${listing.status.toLowerCase()}">${listing.status}</span>
                    </td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/moderator/listings" style="display: inline;">
                            <input type="hidden" name="listingId" value="${listing.id}">
                            <input type="hidden" name="action" value="delete">
                            <button type="submit" class="btn btn-danger">Удалить</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>