package org.gameorganizer.ui.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Transient;


@Entity
public class Player {

	@Id
	private Long id;
	private String name;
	private String email;
	
	@Transient
	private Set<GameSession> createdGameSession = new HashSet<GameSession>();
	
	@Transient
	private Set<GameSession> joinedGameSession = new HashSet<GameSession>();
		
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

	public Set<GameSession> getCreatedGameSession() {
		return createdGameSession;
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
	
	@PostLoad
	private void identifyGameSession() {
		for (Attendant attendant : attendants) {
			
			if(GameSessionRelation.OWNER.equals(attendant.getRelation()))
				createdGameSession.add(attendant.getGameSession());
			else
				joinedGameSession.add(attendant.getGameSession());
			
		}
	}
	
	
	
}
