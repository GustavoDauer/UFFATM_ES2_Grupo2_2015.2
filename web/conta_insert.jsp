<%-- 
    Document   : cliente_insert
    Created on : 13/02/2016, 15:49:40
    Author     : gustavo
--%>

<%@include file="include/header.jsp" %>                           
<form action="<%=request.getContextPath()%>/ContaController" method="post">
    <input type="hidden" name="command" value="insert" />            
    <h1>Cadastrar Conta</h1>
    <table class="formulario" cellpadding="0" cellspacing="0">
        <tr>
            <td align="right">Agência: </td> <td><input class="campo" type="text" name="agencia" value="0125-0" /></td>
        </tr> 
        <tr>
            <td align="right">Banco </td> <td><select name="banco"><option selected="selected">Banco UFF</option></select></td>
        </tr>
        <tr>
            <td align="right">Poupança </td> <td><input type="checkbox" name="poupanca_status" checked="checked" value="1"/> Ativada</td>
        </tr>
        <tr>
            <td align="right">Status da conta </td> <td><input type="checkbox" name="status" checked="checked" value="1" /> Ativada</td>
        </tr>
        <tr>
            <td align="right">Limite </td> <td><input class="campo" type="text" name="limite" value="-1000" /></td>
        </tr>
        <tr>
            <td align="right">Saldo </td> <td><input class="campo" type="text" name="saldo" disabled="disabled" /></td>
        </tr>        
        <tr>
            <td></td>
            <td align="right"><input class="botao" type="submit" value="Cadastrar" /></td>
        </tr>
    </table>
</form>
<%@include file="include/footer.jsp" %>            