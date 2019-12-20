package CookingClass;
import java.io.*;

public class MenuList implements Serializable {
	private String menuID;
	private String menuName;
	private double price;

   	public MenuList(String menuID, String menuName, double price)
   	{
      		this.menuID = menuID;
     		this.menuName = menuName;
      		this.price = price;
  	}

	public String getMenuID() { return menuID; }
	public String getMenuName() { return menuName; }
	public double getPrice() { return price; }
}