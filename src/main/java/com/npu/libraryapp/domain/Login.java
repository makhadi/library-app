package com.npu.libraryapp.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class Login {
	
	@NotEmpty
	private String emailId;
	@NotEmpty
	private String password;
	
	public Login(){
	}
	
	@Override
	public String toString() {
		return "Login [emailId=" + emailId + ", password=" + password + "]";
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Login(String emailId, String password) {
		this.emailId = emailId;
		this.password = password;
	}

}
