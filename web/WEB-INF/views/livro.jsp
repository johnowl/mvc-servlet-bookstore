<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <c:if test="${empty livro}">
             <title>Livro não encontrado</title>           
        </c:if>
        <c:if test="${not empty livro}">
             <title>Livro - ${livro.titulo}</title>           
        </c:if>        
    </head>
    <body>        
        
        <ul>
            <li><a href="livros">Livros</a></li>
            <li><a href="perfil">Perfil</a></li>
            <li><a href="ranking">Ranking</a></li>
        </ul>
        
         <c:if test="${empty livro}">
             <h1>Ops!</h1>
            <p>Livro não encontrado.</p>            
        </c:if>
    
        <c:if test="${not empty livro}">
            <h1>${livro.titulo}</h1>
            
            <table>
                <tr>
                    <td>
                        <img src="${livro.foto}" width="300" />
                    </td>
                    <td valign="top">
                        Autor: ${livro.autor} <br />
                        Páginas: ${livro.paginas} <br />
                        Estilo: ${livro.estilo} <br />
                                            <p>
                    <form method="post" action="registrar">
                        <input type="hidden" name="codigoLivro" value="${livro.codigo}" />                        
                        <input type="submit" id="registrar" value="Esse eu já li!" />
                    </form>
                        
                    </p>
                    </td>
                </tr>
            </table>
        </c:if>
    </body>
</html>
