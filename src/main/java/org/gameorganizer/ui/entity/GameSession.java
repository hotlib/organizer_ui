package org.gameorganizer.ui.entity;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class GameSession {

	@Id
	private Long id;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "GameSession", cascade = CascadeType.ALL)
	private Set<Attendant> attendants;

	@Transient
	private Set<Player> joinedPlayers = new HashSet<Player>();

	@Transient
	private Player creator;

	private String place;

	@Temporal(value=TemporalType.TIMESTAMP)
	private Calendar sessionBegin;
	
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

	
	
	public Calendar getSessionBegin() {
		return sessionBegin;
	}

	public void setSessionBegin(Calendar sessionBegin) {
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

	public Set<Player> getJoinedPlayers() {
		return joinedPlayers;
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
		for (Attendant attendant : attendants) {

			if (GameSessionRelation.OWNER.equals(attendant.getRelation()))
				this.creator = attendant.getPlayer();
			else
				joinedPlayers.add(attendant.getPlayer());

		}
	}

}
