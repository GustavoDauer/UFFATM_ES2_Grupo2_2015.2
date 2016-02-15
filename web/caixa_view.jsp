<%-- 
    Document   : caixa_view
    Created on : Feb 13, 2016, 11:13:24 PM
    Author     : Matheus Froes Batista
--%>
<%@page import="model.CaixaEletronico"%>
<%@include file="include/header.jsp" %>     
<%
    CaixaEletronico caixaEletronico = (CaixaEletronico) request.getAttribute("caixaEletronico");
%>
<form action="<%=request.getContextPath()%>/CaixaEletronicoController?id=<%=caixaEletronico.getId()%>" method="post">
    <input type="hidden" name="command" value="edit" />
    <h1>Editar Caixa Eletrônico</h1>
    <table class="formulario" cellpadding="0" cellspacing="0">
        <tr>
            <td align="right">Id: </td> <td><input class="campo" type="text" name="id" value="<%=caixaEletronico.getId()%>" required="required" readonly/></td>
        </tr> 
        <tr>
            <td align="right">Nota 2: </td> <td><input class="campo" type="text" name="nota2" value="<%=caixaEletronico.getNota2()%>" required="required" /></td>
        </tr>
        <tr>
            <td align="right">Nota 5: </td> <td><input class="campo" type="text" name="nota5" value="<%=caixaEletronico.getNota5()%>" required="required" /></td>
        </tr>
        <tr>
            <td align="right">Nota 10: </td> <td><input class="campo" type="text" name="nota10" value="<%=caixaEletronico.getNota10()%>" required="required" /></td>
        </tr>
        <tr>
            <td align="right">Nota 20: </td> <td><input class="campo" type="text" name="nota20" value="<%=caixaEletronico.getNota20()%>" required="required" /></td>
        </tr>
        <tr>
            <td align="right">Nota 50: </td> <td><input class="campo" type="text" name="nota50" value="<%=caixaEletronico.getNota50()%>" required="required" /></td>
        </tr>
        <tr>
            <td align="right">Nota 100: </td> <td><input class="campo" type="text" name="nota100" value="<%=caixaEletronico.getNota100()%>" required="required" /></td>
        </tr>
        <tr>
            <td align="right">Cheque: </td> <td><input class="campo" type="text" name="cheque" value="<%=caixaEletronico.getCheque()%>" required="required" /></td>
        </tr>
        <tr>
            <td align="right">Papel Comprovante: </td> <td><input class="campo" type="text" name="papelComprovante" value="<%=caixaEletronico.getPapelComprovante()%>" required="required" /></td>
        </tr>
        <tr>
            <td align="right">Data: </td> <td><input class="campo" type="text" name="dataDoCaixa" value="<%=caixaEletronico.getDataDoCaixa()%>" required="required" readonly/></td>
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