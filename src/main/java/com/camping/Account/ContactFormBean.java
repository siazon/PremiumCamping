package com.camping.Account;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.camping.utility.SendEmail;

/*
 * author: Aleksandra Marjanovic
 * A00303157
 */

@ManagedBean(name = "ContactFormBean")
@ViewScoped
public class ContactFormBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userName;
	private String email;
	private String subject;
	private String message;
	private String companyMail = "aleksandra.micunovic25@gmail.com";
	
	public ContactFormBean() {
	
	} 
	
	public ContactFormBean(String userName, String email, String subject, String message, String companyMail) {
		super();
		this.userName = userName;
		this.email = email;
		this.subject = subject;
		this.message = message;
		this.companyMail = companyMail;
	}

	public ContactFormBean(String userName, String email, String subject, String message) {
		super();
		this.userName = userName;
		this.email = email;
		this.subject = subject;
		this.message = message;
	}
	
	public String getCompanyMail() {
		return companyMail;
	}

	public void setCompanyMail(String companyMail) {
		this.companyMail = companyMail;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void sendEmail() {
		new Thread(new Runnable() {// new Thread for reduce delay
			public void run() {
				try {
					SendEmail.Send(companyMail, "From contact form", "<p> Sender email: </p>" + email +
							 "<p>Name :</p>" + userName + "<p> Subject:</p>" + subject + "<p> Mesagge: </p>" + message);
					System.out.println("Contact form sent to email: " + companyMail);
				} catch (Exception ex) {
					System.out.println("Could not send an email to : " + companyMail + " Error thrown: " + ex.getMessage());
				}
			}
		}).start();

		userName = null;
		email = null;
		subject = null;
		message = null;
		FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
				"Sending email with your query, we will come back to you soon. If answer is not received, please contact us via phone number."));
		RequestContext.getCurrentInstance().update("messages");
		
	}
	
}
