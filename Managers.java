package CookingClass;

public class Managers {
	private String managerID;
	private String managerPW;

	public String getManagerID() { return managerID; }
	public String getManagerPW() { return managerPW; }

	public Managers(String managerID, String managerPW) {
		this.managerID = managerID;
		this.managerPW = managerPW;
	}
}