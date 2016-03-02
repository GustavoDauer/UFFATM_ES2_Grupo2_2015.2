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
        
        document.getElementById("numeroCartao").value = document.getElementById("idCliente").value;
    }
</script> 
<form action="<%=request.getContextPath()%>/ContaController?id=<%=conta.getId()%>" method="post" onsubmit="return checkForm(this)">
    <input type="hidden" name="command" value="edit" />
    <h1>Editar Conta</h1>
    <table class="formulario" id="formulario" cellpadding="0" cellspacing="0">
        <tr>
            <td valign="top">
                <table class="formulario" id="formulario" cellpadding="0" cellspacing="0">        
                    <tr>                      
                        <td>
                            <select id="idCliente" name="idCliente" onchange="showRows('param', this.value);">
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
                                        <option value="<%=cliente.getId()%>" onclick="showRows('param');"><%=cliente.getNome()%></option>
                                <%
                                    }
                                %>
                            </select>
                        </td>
                        <td>&nbsp;</td>
                    </tr>         
                    <tr>
                        <td class="param"><input id="numeroCartao" class="campo" type="text" name="numeroCartao" readonly="readonly" />
                            <br /><sub>(Número Cartão)</sub></td>
                        <td class="param" align="left"> (novo cliente)</td> 
                    </tr> 
                    <tr>
                        <td class="param"><input class="campo" placeholder="SENHA" type="password" name="senha" value="" /></td>
                        <td class="param" align="left"> (novo cliente)</td> 
                    </tr> 
                    <tr>
                        <td class="param"><input class="campo" placeholder="REPETIR SENHA" type="password" name="senha2" value="" /></td>
                        <td class="param" align="left"> (novo cliente)</td> 
                    </tr>
                    <tr>
                        <td colspan="2"/><hr /></td>
                    </tr> 
                    <tr>
                        <td><input class="campo" type="text" name="agencia" value="<%=conta.getAgencia()%>" /></td>
                        <td align="left"><sub>(Agência)</sub></td>
                    </tr> 
                    <tr>
                        <td><select name="banco"><option selected="selected">Banco UFF</option></select></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" name="poupanca_status" <%if (conta.getPoupanca_status() != null && conta.getPoupanca_status().equals("1")) { %> checked="checked"<% } %> value="1"/> Poupança Ativada</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" name="status" <%if (conta.getStatus() != null && conta.getStatus().equals("1")) { %> checked="checked"<% }%> value="1" />Conta Ativada</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td><input class="campo" type="text" name="limite" value="<%=conta.getLimite()%>" /></td>
                        <td align="left"><sub>(Limite)</sub></td>
                    </tr>
                    <tr>
                        <td><input class="campo" type="text" name="saldo" readonly="readonly" value="<%=conta.getSaldo()%>,<%=conta.getSaldo_centavos()%>"/></td>
                        <td align="left"><sub>(Saldo)</sub></td>
                    </tr>        
                    <tr>            
                        <td align="right"><input class="botao" type="submit" value="Editar" /></td>
                        <td>&nbsp;</td>
                    </tr>
                </table>
            </td>
            <td align="center" valign="top"><b>Clientes associados</b><br /><br />
                <%
                    for (Cliente cliente : listaClientes) {
                %>
                <%=cliente.getNome()%> <input class="botao" type="button" value="Remover" onclick="alert('TODO');" />                   
                <%
                    }
                %>   
            </td>
        </tr>  
    </table>    

    <script type="text/javascript">
        hideRows('param');
    </script>      
</form>
<%@include file="include/footer.jsp" %>   