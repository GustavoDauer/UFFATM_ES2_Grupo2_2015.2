<%-- 
    Document   : cliente_view
    Created on : 13/02/2016, 15:45:39
    Author     : gustavo
--%>
<%@include file="include/header.jsp" %>                           
<form action="<%=request.getContextPath()%>/ClienteController?id=<%=request.getParameter("id")%>" method="post">
    <input type="hidden" name="command" value="edit" />
    <h1>Editar Cliente</h1>
    <table class="formulario" cellpadding="0" cellspacing="0">
        <tr>
            <td align="right">Nome: </td> <td><input class="campo" type="text" name="nome" value="<%=request.getAttribute("nome")%>" required="required" /></td>
        </tr> 
        <tr>
            <td align="right">Status: </td> 
            <td>
                <input type="radio" name="status" <%if (request.getAttribute("status").equals("1")) { %> checked="checked"<% } %> value="1"/> Ativo 
                <input type="radio" name="status" <%if (request.getAttribute("status").equals("0")) { %> checked="checked"<% } %> value="0"/> Inativo
            </td>
        </tr>
        <tr>
            <td></td>
            <td align="right">
                <input class="botao" type="submit" value="Editar" />
            </td>
        </tr>
    </table>
</form>
<%@include file="include/footer.jsp" %>   