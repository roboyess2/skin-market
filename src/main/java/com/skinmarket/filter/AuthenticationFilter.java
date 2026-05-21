package com.skinmarket.filter;

import com.skinmarket.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String path = req.getRequestURI();
        String contextPath = req.getContextPath();

        // Разрешённые пути без авторизации
        if (path.startsWith(contextPath + "/static") ||
                path.startsWith(contextPath + "/uploads") ||
                path.equals(contextPath + "/") ||
                path.equals(contextPath + "/home") ||
                path.equals(contextPath + "/login") ||
                path.equals(contextPath + "/register") ||
                path.equals(contextPath + "/listings") ||
                path.equals(contextPath + "/listing/detail") ||
                path.equals(contextPath + "/articles")) {
            chain.doFilter(request, response);
            return;
        }

        // Проверяем сессию
        HttpSession session = req.getSession(false);
        User user = null;
        if (session != null) {
            user = (User) session.getAttribute("user");
        }

        // Если не залогинен — на страницу входа
        if (user == null) {
            resp.sendRedirect(contextPath + "/login");
            return;
        }

        // Проверка доступа к страницам модератора
        if (path.startsWith(contextPath + "/moderator")) {
            if (!"MODERATOR".equals(user.getRole())) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Доступ запрещён");
                return;
            }
        }

        // Проверка доступа к созданию объявления и управлению своими объявлениями
        if (path.startsWith(contextPath + "/listing/create") ||
                path.startsWith(contextPath + "/my-listings")) {
            if (!"USER".equals(user.getRole()) && !"MODERATOR".equals(user.getRole())) {
                resp.sendRedirect(contextPath + "/login");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}