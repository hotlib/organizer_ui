package org.gameorganizer.ui;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named()
@RequestScoped
public class HelloBean {
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

}
