<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
    <div class="container">
        <div class="form-container">
            <h2>Вход в аккаунт</h2>

            <% if (request.getAttribute("error") != null) { %>
                <div class="error"><%= request.getAttribute("error") %></div>
            <% } %>

            <form method="post" action="${pageContext.request.contextPath}/login">
                <div class="form-group">
                    <label>Имя пользователя:</label>
                    <input type="text" name="username" required>
                </div>
                <div class="form-group">
                    <label>Пароль:</label>
                    <input type="password" name="password" required>
                </div>
                <button type="submit" class="btn btn-primary">Войти</button>
            </form>

            <p style="margin-top: 15px;">
                Нет аккаунта? <a href="${pageContext.request.contextPath}/register">Зарегистрироваться</a>
            </p>
        </div>
    </div>
</body>
</html>