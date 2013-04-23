package org.gameorganizer.ui;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.gameorganizer.ui.entity.Attendant;
import org.gameorganizer.ui.entity.GameSession;
import org.gameorganizer.ui.entity.GameSessionRelation;
import org.gameorganizer.ui.entity.Player;


@Stateful
public class RealEjb {

	@PersistenceContext(unitName="testtest")
	private EntityManager entityManeger;
	
	private long id = 6;
	
	
	public GameSession someCall() {
//		Set <Attendant> h = new HashSet<Attendant>();
//		Player p = new Player();
//		p.setNickName("Jack");
//		p.setEmail("aa@aa.com");
//		p.setId(id);
//		
//		GameSession gs = new GameSession();
//		gs.setId(id);
//		gs.setPlace("aaa");
//		
//		Attendant a = new Attendant();
//		a.setPlayer(p);
//		a.setId(id);
//		a.setRelation(GameSessionRelation.OWNER);
//		a.setGameSession(gs);
//		
//		h.add(a);
//		gs.setAttendants(h);
//		//p.setAttendants(h);
//		
//		entityManeger.persist(p);
//		entityManeger.persist(gs);
		return null;
	}
	
	public void someCall2(GameSession gs) {
//		Attendant a = gs.getAttendants().iterator().next();
//		a.setRelation(GameSessionRelation.JOINER);
//		Set <Attendant> h = new HashSet<Attendant>();
//		h.add(a);
//		gs.setAttendants(h);
//		entityManeger.merge(gs);
		return;
	}
	
	
}
