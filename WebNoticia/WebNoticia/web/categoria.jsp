<%@page import="java.util.List"%>
<%@page import="model.Usuario"%>
<%@page import="model.Categoria"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="model.CategoriaDAO"%>
<%@page import="util.Format"%>
<jsp:include page="WEB-INF/templates/header.jsp" />
<%
    CategoriaDAO dao = new CategoriaDAO((EntityManager)request.getAttribute("manager"));
    Usuario user = (Usuario)session.getAttribute("user");
    Categoria categoria = null;
    
    int cod = Format.getInt(request.getParameter("editar"));
    if (cod > 0) {
        categoria = dao.getById(cod);
        if ((categoria == null) || categoria.getUsuario() != user || !categoria.isAtivo()) {
            categoria = null;
        }
    }
    if (categoria == null) {
        categoria = new Categoria();
    }
    request.setAttribute("categoria", categoria);
    
    List<Categoria> listaCategoria = dao.getAllByUser(user);
    if (listaCategoria.size() == 0) {
        request.setAttribute("rodape", "Nenhuma categoria cadastrada");
    }
    else {
        request.setAttribute("rodape", listaCategoria.size() + " Categoria(s)");
    }
%>
<div class="container">    
    <div class="row">          
        <div class="span9">
            <%
            if (request.getAttribute("error") != null) {
                out.print("<span class='label label-warning'>" + request.getAttribute("error") + "</span>");
            }
            %>
            <%-- Categorias --%>
            <h2>Categoria</h2>
            <form action="CategoriaControler" method="post">
                <input type="hidden" name="acao" value="cadastro">
                <input type="hidden" name="codigo" value="${requestScope.categoria.codigo}">
                <div class="control-group">
                  <label class="control-label" for="inputNome">Nome</label>
                  <div class="controls">
                    <input type="text" name="nome" id="inputNome" placeholder="Nome da categoria" value="${requestScope.categoria.nome}">
                  </div>
                </div>
                <hr>
                <div class="control-group">
                  <div class="controls">
                    <button type="submit" class="btn btn-primary">Salvar</button>
                    <button type="reset" class="btn btn-small">Cancelar</button>
                  </div>
                </div>
            </form>
            <h2>Lista de Categorias</h2>
            <table class="table-condensed table-striped table-bordered">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Nome</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="categ" items="${requestScope.categorias}">
<%
            for (Categoria categ : listaCategoria) {
%>
                    <tr>
                        <td><%= categ.getCodigo() %></td>
                        <td><%= categ.getNome() %></td>
                        <td>
                            <a href="categoria.jsp?editar=<%= categ.getCodigo() %>" title="Editar"><img src="img/edit.png"></a>
                            <a href="javascript:excluir(<%= categ.getCodigo() %>)" title="Excluir"><img src="img/delete.png"></a>
                        </td>
                    </tr>
<%
            }
%>
            </tbody>
            <tfoot><tr><td colspan="3"><i>${requestScope.rodape}</i></td></tr></tfoot>                    
            </table>
        </div>
        <script>
            function excluir(codigo) {
                if (confirm('Deseja realmente excluir o registro?')) {
                    location.href = 'CategoriaControler?acao=excluir&codigo=' + codigo;
                }
            }
        </script>
        <%-- Dados do usuário --%>
        <jsp:include page="WEB-INF/templates/${requestScope.userPanel}" />
    </div>    
</div>
<jsp:include page="WEB-INF/templates/footer.jsp" />