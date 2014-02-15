/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.util.List;

/**
 *
 * @author Owner
 */
public interface IMenuItemDAO {

    List<MenuItem> getAllMenuItems() throws Exception;
    //    public static void main(String[] args) throws Exception {
    //        MenuItemDAO m=new MenuItemDAO();
    //        List<MenuItem> recs = m.getAllMenuItems();
    //        for(MenuItem rec:recs){
    //            System.out.println(rec);
    //
    //        }
    //   }
    
    List<MenuItem> getSingleMenuItems(int i) throws Exception;
    
}
