package org.gameorganizer.ui;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named()
@SessionScoped
public class UserAuthenticator implements Serializable{
	private String username = "fritz ";
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
