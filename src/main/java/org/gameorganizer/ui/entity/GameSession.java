package org.gameorganizer.ui.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;



@Entity
public class GameSession {
	
	@Id
	private Long id;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="GameSession")
	private Set <Attendant> attendants;
	
	
	
	@Transient
	private Set<Player> joinedPlayers;
	
	@Transient
	private Player creator;
	
	private String place;
	
	
	public Set<Attendant> getAttendants() {
		return attendants;
	}
	public void setAttendants(Set<Attendant> attendants) {
		this.attendants = attendants;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Set<Player> getJoinedPlayers() {
		return joinedPlayers;
	}
	public void setJoinedPlayers(Set<Player> joinedPlayers) {
		this.joinedPlayers = joinedPlayers;
	}
	public Player getCreator() {
		return creator;
	}
	public void setCreator(Player creator) {
		this.creator = creator;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	
}
