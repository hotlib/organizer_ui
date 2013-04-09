package org.gameorganizer.ui;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AbortProcessingException;
import javax.inject.Named;

import org.richfaces.event.ItemChangeEvent;
import org.richfaces.event.ItemChangeListener;


@Named
@SessionScoped
public class MainMenu implements ItemChangeListener, Serializable {
	private String currentPage = "create_session";

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	@Override
	public void processItemChange(ItemChangeEvent arg0)
			throws AbortProcessingException {
		currentPage = arg0.getNewItemName();
		
	}
	
}
