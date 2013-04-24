package org.gameorganizer.ui.service;


import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.gameorganizer.ui.entity.Attendant;
import org.gameorganizer.ui.entity.GameSession;
import org.gameorganizer.ui.entity.GameSessionRelation;
import org.gameorganizer.ui.entity.Player;

@Named
@Stateless
public class GameSessionService implements Serializable{

	@PersistenceContext(unitName="testtest")
	private EntityManager entityManager;
		
	public GameSession createGameSession(Player player) {
		
		GameSession gs = new GameSession(player);		
		
		entityManager.persist(gs);
		
		return gs;
	}
	
	public GameSession update(GameSession gameSession){
		return entityManager.merge(gameSession);
	}
	
	public void deleteGameSession(GameSession gameSession){
		gameSession = entityManager.merge(gameSession);
		
		for (Attendant attendant : gameSession.getAttendants()) { 
			
			entityManager.remove(entityManager.merge(attendant));
		}
		
		entityManager.remove(gameSession);
	}
	
	public void updateSessionMessage(String playerEmail, String sessionMessage, GameSession gameSession) {
		Attendant attendantToBeUpdated = null;
		
		attendantToBeUpdated = findAttendantWithEmail(playerEmail, gameSession);
		
		attendantToBeUpdated.setSessionMessage(sessionMessage);
		
		entityManager.merge(attendantToBeUpdated);
		
	}

	//TODO has to find attendats from DB because gameSession.getAttendants() might be empty
	private Attendant findAttendantWithEmail(String playerEmail,
			GameSession gameSession) {
		
		for (Attendant attendant : gameSession.getAttendants()) { 
			if(attendant.getPlayer().getEmail().equals(playerEmail))
				return attendant;
		}
		throw new RuntimeException("AttendantWithEmail not found, email: " + playerEmail);
	}
	
	
	public void joinGameSession(Player player, GameSession gameSession) {
		Attendant at = new Attendant(player, gameSession, GameSessionRelation.JOINER);
		
		entityManager.persist(at);
	}
	
	public void leaveGameSession(Player player, GameSession gameSession) {
		Attendant attendant = findAttendantWithEmail(player.getEmail(), gameSession);
		entityManager.remove(entityManager.merge(attendant));
	}

	
	public List<GameSession> getGameSessionsForPlayer(Player player, GameSessionRelation relation) {
		//Query query = entityManager.createQuery("SELECT x FROM GameSession x WHERE x.attendants.player.email=:email AND x.attendants.relation=:relation");
		Query query = entityManager.createQuery("SELECT x FROM GameSession x");
		
		
		//query.setParameter("email", player.getEmail());
		//query.setParameter("relation", relation);
		return query.getResultList();
	}

	
	
}
