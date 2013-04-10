package org.gameorganizer.ui;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

@Named
@SessionScoped
public class HelloBean  implements Serializable{
	private String msg = "ako sa mas";
	private String loginStatus = "";

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;

	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void loginUser() {
		this.loginStatus = "vsetk ok";
	}
	
	public String changeHappened(ActionEvent event){
		return "sssssssss";
	}

}
