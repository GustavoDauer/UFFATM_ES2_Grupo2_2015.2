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
public class Cliente implements DatabaseActions{

    public Cliente() {
    }

    @Override
    public boolean insert(HttpServletRequest request) {
        String nome = request.getParameter("nome");              
              
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
    public boolean edit(HttpServletRequest request) {
        String id = request.getParameter("id"); // Numero
        String nome = request.getParameter("nome"); // String
        String status = request.getParameter("status"); // TRUE ou FALSE
              
        Connection conexao = null;
        PreparedStatement stmt;
        String query;
        try {
            conexao = Conexao.conectar(); 
            
            query = "UPDATE `BD_ES2`.`Cliente` "
                    + "SET "
                    + "`nome` = '" + nome + "',"
                    + "`status` = " + status
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
    public boolean delete(HttpServletRequest request) {
        String id = request.getParameter("id"); // Numero        
              
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
        String id = request.getParameter("id"); // Numero        
              
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
                request.setAttribute(id, rs);
                request.setAttribute(rs.getString("nome"), rs);
                request.setAttribute(rs.getString("status"), rs); // TODO: Testar se o retorno será correto. (Tentando retornar String de um RS Boolean)                                                             
            }
            else {
                //TODO: Consulta vazia (registro já foi deletado)
            }

            conexao.close();
            return true;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            return false;
        }
    }

}
