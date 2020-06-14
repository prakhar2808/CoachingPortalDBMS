package com.dbmsproject.ignite.tutorials.model;

import java.sql.Date;

/*
 * create table guardian( 
 * GuardianId int not null auto_increment, 
 * Password varchar(20) not null,
 * FirstName varchar(20) not null, 
 * LastName varchar(20) not null, 
 * Gender char(1) not null, 
 * Dob date not null, 
 * HouseNo varchar(5) not null, 
 * Street varchar(20) not null, 
 * City varchar(20) not null, 
 * Occupation varchar(20) not null, 
 * primary key(GuardianId) );
 */
public class Guardian {
	private int GuardianId;
	private String Password;
	private String FirstName;
	private String LastName;
	private char Gender;
	private Date Dob;
	private String HouseNo;
	private String Street;
	private String City;
	private String Occupation;
	private final String Role = "ROLE_GUARDIAN";
	private String Representation;
	public int getGuardianId() {
		return GuardianId;
	}
	public void setGuardianId(int guardianId) {
		GuardianId = guardianId;
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
	public String getOccupation() {
		return Occupation;
	}
	public void setOccupation(String occupation) {
		Occupation = occupation;
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
		return "GUA"+Integer.toString(this.getGuardianId());
	}
	public String getRepresentation() {
		return Representation = "GUA"+getGuardianId()+": "+getFirstName()+" "+getLastName();
	}
	public void setRepresentation(String label) {
		Representation = "GUA"+getGuardianId()+": "+getFirstName()+getLastName();
	}
}
