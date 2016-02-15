<%-- 
    Document   : index
    Created on : 13/02/2016, 16:03:24
    Author     : gustavo
--%>

<%@page import="model.CaixaEletronico"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <h1>Teste do Módulo Cliente</h1>
        <ul>
            <li><a href="cliente_insert.jsp">Cadastrar Cliente</a></li>
            <li><a href="ClienteController?command=viewAll">Listar Clientes</a></li>
        </ul>                
        <hr>

        <h1>Teste do Módulo Conta</h1>
        <ul>
            <li><a href="conta_insert.jsp">Cadastrar Conta</a></li>
            <li><a href="ContaController?command=viewAll">Listar Contas</a></li>
        </ul>                  
        <hr>

        <h1>Teste do Módulo Caixa Eletrônico</h1>
        <ul>
            <li><a href="caixa_insert.jsp">Cadastrar Caixa Eletrônico</a></li>
            <li><a href="CaixaEletronicoController?command=viewAll">Listar Caixas Eletrônicos</a></li>
        </ul>                  
        <hr>

        <h1>Teste do Módulo Login - TODO</h1>
        <form action="LoginController" method="POST">
            <input type="text" name="numeroCartao" value="716726747" />
            <input type="password" name="senha" value="716726747" />
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
            </select>
            <input type="submit" value="Inserir cartão (Login)" />
            <input type="submit" value="Não correntista" disabled="disabled" />
        </form>
        <hr>
    </body>
</html>
