<jsp:include page="WEB-INF/templates/header.jsp" />
<div class="container">    
    <div class="row">          
        <div class="span9">
            <%-- Lista de not�cias... --%>
            <h2>�ltimas not�cias</h2>
        </div>
        
        <%-- Dados do usu�rio --%>
        <jsp:include page="WEB-INF/templates/${requestScope.userPanel}" />
    </div>    
</div>
<jsp:include page="WEB-INF/templates/footer.jsp" />