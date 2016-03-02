<%-- 
    Document   : cliente_view
    Created on : 13/02/2016, 15:45:39
    Author     : gustavo
--%>
<%@page import="model.Cliente"%>
<%@include file="include/header.jsp" %>     
<%
    Cliente cliente = (Cliente) request.getAttribute("cliente");
%>
<form action="<%=request.getContextPath()%>/ClienteController?id=<%=cliente.getId()%>" method="post">
    <input type="hidden" name="command" value="edit" />
    <h1>Editar Cliente</h1>
    <table class="formulario" cellpadding="0" cellspacing="0">
        <tr>
            <td><input class="campo" type="text" name="nome" value="<%=cliente.getNome()%>" required="required" /></td>
        </tr> 
        <tr>            
            <td>
                <input type="radio" name="status" <%if (cliente.getStatus().equals("1")) { %> checked="checked"<% } %> value="1"/> Ativo 
                <input type="radio" name="status" <%if (cliente.getStatus().equals("0")) { %> checked="checked"<% }%> value="0"/> Inativo
            </td>
        </tr>
        <tr>            
            <td align="right">
                <input class="botao" type="submit" value="Editar" />
            </td>
        </tr>
    </table>
</form>
<%@include file="include/footer.jsp" %>   