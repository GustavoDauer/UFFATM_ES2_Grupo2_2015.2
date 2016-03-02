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
<h1>Consulta poupança</h1>
<div>
    Poupança
    <br /><br />R$ <%=conta.getPoupanca()%>,<%if(conta.getPoupanca_centavos().length() == 1) { %>0<%=conta.getPoupanca_centavos()%><% } else { %><%=conta.getPoupanca_centavos()%><% } %><br /><br />
    <input type="button" value="Imprimir comprovante" onclick="document.location = 'CaixaEletronicoController?command=printPage'" />    
</div>            
<%@include file="include/footer.jsp" %>                       