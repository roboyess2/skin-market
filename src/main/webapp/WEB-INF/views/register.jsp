<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Регистрация</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
    <div class="container">
        <div class="form-container">
            <h2>Регистрация</h2>

            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>

            <form method="post" action="${pageContext.request.contextPath}/register">
                <div class="form-group">
                    <label>Имя пользователя:</label>
                    <input type="text" name="username" required>
                </div>
                <div class="form-group">
                    <label>Email:</label>
                    <input type="email" name="email" required>
                </div>
                <div class="form-group">
                    <label>Пароль:</label>
                    <input type="password" name="password" required>
                </div>
                <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
            </form>

            <p style="margin-top: 15px;">
                Уже есть аккаунт? <a href="${pageContext.request.contextPath}/login">Войти</a>
            </p>
        </div>
    </div>
</body>
</html>