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
<h1>Depositar em conta</h1>
<div>
    <form action="ContaController" method="post">
        <input type="hidden" name="command" value="deposit" /> 
        <input type="hidden" name="idCliente" value="<%=cliente.getId()%>" /> 
        <input type="hidden" name="idConta" value="<%=conta.getId()%>" /> 
        <table class="formulario">
            <tr>
                <td>
                    <input type="text" name="valor" placeholder="VALOR"class="campo" onkeypress='return event.charCode >= 48 && event.charCode <= 57' maxlength="9" /> <br />
                    <input type="submit" value="Depositar" class="botao" /><br /><br />
                    <%
                        if (request.getAttribute("msgError") != null) {
                            out.println(request.getAttribute("msgError"));
                        }
                    %>    
                </td>
            </tr>
        </table>
    </form>
</div>                
<%@include file="include/footer.jsp" %>                       