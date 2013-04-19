package org.gameorganizer.ui;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


//TODO zistit ako sa robia schemy
//zistit ako robit dialect pre postgresql
//zistit ci sa da on-the-fly vytvorit tabulka pre entitu

@Stateful
public class RealEjb {

	@PersistenceContext(unitName="testtest")
	private EntityManager entityManeger;
	
	private long id = 5;
	
	
	public void someCall() {
		id++;
		Person p = new Person(id, "test");
		entityManeger.persist(p);
		
		return;
	}
	
}
