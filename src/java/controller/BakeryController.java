/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.MenuItem;
import Model.MenuService;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Teresa Mahoney
 */
@WebServlet(name = "BakeryController", urlPatterns = {"/bakery"})
public class BakeryController extends HttpServlet {

    private static final String MENU_PAGE = "menu.jsp";
    private static final String ORDER_PAGE = "order.jsp";

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");

        String select = (String) request.getParameter("action");
        MenuService ms = new MenuService();
        // process order if order else build menu

        if (select.equals("order")) {
            String[] itemsOrdered = request.getParameterValues("itemId");
           // processOrder(itemsOrdered);
             List<Map> orderSummary = new ArrayList();
       List<MenuItem> oi = new ArrayList();
        for (int x = 0; x < (itemsOrdered.length); x++) {
            int itemId = Integer.valueOf(itemsOrdered[x]);

            oi = ms.getSingleMenuItem(itemId);
        }
            RequestDispatcher view
                    = request.getRequestDispatcher(ORDER_PAGE);
            request.setAttribute("orderlist", oi);
            view.forward(request, response);
        } else {

        // get a menu list from the database
            List<MenuItem> mi = ms.getAllMenuItems();
            request.setAttribute("menulist", mi);

            RequestDispatcher view
                    = request.getRequestDispatcher(MENU_PAGE);
            view.forward(request, response);

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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(BakeryController.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            request.setAttribute("errormsg", ex);
             RequestDispatcher view
                    = request.getRequestDispatcher("error.jsp");
            view.forward(request, response);
            Logger.getLogger(BakeryController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
// helper method to process order

//    private List<MenuItem> processOrder(String[] itemsOrdered) throws Exception {
//        // validate itemsOredered for null
//
//       
//        return oi;
//    }
}
