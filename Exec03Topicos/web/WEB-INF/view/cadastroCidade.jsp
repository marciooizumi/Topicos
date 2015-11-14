<%-- 
    Document   : newjsp
    Created on : 19/09/2015, 02:15:31
    Author     : Márcio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Cadastre a cidade!</h1>
        <form action="cadastroCid" method="post" id="cadastro">
            <h2>${requestScope.acao}</h2>
            <input type="hidden" name="" value="${requestScope.acao}">
            <label for="fNomeCid">Nome da cidade: </label>
            <input type="text" name="nomeCidade" id="fNomeCid" autofocus="true" value="${requestScope.nomeCidU}">
            <label for="fEstado">Estado: </label>
            <input type="text" name="estado" id="fEstado" value="${requestScope.estadoU}">
            <input type="submit" value="${requestScope.btn}">
        </form>
        
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <ul>
            <c:forEach items="${cidade}" var="ci">
                <li>${ci.nomeCidade} - ${ci.estado} 
                    <a href="cadastroCid?acao=D&id=${ci.id}" >Excluir</a>
                    <a href="cadastroCid?acao=U&id=${ci.id}" >Alterar</a>
                </li>
            </c:forEach>
                
        </ul%>

        <a href="cadastrar">Voltar para cadastro de usuarios</a>
    </body>
</html>
