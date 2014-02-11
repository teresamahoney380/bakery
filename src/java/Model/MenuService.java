package Model;

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

}
