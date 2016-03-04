<%-- 
    Document   : transferencia
    Created on : 29/02/2016, 01:08:50
    Author     : Igor
--%>

<%@page import="model.Conta"%>
<%@page import="model.Cliente"%>
<%@page import="model.CaixaEletronico"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="include/header.jsp" %>                       
<%
    Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
    Conta conta = (Conta) request.getSession().getAttribute("conta");
    CaixaEletronico caixaEletronico = (CaixaEletronico) request.getSession().getAttribute("caixaEletronico");
%>    
<h1>Transferência</h1>
<form action="ContaController" method="post">
    <table class="formulario">
        <tr>
            <td>
                <input type="hidden" name="command" value="transferencia" /> 
                <input type="hidden" name="idConta" value="<%=conta.getId()%>" />
                <input type="hidden" name="idCliente" value="<%=cliente.getId()%>" />
                Conta de Destino: <input type="text" name="idContaTransferencia" class="campo" /> 
                R$ <input type="text" name="valor" class="campo" /> 
                <input type="submit" value="Transferir" />    
            </td>
        </tr>
    </table>
</form>        
<%@include file="include/footer.jsp" %>     
