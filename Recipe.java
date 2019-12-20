package CookingClass;
import java.io.*;

public class Recipe implements Serializable {
	private String menuID;
	private String reserveID;
	private double requires;

	public Recipe(String menuID, String reserveID, double requires) {
		this.menuID=menuID;
		this.reserveID=reserveID;
		this.requires=requires;
	}

	public String getMenuID() { return menuID; }
	public String getReserveID() { return reserveID; }
	public double getRequires() { return requires; }
}