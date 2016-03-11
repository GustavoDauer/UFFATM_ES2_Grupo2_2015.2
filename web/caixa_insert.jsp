<%-- 
    Document   : caixa_insert
    Created on : Feb 13, 2016, 11:12:36 PM
    Author     : Matheus Froes Batista
--%>
<%@include file="include/header.jsp" %>                           
<form action="<%=request.getContextPath()%>/CaixaEletronicoController" method="post">
    <input type="hidden" name="command" value="insert" />            
    <h1>Cadastrar Caixa Eletrônico</h1>
    <table class="formulario" cellpadding="0" cellspacing="0">              
        <tr>
            <td><input class="campo" type="text" name="nota50" value="100" required="required" onkeypress='return event.charCode >= 48 && event.charCode <= 57' /></td>
            <td><sub>(Notas de 50 reais)</sub></td>
        </tr>
        <tr>
            <td><input class="campo" type="text" name="nota100" value="100" required="required" onkeypress='return event.charCode >= 48 && event.charCode <= 57' /></td>
            <td><sub>(Notas de 100 reais)</sub></td>
        </tr>
        <tr>
            <td><input class="campo" type="text" name="cheque" value="100" required="required" onkeypress='return event.charCode >= 48 && event.charCode <= 57' /></td>
            <td><sub>(Cheque)</sub></td>
        </tr>
        <tr>
            <td><input class="campo" type="text" name="papelComprovante" value="1000" required="required" onkeypress='return event.charCode >= 48 && event.charCode <= 57' /></td>
            <td><sub>(Papel comprovante)</sub></td>
        </tr>
        <tr>
            <td><input class="campo" type="date" name="dataDoCaixa" required="required" /></td>
            <td><sub>(Data do Caixa)</sub></td>
        </tr>
        <tr>            
            <td align="right"><input class="botao" type="submit" value="Cadastrar" /></td>
            <td>&nbsp;</td>
        </tr>
    </table>
</form>
<%@include file="include/footer.jsp" %>