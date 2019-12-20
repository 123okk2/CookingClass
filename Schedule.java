package CookingClass;
import java.io.*;

public class Schedule implements Serializable { // 강의일정
	private String branch;
	private int year;
	private String teacherID;
	private String day;
	private int from;
	private int to;

	public String getBranch() {
		return branch;
	}

	public int getYear() {
		return year;
	}

	public String getTeacherID() {
		return teacherID;
	}

	public String getDay() {
		return day;
	}

	public int getFrom() {
		return from;
	}

	public int getTo() {
		return to;
	}

	public Schedule(String branch, int year, String teacherID, String day, int from, int to) {
		this.branch = branch;
		this.year = year;
		this.teacherID = teacherID;
		this.day = day;
		this.from = from;
		this.to = to;
	}
}