<%-- 
    Document   : cadastro
    Created on : 15/09/2015, 15:57:39
    Author     : Márcio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro</title>
    </head>
    <body>
        <div>
            <h1>Cadastre-se!</h1>
            <form action="../UsuarioController" method="post" id="cadastro">
                <label for="fnome">Nome</label>
                <input type="text" name="nome" id="fnome">
                <label for="fsenha">Senha</label>
                <input type="text" name="senha" id="fsenha">
                <input type="submit">
            </form>
        </div>
    </body>
</html>
