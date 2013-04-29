package org.gameorganizer.ui.view;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.gameorganizer.ui.entity.Attendant;
import org.gameorganizer.ui.entity.GameSession;
import org.gameorganizer.ui.entity.GameSessionRelation;

@Named("gameSessionManager")
@SessionScoped
public class CreateGameSessionController extends GameSessionController {

	private static final long serialVersionUID = -4421765206901163944L;

	public int getSelectedItemIndex() {
		if (selectionItem == null) {
			return -1;
		}
		System.out.println("preselect");
		int result = gameSessions.indexOf(selectionItem);
		System.out.println("selected: " + result);

		return result;
	}

	public void setLastIndex(int lastIndex) {
	}

	

	

	public List<GameSession> getCreatedGameSessions() {
		gameSessions.clear();
		gameSessions.addAll(gameSessionService.getGameSessionsForPlayer(
				loggedInPlayer.getPlayer(), GameSessionRelation.OWNER));
		return gameSessions;
	}

	public void deleteSession() {
		if (selectionItem == null)
			return;

		gameSessionService.deleteGameSession(selectionItem);

		gameSessions.remove(selectionItem);
		selectionItem = null;
	}

	public void createSession() {

		GameSession gs = gameSessionService.createGameSession(loggedInPlayer
				.getPlayer());
		gameSessions.add(gs);
		selectionItem = gs;
	}

	// TODO rename and wtf
	public void save() {
		gameSessionService.update(selectionItem);
	}

	public List<GameSession> getGameSessions() {
		return getCreatedGameSessions();
	}

	public String getOwnerSessionMessage() {
		if (selectionItem == null)
			return "";

		return getOwnerSessionMessage(selectionItem);
	}

	public void setOwnerSessionMessage(String sessionMessage) {
		for (Attendant at : selectionItem.getAttendants()) {
			if (GameSessionRelation.OWNER.equals(at.getRelation())) {
				at.setSessionMessage(sessionMessage);
			}
		}
	}

}
