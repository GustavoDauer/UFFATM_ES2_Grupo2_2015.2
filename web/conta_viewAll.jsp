<%-- 
    Document   : cliente_view
    Created on : 13/02/2016, 15:45:39
    Author     : gustavo
--%>

<%@page import="model.Conta"%>
<%@page import="java.util.ArrayList"%>
<%@include file="include/header.jsp" %>                       
<h1>Lista de todas contas</h1>
<table class="dadosTabelados" cellpadding="0" cellspacing="0">    
    <tr>
        <th>ID</th>
        <th>Agência</th>    
        <th>Banco</th>
        <th>&nbsp;</th>
    </tr>
    <%
        ArrayList<Conta> todasContas = (ArrayList) request.getAttribute("todasContas");
        for (Conta conta : todasContas) {
    %>                
    <tr>
        <td> 
            <%=conta.getId()%>
        </td>
        <td>
            <%=conta.getAgencia()%>
        </td>  
        <td>
            <%=conta.getBanco()%>
        </td>
        <td>
            <input type="button" value="Editar" onclick="document.location = 'ContaController?command=view&id=<%=conta.getId()%>'" />
            <input type="button" value="Remover" onclick="document.location = 'ContaController?command=delete&id=<%=conta.getId()%>'" /> <br /> <br />
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