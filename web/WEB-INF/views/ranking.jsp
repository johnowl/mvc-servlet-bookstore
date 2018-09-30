<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ranking</title>
    </head>
    <body>
        
        <ul>
            <li><a href="livros">Livros</a></li>
            <li><a href="perfil">Perfil</a></li>
            <li><a href="ranking">Ranking</a></li>
        </ul>
        
        <h1>Ranking</h1>
        
         <c:if test="${empty ranking}">
          <p>Ranking vazio.</p>            
        </c:if>
    
        <c:if test="${not empty ranking}">
            <table id="ranking">            
                <tr>
                    <th>Nome</th>
                    <th>Pontuação</th>                    
                </tr>
            <c:forEach var="pontuacao" items="${ranking}">
                <tr>
                    <td class="nome">
                        ${pontuacao.nome}
                    </td>
                    <td class="pontuacao">
                        ${pontuacao.pontos}
                    </td>
                </tr>
            </c:forEach>
                
            </table>
        </c:if>
    </body>
</html>
