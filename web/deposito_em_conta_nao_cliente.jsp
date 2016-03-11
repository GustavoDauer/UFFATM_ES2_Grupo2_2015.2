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
                <input type="hidden"  name="command" value="deposit_nao_cliente" /> 
                <input type="text" name="idCliente" placeholder="NÚMERO DO CLIENTE" class="campo" onkeypress='return event.charCode >= 48 && event.charCode <= 57' maxlength="10"/> <br />
                <input type="text" name="idConta" placeholder="NÚMERO DA CONTA" class="campo" onkeypress='return event.charCode >= 48 && event.charCode <= 57' maxlength="10" /><br /> 
                <input type="text" name="valor" placeholder="VALOR" class="campo" onkeypress='return event.charCode >= 48 && event.charCode <= 57' maxlength="9" /><br />
                <input type="submit" value="Depositar" class="botao" /> <br /><br />
                <%
                    if (request.getAttribute("msg") != null) {
                        out.println(request.getAttribute("msg"));
                    }
                %>  
            </td>
        </tr>
    </table>
</form>                   
<%@include file="include/footer.jsp" %>                       