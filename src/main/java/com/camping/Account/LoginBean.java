package com.camping.Account;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import com.camping.common.DataCache;
import com.camping.common.User;

/**
 * 
 * @author Lankani Wijesinghe A00267225
 *
 */
@ManagedBean(name = "LoginBean")
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	String email, password;

	ArrayList<User> registeredUsers;

	@PostConstruct
	public void init() {
		registeredUsers = DataCache.getSingleton().getUserList();

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// User validation and direct to the relevant dashboard, if login is successful
	public String validateUserLogin() {
		User loginUser = getUser(email);
		if (loginUser != null && loginUser.getPassword().equals(password)) {
			// Login successful
			// Store user name in session
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);
			session.setAttribute("username", loginUser.getName());

			System.out.println("User " + session.getAttribute("username") + "(" + loginUser.getEmail()
					+ ") login to the system as " + loginUser.getRole());

			String location = null;
			String userRole = loginUser.getRole();
			DataCache.getSingleton().setLoginedUserEmail(email);
			switch (userRole) {
			case "Staff":
				// Goto staff dashboard
				location = "staffDashboard.xhtml?faces-redirect=true&isStaff=true";
				break;
			case "Customer":
				// Goto customer dashboard
				location = "customerDashboard.xhtml?faces-redirect=true&isStaff=false";
				break;
			default:
				// Goto home page as default
				location = "index.xhtml";
				break;
			}

			return location;

		} else {
			// Login failed
			System.out.println("User login failed");
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage errorMessage = new FacesMessage("Invalid email or password combination");
			errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage("errorMsg", errorMessage);
			// callback to Javascript
			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.addCallbackParam("msg", "Error");
			return (null);

		}
	}

	// Get the user by email
	private User getUser(String email) {
		User result = null;
		for (User user : registeredUsers) {
			if (user.getEmail().equals(email)) {
				result = user;
			}
		}
		return result;
	}

}
