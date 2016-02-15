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
            Caixa Eletrônico <%=caixaEletronico.getIdCaixaEletronico()%> <br />
            Data de hoje: <%=caixaEletronico.getDataDoCaixa()%> <br />                 
            Quantidade de papel para impressão de comprovantes no caixa: <%=caixaEletronico.getPapelComprovante()%>
        </h2>
        <hr>
        <h1>Consulta poupança</h1>
        <p>
            Poupança: R$ <%=conta.getPoupanca()%>,<%=conta.getPoupanca_centavos()%>
        </p>
        <ul>
            <li><a href="login.jsp">Página inicial da conta</a></li>            
        </ul>                
        <hr>           
    </body>
</html>
