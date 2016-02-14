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
        <h1>Teste do Módulo Cliente</h1>
        <ul>
            <li><a href="cliente_insert.jsp">Cadastrar Cliente</a></li>
            <li><a href="ClienteController?command=viewAll">Listar Clientes</a></li>
        </ul>                
        <hr>

        <h1>Teste do Módulo Conta</h1>
        <ul>
            <li><a href="conta_insert.jsp">Cadastrar Conta</a></li>
            <li><a href="ContaController?command=viewAll">Listar Contas</a></li>
        </ul>                  
        <hr>

        <h1>Teste do Módulo Caixa Eletrônico</h1>
        <ul>
            <li><a href="caixa_insert.jsp">Cadastrar Caixa Eletrônico</a></li>
            <li><a href="CaixaEletronicoController?command=viewAll">Listar Caixas Eletrônicos</a></li>
        </ul>                  
        <hr>                
    </body>
</html>
