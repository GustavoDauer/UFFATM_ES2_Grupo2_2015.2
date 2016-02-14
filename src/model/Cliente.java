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
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ccz
 */
public class Cliente implements DatabaseActions, Comparable<Cliente> {

    String id, nome, status;

    public Cliente() {
        id = "0";
        nome = "";
        status = "";
    }

    public Cliente(HttpServletRequest request) {
        id = request.getParameter("id");
        nome = request.getParameter("nome");
        status = request.getParameter("status");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean insert() {
        Connection conexao = null;
        PreparedStatement stmt;
        String query;
        try {
            conexao = Conexao.conectar();

            query = "INSERT INTO `BD_ES2`.`Cliente`(`nome`) "
                    + "VALUES('" + nome + "')";
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

            query = "UPDATE `BD_ES2`.`Cliente` "
                    + "SET "
                    + "`nome` = '" + nome + "',"
                    + "`status` = " + status
                    + " WHERE `idCliente` = " + id;
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

            query = "DELETE FROM `BD_ES2`.`Cliente` "
                    + "WHERE `idCliente` = " + id;
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

            query = "SELECT `Cliente`.`idCliente`,"
                    + "`Cliente`.`nome`,"
                    + "`Cliente`.`status` "
                    + "FROM `BD_ES2`.`Cliente` "
                    + "WHERE `idCliente` = " + id;
            stmt = conexao.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getString("idCliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setStatus(rs.getString("status"));
                request.setAttribute("cliente", cliente);

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
        ArrayList<Cliente> todosClientes = getAll();

        if (todosClientes != null) {
            request.setAttribute("todosClientes", todosClientes);
            return true;
        }
        return false;
    }

    public static ArrayList<Cliente> getAll() {
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

            ArrayList<Cliente> todosClientes = new ArrayList();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getString("idCliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setStatus(rs.getString("status"));

                todosClientes.add(cliente);
            }

            conexao.close();
            return todosClientes;

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            return null;
        }
    }

    @Override
    public int compareTo(Cliente o) {
        if (this.id.compareTo(o.id) > 0) {
            return 1;
        }
        
        if (this.id.compareTo(o.id) < 0) {
            return -1;
        }
        
        return 0;
    }
}
