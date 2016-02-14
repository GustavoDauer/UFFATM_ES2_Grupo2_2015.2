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
            <td align="right">Id </td> <td><input class="campo" type="text" name="idCaixaEletronico" value="" /></td>
        </tr> 
        <tr>
            <td align="right">Nota 2 reais </td> <td><input class="campo" type="text" name="nota2" value="100" required="required" /></td>
        </tr>
        <tr>
            <td align="right">Nota 5 reais </td> <td><input class="campo" type="text" name="nota5" value="100" required="required" /></td>
        </tr>
        <tr>
            <td align="right">Nota 10 reais </td> <td><input class="campo" type="text" name="nota10" value="100" required="required" /></td>
        </tr>
        <tr>
            <td align="right">Nota 20 reais </td> <td><input class="campo" type="text" name="nota20" value="100" required="required" /></td>
        </tr>
        <tr>
            <td align="right">Nota 50 reais </td> <td><input class="campo" type="text" name="nota50" value="100" required="required" /></td>
        </tr>
        <tr>
            <td align="right">Nota 100 reais </td> <td><input class="campo" type="text" name="nota100" value="100" required="required" /></td>
        </tr>
        <tr>
            <td align="right">Cheque </td> <td><input class="campo" type="text" name="cheque" value="100" required="required" /></td>
        </tr>
        <tr>
            <td align="right">Papel de Comprovante </td> <td><input class="campo" type="text" name="papelComprovante" value="1000" required="required" /></td>
        </tr>
        <tr>
            <td align="right">Data </td> <td><input class="campo" type="date" name="Data" required="required" /></td>
        </tr>
        <tr>
            <td></td>
            <td align="right"><input class="botao" type="submit" value="Cadastrar" /></td>
        </tr>
    </table>
</form>
<%@include file="include/footer.jsp" %>