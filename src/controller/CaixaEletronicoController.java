package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CaixaEletronico;

/**
 *
 * @author Matheus Froes Batista
 */
@WebServlet(name = "CaixaEletronicoController", urlPatterns = {"/CaixaEletronicoController"})
public class CaixaEletronicoController extends HttpServlet {

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
            out.println("<title>Servlet CaixaEletronicoController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CaixaEletronicoController at " + request.getContextPath() + "</h1>");

            CaixaEletronico caixaEletronico = new CaixaEletronico(request);

            // Lista de ações disponíveis
            switch (request.getParameter("command")) {

                case "insert":
                    if (caixaEletronico.insert()) {
                        out.println("<div><b> Caixa Eletrônico inserido! </b></div>");
                        out.println("<div><a href='CaixaEletronicoController?command=viewAll'>Visualizar todos caixas eletrônicos</a>");
                        response.sendRedirect("CaixaEletronicoController?command=viewAll");
                    } else {
                        request.setAttribute("msgError", "Caixa Eletrônico não inserido!");
                        request.getRequestDispatcher("/caixa_viewAll.jsp").forward(request, response);
                    }

                    break;

                case "edit":
                    if (caixaEletronico.edit()) {
                        out.println("<div><b> Caixa Eletrônico editado! </b></div>");
                        out.println("<div><a href='CaixaEletronicoController?command=viewAll'>Visualizar todos caixas eletrônicos</a>");
                        response.sendRedirect("CaixaEletronicoController?command=viewAll");
                    } else {
                        request.setAttribute("msgError", "Caixa Eletrônico não editado!");
                        request.getRequestDispatcher("/caixa_viewAll.jsp").forward(request, response);
                    }

                    break;

                case "delete":
                    if (caixaEletronico.delete()) {
                        out.println("<div><b> Caixa Eletrônico deletado! </b></div>");
                        out.println("<div><a href='CaixaEletronicoController?command=viewAll'>Visualizar todos caixas eletrônicos</a>");
                        response.sendRedirect("CaixaEletronicoController?command=viewAll");
                    } else {
                        request.setAttribute("msgError", "Caixa Eletrônico não deletado!");
                        request.getRequestDispatcher("/caixa_viewAll.jsp").forward(request, response);
                    }

                    break;

                case "view":
                    if (caixaEletronico.view(request)) {
                        request.getRequestDispatcher("caixa_view.jsp").forward(request, response);
                    } else {
                        request.setAttribute("msgError", "Caixa Eletrônico não pode ser visualizado!");
                        request.getRequestDispatcher("/caixa_viewAll.jsp").forward(request, response);
                    }

                    break;

                case "viewAll":
                    if (caixaEletronico.viewAll(request)) {
                        request.getRequestDispatcher("caixa_viewAll.jsp").forward(request, response);
                    } else {
                        request.setAttribute("msgError", "Caixa Eletrônico não pode ser visualizado!");
                        request.getRequestDispatcher("/caixa_viewAll.jsp").forward(request, response);
                    }

                    break;
                case "printPage":
                    if (caixaEletronico.printPage()) {
                        out.println("<div><b> Comprovante/Extrato impresso com sucesso! </b><br /><a href='login.jsp'>Voltar</a></div>");
                        //request.getRequestDispatcher((String) request.getAttribute("javax.servlet.forward.request_uri")).forward(request, response);
                    } else {
                        out.println("<div><b> Caixa Eletrônico não conseguiu imprimir! </b><br /><a href='login.jsp'>Voltar</a></div>");
                        //request.getRequestDispatcher( ((String) request.getAttribute("javax.servlet.forward.request_uri")).concat("?error=1") ).forward(request, response);
                    }
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
