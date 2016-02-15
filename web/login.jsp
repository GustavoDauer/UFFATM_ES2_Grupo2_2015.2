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
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
        Conta conta = (Conta) request.getSession().getAttribute("conta");
        CaixaEletronico caixaEletronico = (CaixaEletronico) request.getSession().getAttribute("caixaEletronico");
    %>
    <body>
        <h1>Logado com sucesso!</h1>
        <h2>         
            Bem vindo <%=cliente.getNome()%> (<%=cliente.getId()%>) 
            logado pela conta <%=conta.getId()%> - Agência <%=conta.getAgencia()%> (<%=conta.getBanco()%>)
            <br /><br />
            Caixa Eletrônico <%=caixaEletronico.getId()%> <br />
            Data de hoje: <%=caixaEletronico.getDataDoCaixa()%> <br />                 
            Quantidade de papel para impressão de comprovantes no caixa: <%=caixaEletronico.getPapelComprovante()%>
        </h2>
        
        <hr>
        <h1>Consultas</h1>
        <ul>
            <li><a href="consulta_saldo.jsp">Consulta saldo</a></li>   
            <li><a href="consulta_poupanca.jsp">Consulta poupança</a></li>            
            <li><a href="consulta_limite.jsp">Consulta limite</a></li>            
        </ul>  
        
        <hr>
        <h1>Depósitos</h1>
        <ul>
            <li><a href="fazer_deposito.jsp">Fazer depósito</a></li>
            <li><a href="historico_transacoes.jsp">Histórico de transações</a></li>            
        </ul>  
        
        <hr>
        <h1>Saques</h1>
        <ul>
            <li><a href="fazer_saque.jsp">Fazer saque</a></li>  
            <li><a href="historico_transacoes.jsp">Histórico de transações</a></li>            
        </ul> 
        
        <hr>
        <h1>Pagamentos</h1>
        <ul>
            <li><a href="fazer_pagamento.jsp">Fazer pagamento</a></li>  
            <li><a href="historico_transacoes.jsp">Histórico de transações</a></li>            
        </ul> 
    </body>
</html>
