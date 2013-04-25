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
public class GameSessionService implements Serializable {

	@PersistenceContext(unitName = "testtest")
	private EntityManager entityManager;

	public GameSession createGameSession(Player player) {

		GameSession gs = new GameSession(player);

		entityManager.persist(gs);

		return gs;
	}

	public GameSession update(GameSession gameSession) {
		return entityManager.merge(gameSession);
	}

	public void deleteGameSession(GameSession gameSession) {
		gameSession = entityManager.merge(gameSession);

		for (Attendant attendant : gameSession.getAttendants()) {

			entityManager.remove(entityManager.merge(attendant));
		}

		entityManager.remove(gameSession);
	}

	public void updateSessionMessage(String playerEmail, String sessionMessage,
			GameSession gameSession) {
		Attendant attendantToBeUpdated = null;

		attendantToBeUpdated = findAttendantWithEmail(playerEmail, gameSession);

		attendantToBeUpdated.setSessionMessage(sessionMessage);

		entityManager.merge(attendantToBeUpdated);

	}

	// TODO has to find attendats from DB because gameSession.getAttendants()
	// might be empty
	private Attendant findAttendantWithEmail(String playerEmail,
			GameSession gameSession) {

		for (Attendant attendant : gameSession.getAttendants()) {
			if (attendant.getPlayer().getEmail().equals(playerEmail))
				return attendant;
		}
		throw new RuntimeException("AttendantWithEmail not found, email: "
				+ playerEmail);
	}

	public void joinGameSession(Player player, GameSession gameSession) {
		Attendant at = new Attendant(player, gameSession,
				GameSessionRelation.JOINER);

		entityManager.persist(at);
	}

	public void leaveGameSession(Player player, GameSession gameSession) {

		// TODO check if joiner
		Query query = entityManager
				.createQuery("DELETE FROM Attendant x WHERE x.player.email=:email AND x.gameSession=:gameSession");

		query.setParameter("email", player.getEmail());
		query.setParameter("gameSession", gameSession);
		query.executeUpdate();
	}

	public Boolean hasJoined(Player player, GameSession gameSession) {
		Query query = null;
		
		query = entityManager
				.createQuery("SELECT x.id FROM GameSession x JOIN x.attendants y WHERE y.player.email=:email AND y.gameSession=:gameSession");

		query.setParameter("email", player.getEmail());
		query.setParameter("gameSession", gameSession);
		return !Boolean.valueOf(query.getResultList().isEmpty());
	}

	public List<GameSession> getGameSessionsForPlayer(Player player,
			GameSessionRelation relation) {

		Query query = null;

		if (GameSessionRelation.OWNER.equals(relation)) {
			query = entityManager
					.createQuery("SELECT x FROM GameSession x JOIN x.attendants y WHERE y.player.email=:email AND y.relation=:relation");

		}

		// all joinable gamesessions
		if (GameSessionRelation.JOINER.equals(relation)) {
			query = entityManager
					.createQuery("SELECT z FROM GameSession z WHERE z.id NOT IN (SELECT x.id FROM GameSession x JOIN x.attendants y WHERE y.player.email=:email AND y.relation=:relation)");
		}

		query.setParameter("email", player.getEmail());
		query.setParameter("relation", GameSessionRelation.OWNER);
		return query.getResultList();

	}

}
