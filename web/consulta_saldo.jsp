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
<h1>Consulta saldo</h1>
<div>
    Saldo: R$ <%=conta.getSaldo()%>,<%=conta.getSaldo_centavos()%> 
    <input type="button" value="Imprimir comprovante" onclick="document.location = 'CaixaEletronicoController?command=printPage'" />
    <ul>
        <li><a href="login.jsp">Página inicial da conta</a></li>            
    </ul>  
</div>                     
<%@include file="include/footer.jsp" %>                       