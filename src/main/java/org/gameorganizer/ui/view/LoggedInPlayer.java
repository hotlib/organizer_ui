package org.gameorganizer.ui.view;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.gameorganizer.ui.entity.Player;
import org.gameorganizer.ui.service.PlayerService;

@Named
@SessionScoped
public class LoggedInPlayer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7038299497222105330L;

	private Player player;

	@EJB
	PlayerService playerseService;
	
	public Player getPlayer() {
		if(player == null){
			String login = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
			player = playerseService.getPlayer(login);
		}
		
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
