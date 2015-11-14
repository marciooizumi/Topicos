<jsp:useBean id="user" class="model.Usuario" scope="session" />
<div class="span3" id="user">
    <h2>${user.nome}</h2>
    <a href="cadastro.jsp">Editar</a> | <a href="Logout">Sair</a>
    <hr>
    <h3>Opções</h3>
    <ul class="menu">
        <li><a href="index.jsp">Início</a></li>
        <li class=""><a href="categoria.jsp">Categorias</a></li>
        <li><a href="listanoticias.jsp">Notícias</a></li>
    </ul>
</div>
