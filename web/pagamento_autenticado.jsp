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
<h1>Pagamento</h1>
<div>
    <form action="ContaController" method="post">
        <input type="hidden" name="command" value="pagamento" /> 
        <input type="hidden" name="id" value="<%=conta.getId()%>" /> 
        <table>
            <tr>
                <td  align="right">Número do documento</td>
                <td><input type="text" name="numeroDocumento" class="campo" value="28786926527" /></td>
            </tr>
            <tr>
                <td align="right">R$ </td>
                <td><input type="text" name="valor" placeholder="Valor" class="campo" />  <input type="submit" value="Pagar" />   </td>
            </tr>
        </table> 
    </form>
</div>
<ul>
    <li><a href="login.jsp">Página inicial da conta</a></li>            
</ul>                       
<%@include file="include/footer.jsp" %>                       