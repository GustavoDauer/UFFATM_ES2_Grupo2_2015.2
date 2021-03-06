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
public class Saque extends Transacao implements DatabaseActions {

    public Saque(HttpServletRequest request) {
        super(request);
        this.tipo = tipoTransacao.SAQUE;
    }

    @Override
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
            }
            CaixaEletronico atm = (CaixaEletronico) CaixaEletronico.sessao.getAttribute("caixaEletronico");
            int iValor = Integer.parseInt(valor);
            int iNota100 = Integer.parseInt(atm.getNota100());
            int iNota50 = Integer.parseInt(atm.getNota50());
            boolean pagPossivel = true;

            while (iValor > 0 && (iNota100 > 0 || iNota50 > 0)) {
                if ((iNota100 - 1 >= 0) && (iValor - 100 >= 0)) {
                    iNota100--;
                    iValor -= 100;
                } else if ((iNota50 - 1 >= 0) && (iValor - 50 >= 0)) {
                    iNota50--;
                    iValor -= 50;
                } else {
                    break;
                }
            }

            if (iValor == 0) {
                atm.setNota100(String.valueOf(iNota100));
                atm.setNota50(String.valueOf(iNota50));
                pagPossivel = true;
            } else {
                pagPossivel = false;
            }

            if (((Integer.parseInt(valor) < Integer.parseInt(conta.saldo)) || ((Integer.parseInt(conta.saldo) - Integer.parseInt(valor)) >= Integer.parseInt(conta.limite))) && pagPossivel) {
                query = "UPDATE `BD_ES2`.`Conta` "
                        + "SET "
                        + "`saldo` = `saldo` - " + valor
                        + " WHERE `idConta` = " + idConta;

                stmt = conexao.prepareStatement(query);
                stmt.executeUpdate(query);

                conta.setSaldo(String.valueOf((Integer.parseInt(conta.saldo) - Integer.parseInt(valor))));
                CaixaEletronico.sessao.setAttribute("conta", conta);

                // Atualiza valores no BD
                query = "UPDATE `BD_ES2`.`CaixaEletronico`"
                        + "SET "
                        + " `nota50` = " + atm.getNota50() + " ,"
                        + " `nota100` = " + atm.getNota100()
                        + " WHERE idCaixaEletronico = " + atm.getId();
                stmt = conexao.prepareStatement(query);
                stmt.executeUpdate(query);
                CaixaEletronico.sessao.setAttribute("caixaEletronico", atm);

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
