package CookingClass;

import java.io.*;

public class Teacher implements Serializable { // °­»ç
	private String teacherID;
	private String teacherName;
	private String accNum;

	public String getTeacherID() {
		return teacherID;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public String getAccNum() {
		return accNum;
	}

	public Teacher(String teacherID, String teacherName, String accNum) {
		this.teacherID = teacherID;
		this.teacherName = teacherName;
		this.accNum = accNum;
	}
}