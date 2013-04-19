package org.gameorganizer.ui;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


//TODO zistit ako sa robia schemy
//zistit ako robit dialect pre postgresql
//zistit ci sa da on-the-fly vytvorit tabulka pre entitu

@Stateless
public class RealEjb {

	@PersistenceContext(unitName="testtest")
	private EntityManager entityManeger;
	
	
	public void someCall() {
		Person p = new Person(1l, "test");
		entityManeger.persist(p);
		
		return;
	}
	
}
