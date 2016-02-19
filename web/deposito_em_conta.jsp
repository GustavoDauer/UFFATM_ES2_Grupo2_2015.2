<%-- 
    Document   : deposito_em_conta
    Created on : 17/02/2016, 16:03:24
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
<h1>Depositar em conta</h1>
<p>
<form action="ContaController" method="post">
    <input type="hidden" name="command" value="deposit" /> 
    <input type="hidden" name="id" value="<%=conta.getId()%>" /> 
    R$ <input type="text" name="valor" class="campo" /> 
    <input type="submit" value="Depositar" />    
</form>
</p>
<ul>
    <li><a href="login.jsp">Página inicial da conta</a></li>            
</ul>                       
<%@include file="include/footer.jsp" %>                       