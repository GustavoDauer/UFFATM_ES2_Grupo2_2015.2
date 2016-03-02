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
<h1>Saque</h1>
<form action="ContaController" method="post">    
    <table class="formulario">        
        <tr>
            <td>
                <input type="hidden" name="command" value="saque" /> 
                <input type="hidden" name="id" value="<%=conta.getId()%>" /> 
                R$ 
                <select name="valor">
                    <%
                        for (int i = 50; i <= 500; i += 50) {
                    %>
                    <option value="<%=i%>"><%=i%></option>
                    <%
                        }
                    %>
                </select>
                <input type="submit" value="Sacar" />    
            </td>
        </tr>
    </table>
</form>                   
<%@include file="include/footer.jsp" %>                       