<%-- 
    Document   : index
    Created on : 13/02/2016, 16:03:24
    Author     : gustavo
--%>

<%@page import="model.Gerente"%>
<%@page import="model.Conta"%>
<%@page import="model.Cliente"%>
<%@page import="model.CaixaEletronico"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="include/header.jsp" %>                       
<%
    Gerente gerente = (Gerente) request.getSession().getAttribute("gerente");
    Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
    Conta conta = (Conta) request.getSession().getAttribute("conta");
    CaixaEletronico caixaEletronico = (CaixaEletronico) request.getSession().getAttribute("caixaEletronico");
%>    
<% if (cliente != null) {%>
<h1>Caixa Eletrônico <%=caixaEletronico.getId()%></h1>
<h2>   
    Bem vindo <%=cliente.getNome()%> (ID: <%=cliente.getId()%>)<br />
    Conta: <%=conta.getId()%> - Agência <%=conta.getAgencia()%> (<%=conta.getBanco()%>)<br /><br />
    Data de hoje: <%=CaixaEletronico.toDateNormalFormat(caixaEletronico.getDataDoCaixa())%> <br />                 
    Quantidade de papel para impressão de comprovantes no caixa: <%=caixaEletronico.getPapelComprovante()%>
</h2>
<% } else if (gerente != null) {%>
<h1>Gerente</h1>
<h2>Bem vindo <%=gerente.getNome()%></h2><br />
<% }%>
<br /><br />
<%@include file="include/footer.jsp" %>                       