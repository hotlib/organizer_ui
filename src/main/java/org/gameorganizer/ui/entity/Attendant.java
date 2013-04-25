package org.gameorganizer.ui.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Attendant {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	private Player player;

	@ManyToOne(fetch = FetchType.EAGER)
	private GameSession gameSession;

	private GameSessionRelation relation;

	private String sessionMessage;

	public Attendant() {
	}

	public Attendant(Player player, GameSession gameSession,
			GameSessionRelation relation) {
		super();
		this.player = player;
		this.gameSession = gameSession;
		this.relation = relation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public GameSession getGameSession() {
		return gameSession;
	}

	public void setGameSession(GameSession gameSession) {
		this.gameSession = gameSession;
	}

	public GameSessionRelation getRelation() {
		return relation;
	}

	public void setRelation(GameSessionRelation relation) {
		this.relation = relation;
	}

	public String getSessionMessage() {
		return sessionMessage;
	}

	public void setSessionMessage(String sessionMessage) {
		this.sessionMessage = sessionMessage;
	}
	
	@Override
	public String toString() {
	return "Id: " + id + " sessionMessage: " + sessionMessage;
	}

}
