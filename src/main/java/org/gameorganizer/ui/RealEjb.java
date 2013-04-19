package org.gameorganizer.ui;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.gameorganizer.ui.entity.Attendant;
import org.gameorganizer.ui.entity.GameSessionRelation;
import org.gameorganizer.ui.entity.Player;
import org.gameorganizer.ui.entity.GameSession;

//TODO zistit ako sa robia schemy
//zistit ako robit dialect pre postgresql
//zistit ci sa da on-the-fly vytvorit tabulka pre entitu

@Stateful
public class RealEjb {

	@PersistenceContext(unitName="testtest")
	private EntityManager entityManeger;
	
	private long id = 5;
	
	
	public void someCall() {
		Player p = new Player();
		p.setName("Jack");
		p.setEmail("aa@aa.com");
		p.setId(id);
		
		GameSession gs = new GameSession();
		gs.setCreator(p);
		gs.setId(id);
		gs.setPlace("aaa");
		
		Attendant a = new Attendant();
		a.setPlayer(p);
		a.setId(id);
		a.setRelation(GameSessionRelation.OWNER);
		a.setGameSession(gs);
		
		
		entityManeger.persist(p);
		entityManeger.persist(gs);
		entityManeger.persist(a);
		return;
	}
	
}
