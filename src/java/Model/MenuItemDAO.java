/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Teresa Mahoney
 */
public class MenuItemDAO implements IMenuItemDAO  {
    private DBAccessor db;

    public MenuItemDAO() {
    }
    
    @Override
    public List<MenuItem> getAllMenuItems() throws Exception{
    db= new DB_MySql();
        try {
            db.openConnection("com.mysql.jdbc.Driver", 
                    "jdbc:mysql://localhost:3306/menu_project",
                    "root", "admin");
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(MenuItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MenuItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Map> rawData=new ArrayList();
        rawData = db.retrieveRecords("select menu_id, menu_item, item_price, item_img_url from menu", true);
        List<MenuItem> records = new ArrayList();
        
         MenuItem mi = null;

        // Translate List<Map> into List<Employee>
        for (Map m : rawData) {
            mi = new MenuItem();

            String id = m.get("menu_id").toString();
            mi.setId(new Integer(id));
            String itemName = m.get("menu_item").toString();
            mi.setItemName(itemName);
            String itemPrice = m.get("item_price").toString();
            mi.setItemPrice(new Double(itemPrice));
            String itemUrl = m.get("item_img_url").toString();
            mi.setItemUrl(itemUrl);
            
            records.add(mi);
        }
    return records;
}
    // Delete records
    public int deleteRecs(int recId) throws IllegalArgumentException, ClassNotFoundException, SQLException, Exception{
        int deleted = 0;
        DB_MySql db = new DB_MySql();
            db.openConnection("com.mysql.jdbc.Driver", 
                    "jdbc:mysql://localhost:3306/menu_project", 
                    "root", "admin");
            String sql = "DELETE FROM menu WHERE"
                               + " menu_id = " + recId;
            deleted = db.deleteRecords(sql, true);
            return deleted;
            
    }
//    public static void main(String[] args) throws Exception {
//        MenuItemDAO m=new MenuItemDAO();
//        List<MenuItem> recs = m.getAllMenuItems();
//        for(MenuItem rec:recs){
//            System.out.println(rec);
//            
//        }
//   }
//    public static void main(String[] args) throws ClassNotFoundException, SQLException, Exception {
//        MenuItemDAO m=new MenuItemDAO();
//        System.out.println(m.deleteRecs(10));
//    }

    @Override
    public List<MenuItem> getSingleMenuItems(int i) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
