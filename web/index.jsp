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
        <option value="0">Nenhum caixa eletrônico selecionado</option>
        <%
            for (CaixaEletronico caixaEletronico : listaCaixas) {
        %>
        <option value="<%=caixaEletronico.getId()%>">Caixa Eletrônico <%=caixaEletronico.getId()%></option>
        <%
            }
        %>
    </select><br />
    <input class="campo" type="text" name="numeroCartao" placeholder="NÚMERO DO CARTÃO" /><br />
    <input class="campo" type="password" name="senha" placeholder="SENHA" /><br />
    <input class="botao" type="submit" value="Inserir cartão" /><br />
    <a href="#">Não correntista</a> <a href="#">Gerente</a>
</form>
<%@include file="include/footer.jsp" %>   