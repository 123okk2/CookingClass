package CookingClass;

public class Show_menu {
		
	private int date;
	private String branch;
	private String menuName1;
	private double price1;
	private String menuName2;
	private double price2;
	private String menuName3;
	private double price3;
	
	public Show_menu(int date,String branch, String menuName1, double price1,String menuName2, double price2,String menuName3, double price3)
	{
		this.date =date;
		this.branch = branch;
		this.menuName1 =menuName1;
		this.price1 = price1;
		this.menuName1 =menuName2;
		this.price1 = price2;
		this.menuName1 =menuName3;
		this.price1 = price3;
	}
	
	public int getDate(){return date;}
	public String getBranch() {return branch;}
	public String getMenuName1() {return menuName1;}
	public double getPrice1() {return price1;}
	public String getMenuName2() {return menuName2;}
	public double getPrice2() {return price2;}
	public String getMenuName3() {return menuName3;}
	public double getPrice3() {return price3;}
}
