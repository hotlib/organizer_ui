package org.gameorganizer.ui;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@Named
public class DbTestEjb {

	public void testDb(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("testtest");
	}
	
}
