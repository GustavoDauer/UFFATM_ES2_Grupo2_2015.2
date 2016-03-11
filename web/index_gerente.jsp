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
    <input class="campo" type="text" name="nome" placeholder="NOME" maxlength="25" /><br />
    <input class="campo" type="password" name="senha" placeholder="SENHA" maxlength="7" /><br />
    <input class="botao" type="submit" value="Login" /><br />    
</form>
<%@include file="include/footer.jsp" %>   