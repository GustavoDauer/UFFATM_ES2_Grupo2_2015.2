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
    ArrayList<Transacao> poupancaList = conta.getListaInvestimentos();
%>    
<h1>Resgate</h1>
<div>
    <%
        if (request.getAttribute("msgError") != null) {
            out.println(request.getAttribute("msgError"));
        }
    %>   

    <table class="dadosTabelados" cellpadding="0" cellspacing="0">    
        <tr>
            <th>Data</th>
            <th>Transação</th>
            <th>Valor</th>  
            <th>Rendimento (1% ao mês)</th> 
            <th>&nbsp;</th>
        </tr>    
        <%
            if (poupancaList != null) {
                for (Transacao transacao : poupancaList) {
        %>
        <tr>
            <td><%=CaixaEletronico.toDateNormalFormat(transacao.getData())%></td>
            <td><%=transacao.getTipo()%></td>
            <td>R$ <%=transacao.getValor()%>,<%=transacao.getValor_centavos()%></td>    
            <td><%
                String rendimento = transacao.getRendimento();

                if (!(rendimento.equals("") || rendimento.isEmpty() || rendimento.equals("0"))) {
                %>
                R$ <%=transacao.getRendimento()%>,00
                <%
                    }
                %></td>   
            <td>
                <form action="ContaController" method="post">
                    <input type="hidden" name="command" value="resgate" /> 
                    <input type="hidden" name="idCliente" value="<%=cliente.getId()%>" /> 
                    <input type="hidden" name="idConta" value="<%=conta.getId()%>" /> 
                    <input type="hidden" name="data" value="<%=transacao.getData()%>" /> 
                    <input type="hidden" name="valor" value="<%=transacao.getValor()%>" />
                    <input type="hidden" name="rendimento" value="<%=transacao.getRendimento()%>" />
                    <input type="submit" value="Resgatar da poupança" class="botao" />                    
                </form>

            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
</div>                
<%@include file="include/footer.jsp" %>                       