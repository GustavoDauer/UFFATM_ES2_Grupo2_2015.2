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
            <td><input class="campo" type="text" name="id" value="<%=caixaEletronico.getId()%>" required="required" readonly/></td>
            <td>&nbsp;</td>
        </tr>         
        <tr>
            <td><input class="campo" type="text" name="nota50" value="<%=caixaEletronico.getNota50()%>" required="required" onkeypress='return event.charCode >= 48 && event.charCode <= 57' /></td>
            <td><sub>(Notas de 50 reais)</sub></td>
        </tr>
        <tr>
            <td><input class="campo" type="text" name="nota100" value="<%=caixaEletronico.getNota100()%>" required="required" onkeypress='return event.charCode >= 48 && event.charCode <= 57' /></td>
            <td><sub>(Notas de 100 reais)</sub></td>
        </tr>
        <tr>
            <td><input class="campo" type="text" name="cheque" value="<%=caixaEletronico.getCheque()%>" required="required" onkeypress='return event.charCode >= 48 && event.charCode <= 57' /></td>
            <td><sub>(Cheque)</sub></td>
        </tr>
        <tr>
            <td><input class="campo" type="text" name="papelComprovante" value="<%=caixaEletronico.getPapelComprovante()%>" required="required" onkeypress='return event.charCode >= 48 && event.charCode <= 57' /></td>
            <td><sub>(Papel comprovante)</sub></td>
        </tr>
        <tr>
            <td><input class="campo" type="date" name="dataDoCaixa" value="<%=caixaEletronico.getDataDoCaixa()%>" required="required"/></td>
            <td><sub>(Data do Caixa)</sub></td>
        </tr>
        <tr>            
            <td align="right">
                <input class="botao" type="submit" value="Editar" />
            </td>
            <td>&nbsp;</td>
        </tr>
    </table>
</form>
<%@include file="include/footer.jsp" %>   