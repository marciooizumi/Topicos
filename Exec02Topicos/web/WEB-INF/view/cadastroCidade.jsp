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
            <form action="CidadeController" method="post" id="cadastro">
                <label for="fNomeCid">Nome</label>
                <input type="text" name="nomeCidade" id="fNomeCid">
                <label for="fEstado">Senha</label>
                <input type="text" name="estado" id="fEstado">
                <input type="submit">
            </form>
    </body>
</html>
