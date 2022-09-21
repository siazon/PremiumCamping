package com.camping.Account;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.camping.Account.ContactFormBean;
/*
 * Aleksandra Marjanovic 
 * A00303157
 * 
 */
class ContactFormBeanTest {
 ContactFormBean contactForm ;
 
 	@BeforeEach
 	void startUp () {
 		contactForm = new ContactFormBean();
 	}
 	@Test
	void testConstructor() {
	ContactFormBean contactBean = new ContactFormBean ("Alex","zana86@gmai.com","Question","Hello","aleksandra.micunovic25@gmail.com" );
	assertEquals("Alex", contactBean.getUserName());
	assertEquals("zana86@gmai.com", contactBean.getEmail());
	}
 	
 	@Test 
 	void testConstructorTwo () {
 		ContactFormBean contactBean = new ContactFormBean ("Alex","zana86@gmai.com","Question","Hello" );
 		assertEquals("Alex", contactBean.getUserName());
 		assertEquals("zana86@gmai.com", contactBean.getEmail());
 	}
 
	@Test
	void testGetterAndSetter() {
		contactForm.setEmail("zana86@gmai.com");
		contactForm.setCompanyMail("aleksandra.micunovic25@gmail.com");
		contactForm.setMessage("Hello");
		contactForm.setUserName("Alex");
		contactForm.setSubject("Question");
		assertEquals("zana86@gmai.com", contactForm.getEmail());
		assertEquals("aleksandra.micunovic25@gmail.com", contactForm.getCompanyMail());
		assertEquals("Hello", contactForm.getMessage());
		assertEquals("Alex", contactForm.getUserName());
		assertEquals("Question", contactForm.getSubject());
	}
	
	@Test
	void sentEmailTest() {
		contactForm.setEmail("zana86@gmai.com");
		contactForm.setCompanyMail("aleksandra.micunovic25@gmail.com");
		contactForm.setMessage("Hello");
		contactForm.setUserName("Alex");
		contactForm.setSubject("Question");
		try {
			contactForm.sendEmail();
		} catch (Exception e) {
			// TODO: handle exception
		}
		assertEquals(null, contactForm.getEmail());
		assertEquals(null, contactForm.getMessage());
		assertEquals(null, contactForm.getUserName());
		assertEquals(null, contactForm.getSubject());
		
	}
	

}
