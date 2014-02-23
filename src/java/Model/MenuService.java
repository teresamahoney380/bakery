package Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Service Program for restaurant menu
 *
 * @Teresa Mahoney
 */
public class MenuService {

    public MenuService() {
    }

    /**
     * this method retrieves all menu item records from the database
     *
     * @return List of MenuItem Objects
     */
    public List<MenuItem> getAllMenuItems() throws Exception {
        MenuItemDAO m = new MenuItemDAO();
        List<MenuItem> recs = m.getAllMenuItems();
        return recs;
    }

    public List<MenuItem> getSingleMenuItem(int i)throws Exception{
    SingleMenuItemDAO m = new SingleMenuItemDAO();
        List<MenuItem> recs = m.getSingleMenuItems(i);
    return recs ;
}
    public int deleteRecs(String ids){
        int deleted = 0;
        MenuItemDAO m = new MenuItemDAO();
        try {
            deleted = m.deleteRecs(ids);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MenuService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MenuService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return deleted;
    }
    public boolean insertRecs(List colHeadings,List fieldValues){
        boolean success=false;
        try {
            int added = 0;
            MenuItemDAO m = new MenuItemDAO();
            
            success =  m.insertRecs(colHeadings, fieldValues);
        } catch (Exception ex) {
            Logger.getLogger(MenuService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return success;
    }
    public boolean updateRec(List colHeadings,List fieldValues, Object whereValue){
        int count = 0;
        MenuItemDAO m = new MenuItemDAO();
        count = m.updateRecs(colHeadings, fieldValues, "menu_id", whereValue);
        return true;
    }
//    public static void main(String[] args) throws Exception {
//        MenuService ms = new MenuService();
//        
//        List l1 = new ArrayList();            
//            l1.add("menu_item");
//            l1.add("item_price");
//            l1.add("item_img_url");
//            List l2 = new ArrayList();            
//            l2.add("new cupcake3");
//            l2.add(3.95);
//            l2.add("images/choco3.jpg");
//        System.out.println(ms.updateRec(l1, l2,21));
//    }
}
