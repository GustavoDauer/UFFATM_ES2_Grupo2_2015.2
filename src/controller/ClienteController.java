/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;

/**
 *
 * @author gustavo
 */
public class ClienteController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ClienteController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClienteController at " + request.getContextPath() + "</h1>");
            
            Cliente client = new Cliente();
            
            // Lista de ações disponíveis
            switch (request.getParameter("command")) {                
                    
                case "insert":                     
                    if(client.insert(request)) {
                        out.println("<div><b> Cliente inserido! </b></div>");
                    }
                    else {
                        out.println("<div><b> Cliente não inserido! </b></div>");
                    }
                    
                    break;
                    
                case "edit":                    
                    if(client.edit(request)) {
                        out.println("<div><b> Cliente editado! </b></div>");
                    }
                    else {
                        out.println("<div><b> Cliente não editado! </b></div>");
                    }
                    
                    break;
                    
                case "delete":                    
                    if(client.delete(request)) {
                        out.println("<div><b> Cliente deletado! </b></div>");
                    }
                    else {
                        out.println("<div><b> Cliente não deletado! </b></div>");
                    }
                                        
                    break;
                    
                case "view":
                    if(client.delete(request)) {
                        out.println("<div><b> Redirecionar para visualização do cliente...! </b></div>");
                        //Exemplo: response.sendRedirect("client_view.jsp?ID=" + client.getID());
                    }
                    else {
                        out.println("<div><b> Cliente não pode ser visualizado! </b></div>");
                    }
                    
                    break;
            }
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Visualiza, edita ou deleta cliente
        // ID do cliente é passado por get na URL
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Insere cliente
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
