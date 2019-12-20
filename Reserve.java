package CookingClass;
import java.io.*;

public class Reserve implements Serializable {
	private String reserveID;
	private String reserveName;
	private String unit;

	public Reserve(String reserveID, String reserveName, String unit) {
		this.reserveID=reserveID;
		this.reserveName=reserveName;
		this.unit=unit;
	}
	
	public String getReserveID() { return reserveID; }
	public String getReserveName() { return reserveName; }
	public String getUnit() { return unit; }

	public String toString() { return reserveName; }
}