<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Выставить скин на продажу</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
    <div class="container">
        <div class="header">
            <a href="${pageContext.request.contextPath}/my-listings">← Назад</a>
        </div>

        <div class="form-container">
            <h2>Новое объявление</h2>

            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>

            <form method="post" action="${pageContext.request.contextPath}/listing/create" enctype="multipart/form-data">
                <div class="form-group">
                    <label>Название скина:</label>
                    <input type="text" name="title" required>
                </div>
                <div class="form-group">
                    <label>Описание:</label>
                    <textarea name="description"></textarea>
                </div>
                <div class="form-group">
                    <label>Цена ($):</label>
                    <input type="number" step="0.01" name="price" required>
                </div>
                <div class="form-group">
                    <label>Фотографии скина:</label>
                    <input type="file" name="images" accept="image/*" multiple>
                </div>
                <button type="submit" class="btn btn-primary">Выставить на продажу</button>
            </form>
        </div>
    </div>
</body>
</html>