package org.gameorganizer.ui;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

import org.richfaces.component.UIExtendedDataTable;

@Named
@SessionScoped
public class GameSessionManager implements Serializable {
	private List<GameSession> gameSessions = new LinkedList<GameSession>();
	private GameSession selectionItem;
	private String dummy = "notin";
	
	
	
	public String getDummy() {
		return dummy;
	}

	public void setDummy(String dummy) {
		this.dummy = dummy;
	}

	public int getLastIndex() {
		return gameSessions.isEmpty() ? 0 : gameSessions.size() - 1;
	}

	public void setLastIndex(int lastIndex) {
	}

	public GameSession getSelectionItem() {
		return selectionItem;
	}

	public void setSelectionItem(GameSession selectionItem) {
		System.out.println("choosing " + selectionItem.getUsername());
		this.selectionItem = selectionItem;
	}

	public void selection(AjaxBehaviorEvent event) {
		if(gameSessions.isEmpty())
			return;
		
		UIExtendedDataTable dataTable = (UIExtendedDataTable) event
				.getComponent();
		if (dataTable.getSelection() != null && !dataTable.getSelection().isEmpty()) {
			Integer index = (Integer) dataTable.getSelection().iterator()
					.next();

			if (index >= gameSessions.size())
				selectionItem = gameSessions.get(gameSessions.size() - 1);
			else
				selectionItem = gameSessions.get(index);
		}
		return;
	}

	public List<GameSession> getGameSessions() {
		if (gameSessions.isEmpty())
			init();

		return gameSessions;
	}

	private void init() {
		List<Player> joinedUsers = new LinkedList<Player>();
		joinedUsers.add(new Player());
		joinedUsers.add(new Player());
		joinedUsers.add(new Player());
		joinedUsers.add(new Player());

		GameSession tmp;

		for (int i = 0; i < 8; i++) {
			tmp = new GameSession("A" + i);
			tmp.setJoinedUsers(joinedUsers);
			gameSessions.add(tmp);
		}

	}

	public String editItem(GameSession session) {
		return null;
	}

	public Boolean isJoined(GameSession session) {
		return session.getJoined();

	}

	public String flipJoined(GameSession session) {
		session.flipJoined();
		return null;
	}

	public void deleteSession(GameSession session) {
		gameSessions.remove(session);
		selectionItem = null;
	}

	public void createSession() {
		GameSession g = new GameSession("XXX");
		gameSessions.add(g);
		selectionItem = g;
	}
	
	public void save() {
		System.out.println("called save");

	}
	
}
