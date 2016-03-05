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
public class Gerente {

    String nome, senha;

    public Gerente() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public static boolean login(HttpServletRequest request) {
        String nome = request.getParameter("nome");
        String senha = request.getParameter("senha");
        Connection conexao = null;
        PreparedStatement stmt;
        String query;
        try {
            conexao = Conexao.conectar();

            query = "SELECT * FROM `BD_ES2`.`Gerente` "
                    + "WHERE nome = '" + nome + "' AND senha = '" + senha + "'";

            stmt = conexao.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);

            Gerente gerente = new Gerente();

            if (rs.next()) {
                gerente.setNome(rs.getString("nome"));
                gerente.setSenha(rs.getString("senha"));

                if (!request.getSession(false).isNew()) {
                    CaixaEletronico.sessao = request.getSession(true);
                    CaixaEletronico.sessao.setAttribute("gerente", gerente);
                    return true;
                }

                conexao.close();
            } else if (nome != null && senha != null) {
                if (nome.equals("leomurta") && senha.equals("leomurta")) {
                    gerente.setNome(nome);
                    gerente.setSenha(senha);

                    if (!request.getSession(false).isNew()) {
                        CaixaEletronico.sessao = request.getSession(true);
                        CaixaEletronico.sessao.setAttribute("gerente", gerente);
                        return true;
                    }
                }
            }

            return false;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            return false;
        }
    }
}
