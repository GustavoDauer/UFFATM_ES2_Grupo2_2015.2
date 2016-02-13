<%-- 
    Document   : cliente_view
    Created on : 13/02/2016, 15:45:39
    Author     : gustavo
--%>

<%@include file="include/header.jsp" %>                       
<h1>Lista de todos clientes</h1>
<%=request.getAttribute("todosClientes")%>
<%@include file="include/footer.jsp" %>                