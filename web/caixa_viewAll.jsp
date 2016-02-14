<%-- 
    Document   : caixa_viewAll
    Created on : Feb 13, 2016, 11:13:37 PM
    Author     : Matheus Froes Batista
--%>

<%@page import="model.CaixaEletronico"%>
<%@page import="java.util.ArrayList"%>
<%@include file="include/header.jsp" %>                       
<h1>Lista de todos Caixas Eletrônicos</h1>
<%
    ArrayList todosCaixasEletronicos = (ArrayList) request.getAttribute("todosCaixasEletronicos");
    for (Object caixaObject : todosCaixasEletronicos) {
        CaixaEletronico caixa = (CaixaEletronico) caixaObject;
%>
<ul>
    <li> 
        ID: <%=caixa.getIdCaixaEletronico()%> / Nota 2: <%=caixa.getNota2()%> / Nota 5: <%=caixa.getNota5()%> / Nota 10: <%=caixa.getNota10()%> / Nota 20: <%=caixa.getNota20()%> / Nota 50: <%=caixa.getNota50()%> / Nota 100: <%=caixa.getNota100()%> / Cheque: <%=caixa.getCheque()%> / Comprovante: <%=caixa.getPapelComprovante()%> / Data: <%=caixa.getDataDoCaixa()%> / >
        <input type="button" value="Editar" onclick="document.location='CaixaEletronicoController?command=view&id=<%=caixa.getIdCaixaEletronico()%>'" />
        <input type="button" value="Remover" onclick="document.location='CaixaEletronicoController?command=delete&id=<%=caixa.getIdCaixaEletronico()%>'" />
    </li> 
</ul>
<%
    }
%>
<%@include file="include/footer.jsp" %>                