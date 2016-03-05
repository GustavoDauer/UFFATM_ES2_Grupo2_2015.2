<%-- 
    Document   : header
    Created on : 13/02/2016, 15:51:39
    Author     : gustavo
--%>
<%@page import="model.CaixaEletronico"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/design.css" />
        <title>Banco UFF - Sistema para Caixa Eletrônico versão 1.0</title>
    </head>
    <body> 
        <table cellspacing="0" cellpadding="0" width="100%">
            <tr>
                <td id="menu">
                    <!-- MENU -->

                    <ul>
                        <!-- Home -->                
                        <li><a href="index.jsp">Página inicial</a></li>                                                       
                    </ul>                   
                              
                    <ul>
                        <!-- Usuário -->    
                        <li><a href="login.jsp">Página inicial da conta</a></li>                                    
                        <li><a href="deposito_em_conta_nao_cliente.jsp">Depósito</a></li>                            
                        <li><a href="pagamento_autenticado.jsp">Pagamentos</a></li>                           
                    </ul>
                   
                 
               
                <td id="conteudo">
                    <!-- CONTEUDO -->



