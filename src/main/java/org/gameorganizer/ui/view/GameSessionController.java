package org.gameorganizer.ui.view;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

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
		joinableGameSessions.addAll(gameSessionService
				.getGameSessionsForPlayer(loggedInPlayer.getPlayer(),
						GameSessionRelation.JOINER));
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

	public void selection(UIExtendedDataTable dataTable,
			List<GameSession> sessions) {
		System.out.println("new select");
		if (sessions.isEmpty())
			return;

		if (dataTable.getSelection() != null
				&& !dataTable.getSelection().isEmpty()) {
			Integer index = (Integer) dataTable.getSelection().iterator()
					.next();

			if (index >= sessions.size())
				selectionItem = sessions.get(sessions.size() - 1);
			else
				selectionItem = sessions.get(index);
		}
		return;
	}

	public void joinableSelection(AjaxBehaviorEvent event) {
		selection((UIExtendedDataTable) event.getComponent(),
				joinableGameSessions);
	}

	public void createdSelection(AjaxBehaviorEvent event) {
		selection((UIExtendedDataTable) event.getComponent(), gameSessions);
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

	public String getSessionMessage(Player player, GameSession gameSession) {
		if (gameSession == null){
			System.out.println("getSessionMessage(): the gameSession is empty " );
			return "";
		}
		
		System.out.println("called getSessionMessage for session "
				+ gameSession.getId());

		return gameSessionService.getSessionMessage(player, gameSession);

	}

	public String getSessionMessage() {
		return getSessionMessage(loggedInPlayer.getPlayer(), selectionItem);

	}

	public String getOwnerSessionMessage(GameSession gameSession) {

		return getSessionMessage(gameSession.getCreator(), gameSession);
	}

	public void setSessionMessage(String sessionMessage) {
		gameSessionService.setSessionMessage(loggedInPlayer.getPlayer(),
				selectionItem, sessionMessage);
	}

	public List<Player> getJoinedPlayers() {

		if (selectionItem == null)
			return Collections.EMPTY_LIST;

		List<Player> players = gameSessionService.getJoinedPlayers(
				loggedInPlayer.getPlayer(), selectionItem);

		return players;
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
