<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Мои объявления</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
    <div class="container">
        <div class="header">
            <div class="nav">
                <a href="${pageContext.request.contextPath}/home">Главная</a>
                <a href="${pageContext.request.contextPath}/listings">Скины</a>
                <a href="${pageContext.request.contextPath}/listing/create">Выставить скин</a>
                <a href="${pageContext.request.contextPath}/logout">Выйти</a>
            </div>
        </div>

        <h2>Мои объявления</h2>

        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>

        <table>
            <tr>
                <th>ID</th>
                <th>Название</th>
                <th>Цена</th>
                <th>Статус</th>
                <th>Действия</th>
            </tr>
            <c:forEach var="listing" items="${listings}">
                <tr>
                    <td>${listing.id}</td>
                    <td>${listing.title}</td>
                    <td>$ ${listing.price}</td>
                    <td>
                        <span class="status status-${listing.status.toLowerCase()}">
                            ${listing.status}
                        </span>
                    </td>
                    <td>
                        <c:if test="${listing.status == 'ACTIVE'}">
                            <form method="post" action="${pageContext.request.contextPath}/my-listings" style="display: inline;">
                                <input type="hidden" name="listingId" value="${listing.id}">
                                <input type="hidden" name="action" value="freeze">
                                <button type="submit" class="btn btn-warning">Заморозить</button>
                            </form>
                        </c:if>
                        <c:if test="${listing.status == 'FROZEN'}">
                            <form method="post" action="${pageContext.request.contextPath}/my-listings" style="display: inline;">
                                <input type="hidden" name="listingId" value="${listing.id}">
                                <input type="hidden" name="action" value="freeze">
                                <button type="submit" class="btn btn-primary">Разморозить</button>
                            </form>
                        </c:if>
                        <c:if test="${listing.status != 'SOLD' && listing.status != 'DELETED_BY_USER' && listing.status != 'DELETED_BY_MODERATOR'}">
                            <form method="post" action="${pageContext.request.contextPath}/my-listings" style="display: inline;">
                                <input type="hidden" name="listingId" value="${listing.id}">
                                <input type="hidden" name="action" value="delete">
                                <button type="submit" class="btn btn-danger">Удалить</button>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <c:if test="${empty listings}">
            <p>У вас пока нет объявлений. <a href="${pageContext.request.contextPath}/listing/create">Выставить скин</a></p>
        </c:if>
    </div>
</body>
</html>