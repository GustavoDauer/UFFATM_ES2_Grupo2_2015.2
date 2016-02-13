<%-- 
    Document   : cliente_insert
    Created on : 13/02/2016, 15:49:40
    Author     : gustavo
--%>

<%@include file="include/header.jsp" %>                           
<form action="<%=request.getContextPath()%>/ClienteController" method="post">
    <input type="hidden" name="command" value="insert" />            
    <h1>Cadastrar Cliente</h1>
    <table class="formulario" cellpadding="0" cellspacing="0">
        <tr>
            <td align="right">Nome: </td> <td><input class="campo" type="text" name="nome" value="Anônimo" required="required" /></td>
        </tr>        
        <tr>
            <td></td>
            <td align="right"><input class="botao" type="submit" value="Cadastrar" /></td>
        </tr>
    </table>
</form>
<%@include file="include/footer.jsp" %>            