<%-- 
    Document   : index
    Created on : 13/02/2016, 16:03:24
    Author     : gustavo
--%>

<%@page import="model.CaixaEletronico"%>
<%@page import="java.util.ArrayList"%>
<%@include file="include/header.jsp" %>   
<h1>Banco UFF</h1>
<form id="login" action="LoginController" method="post">    
    <%
        ArrayList<CaixaEletronico> listaCaixas = CaixaEletronico.getAll();
    %>
    <select name="id">
        <option value="0">Nenhum caixa eletr�nico selecionado</option>
        <%
            for (CaixaEletronico caixaEletronico : listaCaixas) {
        %>
        <option value="<%=caixaEletronico.getId()%>">Caixa Eletr�nico <%=caixaEletronico.getId()%></option>
        <%
            }
        %>
    </select><br />
    <input class="campo" type="text" name="numeroCartao" placeholder="N�MERO DO CART�O" onkeypress='return event.charCode >= 48 && event.charCode <= 57' /><br />
    <input class="campo" type="password" name="senha" placeholder="SENHA" /><br />
    <input class="botao" type="submit" value="Inserir cart�o" /><br />
    <a href="index_nao_correntista.jsp">N�o correntista</a> <a href="index_gerente.jsp">Gerente</a>
    <br /><br />
    <%        
        if (request.getAttribute("msgError") != null) {
            out.println(request.getAttribute("msgError"));
        }
    %>
</form>
<%@include file="include/footer.jsp" %>   