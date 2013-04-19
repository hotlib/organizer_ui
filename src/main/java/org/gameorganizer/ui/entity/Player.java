package org.gameorganizer.ui.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;


@Entity
public class Player {

	@Id
	private Long id;
	private String name;
	private String email;
	
	@Transient
	private Set<GameSession> createdGameSession;
	
	@Transient
	private Set<GameSession> joinedGameSession;
		
	@OneToMany(fetch=FetchType.EAGER, mappedBy="Player")
	private Set <Attendant> attendants;
	
	public Set<Attendant> getAttendants() {
		return attendants;
	}
	public void setAttendants(Set<Attendant> attendants) {
		this.attendants = attendants;
	}
	public Set<GameSession> getJoinedGameSession() {
		return joinedGameSession;
	}
	public void setJoinedGameSession(Set<GameSession> joinedGameSession) {
		this.joinedGameSession = joinedGameSession;
	}
	public Set<GameSession> getCreatedGameSession() {
		return createdGameSession;
	}
	public void setCreatedGameSession(Set<GameSession> createdGameSession) {
		this.createdGameSession = createdGameSession;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
