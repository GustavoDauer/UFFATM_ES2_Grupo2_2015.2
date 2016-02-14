<%-- 
    Document   : conta_view
    Created on : 13/02/2016, 15:45:39
    Author     : gustavo
--%>
<%@page import="model.Conta"%>
<%@include file="include/header.jsp" %>     
<%
    Conta conta = (Conta) request.getAttribute("conta");
%>
<form action="<%=request.getContextPath()%>/ContaController?id=<%=conta.getId()%>" method="post">
    <input type="hidden" name="command" value="edit" />
    <h1>Editar Conta</h1>
    <table class="formulario" cellpadding="0" cellspacing="0">
        <tr>
            <td align="right">Agência: </td> <td><input class="campo" type="text" name="agencia" value="<%=conta.getAgencia()%>" /></td>
        </tr> 
        <tr>
            <td align="right">Banco </td> <td><select name="banco"><option selected="selected">Banco UFF</option></select></td>
        </tr>
        <tr>
            <td align="right">Poupança </td> <td><input type="checkbox" name="poupanca_status" <%if (conta.getPoupanca_status() != null && conta.getPoupanca_status().equals("1")) { %> checked="checked"<% } %> value="1"/> Ativada</td>
        </tr>
        <tr>
            <td align="right">Status da conta </td> <td><input type="checkbox" name="status" <%if (conta.getStatus() != null && conta.getStatus().equals("1")) { %> checked="checked"<% } %> value="1" /> Ativada</td>
        </tr>
        <tr>
            <td align="right">Limite </td> <td><input class="campo" type="text" name="limite" value="<%=conta.getLimite()%>" /></td>
        </tr>
        <tr>
            <td align="right">Saldo </td> <td><input class="campo" type="text" name="saldo" readonly="readonly" value="<%=conta.getSaldo()%>,<%=conta.getSaldo_centavos()%>"/></td>
        </tr>        
        <tr>
            <td></td>
            <td align="right"><input class="botao" type="submit" value="Editar" /></td>
        </tr>
    </table>
</form>
<%@include file="include/footer.jsp" %>   