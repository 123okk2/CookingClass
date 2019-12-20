package CookingClass;

import java.io.*;

public class ApplicationsList implements Serializable {
	private int appNum;
	private String menuID;
	private double amount;
	private double totalPrice;

	public ApplicationsList( String menuID, double amount, double totalPrice)
   	{
		appNum=0;
      		this.menuID = menuID;
      		this.amount = amount;
      		this.totalPrice = totalPrice;
   	}

	public ApplicationsList(int appNum, String menuID, double amount, double totalPrice) {
		this.appNum = appNum;
		this.menuID = menuID;
      		this.amount = amount;
      		this.totalPrice = totalPrice;
	}	

	public int getAppNum() { return appNum; }
	public String getMenuID() { return menuID; }
	public double getAmount() { return amount; }
	public double getTotalPrice() { return totalPrice; }
}