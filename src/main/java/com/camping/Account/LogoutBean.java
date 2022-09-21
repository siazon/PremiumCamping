package com.camping.Account;

/**
 * @author Kate Goode A00290477
 * 
 * @date 01 Aug 2022 10:35:36
 * @version 1.0
 */

import java.io.Serializable;

//import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import com.camping.common.DataCache;

//import com.camping.common.DataCache;

@ManagedBean(name = "LogoutBean")
@SessionScoped
public class LogoutBean implements Serializable {

	// private String message;

	private static final long serialVersionUID = 1L;
	
	
	/*
	 * public void confirm() { addMessage("Confirmed", "You have logged out"); }
	 */


	public String logout() {
		// System.out.println("Successfully logged out");

		// callback to Javascript
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.addCallbackParam("msg", "OK");
		System.out.println("Logout");
		
		/* addMessage("Confirmed", "You have logged out"); */

		// invalidate the user's session
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		// message to appear if successfully logged out

//		addMessage("You are successfully logged out");

		DataCache.getSingleton().setLoginedUserEmail(null);
		// link back to login page
		return "/login.xhtml?faces-redirect=true";
	}

	/*
	 * public void addMessage(String summary, String detail) { FacesMessage message
	 * = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
	 * FacesContext.getCurrentInstance().addMessage(null, message); }
	 */

}
