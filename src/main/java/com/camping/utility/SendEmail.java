package com.camping.utility;

/**
* @author Xiasong Chen A00291322

* @date 28 Jul 2022 23:03:48
* @version 1.0
*/

import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	final static String myEmailAccount = "siazonchen@163.com";
	final static String myEmailPassword = "FVHBORTXYCMKODVM";

	final static String myEmailSMTPHost = "smtp.163.com";

	//
//	public static void main(String[] args) throws Exception {
//		SendEmail("xxx@gmail.com", "Reset password", "I can'g find my password");
//	}

	public static void Send(String email, String subject, String content) throws Exception {
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.host", myEmailSMTPHost);
		props.setProperty("mail.smtp.auth", "true");

		Session session = Session.getInstance(props);

		MimeMessage message = createMimeMessage(session, myEmailAccount, email, subject, content);

		Transport transport = session.getTransport();

		transport.connect(myEmailAccount, myEmailPassword);
		transport.sendMessage(message, message.getAllRecipients());
		System.out.println("Email sent successfully");
		transport.close();
	}

	/**
	 * 
	 *
	 * @param session
	 * @param sendMail
	 * @param receiveMail
	 * @return
	 * @throws Exception
	 */
	static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail, String subject,
			String Content) throws Exception {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(sendMail, "The Sprinter", "UTF-8"));
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "Dear Customer", "UTF-8"));
		message.setSubject(subject, "UTF-8");
		message.setContent(Content, "text/html;charset=UTF-8");
		message.setSentDate(new Date());
		message.saveChanges();
		return message;
	}
}