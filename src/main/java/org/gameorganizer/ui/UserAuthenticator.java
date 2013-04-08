package org.gameorganizer.ui;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named()
@RequestScoped
public class UserAuthenticator {
	private String username;
	private String password;
	private Boolean isLoggedIn = Boolean.FALSE;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public Boolean getIsLoggedIn() {
		return isLoggedIn;
	}
	public void setIsLoggedIn(Boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void authenticateUser() {
		isLoggedIn = Boolean.TRUE;
	}
	
	
}
