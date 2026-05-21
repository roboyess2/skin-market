<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Ошибка</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
    <div class="container">
        <div class="form-container">
            <h2>Произошла ошибка</h2>
            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>
            <c:if test="${empty error}">
                <div class="error">Что-то пошло не так. Попробуйте позже.</div>
            </c:if>
            <a href="${pageContext.request.contextPath}/home" class="btn btn-primary">На главную</a>
        </div>
    </div>
</body>
</html>