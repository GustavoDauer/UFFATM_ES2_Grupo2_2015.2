
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.*;

import org.junit.*;
import static org.junit.Assert.assertEquals;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Igor
 */
public class PagamentoTest extends Mockito {

    Transacao transacao;
    Cliente cliente;
    Conta conta;
    CaixaEletronico caixaEletronico;
    static HttpServletRequest request;
    static HttpSession sessao;
    HttpServletResponse response;
    int id;

    Pagamento pagamento;

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        sessao = mock(HttpSession.class);
        when(request.getSession()).thenReturn(sessao);

        id = selecionaId();

        caixaEletronico = new CaixaEletronico();
        caixaEletronico.setCheque("0");
        caixaEletronico.setDataDoCaixa("2016-03-09 20:27:05");
        caixaEletronico.setId(String.valueOf(id + 1));
        caixaEletronico.setNota100("2");
        caixaEletronico.setNota50("2");
        caixaEletronico.setPapelComprovante("0");
        caixaEletronico.insert();

        cliente = new Cliente();
        cliente.setId(String.valueOf(id + 1));
        cliente.setNome("Teste");
        cliente.setStatus("1");
        cliente.insert();

        ArrayList<Cliente> listCliente = new ArrayList<>();
        listCliente.add(cliente);

        conta = new Conta();
        conta.setAgencia("0");
        conta.setBanco("0");
        conta.setId(String.valueOf(id + 1));
        conta.setIdCliente(String.valueOf(id + 1));
        conta.setLimite("1000");
        conta.setListaClientes(listCliente);
        conta.setNumeroCartao(String.valueOf(id + 1));
        conta.setStatus("1");
        conta.setPoupanca("0");
        conta.setPoupanca_centavos("0");
        conta.setPoupanca_status("1");
        conta.setSaldo("0");
        conta.setSaldo_centavos("0");
        conta.setSenha("123");
        conta.setStatus("1");
        conta.insert();

        when(sessao.getAttribute("caixaEletronico")).thenReturn(caixaEletronico);
        when(sessao.getAttribute("cliente")).thenReturn(cliente);
        when(sessao.getAttribute("conta")).thenReturn(conta);

        CaixaEletronico.setSessao(sessao);

        when(request.getParameter("data")).thenReturn(caixaEletronico.getDataDoCaixa());
        when(request.getParameter("idCliente")).thenReturn(cliente.getId());
        when(request.getParameter("idConta")).thenReturn(conta.getId());

    }

    @Test
    public void pagamentoSaldoSuficiente() {
        when(request.getParameter("valor")).thenReturn("1000");
        pagamento = new Pagamento(request);

        assertEquals(true, pagamento.insert());
    }

    @Test
    public void pagamentoSaldoInsuficiente() {
        when(request.getParameter("valor")).thenReturn("5000");
        pagamento = new Pagamento(request);

        assertEquals(false, pagamento.insert());
    }

    private int selecionaId() {
        String ultId = "0";
        Connection conexao = null;
        PreparedStatement stmt;
        String query;
        try {
            conexao = Conexao.conectar();

            query = "SELECT `Cliente`.`idCliente`,"
                    + "`Cliente`.`nome`,"
                    + "`Cliente`.`status` "
                    + "FROM `BD_ES2`.`Cliente` ";
            stmt = conexao.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {

                ultId = (rs.getString("idCliente"));

            }

            conexao.close();
            return Integer.parseInt(ultId);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }
}
