package com.camping.Account;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.camping.common.DataCache;
import com.camping.common.User;
import com.camping.utility.SendEmail;
import com.camping.utility.Utilitys;

/*
 * This example shows how to login for a single user with fixed username and password
 */
@ManagedBean(name = "RegistrationBean")
@SessionScoped
public class RegistrationBean implements Serializable {
	String email, password, password1, verificationCode;

	public RegistrationBean() {
	}

	public RegistrationBean(String email, String password) {
		super();
		this.email = email;
		this.password = password;
		this.verificationCode = null;
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

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public String Register() {
		// verification email
		if (!Utilitys.checkEmailFormat(email)) {
			requestCallback("Invalid EmailFormat");
			return (null);
		}
		// verification password
		if (password == null || password.length() < 5) {
			requestCallback("Invalid password(more than 5 chars)");
			return (null);
		}
		// verification password
		if (!password.equals(password1)) {
			requestCallback("password mismatch");
			return (null);
		}
		// verification verification code
		if (verificationCode == null || verificationCode.equalsIgnoreCase("")
				|| !verificationCode.equalsIgnoreCase(code)) {
			requestCallback("The verification code is incorrect.");
			return (null);
		}
		String role = "Customer";
		if (email.indexOf("ait.ie") >= 0)
			role = "Staff";
		boolean isSucceeded = addUser(new User(email, password, role, "Xiasong Chen"));
		if (isSucceeded) {
			code = null;
			requestCallback("user List: " + DataCache.getSingleton().getUserList().toString());
			requestCallback("OK");
			return (null);
		} else {
			requestCallback("Email address already exists");
			return (null);
		}
	}

	public void requestCallback(String msg) {
		if (!msg.equals("OK")) {
			// callback to h:message
			FacesContext context = findContext();
			FacesMessage errorMessage = new FacesMessage(msg);
			errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage("signupMsg", errorMessage);
		}
		// callback to Javascript
		RequestContext requestContext = findRequestContext();
		requestContext.addCallbackParam("msg", msg);
	}

	FacesContext findContext() {
		return FacesContext.getCurrentInstance();
	}

	public boolean addUser(User user) {
		ArrayList<User> userList = DataCache.getSingleton().getUserList();
		boolean isExist = false;
		for (User item : userList) {
			if (item.getEmail().equals(user.getEmail()))
				isExist = true;
		}
		if (isExist) {
			return false;// fail becouse user already exists
		} else {
			userList.add(user);
			DataCache.getSingleton().setUserList(userList);
			System.out.println("size:" + DataCache.getSingleton().getUserList().size());
			return true;// add successfully
		}
	}

	String code = "";

	public String CreateVerificationCode() throws Exception {
		System.out.println("Code sended to you email");
//		FacesContext context = FacesContext.getCurrentInstance();
//		FacesMessage errorMessage = new FacesMessage("Code sent to you email");
//		errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
//		context.addMessage("isValid", errorMessage);
		code = "" + (int) (Math.random() * 9000 + 1000);

		RequestContext requestContext = findRequestContext();
		requestContext.addCallbackParam("msg", code);

		new Thread(new Runnable() {// new Thread for reduce delay
			public void run() {
				try {

					SendEmail.Send(email, "Verification Code", "Dear Customer,<p> Use code " + code
							+ " to complete the step. </p><p>Thanks</p> Premium Camping");
				} catch (Exception ex) {
				}
			}
		}).start();
		return code;
	}

	RequestContext findRequestContext() {
		return RequestContext.getCurrentInstance();
	}

}
