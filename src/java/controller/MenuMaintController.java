package controller;

import Model.MenuItem;
import Model.MenuService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
        String addUpdate = (String) request.getParameter("add");
        String menuId = (String)request.getParameter("menuId");
        MenuService ms = new MenuService();
        String[] itemsSelected = request.getParameterValues("itemId");
        // Determine what action
        if (select.equals("update")) {
            
            // process add or insert
           
            List<String> colHeadings = new ArrayList();
            List fieldValues = new ArrayList();
            Integer menu_id = Integer.valueOf(request.getParameter("menuId"));
            // if update, include menu_Id
//            if (addUpdate.equals("edit")){
//            colHeadings.add("menu_id");
//            fieldValues.add(Integer.valueOf(request.getParameter("menuId")));
//            }
            //col headings list
            colHeadings.add("menu_item");
            colHeadings.add("item_price");
            colHeadings.add("item_img_url");
            //col values list        
            fieldValues.add(request.getParameter("txt_menu_item"));
            fieldValues.add(Double.valueOf(request.getParameter("txt_item_price")));
            fieldValues.add(request.getParameter("txt_item_img_url"));
            //
            if (addUpdate.equals("edit")){
                boolean success = false;
            success = ms.updateRec(colHeadings, fieldValues, menu_id);
            
                request.setAttribute("returnMsg", "from edit");
            
            }else{
            ms.insertRecs(colHeadings, fieldValues); 
            request.setAttribute("returnMsg", "from add");
            }
            List<MenuItem> mi = ms.getAllMenuItems();
            request.setAttribute("menulist", mi);

            RequestDispatcher view
                    = request.getRequestDispatcher(MAINT_PAGE);
            view.forward(request, response);

        } else {
            //all other        }

            if (addEdit == null && delete == null) {
                //get database records
                List<MenuItem> mi = ms.getAllMenuItems();
                request.setAttribute("menulist", mi);

                RequestDispatcher view
                        = request.getRequestDispatcher(MAINT_PAGE);
                view.forward(request, response);

            } else if (addEdit != null) {
                // addEdit button is selected
                //request.setAttribute("returnMsg", "AddEdit is selected");
                if (itemsSelected != null) {
                    request.setAttribute("update", "edit");
                    // get the item selected for update
                    List<MenuItem> mi = ms.getSingleMenuItem(Integer.valueOf(itemsSelected[0]));
                    request.setAttribute("single", mi);
//                    MenuItem single = mi.get(0);
//                    String name = single.getItemName();
//                    request.setAttribute("menu_id", single.getId());
//                    request.setAttribute("menu_item",name);
//                    request.setAttribute("item_price",single.getItemPrice());
//                    request.setAttribute("item_img_url", single.getItemUrl());

                } else {

                    request.setAttribute("update", "add");
                    request.setAttribute("menu_id", "");
                    request.setAttribute("menu_item", "");
                    request.setAttribute("item_price", "");
                    request.setAttribute("item_img_url", "");
                }
                RequestDispatcher view
                        = request.getRequestDispatcher("update.jsp");
                view.forward(request, response);
            } else {
                // delete must be selected

                String paramSql = "";
                // This should have validation code here to make sure an item is selected
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
