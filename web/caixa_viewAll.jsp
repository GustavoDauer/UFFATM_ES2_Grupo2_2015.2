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
    ArrayList<CaixaEletronico> todosCaixasEletronicos = (ArrayList) request.getAttribute("todosCaixasEletronicos");
    for (CaixaEletronico caixa : todosCaixasEletronicos) {
%>
<ul>
    <li> 
        ID: <%=caixa.getId()%> | 
        Nota 50: <%=caixa.getNota50()%> | 
        Nota 100: <%=caixa.getNota100()%> | 
        Cheque: <%=caixa.getCheque()%> | 
        Comprovante: <%=caixa.getPapelComprovante()%> | 
        Data: <%=CaixaEletronico.toDateNormalFormat(caixa.getDataDoCaixa())%>
        <input type="button" value="Editar" onclick="document.location = 'CaixaEletronicoController?command=view&id=<%=caixa.getId()%>'" />
        <input type="button" value="Remover" onclick="document.location = 'CaixaEletronicoController?command=delete&id=<%=caixa.getId()%>'" />
    </li> 
</ul>
<%
    }
%>
<%@include file="include/footer.jsp" %>                