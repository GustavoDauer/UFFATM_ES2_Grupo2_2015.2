<%-- 
    Document   : cliente_view
    Created on : 13/02/2016, 15:45:39
    Author     : gustavo
--%>

<%@page import="model.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@include file="include/header.jsp" %>                       
<h1>Lista de todos clientes</h1>
<table class="dadosTabelados" cellpadding="0" cellspacing="0">    
    <tr>
        <th>Nome</th>
        <th>ID</th>        
        <th>&nbsp;</th>
    </tr>
    <%
        ArrayList<Cliente> todosClientes = (ArrayList) request.getAttribute("todosClientes");
        for (Cliente cliente : todosClientes) {
    %>   
    <tr>
        <td> 
            <%=cliente.getNome()%>
        </td>
        <td>
            <%=cliente.getId()%>
        </td>        
        <td>
            <input type="button" value="Editar" onclick="document.location = 'ClienteController?command=view&id=<%=cliente.getId()%>'" />
            <input type="button" value="Remover" onclick="document.location = 'ClienteController?command=delete&id=<%=cliente.getId()%>'" />
        </td>
    </tr>
    <%
        }
    %>
</table>
<%@include file="include/footer.jsp" %>                