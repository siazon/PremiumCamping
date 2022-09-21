package com.camping.Account;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.faces.context.FacesContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.primefaces.context.RequestContext;

import com.camping.common.User;

/**
 * @author Xiasong Chen A00291322
 * 
 * @date 31 Jul 2022 19:49:25
 * @version 1.0
 */
class RegistrationBeanTest {

	RegistrationBean registration;

	@Mock
	private RequestContext requestContext;

	@BeforeEach
	void setUp() throws Exception {
		registration = new RegistrationBean("siazonchen@gmail.com", "qwe") {
			@Override
			RequestContext findRequestContext() {
				RequestContext requestContext = Mockito.mock(RequestContext.class);
				return requestContext;
			}

			@Override
			FacesContext findContext() {
				FacesContext context = Mockito.mock(FacesContext.class);
				return context;
			}
		};
	}

	@Test
	void testRequestCallback() {
		registration.requestCallback("OK");
	}

	@Test
	void testRegister() throws Exception {

		String code = registration.CreateVerificationCode();
		registration.setVerificationCode(code);
		assertEquals(null, registration.Register());
	}

	@Test
	void testRegisterErrEmail() throws Exception {
		registration.setEmail("test");
		String code = registration.CreateVerificationCode();
		registration.setVerificationCode(code);
		assertEquals(null, registration.Register());
	}

	@Test
	void testRegisterErrPWD() throws Exception {
		registration.setPassword1("qwe123456");
		registration.setPassword("qwe12345");
		String code = registration.CreateVerificationCode();
		registration.setVerificationCode(code);
		assertEquals(null, registration.Register());
	}

	@Test
	void testRegisterErrCode() throws Exception {
		registration.setPassword1("qwe123456");
		registration.setPassword("qwe123456");
		assertEquals(null, registration.Register());
	}

	@Test
	void testRegisterexsist() throws Exception {
		registration.setPassword1("qwe123");
		registration.setPassword("qwe123");
		String code = registration.CreateVerificationCode();
		registration.setVerificationCode(code);
		assertEquals("Email address already exists", registration.Register());
	}

	@Test
	void testRegisterCode() throws Exception {
		registration.setEmail("q@qq.com");
		registration.setPassword1("qwe123");
		registration.setPassword("qwe123");
		String code = registration.CreateVerificationCode();
		registration.setVerificationCode(code);
		assertEquals(null, registration.Register());
	}

	@Test
	void testRegisterCodestaff() throws Exception {
		registration.setEmail("q@ait.com");
		registration.setPassword1("qwe123");
		registration.setPassword("qwe123");
		String code = registration.CreateVerificationCode();
		registration.setVerificationCode(code);
		assertEquals(null, registration.Register());
	}

	@Test
	void testAddUser() {
		User user = new User("siazonchen@gmail.com", "qwe", null, "Chen");
		boolean result = registration.addUser(user);
		assertEquals(true, result);
	}

	@Test
	void testCreateVerificationCode() {

	}

}
