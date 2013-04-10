package org.gameorganizer.ui;

import java.util.Date;

public class GameSession {
	
	private Date sessionBegin = new Date();
	private String username = "Fritz";
	private String place = "Neubau Str. 18";
		
	public Date getSessionBegin() {
		return sessionBegin;
	}
	
	public void setSessionBegin(Date sessionBegin) {
		this.sessionBegin = sessionBegin;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPlace() {
		return place;
	}
	
	public void setPlace(String place) {
		this.place = place;
	}	
}
