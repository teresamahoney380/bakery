package Model;

import java.util.List;

/**
 * Start up class for testing purposes
 *
 * @author Teresa Mahoney
 */
public class StartUp {

    public static void main(String[] args) throws Exception {
        MenuService ms = new MenuService();
        List<MenuItem> mi = ms.getAllMenuItems();
        
        for(MenuItem rec:mi){
            System.out.println(rec);  
        }
    }

}
