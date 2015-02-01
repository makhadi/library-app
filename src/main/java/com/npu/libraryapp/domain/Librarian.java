package com.npu.libraryapp.domain;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@XmlRootElement(name = "librarian")
public class Librarian {
	
	//@Size(min=2, max=30)
	@NotEmpty
	private String firstName;
	@Override
	public String toString() {
		return "Librarian [firstName=" + firstName + ", lastName=" + lastName
				+ ", emailId=" + emailId + ", password=" + password
				+ ", librarianId=" + librarianId + "]";
	}

	
	//@Size(min=2, max=30)
	@NotEmpty
	private String lastName;
	
	//@Size(min=8, max=30)
	@NotEmpty
	private String emailId;
	
	//@Size(min=1, max=20)
	@NotEmpty
	private String password;
	int librarianId;
	
	public Librarian(){
	}
	
	public Librarian(String firstName, String lastName, String emailId, String password){
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.password = password;
	}
	
	public void setLibrarianId(int librarianId){
		this.librarianId = librarianId;
	}
	
	public int getLibrarianId(){
		return librarianId;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getPassword() {
		return password;
	}

}