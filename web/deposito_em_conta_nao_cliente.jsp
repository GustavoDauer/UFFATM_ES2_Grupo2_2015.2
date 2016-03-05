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
  
    CaixaEletronico caixaEletronico = (CaixaEletronico) request.getSession().getAttribute("caixaEletronico");
%>    
<h1>Depositar em conta</h1>
<form action="ContaController" method="post">
    <table class="formulario">
        <tr>
            <td>
                <input type="hidden"  name="command" value="deposit" /> 
                Id Cliente <input type="text" name="idCliente" class="campo"/> <br>
                Conta Corrente <input type="text" name="idConta" class="campo" /><br> 
                R$ <input type="text" name="valor" class="campo" /> 
                <input type="submit" value="Depositar" />    
            </td>
        </tr>
    </table>
</form>                   
<%@include file="include/footer.jsp" %>                       