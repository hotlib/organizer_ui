package org.gameorganizer.ui;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class GameSession implements Serializable{
	
	private Date sessionBegin = new Date();
	private String username = "Fritz";
	private String place = "Neubau Str. 18";
	private Boolean joined = Boolean.FALSE;
	private List <Player> joinedUsers = new LinkedList<Player>();
	
	
	public List<Player> getJoinedUsers() {
		return joinedUsers;
	}

	public void setJoinedUsers(List<Player> joinedUsers) {
		this.joinedUsers = joinedUsers;
	}

	public GameSession(String username) {
		super();
		this.username = username;
	}

	public Boolean getJoined() {
		return joined;
	}

	public void setJoined(Boolean joined) {
		this.joined = joined;
	}

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
	
	public String flipJoined() {
		joined = !joined;
		return null;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this)
			return true;
		
		return false;
	}
	
}
