package com.camping.Account;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Test for class ForgotPassword.java
 * @Author Kate Goode A00290477
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LogoutBeanTest {
	LogoutBean logout;
	LoginBean loginBean;
	@BeforeEach
	void setUp() throws Exception {
		logout = new LogoutBean();
	}

//	@Test
//	void testAddMessage() {
//		String message = logout.addMessage(null);
//	}

	@Test
	void testLogout() {
		/*
		 * String location = loginBean.validateUserLogin();
		 * 
		 * // login should success and goto customer dashboard
		 * assertEquals("login.xhtml?faces-redirect=true", location);
		 */
		assertEquals("/login.xhtml?faces-redirect=true",logout.logout());
	}

}
