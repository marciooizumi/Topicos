<%@page import="model.Usuario"%>
<jsp:include page="WEB-INF/templates/header.jsp" />
<div class="container">
    <div class="row">
        <div class="span12">
<%
    String botao = "";
    Usuario user = (Usuario)request.getSession().getAttribute("user");
    if (user == null) {
%>
        <h2>Cadastre-se!</h2>
        <p>Para cadastrar-se basta informar o nome, usuário e a senha</p>
<%
        botao = "Cadastrar";
    }                
    else {
%>
                <h2>Alterar dados do usuário</h2>
                <a href="index.jsp">Voltar</a>
<%
        botao = "Salvar";
%>
<hr>
<%
 if (request.getAttribute("error") != null) {
     out.print("<span class='label label-warning'>" + request.getAttribute("error") + "</span>");
 }

 %>
            <form action="UsuarioControler" class="form-horizontal" method="post" id="cadastro">
              <input type="hidden" name="codigo" value="${user.codigo}">
              <div class="control-group">
                <label class="control-label" for="inputNome">Nome</label>
                <div class="controls">
                  <input type="text" name="nome" id="inputNome" placeholder="Nome completo" value="${user.nome}">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="inputEmail">Usuário</label>
                <div class="controls">
                  <input type="text" name="usuario" id="inputEmail" placeholder="Usuario" value="${user.usuario}">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="inputPassword">Senha</label>
                <div class="controls">
                  <input type="password" name="senha" id="inputPassword" placeholder="Senha" value="${user.senha}">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="inputPassword1">Confirmação</label>
                <div class="controls">
                  <input type="password" id="inputPassword1" placeholder="Confirmação da senha" value="${user.senha}">
                </div>
              </div>
              <hr>
              <div class="control-group">
                <div class="controls">
                  <button type="submit" class="btn btn-primary"><%= botao %></button>
                  <button type="reset" class="btn btn-small">Cancelar</button>
                </div>
              </div>
            </form>
        </div>
    </div>
<jsp:include page="WEB-INF/templates/footer.jsp" />