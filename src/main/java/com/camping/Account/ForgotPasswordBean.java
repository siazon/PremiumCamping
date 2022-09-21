package com.camping.Account;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.primefaces.context.RequestContext;

import com.camping.common.DataCache;
import com.camping.common.User;
import com.camping.utility.SendEmail;

/*
 * Sending Email with verification code and using that code to verify user and update his password
 * author Aleksandra Marjanovic
 *  A00303157
 */
@ManagedBean
@ViewScoped
public class ForgotPasswordBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private boolean verificationVisible = false;
	private String email = "";
	private String verificationCode = "";
	private String password;
	private String passwordRepeated;
	String generatedCode;
	
	public ForgotPasswordBean() {
		System.out.println("Constructor initiated.");}

	public boolean isVerificationVisible() {
		return verificationVisible;
	}

	public void setVerificationVisible(boolean verificationVisible) {
		this.verificationVisible = verificationVisible;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
	
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordRepeated() {
		return passwordRepeated;
	}
	public void setPasswordRepeated(String passwordRepeated) {
		this.passwordRepeated = passwordRepeated;
	}
		
	public void sendEmail() throws Exception {
		if (email == null || email.equals("")) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Please enter valid email."));
			return;
		}
		
		ArrayList<User> userList = DataCache.getSingleton().getUserList();
		boolean emailExists = false;
		for (User user : userList) {
			if (user.getEmail().equals(email)) emailExists = true;
		}
//		emailExists = true; // delete this line, it is only for testing without user
		if (!emailExists) {
			FacesContext.getCurrentInstance().addMessage("emailMessage", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Please enter valid email."));
		} else {
			generatedCode = "" + (int) (Math.random() * 9000 + 1000);
			System.out.println("Generated code is: " + generatedCode);	// delete this line, it is only for testing
			
			new Thread(new Runnable() {// new Thread for reduce delay
				public void run() {
					try {
						SendEmail.Send(email, "Verification Code", "Dear Customer,<p> Use code " + generatedCode
								+ " to complete the step. </p><p>Thanks</p> Premium Camping");
						System.out.println("Verification Code sent to email: " + email);
					} catch (Exception ex) {
						System.out.println("Could not send an email to : " + email + " Error thrown: " + ex.getMessage());
					}
				}
			}).start();
			
			setVerificationVisible(true);
			FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
					"Sending email with verification code, please check your email. If verification code is not received, please retry operation."));
			RequestContext.getCurrentInstance().update("firstForm");
		}	
	}

	public void backToSendEmailForm() {
		System.out.println("Back to resend the code");
		setVerificationVisible(false);
		RequestContext.getCurrentInstance().update("secondForm");
	}
	
	public String updatePassword() {
		if (verificationCode.equals("") || !verificationCode.equalsIgnoreCase(generatedCode)) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Wrong verification code"));
			return "";
		}
		if (!isPasswordValid(password)) {
			FacesContext.getCurrentInstance().addMessage("messages", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Password have to contain at least 5 characters."));
			return "";
		}
		if (!password.equals(passwordRepeated)) {
			FacesContext.getCurrentInstance().addMessage("messages", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Passwords mismatch. Please retype passwords."));
			return "";
		}

		ArrayList<User> userList = DataCache.getSingleton().getUserList();
		for (User user : userList) {
			if (user.getEmail().equals(email)) {
				user.setPassword(password);
			}
		}
		DataCache.getSingleton().setUserList(userList);

		setVerificationVisible(false);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Flash flash = facesContext.getExternalContext().getFlash();
		flash.setKeepMessages(true);
		facesContext.addMessage("errorMsg", 
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Success! Password was successfully updated.", ""));
		return "login?faces-redirect=true"; 
	}

	private boolean isPasswordValid(String password) {
		return password.length() > 4;
	}
	
}
