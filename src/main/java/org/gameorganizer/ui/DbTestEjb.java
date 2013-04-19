package org.gameorganizer.ui;

import javax.ejb.EJB;
import javax.inject.Named;


@Named
public class DbTestEjb {
	
	@EJB
	RealEjb realEjb;
	
	public void testDb(){

		realEjb.someCall();
		realEjb.someCall2();
	
		
	}
	
}
