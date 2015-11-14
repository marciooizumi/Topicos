<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro</title>
        <%@page import="java.util.List"%>
        <%@page import="model.Usuario"%>
    </head>
    <body>
        <div>
            <h1>Cadastre-se!</h1>
            <form action="cadastrar" method="post" id="cadastro">
                <label for="fnome">Nome</label>
                <input type="text" name="nome" id="fnome">
                <label for="fsenha">Senha</label>
                <input type="text" name="senha" id="fsenha">
                <input type="submit">
            </form>
            <a href="cadastroCid">Voltar para cadastro de cidades</a>
        </div>
        <h1>Relação de usuarios</h1>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <ul>
            <c:forEach items="${usuario}" var="usu">
                <li>${usu.nome} - ${usu.senha}</li>
            </c:forEach>
                
        </ul%>
    </body>
</html>
