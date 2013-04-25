package org.gameorganizer.ui.view;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.gameorganizer.ui.entity.Attendant;
import org.gameorganizer.ui.entity.GameSession;
import org.gameorganizer.ui.entity.GameSessionRelation;
import org.gameorganizer.ui.entity.Player;
import org.gameorganizer.ui.service.GameSessionService;
import org.richfaces.component.UIExtendedDataTable;

@Named("gameSessionManager")
@SessionScoped
public class GameSessionController implements Serializable {

	private static final long serialVersionUID = -4421765006901163944L;
	private List<GameSession> gameSessions = new LinkedList<GameSession>();
	private GameSession selectionItem;
	private List<GameSession> joinableGameSessions = new LinkedList<GameSession>();
	
	@Inject
	private LoggedInPlayer loggedInPlayer;

	@EJB
	GameSessionService gameSessionService;

	public int getSelectedItemIndex() {
		if (selectionItem == null) {
			return -1;
		}
		System.out.println("preselect");
		int result = gameSessions.indexOf(selectionItem);
		System.out.println("selected: " + result);

		return result;
	}

	
	
	public List<GameSession> getJoinableGameSessions() {
		joinableGameSessions.clear();
		joinableGameSessions.addAll(gameSessionService.getGameSessionsForPlayer(
				loggedInPlayer.getPlayer(), GameSessionRelation.JOINER));
		return joinableGameSessions;
	}

	public void setLastIndex(int lastIndex) {
	}

	public GameSession getSelectionItem() {
		return selectionItem;
	}

	public void setSelectionItem(GameSession selectionItem) {
		this.selectionItem = selectionItem;
	}

	public void selection(AjaxBehaviorEvent event) {
		System.out.println("new select");
		if (gameSessions.isEmpty())
			return;

		UIExtendedDataTable dataTable = (UIExtendedDataTable) event
				.getComponent();
		if (dataTable.getSelection() != null
				&& !dataTable.getSelection().isEmpty()) {
			Integer index = (Integer) dataTable.getSelection().iterator()
					.next();

			if (index >= gameSessions.size())
				selectionItem = gameSessions.get(gameSessions.size() - 1);
			else
				selectionItem = gameSessions.get(index);
		}
		return;
	}

	public List<GameSession> getCreatedGameSessions() {
		gameSessions.clear();
		gameSessions.addAll(gameSessionService.getGameSessionsForPlayer(
				loggedInPlayer.getPlayer(), GameSessionRelation.OWNER));
		return gameSessions;
	}

	
	public String editItem(GameSession session) {
		return null;
	}

	public Boolean isJoined(GameSession session) {
		
		return gameSessionService.hasJoined(loggedInPlayer.getPlayer(), session);
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

}
