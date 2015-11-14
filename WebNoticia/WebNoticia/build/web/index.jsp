<jsp:include page="WEB-INF/templates/header.jsp" />
<div class="container">    
    <div class="row">          
        <div class="span9">
            <%-- Lista de notícias... --%>
            <h2>Últimas notícias</h2>
        </div>
        
        <%-- Dados do usuário --%>
        <jsp:include page="WEB-INF/templates/${requestScope.userPanel}" />
    </div>    
</div>
<jsp:include page="WEB-INF/templates/footer.jsp" />