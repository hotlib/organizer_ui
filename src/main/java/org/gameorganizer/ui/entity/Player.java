package org.gameorganizer.ui.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Transient;


@Entity
public class Player {

	@Id
	@GeneratedValue
	private Long id;
	private String nickName;
	private String email;
	private String password;
	
	@Transient
	private Set<GameSession> createdGameSession = new HashSet<GameSession>();
	
	@Transient
	private Set<GameSession> joinedGameSession = new HashSet<GameSession>();
		
	@OneToMany(fetch=FetchType.EAGER, mappedBy="Player", cascade=CascadeType.ALL)
	private Set <Attendant> attendants;
		
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
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
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@PostLoad
	@SuppressWarnings("unused")
	private void identifyGameSession() {
		for (Attendant attendant : attendants) {
			
			if(GameSessionRelation.OWNER.equals(attendant.getRelation()))
				createdGameSession.add(attendant.getGameSession());
			else
				joinedGameSession.add(attendant.getGameSession());
			
		}
	}
	
	
	
}
