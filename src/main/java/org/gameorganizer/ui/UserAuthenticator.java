package org.gameorganizer.ui;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@SessionScoped
public class UserAuthenticator implements Serializable {

	private static final long serialVersionUID = 3670457094853881924L;
	private String username = "fritz ";
	private String password;
	private Boolean isLoggedIn = Boolean.FALSE;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public Boolean getIsLoggedIn() {
		return isLoggedIn;
	}

	public void setIsLoggedIn(Boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String authenticateUser() {
		isLoggedIn = !isLoggedIn;
		return null;
	}

	public String logOut() {
	
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession httpSession = (HttpSession) facesContext
				.getExternalContext().getSession(false);
		httpSession.invalidate();
	
		return "welcome.xhtml?faces-redirect=true";
	}

}
