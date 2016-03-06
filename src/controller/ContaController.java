/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Conta;

/**
 *
 * @author gustavo
 */
@WebServlet(name = "ContaController", urlPatterns = {"/ContaController"})
public class ContaController extends HttpServlet {

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
            out.println("<title>Servlet ContaController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ContaController at " + request.getContextPath() + "</h1>");

            Conta conta = new Conta(request);

            // Lista de ações disponíveis
            switch (request.getParameter("command")) {

                case "insert":
                    if (conta.insert()) {
                        out.println("<div><b> Conta inserida e associada a cliente! </b></div>");
                        out.println("<div><a href='ContaController?command=viewAll'>Visualizar todas contas</a>");
                        response.sendRedirect("ContaController?command=viewAll");
                    } else {
                        request.setAttribute("msgError", "Conta não inserido e/ou não associada a cliente!");
                        request.getRequestDispatcher("/conta_viewAll.jsp").forward(request, response);
                    }

                    break;

                case "edit":
                    if (conta.edit()) {
                        out.println("<div><b> Conta editado! </b></div>");
                        out.println("<div><a href='ContaController?command=viewAll'>Visualizar todos contas</a>");
                        response.sendRedirect("ContaController?command=viewAll");
                    } else {
                        request.setAttribute("msgError", "Conta não editada!");
                        request.getRequestDispatcher("/conta_viewAll.jsp").forward(request, response);
                    }

                    break;

                case "delete":
                    if (conta.delete()) {
                        out.println("<div><b> Conta deletado! </b></div>");
                        out.println("<div><a href='ContaController?command=viewAll'>Visualizar todos contas</a>");
                        response.sendRedirect("ContaController?command=viewAll");
                    } else {
                        request.setAttribute("msgError", "Conta não deletada!");
                        request.getRequestDispatcher("/conta_viewAll.jsp").forward(request, response);
                    }

                    break;

                case "view":
                    if (conta.view(request)) {
                        request.getRequestDispatcher("conta_view.jsp").forward(request, response);
                    } else {
                        request.setAttribute("msgError", "Conta não pode ser visualizado!");
                        request.getRequestDispatcher("/conta_viewAll.jsp").forward(request, response);
                    }

                    break;

                case "viewAll":
                    if (conta.viewAll(request)) {
                        request.getRequestDispatcher("conta_viewAll.jsp").forward(request, response);
                    } else {
                        request.setAttribute("msgError", "Conta não pode ser visualizado!");
                        request.getRequestDispatcher("/conta_viewAll.jsp").forward(request, response);
                    }

                    break;
                case "deposit":
                    if (conta.deposit(request)) {
                        out.println("<div><b> Depositado com sucesso! </b> <input type=\"button\" value=\"Imprimir comprovante\" onclick=\"document.location = 'CaixaEletronicoController?command=printPage'\" /></div>");
                        out.println("<div><a href='consulta_saldo.jsp'>Consulta saldo</a>");
                        response.sendRedirect("consulta_saldo.jsp");
                    } else {
                        request.setAttribute("msgError", "Depósito não efetuado!");
                        request.getRequestDispatcher("/deposito_em_conta.jsp").forward(request, response);
                    }

                    break;

                case "saque":
                    if (conta.saque(request)) {
                        out.println("<div><b> Sacado com sucesso! </b> <input type=\"button\" value=\"Imprimir comprovante\" onclick=\"document.location = 'CaixaEletronicoController?command=printPage'\" /></div>");
                        out.println("<div><a href='consulta_saldo.jsp'>Consulta saldo</a>");
                        response.sendRedirect("consulta_saldo.jsp");
                    } else {
                        request.setAttribute("msgError", "Saque não efetuado!");
                        request.getRequestDispatcher("/saque.jsp").forward(request, response);
                    }

                    break;
                case "pagamento":
                    if (conta.pagamento(request)) {
                        out.println("<div><b> Pagamento feito com sucesso! </b> <input type=\"button\" value=\"Imprimir comprovante\" onclick=\"document.location = 'CaixaEletronicoController?command=printPage'\" /></div>");
                        out.println("<div><a href='consulta_saldo.jsp'>Consulta saldo</a>");
                        response.sendRedirect("consulta_saldo.jsp");
                    } else {
                        request.setAttribute("msgError", "Pagamento não efetuado!");
                        request.getRequestDispatcher("/pagamento_autenticado.jsp").forward(request, response);;
                    }

                    break;
                case "transferencia":
                    if (conta.transferencia(request)) {
                        out.println("<div><b> Transferência efetuada com sucesso!</b></div>");
                        out.println("<div><a href='consulta_saldo.jsp'>Consulta saldo</a>");
                        response.sendRedirect("consulta_saldo.jsp");
                    } else {
                        request.setAttribute("msgError", "Transferência não efetuada!");
                        request.getRequestDispatcher("/transferencia.jsp").forward(request, response);
                    }

                    break;
                case "investimento":
                    if (conta.investimento(request)) {
                        out.println("<div><b> Investimento efetuado com sucesso!</b></div>");                        
                        response.sendRedirect("consulta_poupanca.jsp");
                    } else {
                        request.setAttribute("msgError", "Investimento não efetuado!");
                        request.getRequestDispatcher("/investimento.jsp").forward(request, response);
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
