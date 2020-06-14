package com.dbmsproject.ignite.tutorials.model;

import java.sql.Date;

public class Student {
	
	private int RollNo;
	private String Password;
	private String FirstName;
	private String LastName;
	private char Gender;
	private Date Dob;
	private String HouseNo;
	private String Street;
	private String City;
	private int GuardianId;
	private final String Role = "ROLE_STUDENT" ;
	public int getRollNo() {
		return RollNo;
	}
	public void setRollNo(int rollNo) {
		RollNo = rollNo;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public char getGender() {
		return Gender;
	}
	public void setGender(char gender) {
		Gender = gender;
	}
	public Date getDob() {
		return Dob;
	}
	public void setDob(Date dob) {
		Dob = dob;
	}
	public String getHouseNo() {
		return HouseNo;
	}
	public void setHouseNo(String houseNo) {
		HouseNo = houseNo;
	}
	public String getStreet() {
		return Street;
	}
	public void setStreet(String street) {
		Street = street;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getRole() {
		return Role;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getUserName() {
		return "STU"+Integer.toString(this.getRollNo());
	}
	public int getGuardianId() {
		return GuardianId;
	}
	public void setGuardianId(int guardianId) {
		GuardianId = guardianId;
	}
}
