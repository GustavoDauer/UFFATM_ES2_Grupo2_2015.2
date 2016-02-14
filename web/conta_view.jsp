<%-- 
    Document   : conta_view
    Created on : 13/02/2016, 15:45:39
    Author     : gustavo
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Cliente"%>
<%@page import="model.Cliente"%>
<%@page import="model.Conta"%>
<%@include file="include/header.jsp" %>    
<%
    Conta conta = (Conta) request.getAttribute("conta");
    ArrayList<Cliente> listaClientes = conta.getListaClientes();
%>
<script type="text/javascript">
    globalIndex = 0;
    
    function checkForm(form)
    {        
        if (globalIndex > 0) {
            if (!(form.senha.value != "" && form.senha.value == form.senha2.value)) {
                alert('As senhas devem ser iguais e não devem estar em branco!');
                return false;
            }
        }

        return true;
    }

    function hideRows(className) {
        var elements = document.getElementsByClassName(className);
        for (var i = 0, length = elements.length; i < length; i++) {
            elements[i].style.visibility = 'hidden';
            elements[i].style.display = 'none';
        }

    }

    function showRows(className, index) {
        globalIndex = index;
        if (index > 0) {
            var elements = document.getElementsByClassName(className);
            for (var i = 0, length = elements.length; i < length; i++) {
                elements[i].style.visibility = 'visible';
                elements[i].style.display = 'table-cell';
            }
        }
        else {
            hideRows('param');
        }
    }
</script> 
<form action="<%=request.getContextPath()%>/ContaController?id=<%=conta.getId()%>" method="post" onsubmit="return checkForm(this)">
    <input type="hidden" name="command" value="edit" />
    <h1>Editar Conta</h1>
    <table class="formulario" id="formulario" cellpadding="0" cellspacing="0">
        <tr>
            <td align="right">Lista de clientes: </td>     
            <td>&nbsp;</td>
        </tr>        
        <%
            for (Cliente cliente : listaClientes) {
        %>
        <tr>
            <td align="right"><%=cliente.getNome()%></td> 
            <td>                
                <input class="botao" type="button" value="Remover" onclick="alert('TODO');" />
            </td>
        </tr> 
        <%
            }
        %>            
        <tr>
            <td colspan="2"/><hr /></td>
        </tr>
        <tr>          
            <td>&nbsp;</td>
            <td>
                <select name="idCliente" onchange="showRows('param', this.value)">
                    <option value="0">Associar novo cliente</option>
                    <%
                        ArrayList<Cliente> todosClientes = (ArrayList) Cliente.getAll();

                        for (int i = 0; i < todosClientes.size(); i++) {
                            for (int j = 0; j < listaClientes.size(); j++) {
                                if (todosClientes.get(i).getId().equals(listaClientes.get(j).getId())) {
                                    todosClientes.remove(i);
                                }
                            }
                        }

                        for (Cliente cliente : todosClientes) {
                    %>
                    <option value="<%=cliente.getId()%>" onclick="showRows('param')"><%=cliente.getNome()%></option>
                    <%
                        }
                    %>
                </select>
            </td>
        </tr>         
        <tr>
            <td class="param" align="right">Número cartão (novo cliente): </td> <td class="param"><input class="campo" type="text" name="numeroCartao" value="Gerado automaticamente" readonly="readonly" /></td>
        </tr> 
        <tr>
            <td class="param" align="right">Senha (novo cliente): </td> <td class="param"><input class="campo" type="password" name="senha" value="" /></td>
        </tr> 
        <tr>
            <td class="param" align="right">Repetir Senha (novo cliente): </td> <td class="param"><input class="campo" type="password" name="senha2" value="" /></td>
        </tr>
        <tr>
            <td colspan="2"/><hr /></td>
        </tr> 
        <tr>
            <td align="right">Agência: </td> <td><input class="campo" type="text" name="agencia" value="<%=conta.getAgencia()%>" /></td>
        </tr> 
        <tr>
            <td align="right">Banco </td> <td><select name="banco"><option selected="selected">Banco UFF</option></select></td>
        </tr>
        <tr>
            <td align="right">Poupança </td> <td><input type="checkbox" name="poupanca_status" <%if (conta.getPoupanca_status() != null && conta.getPoupanca_status().equals("1")) { %> checked="checked"<% } %> value="1"/> Ativada</td>
        </tr>
        <tr>
            <td align="right">Status da conta </td> <td><input type="checkbox" name="status" <%if (conta.getStatus() != null && conta.getStatus().equals("1")) { %> checked="checked"<% }%> value="1" /> Ativada</td>
        </tr>
        <tr>
            <td align="right">Limite </td> <td><input class="campo" type="text" name="limite" value="<%=conta.getLimite()%>" /></td>
        </tr>
        <tr>
            <td align="right">Saldo </td> <td><input class="campo" type="text" name="saldo" readonly="readonly" value="<%=conta.getSaldo()%>,<%=conta.getSaldo_centavos()%>"/></td>
        </tr>        
        <tr>
            <td></td>
            <td align="right"><input class="botao" type="submit" value="Editar" /></td>
        </tr>
    </table>

    <script type="text/javascript">
        hideRows('param');
    </script>      
</form>
<%@include file="include/footer.jsp" %>   