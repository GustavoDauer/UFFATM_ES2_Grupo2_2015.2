<%-- 
    Document   : cliente_insert
    Created on : 13/02/2016, 15:49:40
    Author     : gustavo
--%>

<%@page import="model.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@include file="include/header.jsp" %>   
<script type="text/javascript">
    function checkForm(form)
    {
        if (!(form.senha.value != "" && form.senha.value == form.senha2.value)) {   
            alert('As senhas devem ser iguais e não devem estar em branco!');
            return false;
        }
        
        if(form.idCliente.value == "0") {
            alert('Você deve selecionar um cliente!');
            return false;
        }
        
        return true;
    }
</script>
<form action="<%=request.getContextPath()%>/ContaController" method="post" onsubmit="return checkForm(this)">
    <input type="hidden" name="command" value="insert" />            
    <h1>Cadastrar Conta</h1>
    <table class="formulario" cellpadding="0" cellspacing="0">
        <tr>
            <td align="right">Cliente </td> 
            <td>
                <select name="idCliente">
                    <option value="0">Selecione um cliente</option>
                    <%
                        ArrayList<Cliente> todosClientes = (ArrayList) Cliente.getAll();

                        for (Cliente cliente : todosClientes) {
                    %>
                    <option value="<%=cliente.getId()%>"><%=cliente.getNome()%></option>
                    <%
                        }
                    %>
                </select>
            </td>
        </tr> 
        <tr>
            <td align="right">Número cartão: </td> <td><input class="campo" type="text" name="numeroCartao" value="Gerado automaticamente" readonly="readonly" /></td>
        </tr> 
        <tr>
            <td align="right">Senha: </td> <td><input class="campo" type="password" name="senha" value="" /></td>
        </tr> 
        <tr>
            <td align="right">Repetir Senha: </td> <td><input class="campo" type="password" name="senha2" value="" /></td>
        </tr>
        <tr>
            <td align="right">Agência: </td> <td><input class="campo" type="text" name="agencia" value="0125-0" /></td>
        </tr> 
        <tr>
            <td align="right">Banco </td> <td><select name="banco"><option selected="selected">Banco UFF</option></select></td>
        </tr>
        <tr>
            <td align="right">Poupança </td> <td><input type="checkbox" name="poupanca_status" checked="checked" value="1"/> Ativada</td>
        </tr>
        <tr>
            <td align="right">Status da conta </td> <td><input type="checkbox" name="status" checked="checked" value="1" /> Ativada</td>
        </tr>
        <tr>
            <td align="right">Limite </td> <td><input class="campo" type="text" name="limite" value="-1000" /></td>
        </tr>
        <tr>
            <td align="right">Saldo </td> <td><input class="campo" type="text" name="saldo" disabled="disabled" /></td>
        </tr>        
        <tr>
            <td></td>
            <td align="right"><input class="botao" type="submit" value="Cadastrar" /></td>
        </tr>
    </table>
</form>
<%@include file="include/footer.jsp" %>            