<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.skinmarket.model.User" %>
<html>
<head>
    <title>Skin Market - Главная</title>
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
                    <%
                        User user = (User) session.getAttribute("user");
                        if (user != null && "MODERATOR".equals(user.getRole())) {
                    %>
                        <a href="${pageContext.request.contextPath}/moderator">Панель модератора</a>
                    <% } %>
                </div>
                <div class="nav-right">
                    <%
                        if (user != null) {
                    %>
                        <span><%= user.getUserName() %> (<%= user.getRole() %>)</span>
                        <a href="${pageContext.request.contextPath}/my-listings">Мои объявления</a>
                        <a href="${pageContext.request.contextPath}/logout">Выйти</a>
                    <% } else { %>
                        <a href="${pageContext.request.contextPath}/login">Войти</a>
                        <a href="${pageContext.request.contextPath}/register">Регистрация</a>
                    <% } %>
                </div>
            </div>
        </div>

        <h1>Добро пожаловать на Skin Market</h1>
        <p>Покупайте и продавайте скины CS:GO</p>

        <div style="margin-top: 30px;">
            <a href="${pageContext.request.contextPath}/listings" class="btn btn-primary">Смотреть скины</a>
            <a href="${pageContext.request.contextPath}/articles" class="btn btn-primary">Читать статьи</a>
        </div>
    </div>
</body>
</html>