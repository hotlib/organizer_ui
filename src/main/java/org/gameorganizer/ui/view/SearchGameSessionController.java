package org.gameorganizer.ui.view;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.gameorganizer.ui.entity.GameSession;
import org.gameorganizer.ui.entity.GameSessionRelation;

@Named("joinSessionManager")
@SessionScoped
public class SearchGameSessionController extends GameSessionController{


	private static final long serialVersionUID = -7043299785320530618L;

	public Boolean isJoined(GameSession session) {

		return gameSessionService
				.hasJoined(loggedInPlayer.getPlayer(), session);
	}
	
	public void flipJoined(GameSession gameSession) {
		System.out.println("player: "
				+ loggedInPlayer.getPlayer().getNickName() + " logged in: "
				+ isJoined(gameSession));
		if (isJoined(gameSession))
			gameSessionService.leaveGameSession(loggedInPlayer.getPlayer(),
					gameSession);
		else
			gameSessionService.joinGameSession(loggedInPlayer.getPlayer(),
					gameSession);
	}
	
	public String getSessionMessage() {
		return getSessionMessage(loggedInPlayer.getPlayer(), selectionItem);

	}
	
	public void setSessionMessage(String sessionMessage) {
		gameSessionService.setSessionMessage(loggedInPlayer.getPlayer(),
				selectionItem, sessionMessage);
	}
	
	public String getSessionMessage(GameSession gameSession) {
		return getSessionMessage(loggedInPlayer.getPlayer(), gameSession);
	}
	
	public List<GameSession> getJoinableGameSessions() {
		gameSessions.clear();
		gameSessions.addAll(gameSessionService.getGameSessionsForPlayer(
				loggedInPlayer.getPlayer(), GameSessionRelation.JOINER));
		return gameSessions;
	}
	
}
