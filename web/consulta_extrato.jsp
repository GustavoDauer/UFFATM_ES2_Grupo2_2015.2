<%-- 
    Document   : index
    Created on : 13/02/2016, 16:03:24
    Author     : gustavo
--%>

<%@page import="model.Transacao"%>
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

    ArrayList<Transacao> transacaoList = conta.getExtrato();
%>    
<h1>Consulta extrato</h1>
<table class="dadosTabelados" cellpadding="0" cellspacing="0">    
    <tr>
        <th>Data</th>
        <th>Transação</th>
        <th>Valor</th>  
        <th>Rendimento (1% ao mês)</th> 
    </tr>    
    <%
        for (Transacao transacao : transacaoList) {
    %>
    <tr>
        <td><%=CaixaEletronico.toDateNormalFormat(transacao.getData())%></td>
        <td><%=transacao.getTipo()%></td>
        <td>R$ <%=transacao.getValor()%>,<%=transacao.getValor_centavos()%></td>    
        <td><% 
        String rendimento = transacao.getRendimento(); 
        
        if(!(rendimento.equals("") || rendimento.isEmpty() || rendimento.equals("0"))) {
            %>
            R$ <%=transacao.getRendimento()%>,00
            <%
        }
        %></td>    
    </tr>
    <%
        }
    %>
</table>
<div>    
    <input type="button" value="Imprimir comprovante" onclick="document.location = 'CaixaEletronicoController?command=printPage'" />     
</div>                     
<%@include file="include/footer.jsp" %>                       