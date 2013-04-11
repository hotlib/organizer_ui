package org.gameorganizer.ui;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

import org.richfaces.component.UIExtendedDataTable;


@Named
@SessionScoped
public class GameSessionManager implements Serializable{
	private List <GameSession> gameSessions = new LinkedList<GameSession>();
	private Object selectionItem;
	
	
	
	
	public Object getSelectionItem() {
		return selectionItem;
	}


	public void setSelectionItem(Object selectionItem) {
		this.selectionItem = selectionItem;
	}


	public void selection(AjaxBehaviorEvent event) {
	    UIExtendedDataTable dataTable = (UIExtendedDataTable) event.getComponent();
	    if(!dataTable.getSelection().isEmpty()){
	    	Integer index = (Integer) dataTable.getSelection().iterator().next();
	    	selectionItem = gameSessions.get(index);
	    }
       return;
	}

	
	public List <GameSession> getGameSessions() {
		if(gameSessions.isEmpty())
			init();
		
		return gameSessions;
	}
	
	private void init() {
		gameSessions.add(new GameSession("A"));
		gameSessions.add(new GameSession("B"));
		gameSessions.add(new GameSession("C"));
		gameSessions.add(new GameSession("D"));
		gameSessions.add(new GameSession("E"));
		gameSessions.add(new GameSession("F"));
		
	}

	public String editItem(GameSession session){
		return null;
	}
	
	private Boolean isJoined(GameSession session) {
		return session.getJoined();

	}
	
	private String flipJoined(GameSession session) {
		session.flipJoined();
		return null;
	}

	
	
}
