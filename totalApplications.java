package CookingClass;

import java.io.*;

public class totalApplications {
	private int num;
	private int date;
	private int date2;
	private String name;
	private String phoneNum;
	private String MenuName;
	
	public totalApplications(int num, int date, String name, String phoneNum, String MenuName, int date2) {
		this.num=num;
		this.date=date;
		this.name=name;
		this.phoneNum=phoneNum;
		this.MenuName=MenuName;
		this.date2=date2;
	}
	
	public int getNum() { return num;}
	public int getDate() { return date; }
	public int getDate2() { return date2; }
	public String getName() { return name; }
	public String getPhoneNum() { return phoneNum; }
	public String getMenuName() { return MenuName; }
}
