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
	private int lastIndex = 3;

	public int getLastIndex() {
		return lastIndex;
	}


	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}


	public GameSession getSelectionItem() {
		return selectionItem;
	}

		
	public void setSelectionItem(GameSession selectionItem) {
		this.selectionItem = selectionItem;
	}

	public void selection(AjaxBehaviorEvent event) {
		UIExtendedDataTable dataTable = (UIExtendedDataTable) event
				.getComponent();
		if (!dataTable.getSelection().isEmpty()) {
			Integer index = (Integer) dataTable.getSelection().iterator()
					.next();
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
		List<String> joinedUsers = new LinkedList<String>();
		joinedUsers.add("aaa");
		joinedUsers.add("bbb");
		joinedUsers.add("ccc");
		joinedUsers.add("ddd");

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
		lastIndex = gameSessions.isEmpty() ? 0 : gameSessions.size() - 1;
		selectionItem = g;
	}
}
