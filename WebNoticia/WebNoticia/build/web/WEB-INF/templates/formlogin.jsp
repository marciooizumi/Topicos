<div class="span3" id="user">    
    <form action="Login" method="post">
        <legend>Login</legend>
        <%
        if (request.getAttribute("error") != null) {
            out.print("<span class='label label-warning'>" + request.getAttribute("error") + "</span>");
        }
        %>
        <label for="usuario">Usuário:</label>
        <input type="text" name="usuario" id="usuario" placeholder="Login">

        <label for="senha">Senha:</label>
        <input type="password" name="senha" id="senha" placeholder="Senha">

        <br><button type="submit" class="btn btn-primary">Entrar</button>
    </form>        
    <a href="cadastro.jsp">Criar uma conta</a>    
</div>

