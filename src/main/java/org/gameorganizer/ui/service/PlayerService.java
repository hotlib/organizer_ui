package org.gameorganizer.ui.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.gameorganizer.ui.entity.Player;

@Named("playerService")
@Stateless
public class PlayerService {
	@PersistenceContext(unitName="testtest")
	private EntityManager entityManager;
	
	public void registerPlayer(String email, String password, String nickName) {
		
		Player p = new Player();
		p.setEmail(email);
		p.setNickName(nickName);
		p.setPassword(password);
		
		entityManager.persist(p);
	}
		
	public boolean checkEmail(String email) {

		Query query = entityManager.createQuery("SELECT x FROM Player x");
		List <Player> players = query.getResultList();
		
		for (Player player : players) {
			if(player.getEmail().equals(email))
				return true;
		}
		
		return false;
	}

	public boolean checkNickname(String nickName)  {

		Query query = entityManager.createQuery("SELECT x FROM Player x");
		List <Player> players = query.getResultList();
		
		for (Player player : players) {
			if(player.getNickName().equals(nickName))
				return true;
		}
		
		return false;
	}
	
}
