package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ccz
 */
public class CaixaEletronico implements DatabaseActions {

    private String id;
    private String nota50, nota100, cheque, papelComprovante, dataDoCaixa;
    static HttpSession sessao;

    public CaixaEletronico() {
        id = "0";
        nota50 = "";
        nota100 = "";
        cheque = "";
        papelComprovante = "";
        dataDoCaixa = "";
    }

    public CaixaEletronico(HttpServletRequest request) {
        id = request.getParameter("id");
        nota50 = request.getParameter("nota50");
        nota100 = request.getParameter("nota100");
        cheque = request.getParameter("cheque");
        papelComprovante = request.getParameter("papelComprovante");
        dataDoCaixa = request.getParameter("dataDoCaixa");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNota50() {
        return nota50;
    }

    public void setNota50(String nota50) {
        this.nota50 = nota50;
    }

    public String getNota100() {
        return nota100;
    }

    public void setNota100(String nota100) {
        this.nota100 = nota100;
    }

    public String getCheque() {
        return cheque;
    }

    public void setCheque(String cheque) {
        this.cheque = cheque;
    }

    public String getPapelComprovante() {
        return papelComprovante;
    }

    public void setPapelComprovante(String papelComprovante) {
        this.papelComprovante = papelComprovante;
    }

    public String getDataDoCaixa() {
        return dataDoCaixa;
    }

    public void setDataDoCaixa(String dataDoCaixa) {
        this.dataDoCaixa = dataDoCaixa;
    }

    public static HttpSession getSessao() {
        return sessao;
    }

    public static void setSessao(HttpSession sessao) {
        CaixaEletronico.sessao = sessao;
    }

    @Override
    public boolean insert() {
        Connection conexao = null;
        PreparedStatement stmt;
        String query;
        try {
            conexao = Conexao.conectar();

            query = "INSERT INTO `BD_ES2`.`CaixaEletronico` (`nota50`, `nota100`, `cheque`, `papelComprovante`, `dataDoCaixa`) "
                    + "VALUES ('"
                    + nota50 + "', '"
                    + nota100 + "', '"
                    + cheque + "', '"
                    + papelComprovante + "', '"
                    + dataDoCaixa + "'"
                    + ");";
            stmt = conexao.prepareStatement(query);
            stmt.executeUpdate(query);

            conexao.close();
            return true;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            return false;
        }
    }

    @Override
    public boolean edit() {
        Connection conexao = null;
        PreparedStatement stmt;
        String query;
        try {
            conexao = Conexao.conectar();

            query = "UPDATE `BD_ES2`.`CaixaEletronico` "
                    + "SET "
                    + "`nota50` = '" + nota50 + "',"
                    + "`nota100` = '" + nota100 + "',"
                    + "`cheque` = '" + cheque + "',"
                    + "`papelComprovante` = '" + papelComprovante + "',"
                    + "`dataDoCaixa` = '" + dataDoCaixa
                    + "' WHERE `idCaixaEletronico` = " + id;
            stmt = conexao.prepareStatement(query);
            stmt.executeUpdate(query);

            conexao.close();
            return true;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            return false;
        }
    }

    @Override
    public boolean delete() {
        Connection conexao = null;
        PreparedStatement stmt;
        String query;
        try {
            conexao = Conexao.conectar();

            query = "DELETE FROM `BD_ES2`.`CaixaEletronico` "
                    + "WHERE `idCaixaEletronico` = " + id;
            stmt = conexao.prepareStatement(query);
            stmt.executeUpdate(query);

            conexao.close();
            return true;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            return false;
        }
    }

    @Override
    public boolean view(HttpServletRequest request) {
        Connection conexao = null;
        PreparedStatement stmt;
        String query;
        try {
            conexao = Conexao.conectar();

            query = "SELECT * FROM `BD_ES2`.`CaixaEletronico` WHERE `idCaixaEletronico` = " + id;
            stmt = conexao.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                CaixaEletronico caixaEletronico = new CaixaEletronico();
                caixaEletronico.setId(rs.getString("idCaixaEletronico"));
                caixaEletronico.setNota50(rs.getString("nota50"));
                caixaEletronico.setNota100(rs.getString("nota100"));
                caixaEletronico.setCheque(rs.getString("cheque"));
                caixaEletronico.setPapelComprovante(rs.getString("papelComprovante"));
                caixaEletronico.setDataDoCaixa(rs.getString("dataDoCaixa"));

                request.setAttribute("caixaEletronico", caixaEletronico);

                conexao.close();
                return true;
            } else {
                //TODO: Consulta vazia (registro j?? foi deletado)
                conexao.close();
                return false;
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            return false;
        }
    }

    @Override
    public boolean viewAll(HttpServletRequest request) {
        ArrayList<CaixaEletronico> todosCaixasEletronicos = CaixaEletronico.getAll();

        if (todosCaixasEletronicos != null) {
            request.setAttribute("todosCaixasEletronicos", todosCaixasEletronicos);
            return true;
        }
        return false;
    }

    public static ArrayList<CaixaEletronico> getAll() {
        Connection conexao = null;
        PreparedStatement stmt;
        String query;
        try {
            conexao = Conexao.conectar();

            query = "SELECT * FROM `BD_ES2`.`CaixaEletronico`";
            stmt = conexao.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<CaixaEletronico> todosCaixasEletronicos = new ArrayList();

            while (rs.next()) {
                CaixaEletronico caixaEletronico = new CaixaEletronico();
                caixaEletronico.setId(rs.getString("idCaixaEletronico"));
                caixaEletronico.setNota50(rs.getString("nota50"));
                caixaEletronico.setNota100(rs.getString("nota100"));
                caixaEletronico.setCheque(rs.getString("cheque"));
                caixaEletronico.setPapelComprovante(rs.getString("papelComprovante"));
                caixaEletronico.setDataDoCaixa(rs.getString("dataDoCaixa"));

                todosCaixasEletronicos.add(caixaEletronico);
            }

            conexao.close();
            return todosCaixasEletronicos;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            return null;
        }
    }

    public boolean login(HttpServletRequest request) {
        String idCliente = request.getParameter("numeroCartao"); // Por enquanto o numeroCartao ?? o ID do cliente, ent??o
        //  vamos consultar logo o idCliente
        String senha = request.getParameter("senha");
        String idCaixa = request.getParameter("id");

        Connection conexao = null;
        PreparedStatement stmt;
        String query;
        try {
            conexao = Conexao.conectar();

            query = "SELECT * FROM `BD_ES2`.`CaixaEletronico` "
                    + "WHERE idCaixaEletronico = " + idCaixa;

            stmt = conexao.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                setId(rs.getString("idCaixaEletronico"));
                setNota50(rs.getString("nota50"));
                setNota100(rs.getString("nota100"));
                setCheque(rs.getString("cheque"));
                setPapelComprovante(rs.getString("papelComprovante"));
                setDataDoCaixa(rs.getString("dataDoCaixa"));

                query = "SELECT * FROM `BD_ES2`.`Cliente_has_Conta` "
                        + "INNER JOIN Cliente on Cliente_idCliente = idCliente "
                        + "INNER JOIN Conta on Conta_idConta = idConta "
                        //+ "WHERE numeroCartao = '" + numeroCartao + "' AND senha = '" + senha + "'";
                        + "WHERE numeroCartao = '" + idCliente + "' AND senha = '" + senha + "'";

                stmt = conexao.prepareStatement(query);
                ResultSet rs2 = stmt.executeQuery(query);

                if (rs2.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(rs2.getString("idCliente"));
                    cliente.setNome(rs2.getString("nome"));
                    cliente.setStatus(rs2.getString("status"));

                    Conta conta = new Conta();
                    conta.setAgencia(rs2.getString("agencia"));
                    conta.setBanco(rs2.getString("banco"));
                    conta.setId(rs2.getString("idConta"));
                    conta.setIdCliente(rs2.getString("idCliente"));
                    conta.setLimite(rs2.getString("limite"));
                    conta.setNumeroCartao(rs2.getString("numeroCartao"));
                    conta.setPoupanca_status(rs2.getString("poupanca_status"));
                    conta.setSaldo(rs2.getString("saldo"));
                    conta.setSaldo_centavos(rs2.getString("saldo_centavos"));
                    conta.setStatus(rs2.getString("Conta.status"));
                    conta.setPoupanca(rs2.getString("poupanca_saldo"));
                    conta.setPoupanca_centavos(rs2.getString("poupanca_saldo_centavos"));
                    //conta.setListaClientes(null); // Ir?? preencher a lista de clientes da conta

                    if (!request.getSession(false).isNew()) {
                        CaixaEletronico.sessao = request.getSession(true);

                        CaixaEletronico.sessao.setAttribute("caixaEletronico", this);
                        CaixaEletronico.sessao.setAttribute("cliente", cliente);  
                        CaixaEletronico.sessao.setAttribute("conta", conta);

                        conta.calculaInvestimentos();                 
                        
                        return true;
                    }
                    conexao.close();

                }
            }
            return false;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            return false;
        }
    }

    public boolean printPage() {
        Connection conexao = null;
        PreparedStatement stmt;
        String query;
        try {
            conexao = Conexao.conectar();

            query = "UPDATE `BD_ES2`.`CaixaEletronico` "
                    + "SET papelComprovante = papelComprovante - 1 "
                    + "WHERE idCaixaEletronico = " + ((CaixaEletronico) CaixaEletronico.sessao.getAttribute("caixaEletronico")).getId();

            stmt = conexao.prepareStatement(query);
            if (stmt.executeUpdate(query) > 0) {

                // Atualizar valor da sess??o
                query = "SELECT papelComprovante FROM `BD_ES2`.`CaixaEletronico` "
                        + "WHERE idCaixaEletronico = " + ((CaixaEletronico) CaixaEletronico.sessao.getAttribute("caixaEletronico")).getId();

                stmt = conexao.prepareStatement(query);
                ResultSet rs = stmt.executeQuery(query);

                if (rs.next()) {
                    CaixaEletronico caixaEletronico = (CaixaEletronico) CaixaEletronico.sessao.getAttribute("caixaEletronico");
                    caixaEletronico.setPapelComprovante(rs.getString("papelComprovante"));
                    CaixaEletronico.sessao.setAttribute("caixaEletronico", caixaEletronico);

                    conexao.close();
                    return true;
                }
            }

            return false;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            return false;
        }
    }

    public static final String toDateNormalFormat(String oldDataString) {
        String data = "";
        if (oldDataString.length() > 9) {
            String ano = oldDataString.substring(0, 4);
            String mes = oldDataString.substring(5, 7);
            String dia = oldDataString.substring(8, 10);
            data = dia.concat("-").concat(mes).concat("-").concat(ano);
        }

        return data;
    }
}
