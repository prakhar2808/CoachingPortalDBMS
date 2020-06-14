package com.dbmsproject.ignite.tutorials.model;

public class Admin {
	private int AdminId;
	private String Password;
	private final String Role="ROLE_ADMIN";
	public int getAdminId() {
		return AdminId;
	}
	public void setAdminId(int adminId) {
		AdminId = adminId;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getRole() {
		return Role;
	}
	public String getUserName() {
		return "ADM"+Integer.toString(this.getAdminId());
	}
}
