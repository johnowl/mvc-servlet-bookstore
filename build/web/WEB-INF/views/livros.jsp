<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Livros</title>
    </head>
    <body>
        
        <ul>
            <li><a href="livros">Livros</a></li>
            <li><a href="perfil">Perfil</a></li>
            <li><a href="ranking">Ranking</a></li>
        </ul>
        
        <h1>Livros</h1>
        
         <c:if test="${empty livros}">
          <p>Nenhum livro cadastrado.</p>            
        </c:if>
    
        <c:if test="${not empty livros}">
            <table>
                <tr>
            <c:forEach var="livro" items="${livros}">
                <td class="livro">
                    <img src="${livro.foto}" width="150" />
                    <p class="titulo"><a href="livro?id=${livro.codigo}">${livro.titulo}</a><br />
                    <p>${livro.autor}</p>
                </td>
            </c:forEach>
                </tr>
            </table>
        </c:if>
    </body>
</html>
