package controller;

import Model.MenuItem;
import Model.MenuService;
import java.io.IOException;
import java.io.PrintWriter;
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
 * this servlet manages the maintenance of the database
 *
 * @author Teresa Mahoney
 */
@WebServlet(name = "MenuMaintController", urlPatterns = {"/maint"})
public class MenuMaintController extends HttpServlet {

    private static final String MAINT_PAGE = "MenuMaint.jsp";

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
        String addEdit = (String) request.getParameter("addEdit");
        String delete = (String) request.getParameter("delete");
        MenuService ms = new MenuService();

        if (addEdit == null && delete == null) {
            //get database records
            List<MenuItem> mi = ms.getAllMenuItems();
            request.setAttribute("menulist", mi);

            RequestDispatcher view
                    = request.getRequestDispatcher(MAINT_PAGE);
            view.forward(request, response);

        } else if (addEdit != null) {
            // addEdit button is selected
            request.setAttribute("returnMsg", "AddEdit is selected");
            RequestDispatcher view
                    = request.getRequestDispatcher("error.jsp");
            view.forward(request, response);
        } else {
            // delete must be selected
            String[] itemsSelected = request.getParameterValues("itemId");
            String paramSql = "";
            for (int x = 0; x < (itemsSelected.length); x++) {
                int itemId = Integer.valueOf(itemsSelected[x]);
                if (x == (itemsSelected.length - 1)) {
                    paramSql = paramSql + itemId;
                } else {
                    paramSql = paramSql + itemId + ", ";
                }
            }
            int deleted = 0;
            deleted = ms.deleteRecs(paramSql);
            String returnMsg = deleted + " Records Deleted.";
            request.setAttribute("returnMsg", returnMsg);
            List<MenuItem> mi = ms.getAllMenuItems();
            request.setAttribute("menulist", mi);

            RequestDispatcher view
                    = request.getRequestDispatcher(MAINT_PAGE);
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
            Logger.getLogger(MenuMaintController.class.getName()).log(Level.SEVERE, null, ex);
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

}
