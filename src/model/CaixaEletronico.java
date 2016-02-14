package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ccz
 */
public class CaixaEletronico implements DatabaseActions {

    private String idCaixaEletronico, nota2, nota5, nota10, nota20;
    private String nota50, nota100, cheque, papelComprovante, dataDoCaixa;

    public CaixaEletronico() {
        idCaixaEletronico = "0";
        nota2 = "";
        nota5 = "";
        nota10 = "";
        nota20 = "";
        nota50 = "";
        nota100 = "";
        cheque = "";
        papelComprovante = "";
        dataDoCaixa = "";
    }

    public CaixaEletronico(HttpServletRequest request) {
        idCaixaEletronico = request.getParameter("id");
        nota2 = request.getParameter("nota2");
        nota5 = request.getParameter("nota5");
        nota10 = request.getParameter("nota10");
        nota20 = request.getParameter("nota20");
        nota50 = request.getParameter("nota50");
        nota100 = request.getParameter("nota100");
        cheque = request.getParameter("cheque");
        papelComprovante = request.getParameter("papelComprovante");
        dataDoCaixa = request.getParameter("dataDoCaixa");
    }

    public String getIdCaixaEletronico() {
        return idCaixaEletronico;
    }

    public void setIdCaixaEletronico(String idCaixaEletronico) {
        this.idCaixaEletronico = idCaixaEletronico;
    }

    public String getNota2() {
        return nota2;
    }

    public void setNota2(String nota2) {
        this.nota2 = nota2;
    }

    public String getNota5() {
        return nota5;
    }

    public void setNota5(String nota5) {
        this.nota5 = nota5;
    }

    public String getNota10() {
        return nota10;
    }

    public void setNota10(String nota10) {
        this.nota10 = nota10;
    }

    public String getNota20() {
        return nota20;
    }

    public void setNota20(String nota20) {
        this.nota20 = nota20;
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

    @Override
    public boolean insert() {
        Connection conexao = null;
        PreparedStatement stmt;
        String query;
        try {
            conexao = Conexao.conectar();

            query = "INSERT INTO `BD_ES2`.`CaixaEletronico` (`idCaixaEletronico`, `nota2`, `nota5`, `nota10`, `nota20`, `nota50`, `nota100`, `cheque`, `papelComprovante`, `dataDoCaixa`) "
                    + "VALUES ('"
                    + idCaixaEletronico + "', '"
                    + nota2 + "', '"
                    + nota5 + "', '"
                    + nota10 + "', '"
                    + nota20 + "', '"
                    + nota50 + "', '"
                    + nota100 + "', '"
                    + cheque + "', '"
                    + papelComprovante + "', CURDATE());";
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
                    + "`nota2` = '" + nota2 + "',"
                    + "`nota5` = '" + nota5 + "',"
                    + "`nota10` = " + nota10 + ","
                    + "`nota20` = " + nota20 + ","
                    + "`nota50` = " + nota50 + ","
                    + "`nota100` = " + nota100 + ","
                    + "`cheque` = " + cheque + ","
                    + "`papelComprovante` = " + papelComprovante + ","
                    + "`dataDoCaixa` = " + dataDoCaixa
                    + " WHERE `idCaixaEletronico` = " + idCaixaEletronico;
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
                    + "WHERE `idCaixaEletronico` = " + idCaixaEletronico;
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

            query = "SELECT `CaixaEletronico`.`idCaixaEletronico`,"
                    + "`CaixaEletronico`.`nota2`,"
                    + "`CaixaEletronico`.`nota5`, "
                    + "`CaixaEletronico`.`nota10`, "
                    + "`CaixaEletronico`.`nota20`, "
                    + "`CaixaEletronico`.`nota50`, "
                    + "`CaixaEletronico`.`nota100`, "
                    + "`CaixaEletronico`.`cheque`, "
                    + "`CaixaEletronico`.`papelComprovante`, "                    
                    + "`CaixaEletronico`.`dataDoCaixa` "
                    + "FROM `BD_ES2`.`CaixaEletronico` "
                    + "WHERE `idCaixaEletronico` = " + idCaixaEletronico;
            stmt = conexao.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                CaixaEletronico caixaEletronico = new CaixaEletronico();
                caixaEletronico.setIdCaixaEletronico(rs.getString("idCaixaEletronico"));
                caixaEletronico.setNota2(rs.getString("nota2"));
                caixaEletronico.setNota5(rs.getString("nota5"));
                caixaEletronico.setNota10(rs.getString("nota10"));
                caixaEletronico.setNota20(rs.getString("nota20"));
                caixaEletronico.setNota50(rs.getString("nota50"));
                caixaEletronico.setNota100(rs.getString("nota100"));
                caixaEletronico.setCheque(rs.getString("cheque"));
                caixaEletronico.setPapelComprovante(rs.getString("papelComprovante"));
                caixaEletronico.setDataDoCaixa(rs.getString("dataDoCaixa"));

                request.setAttribute("caixaEletronico", caixaEletronico);

                conexao.close();
                return true;
            } else {
                //TODO: Consulta vazia (registro já foi deletado)
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
                caixaEletronico.setIdCaixaEletronico(rs.getString("idCaixaEletronico"));
                caixaEletronico.setNota2(rs.getString("nota2"));
                caixaEletronico.setNota5(rs.getString("nota5"));
                caixaEletronico.setNota10(rs.getString("nota10"));
                caixaEletronico.setNota20(rs.getString("nota20"));
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
}
