<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">         
        <title>Perfil - ${pontuacao.nome}</title>       
    </head>
    <body>        

        <ul>
            <li><a href="livros">Livros</a></li>
            <li><a href="perfil">Perfil</a></li>
            <li><a href="ranking">Ranking</a></li>
        </ul>

        <h1>${pontuacao.nome}</h1>

        <table>
            <tr>
                <td valign="top">Pontos<h1 id="pontuacao">${pontuacao.pontos}</h1></td>
                <td valign="top">
                    Troféus
                    <c:if test="${empty pontuacao.trofeus}">
                        <p>Nenhum troféu conquistado.</p>            
                    </c:if>
                    <c:if test="${not empty pontuacao.trofeus}">
                        <ul id="trofeus">
                            <c:forEach var="trofeu" items="${pontuacao.trofeus}">
                                <li>${trofeu}</li> 
                            </c:forEach>
                        </ul>
                    </c:if>
                </td>
            </tr>
        </table>
    </body>
</html>
