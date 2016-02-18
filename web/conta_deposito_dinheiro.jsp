<%-- 
    Document   : conta_deposito_dinheiro
    Created on : 17/02/2016, 15:49:40
    Author     : augusto
--%>

<%@include file="include/header.jsp" %>                           
<form action="<%=request.getContextPath()%>/ContaController" method="post">
    <input type="hidden" name="command" value="deposit" />            
    <h1>Depósito</h1>
    <table class="formulario" cellpadding="0" cellspacing="0">
        <tr>
            <td align="right">Valor: </td> <td><input class="campo" type="text" name="valor" value="0,00" required="required" /></td>
        </tr>        
        <tr>
            <td></td>
            <td align="right"><input class="botao" type="submit" value="Depositar" /></td>
        </tr>
    </table>
</form>
<%@include file="include/footer.jsp" %>            