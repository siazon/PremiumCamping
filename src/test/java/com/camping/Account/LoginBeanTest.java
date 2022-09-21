package com.camping.Account;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.camping.common.DataCache;
import com.camping.common.User;

class LoginBeanTest {

	private LoginBean loginBean;
	
	@BeforeEach
	void setUp() throws Exception {
		loginBean = new LoginBean();
	}

//	@Test
//	void testInit() {
//		assertEquals(0, registeredUsers.);
//		assertEquals(null, loginBean.getEmail());
//		assertEquals(null, loginBean.getEmail());
//	}

	@Test
	void testGetEmail() {
		loginBean.setEmail("abc@ait.ie");
		assertEquals("abc@ait.ie", loginBean.getEmail());
	}

	@Test
	void testSetEmail() {
		loginBean.setEmail("abc@ait.ie");
		assertEquals("abc@ait.ie", loginBean.getEmail());
		loginBean.setEmail("xyz@ait.ie");
		assertEquals("xyz@ait.ie", loginBean.getEmail());
	}

	@Test
	void testGetPassword() {
		loginBean.setPassword("123456");
		assertEquals("123456", loginBean.getPassword());
	}

	@Test
	void testSetPassword() {
		loginBean.setPassword("123456");
		assertEquals("123456", loginBean.getPassword());
		loginBean.setPassword("987654");
		assertEquals("987654", loginBean.getPassword());
	}

	@Disabled
	@Test
	void testValidateUserLoginForCustomer() {
		// Prepare users for store in data cache
		ArrayList<User> userList = new ArrayList<User>();
		userList.add(new User("lankanipw@gmail.com", "123456", "Customer", "L")); // customer
		userList.add(new User("a1234567@student.ait.ie", "123456", "Staff", "W")); // staff
		
		// store in data cache
		DataCache.getSingleton().setUserList(userList);
		
		// set login user info for correct login
		loginBean.setEmail("lankanipw@gmail.com");
		loginBean.setPassword("123456");
		
		// Try login
		String location = loginBean.validateUserLogin();
		
		// login should success and goto customer dashboard
		assertEquals("customerDashboard.xhtml?faces-redirect=true", location);
		
	}
		

}
