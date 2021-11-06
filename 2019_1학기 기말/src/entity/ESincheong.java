package entity;

import java.util.Scanner;

public class ESincheong {
	private String number;
	private String name;
	private String professorName;
	private String credit;
	private String time;
	private String userId;
	
	public void read(Scanner scanner) {
		this.userId= scanner.next();
		this.number = scanner.next();
		this.name = scanner.next();
		this.professorName = scanner.next();
		this.credit = scanner.next();
		this.time = scanner.next();
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfessorName() {
		return professorName;
	}

	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
