 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <html>
 <head>
     <title>${empty article ? 'Новая статья' : 'Редактирование статьи'}</title>
     <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
 </head>
 <body>
     <div class="container">
         <div class="header">
             <a href="${pageContext.request.contextPath}/moderator/articles">← Назад</a>
         </div>

         <div class="form-container">
             <h2>${empty article ? 'Новая статья' : 'Редактирование статьи'}</h2>

             <form method="post" action="${pageContext.request.contextPath}/moderator/articles">
                 <input type="hidden" name="action" value="save">
                 <c:if test="${not empty article}">
                     <input type="hidden" name="id" value="${article.id}">
                 </c:if>

                 <div class="form-group">
                     <label>Заголовок:</label>
                     <input type="text" name="title" value="${article.title}" required>
                 </div>
                 <div class="form-group">
                     <label>Содержание:</label>
                     <textarea name="content" required>${article.content}</textarea>
                 </div>
                 <button type="submit" class="btn btn-primary">Сохранить</button>
             </form>
         </div>
     </div>
 </body>
 </html>