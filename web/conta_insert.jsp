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

        if (form.idCliente.value == "0" || form.idCliente.value == "") {
            alert('Você deve selecionar um cliente!');
            return false;
        }

        return true;
    }

    function numeroCartao()
    {
        document.getElementById("numeroCartao").value = document.getElementById("idCliente").value;
    }
</script>  
<form action="<%=request.getContextPath()%>/ContaController" method="post" onsubmit="return checkForm(this)">
    <input type="hidden" name="command" value="insert" />            
    <h1>Cadastrar Conta</h1>
    <table class="formulario" cellpadding="0" cellspacing="0">
        <tr>            
            <td>
                <select id="idCliente" name="idCliente" onclick="this.options[this.selectedIndex].onclick()">
                    <option value="">Selecione um cliente</option>
                    <%
                        ArrayList<Cliente> todosClientes = (ArrayList) Cliente.getAll();

                        for (Cliente cliente : todosClientes) {
                    %>
                    <option value="<%=cliente.getId()%>" onclick="javascript:numeroCartao();"><%=cliente.getNome()%></option>
                    <%
                        }
                    %>
                </select>
            </td>
        </tr> 
        <tr>
            <td><input id="numeroCartao" placeholder="NÚMERO CARTÃO" class="campo" type="text" name="numeroCartao" readonly="readonly" maxlength="10" />
                <br /><sub>(Gerado automaticamente)</sub></td>
        </tr>     
        <tr>
            <td><select name="banco"><option selected="selected">Banco UFF</option></select></td>
        </tr>
        <tr>
            <td><input class="campo" placeholder="AGÊNCIA" type="text" name="agencia" maxlength="5" /></td>
        </tr>                 
        <tr>
            <td><input class="campo" placeholder="LIMITE" type="text" name="limite" onkeypress='return event.charCode >= 48 && event.charCode <= 57' maxlength="9" /></td>
        </tr>    
        <tr>
            <td><input type="checkbox" name="poupanca_status" checked="checked" value="1"/> Poupança</td>
        </tr>
        <tr>
            <td><input type="checkbox" name="status" checked="checked" value="1" /> Conta ativa</td>
        </tr>
        <tr>
            <td><input class="campo" placeholder="SENHA" type="password" name="senha" value="" maxlength="7" /></td>
        </tr> 
        <tr>
            <td><input placeholder="REPETIR SENHA" class="campo" type="password" name="senha2" value="" maxlength="7" /></td>
        </tr>
        <tr>            
            <td><input class="botao" type="submit" value="Cadastrar" /></td>
        </tr>
    </table>
</form>     
<%@include file="include/footer.jsp" %>            