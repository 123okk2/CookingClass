package CookingClass;
import java.io.*;

public class Menu implements Serializable {
	private String branch;
	private int date;
	private String menuID;
  	public Menu(String branch, int date, String menuID) {
  		this.branch = branch;
      		this.date = date;
      		this.menuID = menuID;
   	}
	public String getBranch() {return branch;}
	public int getDate(){return date;}
	public String getMenuID() {return menuID;}
}