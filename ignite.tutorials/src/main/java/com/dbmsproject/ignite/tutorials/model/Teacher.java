package com.dbmsproject.ignite.tutorials.model;

import java.sql.Date;

/*
create table teacher( 
TeacherId int not null auto_increment, 
Password varchar(20) not null,
FirstName varchar(20) not null, 
LastName varchar(20) not null, 
Gender char(1) not null, 
Dob date not null, 
HouseNo varchar(5) not null,
Street varchar(20) not null, 
City varchar(20) not null, 
primary key(TeacherId) );
*/
public class Teacher {
	private int TeacherId;
	private String Password;
	private String FirstName;
	private String LastName;
	private char Gender;
	private Date Dob;
	private String HouseNo;
	private String Street;
	private String City;
	private String AccountNo;
	private int Salary;
	private final String Role = "ROLE_TEACHER";
	private String Representation;
	public int getTeacherId() {
		return TeacherId;
	}
	public void setTeacherId(int teacherId) {
		TeacherId = teacherId;
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
		return "TEA"+Integer.toString(this.getTeacherId());
	}
	public String getAccountNo() {
		return AccountNo;
	}
	public void setAccountNo(String accoutNo) {
		AccountNo = accoutNo;
	}
	public int getSalary() {
		return Salary;
	}
	public void setSalary(int salary) {
		Salary = salary;
	}
	public String getRepresentation() {
		return Representation = getTeacherId()+": "+getFirstName()+" "+getLastName();
	}
	public void setRepresentation(String representation) {
		Representation = representation;
	}
}
