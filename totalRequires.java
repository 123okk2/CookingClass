package CookingClass;

import java.io.*;

public class totalRequires implements Serializable {
	private String name;
	private double amount;
	private String unit;

	public totalRequires(String name, double amount, String unit){
		this.name=name;
		this.amount=amount;
		this.unit=unit;
	}
	public String getName() { return name; }
	public double getAmount() { return amount; }
	public String getUnit() {return unit;}
	public void addAmount(double num) { amount+=num; }
	public String toString() { return name+"\t"+amount+"\t"+unit; }
}