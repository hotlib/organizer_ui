package org.gameorganizer.ui;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named
@SessionScoped
public class GameSessionManager implements Serializable{
	List <GameSession> gameSessions = new LinkedList<GameSession>();
	
	
	private List <GameSession> getGameSessions() {
		gameSessions.clear();
		gameSessions.add(new GameSession());
		gameSessions.add(new GameSession());
		gameSessions.add(new GameSession());
		gameSessions.add(new GameSession());
		gameSessions.add(new GameSession());
		gameSessions.add(new GameSession());
		return gameSessions;
	}
	
}
