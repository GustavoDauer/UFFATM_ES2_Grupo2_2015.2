<%-- 
    Document   : cliente_view
    Created on : 13/02/2016, 15:45:39
    Author     : gustavo
--%>

<%@page import="model.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@include file="include/header.jsp" %>                       
<h1>Lista de todos clientes</h1>
<%
    ArrayList<Cliente> todosClientes = (ArrayList) request.getAttribute("todosClientes");
    for (Cliente cliente : todosClientes) {        
%>
<ul>
    <li> 
        Nome: <%=cliente.getNome()%> 
        <input type="button" value="Editar" onclick="document.location='ClienteController?command=view&id=<%=cliente.getId()%>'" />
        <input type="button" value="Remover" onclick="document.location='ClienteController?command=delete&id=<%=cliente.getId()%>'" />
    </li> 
</ul>
<%
    }
%>
<%@include file="include/footer.jsp" %>                