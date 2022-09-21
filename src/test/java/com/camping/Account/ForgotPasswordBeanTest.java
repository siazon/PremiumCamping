package com.camping.Account;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.camping.common.DataCache;
import com.camping.common.User;

/*
 * Test for class ForgotPassword.java
 * @Author Aleksandra Marjanovic A00303157
 */
class ForgotPasswordBeanTest {

	ForgotPasswordBean forgotPasswordBean;

	@BeforeEach
	void setUp() throws Exception {
		forgotPasswordBean = new ForgotPasswordBean();
	}
	
	@Test
	void testForgotBeanFields() {
		forgotPasswordBean.setEmail("alex.micun25@gmail.com");
		forgotPasswordBean.setPassword("12345");
		forgotPasswordBean.setVerificationCode("6789");
		assertEquals("alex.micun25@gmail.com", forgotPasswordBean.getEmail());
		assertEquals("12345", forgotPasswordBean.getPassword());
		assertEquals("6789", forgotPasswordBean.getVerificationCode());

	}

	@Test
	void testSendEmailIsNull() {
		forgotPasswordBean.setEmail(null);
		try {
			forgotPasswordBean.sendEmail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		assertFalse(forgotPasswordBean.isVerificationVisible());
	}

	@Test
	void testSendEmailIsEmpty() {
		forgotPasswordBean.setEmail("");
		try {
			forgotPasswordBean.sendEmail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		assertFalse(forgotPasswordBean.isVerificationVisible());
	}

	@Test
	void testSendEmailNoUserContainsProvidedEmail() {
		forgotPasswordBean.setEmail("aleksandra25@gmail.com");
		try {
			forgotPasswordBean.sendEmail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		assertFalse(forgotPasswordBean.isVerificationVisible());
	}

	@Test
	void testSendEmailSuccess() {
		User testUser = new User("aleksandra@gmail.com", "testPassword", "administrator", "Aleksandra");
		ArrayList<User> testUserList = new ArrayList<>(Arrays.asList(testUser));
		DataCache.getSingleton().setUserList(testUserList);
		forgotPasswordBean.setEmail("aleksandra@gmail.com");
		try {
			forgotPasswordBean.sendEmail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		assertTrue(forgotPasswordBean.isVerificationVisible());
	}

	@Test
	void testBackToSendEmailForm() {
		forgotPasswordBean.setVerificationVisible(true);
		try {
			forgotPasswordBean.backToSendEmailForm();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		assertFalse(forgotPasswordBean.isVerificationVisible());
	}

	@Test
	void updatePasswordVerificationCodeEmpty() {
		forgotPasswordBean.setVerificationVisible(true);
		forgotPasswordBean.generatedCode = "1234";
		forgotPasswordBean.setVerificationCode("");
		forgotPasswordBean.setPassword("testPassword");
		forgotPasswordBean.setPasswordRepeated("testPassword");
		try {
			forgotPasswordBean.updatePassword();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		assertTrue(forgotPasswordBean.isVerificationVisible());
	}

	@Test
	void updatePasswordWrongCode() {
		forgotPasswordBean.setVerificationVisible(true);
		forgotPasswordBean.setPassword("testPassword");
		forgotPasswordBean.setPasswordRepeated("testPassword");
		forgotPasswordBean.generatedCode = "1234";
		forgotPasswordBean.setVerificationCode("12345");
		try {
			forgotPasswordBean.updatePassword();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		assertTrue(forgotPasswordBean.isVerificationVisible());
	}

	@Test
	void updatePasswordShortPassword() {
		forgotPasswordBean.setVerificationVisible(true);
		forgotPasswordBean.generatedCode = "1234";
		forgotPasswordBean.setVerificationCode("1234");
		forgotPasswordBean.setPassword("alex");
		forgotPasswordBean.setPasswordRepeated("alex");
		try {
			forgotPasswordBean.updatePassword();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		assertTrue(forgotPasswordBean.isVerificationVisible());
	}

	@Test
	void updatePasswordMismatch() {
		forgotPasswordBean.setVerificationVisible(true);
		forgotPasswordBean.generatedCode = "1234";
		forgotPasswordBean.setVerificationCode("1234");
		forgotPasswordBean.setPassword("12345");
		forgotPasswordBean.setPasswordRepeated("54321");
		try {
			forgotPasswordBean.updatePassword();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		assertTrue(forgotPasswordBean.isVerificationVisible());
	}

	@Test
	void updatePasswordSuccess() {
		forgotPasswordBean.setVerificationVisible(true);
		forgotPasswordBean.generatedCode = "1234";
		forgotPasswordBean.setVerificationCode("1234");
		forgotPasswordBean.setPassword("testPassword");
		forgotPasswordBean.setPasswordRepeated("testPassword");
		try {
			forgotPasswordBean.updatePassword();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		assertFalse(forgotPasswordBean.isVerificationVisible());
	}
}
