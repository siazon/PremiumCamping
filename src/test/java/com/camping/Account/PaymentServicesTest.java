package com.camping.Account;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.paypal.base.rest.PayPalRESTException;
/*
 * Aleksandra Marjanovic A00303157.
 */
class PaymentServicesTest {

	PaymentServices paymentServices;
	
	@Test
	void testAuthorizePayment() {
		paymentServices = new PaymentServices();
		String authorizationResult = "";
		OrderDetailsBean orderDetail = new OrderDetailsBean("123", "Tent", 200 );
		try {
			authorizationResult = paymentServices.authorizePayment(orderDetail);
			System.out.println("Authorization result is: " + authorizationResult);
		} catch (PayPalRESTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean containsProperResponse = authorizationResult.contains("https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=");
		assertTrue(containsProperResponse);
	}

}


