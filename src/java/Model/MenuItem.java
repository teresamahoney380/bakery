package Model;

/**
 * Entity Class for Menu Item
 *
 * @author Teresa Mahoney
 */
public class MenuItem {

    private int id;
    private String itemName;
    private double itemPrice;
    private String itemUrl;

    public MenuItem() {
    }
    
    

    public MenuItem(int id, String itemName, double itemPrice, String itemUrl) {
        this.id = id;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemUrl = itemUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MenuItem other = (MenuItem) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MenuItem{" + "id=" + id + ", itemName=" + itemName + ", itemPrice=" + itemPrice + '}';
    }
    

}
