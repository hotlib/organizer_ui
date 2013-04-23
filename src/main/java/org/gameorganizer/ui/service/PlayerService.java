package org.gameorganizer.ui.service;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.gameorganizer.ui.entity.Player;

@Named("playerService")
@Stateless
public class PlayerService {
	@PersistenceContext(unitName="testtest")
	private EntityManager entityManager;
	
	public void registerPlayer(Player player) {
		entityManager.persist(player);
	}
	
	
	
}
