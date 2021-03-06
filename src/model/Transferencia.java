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
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ccz
 */
public class Transferencia extends Transacao implements DatabaseActions {

    public Transferencia(HttpServletRequest request) {
        super(request);
        this.tipo = tipoTransacao.TRANSFERENCIA;
    }

    public boolean insert() {
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
                    + "FROM `BD_ES2`.`Conta` "
                    + "WHERE idConta = " + idConta;

            stmt = conexao.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);

            Conta conta = new Conta();
            if (rs.next()) {
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

                CaixaEletronico.sessao.setAttribute("conta", conta);
            }

            if ((Integer.parseInt(valor) < Integer.parseInt(conta.saldo)) || ((Integer.parseInt(conta.saldo) - Integer.parseInt(valor)) >= Integer.parseInt(conta.limite))) {
                //Altera Conta Cliente
                query = "UPDATE `BD_ES2`.`Conta` "
                        + "SET "
                        + "`saldo` = `saldo` - " + valor
                        + " WHERE `idConta` = " + idConta;

                stmt = conexao.prepareStatement(query);
                stmt.executeUpdate(query);
                //Altera Conta Destino
                query = "SELECT `idConta` "
                        + "FROM `BD_ES2`.`Conta` "
                        + " WHERE `idConta` = " + idContaTransferencia;
                stmt = conexao.prepareStatement(query);
                ResultSet rs1 = stmt.executeQuery(query);
                if (rs1.next()) {
                    query = "UPDATE `BD_ES2`.`Conta` "
                            + "SET "
                            + "`saldo` = `saldo` + " + valor
                            + " WHERE `idConta` = " + idContaTransferencia;

                    stmt = conexao.prepareStatement(query);
                    stmt.executeUpdate(query);

                    conta.setSaldo(String.valueOf((Integer.parseInt(conta.saldo) - Integer.parseInt(valor))));
                    CaixaEletronico.sessao.setAttribute("conta", conta);

                    // Armazena transa????o            
                    query = "INSERT INTO `BD_ES2`.`Transacao` ("
                            + "`data`, "
                            + "`Cliente_idCliente`, "
                            + "`Conta_idConta`, "
                            + "`valor`, "
                            + "`valor_centavos`, "
                            + "`tipoTransacao`, "
                            + "`Transferencia_Conta_idConta`"
                            + ") "
                            + "VALUES ("
                            + "CURRENT_TIMESTAMP(), "
                            + this.idCliente + ", "
                            + this.idConta + ", "
                            + "-" + this.valor + ", "
                            + this.valor_centavos + ", "
                            + "'" + this.tipo + "', "
                            + "'" + this.idContaTransferencia + "'"
                            + ")";

                    stmt = conexao.prepareStatement(query);
                    stmt.executeUpdate(query);
                    /*
                    query = "SELECT `Cliente_idCliente` "
                            + "FROM `BD_ES2`.`cliente_has_conta` "
                            + " WHERE `Conta_idConta` = " + idContaTransferencia;
                    stmt = conexao.prepareStatement(query);
                    ResultSet rs2 = stmt.executeQuery(query);

                    rs2.next();

                    query = "INSERT INTO `BD_ES2`.`Transacao` ("
                            + "`data`, "
                            + "`Cliente_idCliente`, "
                            + "`Conta_idConta`, "
                            + "`valor`, "
                            + "`valor_centavos`, "
                            + "`tipoTransacao`, "
                            + "`Transferencia_Conta_idConta`"
                            + ") "
                            + "VALUES ("
                            + "CURRENT_TIMESTAMP(), "
                            + rs2.getString("Cliente_idCliente") + ", "
                            + this.idContaTransferencia + ", "
                            + "+" + this.valor + ", "
                            + this.valor_centavos + ", "
                            + "'" + this.tipo + "', "
                            + "'" + this.idConta + "'"
                            + ")";
                    stmt = conexao.prepareStatement(query);
                    stmt.executeUpdate(query);
                    */
                } else {
                    // Conta de outro banco                    
                    // Armazena transa????o            
                    query = "INSERT INTO `BD_ES2`.`Transacao` ("
                            + "`data`, "
                            + "`Cliente_idCliente`, "
                            + "`Conta_idConta`, "
                            + "`valor`, "
                            + "`valor_centavos`, "
                            + "`tipoTransacao`, "
                            + "`Transferencia_Conta_idConta`"
                            + ") "
                            + "VALUES ("
                            + "CURRENT_TIMESTAMP(), "
                            + this.idCliente + ", "
                            + this.idConta + ", "
                            + this.valor + ", "
                            + this.valor_centavos + ", "
                            + "'" + this.tipo + "', "
                            + "NULL"
                            + ")";
                    stmt = conexao.prepareStatement(query);
                    stmt.executeUpdate(query);
                }
            } else {
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean view(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean viewAll(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
