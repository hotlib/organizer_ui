package org.gameorganizer.ui.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class GameSession {

	@Id
	@GeneratedValue
	private Long id;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "GameSession", cascade = CascadeType.ALL)
	private Set<Attendant> attendants;

	@Transient
	private Set<Player> joinedPlayers = new HashSet<Player>();

	@Transient
	private Player creator;

	private String place;

	@Temporal(value=TemporalType.TIMESTAMP)
	private Date sessionBegin;
	
	public GameSession(Player creator) {
		this.creator = creator;
		Attendant at = new Attendant(creator, this, GameSessionRelation.OWNER);
		this.attendants = new HashSet<Attendant>();
		this.attendants.add(at);
	}
	
	public GameSession() {
	}

	public Set<Attendant> getAttendants() {
		return attendants;
	}

	
	
	public Date getSessionBegin() {
		return sessionBegin;
	}

	public void setSessionBegin(Date sessionBegin) {
		this.sessionBegin = sessionBegin;
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

	public List<Player> getJoinedPlayers() {
		List <Player>result = new LinkedList<Player>();
		result.addAll(joinedPlayers);
		return result;
	}

	public Player getCreator() {
		return creator;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@PostLoad
	@SuppressWarnings("unused")
	private void identifyPlayersAccordingToRelation() {
		joinedPlayers.clear();
		for (Attendant attendant : attendants) {

			if (GameSessionRelation.OWNER.equals(attendant.getRelation()))
				this.creator = attendant.getPlayer();
			else
				joinedPlayers.add(attendant.getPlayer());

		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if(id == null)
			return true;
		
		return id.equals(((GameSession)obj).getId());
	}

}
