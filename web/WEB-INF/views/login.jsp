<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <form method="POST" action="login">
            <c:if test="${erroLogin == true}">
                <p>Usuário ou senha inválida.</p>
            </c:if>
                
            <c:if test="${param.expired == 'true'}">
                <p>Faça login para continuar.</p>
            </c:if>
            
            <label>E-mail</label>
            <input type="text" name="email" id="email" />

            <label>Senha</label>
            <input type="password" name="senha" id="senha" />

            <input type="submit" value="Login" id="entrar" />
            
        </form>
    </body>
</html>
