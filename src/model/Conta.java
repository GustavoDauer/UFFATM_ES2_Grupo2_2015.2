/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.MONTHS;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ccz
 */
public class Conta implements DatabaseActions {

    String id, saldo, saldo_centavos, limite, agencia, banco, status, poupanca_status, poupanca, poupanca_centavos;
    String idCliente, numeroCartao, senha;
    ArrayList<Cliente> listaClientes;

    public Conta() {
        listaClientes = new ArrayList();
    }

    public Conta(HttpServletRequest request) {
        id = request.getParameter("id");
        saldo = request.getParameter("saldo");
        saldo_centavos = request.getParameter("saldo_centavos");
        limite = request.getParameter("limite");
        agencia = request.getParameter("agencia");
        banco = request.getParameter("banco");
        status = request.getParameter("status");
        poupanca_status = request.getParameter("poupanca_status");
        poupanca = request.getParameter("poupanca");
        poupanca_centavos = request.getParameter("poupanca_centavos");

        idCliente = request.getParameter("idCliente");
        numeroCartao = request.getParameter("numeroCartao");
        senha = request.getParameter("senha");
        listaClientes = new ArrayList();
    }

    public Conta(Conta conta) {
        id = conta.id;
        saldo = conta.saldo;
        saldo_centavos = conta.saldo_centavos;
        limite = conta.limite;
        agencia = conta.agencia;
        banco = conta.banco;
        status = conta.status;
        poupanca_status = conta.poupanca_status;
        poupanca = conta.poupanca;
        poupanca_centavos = conta.poupanca_centavos;

        idCliente = conta.idCliente;
        numeroCartao = conta.numeroCartao;
        senha = conta.senha;
        listaClientes = conta.listaClientes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getSaldo_centavos() {
        return saldo_centavos;
    }

    public void setSaldo_centavos(String saldo_centavos) {
        this.saldo_centavos = saldo_centavos;
    }

    public String getLimite() {
        return limite;
    }

    public void setLimite(String limite) {
        this.limite = limite;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPoupanca_status() {
        return poupanca_status;
    }

    public void setPoupanca_status(String poupanca_status) {
        this.poupanca_status = poupanca_status;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        if (listaClientes != null && !listaClientes.isEmpty()) {
            this.listaClientes = listaClientes;
        } else if (!this.id.equals("0") && !this.id.isEmpty() && this.id != null) {
            this.listaClientes = new ArrayList();

            Connection conexao = null;
            PreparedStatement stmt;
            String query;
            try {
                conexao = Conexao.conectar();

                query = "SELECT `Conta`.`idConta`,"
                        + "    `Conta`.`saldo`,"
                        + "    `Conta`.`saldo_centavos`,"
                        + "    `Conta`.`limite`,"
                        + "    `Conta`.`agencia`,"
                        + "    `Conta`.`banco`,"
                        + "    `Conta`.`status`,"
                        + "    `Conta`.`poupanca_status`,"
                        + "    `Conta`.`poupanca_saldo`,"
                        + "    `Conta`.`poupanca_saldo_centavos`"
                        + " FROM `BD_ES2`.`Conta` "
                        + "WHERE `idConta` = " + id;
                stmt = conexao.prepareStatement(query);
                ResultSet rs = stmt.executeQuery(query);

                if (rs.next()) {
                    query = "SELECT Cliente_idCliente, Conta_idConta, numeroCartao, senha, "
                            + "nome, Cliente.status "
                            + "FROM Cliente_has_Conta "
                            + "INNER JOIN Cliente on idCliente = Cliente_idCliente "
                            + "WHERE Conta_idConta = " + id;

                    stmt = conexao.prepareStatement(query);
                    ResultSet rs2 = stmt.executeQuery(query);

                    while (rs2.next()) {
                        Cliente cliente = new Cliente();
                        cliente.setId(rs2.getString("Cliente_idCliente"));
                        cliente.setNome(rs2.getString("nome"));
                        cliente.setNome(rs2.getString("Cliente.status"));

                        listaClientes.add(cliente);
                    }
                }

                conexao.close();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            }
        }
    }

    public String getPoupanca() {
        return poupanca;
    }

    public void setPoupanca(String poupanca) {
        this.poupanca = poupanca;
    }

    public String getPoupanca_centavos() {
        return poupanca_centavos;
    }

    public void setPoupanca_centavos(String poupanca_centavos) {
        this.poupanca_centavos = poupanca_centavos;
    }

    public void geraNumero() {

    }

    public void geraCartao() {

    }

    @Override
    public boolean insert() {
        Connection conexao = null;
        PreparedStatement stmt;
        String query;
        try {
            conexao = Conexao.conectar();

            query = "INSERT INTO `BD_ES2`.`Conta` "
                    + "("
                    + "`limite`,"
                    + "`agencia`,"
                    + "`banco`,"
                    + "`status`,"
                    + "`poupanca_status`) "
                    + "VALUES"
                    + "("
                    + "-" + limite + "," // ATEN??AO: LIMITE E MULTIPLICADO POR -1 PARA SER CADASTRADO NEGATIVO!
                    + "'" + agencia + "',"
                    + "'" + banco + "',"
                    + status + ","
                    + poupanca_status + ");";

            stmt = conexao.prepareStatement(query); // Conta
            stmt.executeUpdate(query);

            query = "SELECT MAX(idConta) FROM Conta;";

            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                id = rs.getString("MAX(idConta)");
                numeroCartao = idCliente; // Pra facilitar, o numero do cartao sera o ID do cliente por enquanto.                

                query = "INSERT INTO `BD_ES2`.`Cliente_has_Conta` "
                        + "(`Cliente_idCliente`,"
                        + "`Conta_idConta`,"
                        + "`numeroCartao`,"
                        + "`senha`)"
                        + "VALUES"
                        + "("
                        + idCliente + "," // ID do cliente prim??rio
                        + id + ",'"
                        + numeroCartao + "','"
                        + senha + "')";

                stmt = conexao.prepareStatement(query); // Cliente_has_Conta
                stmt.executeUpdate(query);
            } else {
                conexao.close();
                return false;
            }

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

            query = "UPDATE `BD_ES2`.`Conta` "
                    + "SET "
                    + "`limite` = -" + limite + ","
                    + "`agencia` = '" + agencia + "',"
                    + "`banco` = '" + banco + "',"
                    + "`status` = " + status + ","
                    + "`poupanca_status` = " + poupanca_status
                    + " WHERE `idConta` = " + id;

            stmt = conexao.prepareStatement(query);
            stmt.executeUpdate(query);

            if (!idCliente.equals("0")) {
                numeroCartao = idCliente; // Pra facilitar, o numero do cartao sera o ID do cliente por enquanto.  

                query = "INSERT INTO `BD_ES2`.`Cliente_has_Conta` "
                        + "(`Cliente_idCliente`,"
                        + "`Conta_idConta`,"
                        + "`numeroCartao`,"
                        + "`senha`)"
                        + "VALUES"
                        + "("
                        + idCliente + "," // ID do cliente prim??rio
                        + id + ",'"
                        + numeroCartao + "','"
                        + senha + "')";

                stmt = conexao.prepareStatement(query); // Cliente_has_Conta
                stmt.executeUpdate(query);
            }

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

            query = "DELETE FROM `BD_ES2`.`Conta` "
                    + "WHERE `idConta` = " + id;
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

            query = "SELECT `Conta`.`idConta`,"
                    + "    `Conta`.`saldo`,"
                    + "    `Conta`.`saldo_centavos`,"
                    + "    `Conta`.`limite`,"
                    + "    `Conta`.`agencia`,"
                    + "    `Conta`.`banco`,"
                    + "    `Conta`.`status`,"
                    + "    `Conta`.`poupanca_status`,"
                    + "    `Conta`.`poupanca_saldo`,"
                    + "    `Conta`.`poupanca_saldo_centavos`"
                    + " FROM `BD_ES2`.`Conta` "
                    + "WHERE `idConta` = " + id;
            stmt = conexao.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                Conta conta = new Conta();
                conta.setId(rs.getString("idConta"));
                conta.setAgencia(rs.getString("agencia"));
                conta.setBanco(rs.getString("banco"));
                conta.setLimite(rs.getString("limite"));
                conta.setPoupanca_status(rs.getString("poupanca_status"));
                conta.setSaldo(rs.getString("saldo"));
                conta.setSaldo_centavos(rs.getString("saldo_centavos"));
                conta.setStatus(rs.getString("status"));
                conta.setPoupanca(rs.getString("poupanca_saldo"));
                conta.setPoupanca_centavos(rs.getString("poupanca_saldo_centavos"));

                query = "SELECT Cliente_idCliente, Conta_idConta, numeroCartao, senha, "
                        + "nome "
                        + "FROM Cliente_has_Conta "
                        + "INNER JOIN Cliente on idCliente = Cliente_idCliente "
                        + "WHERE Conta_idConta = " + id;

                stmt = conexao.prepareStatement(query);
                ResultSet rs2 = stmt.executeQuery(query);

                while (rs2.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(rs2.getString("Cliente_idCliente"));
                    cliente.setNome(rs2.getString("nome"));

                    conta.listaClientes.add(cliente);
                }

                request.setAttribute("conta", conta);

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
        Connection conexao = null;
        PreparedStatement stmt;
        String query;
        try {
            conexao = Conexao.conectar();

            query = "SELECT `Conta`.`idConta`,"
                    + "    `Conta`.`saldo`,"
                    + "    `Conta`.`saldo_centavos`,"
                    + "    `Conta`.`limite`,"
                    + "    `Conta`.`agencia`,"
                    + "    `Conta`.`banco`,"
                    + "    `Conta`.`status`,"
                    + "    `Conta`.`poupanca_status`,"
                    + "    `Conta`.`poupanca_saldo`,"
                    + "    `Conta`.`poupanca_saldo_centavos`"
                    + "FROM `BD_ES2`.`Conta`";
            stmt = conexao.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<Conta> todasContas = new ArrayList();

            while (rs.next()) {
                Conta conta = new Conta();
                conta.setId(rs.getString("idConta"));
                conta.setAgencia(rs.getString("agencia"));
                conta.setBanco(rs.getString("banco"));
                conta.setLimite(rs.getString("limite"));
                conta.setPoupanca_status(rs.getString("poupanca_status"));
                conta.setSaldo(rs.getString("saldo"));
                conta.setSaldo_centavos(rs.getString("saldo_centavos"));
                conta.setStatus(rs.getString("status"));
                conta.setPoupanca(rs.getString("poupanca_saldo"));
                conta.setPoupanca_centavos(rs.getString("poupanca_saldo_centavos"));

                todasContas.add(conta);
            }

            request.setAttribute("todasContas", todasContas);

            conexao.close();
            return true;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            return false;
        }
    }

    public boolean deposit(HttpServletRequest request) {
        Deposito deposito = new Deposito(request);
        return deposito.insert();
    }

    public boolean saque(HttpServletRequest request) {
        Saque saque = new Saque(request);
        return saque.insert();
    }

    public boolean pagamento(HttpServletRequest request) {
        Pagamento pagamento = new Pagamento(request);
        return pagamento.insert();
    }

    public boolean transferencia(HttpServletRequest request) {
        Transferencia transferencia = new Transferencia(request);
        return transferencia.insert();
    }

    public ArrayList<Transacao> getExtrato() {
        ArrayList<Transacao> transacaoList = new ArrayList();

        Connection conexao = null;
        PreparedStatement stmt;
        String query;
        try {
            conexao = Conexao.conectar();

            query = "SELECT `Transacao`.`data`,"
                    + "    `Transacao`.`Cliente_idCliente`,"
                    + "    `Transacao`.`Conta_idConta`,"
                    + "    `Transacao`.`valor`,"
                    + "    `Transacao`.`valor_centavos`,"
                    + "    `Transacao`.`tipoTransacao`,"
                    + "    `Transacao`.`Transferencia_Conta_idConta`, "
                    + "    `Transacao`.`rendimento` "
                    + "FROM `BD_ES2`.`Transacao` "
                    + "WHERE Conta_idConta = " + id + " OR Transferencia_Conta_idConta = " + id;

            stmt = conexao.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Transacao transacao = new Transacao();
                transacao.setData(rs.getString("data"));
                transacao.setIdCliente(rs.getString("Cliente_idCliente"));
                transacao.setIdConta(rs.getString("Conta_idConta"));
                transacao.setIdContaTransferencia(rs.getString("Transferencia_Conta_idConta"));
                transacao.setTipo(Transacao.tipoTransacao.valueOf(rs.getString("tipoTransacao")));
                if (rs.getString("Transferencia_Conta_idConta") != null && rs.getString("Transferencia_Conta_idConta").equals(id)) {
                    transacao.setValor(String.valueOf(Integer.parseInt(rs.getString("valor")) * (-1)));
                } else {
                    transacao.setValor(rs.getString("valor"));
                }
                transacao.setValor_centavos(rs.getString("valor_centavos"));
                transacao.setRendimento(rs.getString("rendimento"));
                transacaoList.add(transacao);
            }

            conexao.close();
            return transacaoList;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            return transacaoList;
        }
    }

    public void calculaInvestimentos() {
        ArrayList<Transacao> transacaoList = getExtrato();
        CaixaEletronico caixaEletronico = (CaixaEletronico) CaixaEletronico.getSessao().getAttribute("caixaEletronico");
        Conta conta = (Conta) CaixaEletronico.getSessao().getAttribute("conta");
        String dataCaixa = caixaEletronico.getDataDoCaixa();

        Connection conexao = null;
        PreparedStatement stmt;
        String query;

        try {
            conexao = Conexao.conectar();
            query = "SELECT valor"
                    + " FROM Transacao "
                    + " WHERE Cliente_idCliente = " + conta.getIdCliente()
                    + " AND Conta_idConta = " + conta.getId()
                    + " AND tipoTransacao = '" + Transacao.tipoTransacao.POUPANCA + "'";

            stmt = conexao.prepareStatement(query);
            ResultSet rs2 = stmt.executeQuery(query);

            int totalAplicacao = 0;

            while (rs2.next()) {
                int aplicacao = rs2.getInt("valor");
                totalAplicacao += aplicacao;
            }

            query = "UPDATE Conta "
                    + " SET "
                    + " poupanca_saldo = " + totalAplicacao
                    + " WHERE idConta = " + conta.getId();

            stmt = conexao.prepareStatement(query);
            stmt.executeUpdate(query);

            conta.setPoupanca(Integer.toString(totalAplicacao));
            CaixaEletronico.getSessao().setAttribute("conta", conta);

            conexao.close();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
        }

        for (Transacao transacao : transacaoList) {
            if (transacao.tipo == Transacao.tipoTransacao.POUPANCA) {
                calculaRendimento(transacao, dataCaixa);

                try {
                    conexao = Conexao.conectar();

                    query = "SELECT rendimento "
                            + " FROM Transacao "
                            + " WHERE Cliente_idCliente = " + transacao.getIdCliente()
                            + " AND Conta_idConta = " + transacao.getIdConta()
                            + " AND tipoTransacao = '" + transacao.getTipo() + "'";

                    stmt = conexao.prepareStatement(query);
                    ResultSet rs = stmt.executeQuery(query);

                    int totalRendimentos = 0;

                    while (rs.next()) {
                        int rendimento = rs.getInt("rendimento");
                        totalRendimentos += rendimento;
                    }

                    query = "UPDATE Conta "
                            + " SET "
                            + " poupanca_saldo = poupanca_saldo + " + totalRendimentos
                            + " WHERE idConta = " + transacao.getIdConta();

                    stmt = conexao.prepareStatement(query);
                    stmt.executeUpdate(query);

                    int poupancaBase = Integer.parseInt(conta.getPoupanca());
                    poupancaBase += totalRendimentos;
                    conta.setPoupanca(Integer.toString(poupancaBase));
                    CaixaEletronico.getSessao().setAttribute("conta", conta);

                    conexao.close();
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                }
            }
        }
    }

    public boolean calculaRendimento(Transacao transacao, String strDataCaixa) {
        String strDataTransacao = transacao.getData();
        String[] splitDataTransacao = strDataTransacao.split("-");
        String[] splitDayTransacao = splitDataTransacao[2].split(" ");

        int year = Integer.parseInt(splitDataTransacao[0]);
        int month = Integer.parseInt(splitDataTransacao[1]);
        int day = Integer.parseInt(splitDayTransacao[0]);

        LocalDate dataTransacao = LocalDate.of(year, month, day);

        String[] splitDataCaixa = strDataCaixa.split("-");
        String[] splitDayCaixa = splitDataCaixa[2].split(" ");

        year = Integer.parseInt(splitDataCaixa[0]);
        month = Integer.parseInt(splitDataCaixa[1]);
        day = Integer.parseInt(splitDayCaixa[0]);

        LocalDate dataCaixa = LocalDate.of(year, month, day);

        long months = MONTHS.between(dataTransacao, dataCaixa);

        for (long i = 0; i < months; i++) {
            Double valor = Double.parseDouble(transacao.getValor());
            Double rendimento = valor * 0.01;
            valor += rendimento;

            int intValor = valor.intValue();
            String strValor = Integer.toString(intValor); // Ignora centavos do rendimento

            transacao.setValor(strValor);
        }

        Connection conexao = null;
        PreparedStatement stmt;
        String query;
        try {
            conexao = Conexao.conectar();

            query = "UPDATE Transacao "
                    + " SET "
                    + " rendimento = " + transacao.getValor() + " - valor"
                    + " WHERE data = '" + transacao.getData() + "' "
                    + " AND Cliente_idCliente = " + transacao.getIdCliente()
                    + " AND Conta_idConta = " + transacao.getIdConta();

            stmt = conexao.prepareStatement(query);
            stmt.executeUpdate(query);

            conexao.close();
            return true;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            return false;
        }
    }

    public boolean investimento(HttpServletRequest request) {
        Investimento investimento = new Investimento(request);
        return investimento.insert();
    }

    public ArrayList<Transacao> getListaInvestimentos() {
        ArrayList<Transacao> listaPoupanca = new ArrayList();

        Connection conexao = null;
        PreparedStatement stmt;
        String query;
        try {
            conexao = Conexao.conectar();

            query = "SELECT * "
                    + " FROM Transacao "
                    + " WHERE Conta_idConta = " + id
                    + " AND tipoTransacao = '" + Transacao.tipoTransacao.POUPANCA + "'";
            stmt = conexao.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Transacao transacao = new Transacao();
                transacao.setData(rs.getString("data"));
                transacao.setIdCliente(rs.getString("Cliente_idCliente"));
                transacao.setIdConta(rs.getString("Conta_idConta"));
                transacao.setIdContaTransferencia(rs.getString("Transferencia_Conta_idConta"));
                transacao.setRendimento(rs.getString("rendimento"));
                transacao.setTipo(Transacao.tipoTransacao.DEPOSITO);
                transacao.setValor(rs.getString("valor"));
                transacao.setValor_centavos(rs.getString("valor_centavos"));
                listaPoupanca.add(transacao);
            }

            conexao.close();
            return listaPoupanca;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            return null;
        }
    }

    public boolean resgate(HttpServletRequest request) {
        Resgate resgate = new Resgate(request);
        return resgate.edit();
    }

    public boolean cheque(HttpServletRequest request) {
        String qtdCheque = request.getParameter("valor");
        Connection conexao = null;
        PreparedStatement stmt;
        String query;
        try {
            conexao = Conexao.conectar();

            CaixaEletronico atm = (CaixaEletronico) CaixaEletronico.sessao.getAttribute("caixaEletronico");

            if ((Integer.parseInt(atm.getCheque()) - Integer.parseInt(qtdCheque)) >= 0) {

                query = "UPDATE `BD_ES2`.`CaixaEletronico` "
                        + "SET "
                        + "`cheque` = " + String.valueOf((Integer.parseInt(atm.getCheque()) - Integer.parseInt(qtdCheque)))
                        + " WHERE `idCaixaEletronico` = " + atm.getId();

                stmt = conexao.prepareStatement(query);
                stmt.executeUpdate(query);

                atm.setCheque(String.valueOf((Integer.parseInt(atm.getCheque()) - Integer.parseInt(qtdCheque))));

                CaixaEletronico.sessao.setAttribute("caixaEletronico", atm);

            } else {
                return false;
            }

            conexao.close();
            return true;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            return false;
        }
    }
}
