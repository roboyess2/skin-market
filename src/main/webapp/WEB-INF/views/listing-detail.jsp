<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${listing.title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
    <div class="container">
        <div class="header">
            <a href="${pageContext.request.contextPath}/listings">← Назад к списку</a>
        </div>

        <h2>${listing.title}</h2>

        <c:if test="${listing.sellerType == 'SITE'}">
            <p style="color: #4caf50;">Продавец: Сайт</p>
        </c:if>
        <c:if test="${listing.sellerType == 'USER'}">
            <p>Продавец: Пользователь #${listing.sellerId}</p>
        </c:if>

        <p class="price" style="font-size: 24px;">$ ${listing.price}</p>

        <div style="display: flex; gap: 10px; margin: 20px 0;">
            <c:forEach var="image" items="${images}">
                <img src="${pageContext.request.contextPath}/image/${image.imagePath}"
                             alt="${listing.title}"
                             style="max-width:100%; max-height:500px; border-radius:10px; margin:20px 0;">
            </c:forEach>
        </div>

        <c:if test="${not empty listing.description}">
            <h3>Описание:</h3>
            <p>${listing.description}</p>
        </c:if>

        <c:if test="${not empty listing.floatValue}">
            <p>Float: ${listing.floatValue}</p>
        </c:if>
        <c:if test="${not empty listing.patternIndex}">
            <p>Pattern Index: ${listing.patternIndex}</p>
        </c:if>
        <c:if test="${not empty listing.stickerInfo}">
            <p>Стикеры: ${listing.stickerInfo}</p>
        </c:if>
    </div>
</body>
</html>