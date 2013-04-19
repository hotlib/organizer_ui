package org.gameorganizer.ui.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Attendant {

	@Id
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Player player;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	private GameSession gameSession;
	
	private GameSessionRelation relation;
	
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
	
	
	
}
