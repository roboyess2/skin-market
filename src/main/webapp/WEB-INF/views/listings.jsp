<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Скины на продажу</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
    <div class="container">
        <div class="header">
            <div class="nav">
                <div class="nav-left">
                    <a href="${pageContext.request.contextPath}/home">Главная</a>
                    <a href="${pageContext.request.contextPath}/listings">Скины</a>
                    <a href="${pageContext.request.contextPath}/articles">Статьи</a>
                </div>
                <div class="nav-right">
                    <c:if test="${not empty sessionScope.user}">
                        <a href="${pageContext.request.contextPath}/listing/create">Выставить скин</a>
                        <a href="${pageContext.request.contextPath}/my-listings">Мои объявления</a>
                        <a href="${pageContext.request.contextPath}/logout">Выйти</a>
                    </c:if>
                </div>
            </div>
        </div>

        <h2>Активные объявления</h2>

        <div class="listing-grid">
            <c:forEach var="listing" items="${listings}">
                <div class="listing-card">
                    <c:if test="${not empty listing.images}">
                        <%-- БЕРЁМ ФАЙЛЫ ИЗ A:\UPLOADS ЧЕРЕЗ СЕРВЛЕТ --%>
                        <img src="${pageContext.request.contextPath}/image/${listing.images[0].imagePath}"
                             alt="${listing.title}"
                             style="width:100%; height:200px; object-fit:cover; border-radius:5px;">
                    </c:if>
                    <c:if test="${empty listing.images}">
                        <div style="width:100%;height:200px;background:#333;border-radius:5px;display:flex;align-items:center;justify-content:center;">
                            <span style="color:#888;">Нет фото</span>
                        </div>
                    </c:if>
                    <h3>${listing.title}</h3>
                    <c:if test="${listing.sellerType == 'SITE'}">
                        <span style="color: #4caf50;">[От сайта]</span>
                    </c:if>
                    <p class="price">$ ${listing.price}</p>
                    <a href="${pageContext.request.contextPath}/listing/detail?id=${listing.id}" class="btn btn-primary">Подробнее</a>
                </div>
            </c:forEach>
        </div>

        <c:if test="${empty listings}">
            <p>Нет активных объявлений</p>
        </c:if>
    </div>
</body>
</html>