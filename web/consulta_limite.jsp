<%-- 
    Document   : index
    Created on : 13/02/2016, 16:03:24
    Author     : gustavo
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
<h1>Consulta limite</h1>
<div>
    Cheque especial
    <br /><br />R$ <%=Integer.parseInt(conta.getLimite())*-1%>,00 <br /><br />
    <input type="button" value="Imprimir comprovante" onclick="document.location = 'CaixaEletronicoController?command=printPage'" />                     
</div>

<%@include file="include/footer.jsp" %>                       