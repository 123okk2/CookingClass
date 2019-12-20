package CookingClass;

import java.io.*;

public class totalMenus {
	private int date;
	private String MenuName;
	private String branch1;
	private String branch2;
	private String branch3;
	
	public totalMenus(int date, String MenuName, String branch1, String branch2, String branch3) {
		this.date=date;
		this.MenuName=MenuName;
		this.branch1=branch1;
		this.branch2=branch2;
		this.branch3=branch3;
	}
	
	public int getDate() { return date; }
	public String getMenuName() { return MenuName; }
	public String getBranch1() { return branch1; }
	public String getBranch2() { return branch2; }
	public String getBranch3() { return branch3; }
}