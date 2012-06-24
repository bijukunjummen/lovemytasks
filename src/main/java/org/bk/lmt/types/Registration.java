package org.bk.lmt.types;

import javax.validation.constraints.Size;

public class Registration {
	
	@Size(min=1, max=100)
	private String fullname;
	
	@Size(min=5, max=20)
	private String username;
	
	@Size(min=6, max=50)
	private String password;
	
	@Size(min=6, max=50)
	private String confirmPassword;

	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
}