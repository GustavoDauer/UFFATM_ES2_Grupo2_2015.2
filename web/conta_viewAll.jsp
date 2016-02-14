<%-- 
    Document   : cliente_view
    Created on : 13/02/2016, 15:45:39
    Author     : gustavo
--%>

<%@page import="model.Conta"%>
<%@page import="java.util.ArrayList"%>
<%@include file="include/header.jsp" %>                       
<h1>Lista de todas contas</h1>
<%
    ArrayList todasContas = (ArrayList) request.getAttribute("todasContas");
    for (Object contaObject : todasContas) {
        Conta conta = (Conta) contaObject;
%>
<ul>
    <li> 
        ID: <%=conta.getId()%> / Agência: <%=conta.getAgencia()%> / Banco: <%=conta.getBanco()%>
        <input type="button" value="Editar" onclick="document.location='ContaController?command=view&id=<%=conta.getId()%>'" />
        <input type="button" value="Remover" onclick="document.location='ContaController?command=delete&id=<%=conta.getId()%>'" />
    </li> 
</ul>
<%
    }
%>
<%@include file="include/footer.jsp" %>                