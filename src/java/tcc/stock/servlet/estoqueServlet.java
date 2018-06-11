/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.stock.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tcc.stock.dao.*;
import tcc.stock.model.*;

/**
 *
 * @author Paulo
 */
@WebServlet(name = "estoqueServlet", urlPatterns = {"/estoqueServlet"}, initParams = {
    @WebInitParam(name = "acao", value = "1")})
public class estoqueServlet extends HttpServlet {

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
        try {
            
        } catch (Exception e) {
        }
        
        }
//    }

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
        
//        processRequest(request, response);
        String acao = request.getParameter("Acao");
        String FkProduto = request.getParameter("codigo");
        
        Historicomovimento h = new Historicomovimento();
        HistoricomovimentoDao hDao = new HistoricomovimentoDao(h);
        
        h.setFkproduto(new ProdutoDao().find(FkProduto));
        
        h.setObs(request.getParameter("motivo"));
        h.setTipomovimento(1);
        
        switch(acao){
            
//            case 1:
//                hDao.retirar(h);
//                break;
//            case 2:
//                hDao.armazenar(h);
//                break;
            default:
                break;
        }
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
    public void List()throws Exception{
        try {
            
        } catch (Exception e) {
            throw e;
        }
    }
}
