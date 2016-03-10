
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
public class ClienteTest extends Mockito {

    Transacao transacao;
    Cliente cliente;
    Conta conta;
    CaixaEletronico caixaEletronico;
    static HttpServletRequest request;
    static HttpSession sessao;
    HttpServletResponse response;
    int id;

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        sessao = mock(HttpSession.class);
        when(request.getSession()).thenReturn(sessao);

        cliente = new Cliente();
        cliente.setId(String.valueOf(id + 1));
        cliente.setNome("Teste");
        cliente.setStatus("1");
        cliente.insert();

        when(sessao.getAttribute("cliente")).thenReturn(cliente);

        CaixaEletronico.setSessao(sessao);

        when(request.getParameter("idCliente")).thenReturn(cliente.getId());

    }

    @Test
    public void ClienteInsert() {

        assertEquals(true, cliente.insert());
    }
}
