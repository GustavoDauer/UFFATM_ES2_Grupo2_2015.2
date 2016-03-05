<%-- 
    Document   : caixa_viewAll
    Created on : Feb 13, 2016, 11:13:37 PM
    Author     : Matheus Froes Batista
--%>

<%@page import="model.CaixaEletronico"%>
<%@page import="java.util.ArrayList"%>
<%@include file="include/header.jsp" %>                       
<h1>Lista de todos Caixas Eletrônicos</h1>
<table class="dadosTabelados" cellpadding="0" cellspacing="0">    
    <tr>
        <th>ID</th>
        <th>Notas 50</th>
        <th>Notas 100</th>
        <th>Cheque</th>
        <th>Comprovante</th>        
        <th>Data</th>
        <th>&nbsp;</th>
    </tr>
    <%
        ArrayList<CaixaEletronico> todosCaixasEletronicos = (ArrayList) request.getAttribute("todosCaixasEletronicos");
        for (CaixaEletronico caixa : todosCaixasEletronicos) {
    %>
    <tr>
        <td> 
            <%=caixa.getId()%>
        </td>
        <td>
            <%=caixa.getNota50()%>
        </td>
        <td>
            <%=caixa.getNota100()%>
        </td>
        <td>
            <%=caixa.getCheque()%>
        </td>
        <td>
            <%=caixa.getPapelComprovante()%>
        </td>
        <td>
            <%=CaixaEletronico.toDateNormalFormat(caixa.getDataDoCaixa())%>
        </td>
        <td>
            <input type="button" value="Editar" onclick="document.location = 'CaixaEletronicoController?command=view&id=<%=caixa.getId()%>'" />
            <input type="button" value="Remover" onclick="document.location = 'CaixaEletronicoController?command=delete&id=<%=caixa.getId()%>'" /><br /> <br />
            <%
                if (request.getAttribute("msgError") != null) {
                    out.println(request.getAttribute("msgError"));
                }
            %>  
        </td>
    </tr>
    <%
        }
    %>
</table>
<%@include file="include/footer.jsp" %>                