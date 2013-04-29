package org.gameorganizer.ui.view;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

import org.gameorganizer.ui.entity.GameSession;
import org.gameorganizer.ui.entity.Player;
import org.gameorganizer.ui.service.GameSessionService;
import org.richfaces.component.UIExtendedDataTable;

public class GameSessionController implements Serializable {

	
	protected GameSession selectionItem;
	
	@Inject
	protected LoggedInPlayer loggedInPlayer;
	
	@EJB
	protected GameSessionService gameSessionService;
	
	protected List<GameSession> gameSessions = new LinkedList<GameSession>();
	
	public List<Player> getJoinedPlayers() {
		return getJoinedPlayers(selectionItem);
	}
	
	public List<Player> getJoinedPlayers(GameSession gameSession) {

		if (gameSession == null)
			return new LinkedList<Player>();

		List<Player> players = gameSessionService.getJoinedPlayers(
				loggedInPlayer.getPlayer(), gameSession);

		System.out.println("players that joined: " + gameSession.getPlace() + " id: " + gameSession.getId());
		
		for (Player player : players) {
			System.out.println(player.getNickName());
		}
		
		return players;
	}
	
	public String getSessionMessage(Player player, GameSession gameSession) {
		if (gameSession == null) {
			System.out
					.println("getSessionMessage(): the gameSession is empty ");
			return "";
		}

		System.out.println("called getSessionMessage for session "
				+ gameSession.getId());

		return gameSessionService.getSessionMessage(player, gameSession);

	}
	
	public String getOwnerSessionMessage(GameSession gameSession) {

		return getSessionMessage(gameSession.getCreator(), gameSession);
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
	
	public void tableSelection(AjaxBehaviorEvent event) {
		selection((UIExtendedDataTable) event.getComponent(),
				gameSessions);
	}
	
	public GameSession getSelectionItem() {
		return selectionItem;
	}
	
	public void setSelectionItem(GameSession selectionItem) {
		this.selectionItem = selectionItem;
	}
}
