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
 * @author Owner
 */
public class MenuItemDAO {
    private DBAccessor db;

    public MenuItemDAO() {
    }
    
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
        rawData = db.retrieveRecords("select menu_id, menu_item, item_price from menu", true);
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
            
            records.add(mi);
        }
    return records;
}
//    public static void main(String[] args) throws Exception {
//        MenuItemDAO m=new MenuItemDAO();
//        List<MenuItem> recs = m.getAllMenuItems();
//        for(MenuItem rec:recs){
//            System.out.println(rec);
//            
//        }
//   }
}
