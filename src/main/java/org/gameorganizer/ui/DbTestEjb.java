package org.gameorganizer.ui;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.inject.Named;


@Named
public class DbTestEjb implements Serializable {
	
	@EJB
	RealEjb realEjb;
	
	public void testDb(){

		
		realEjb.someCall2(realEjb.someCall());
	
		
	}
	
}
