package com.camping.Account;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/*
 * Aleksandra Marjanovic 
 * A00303157
 */
class OrderDetailsBeanTest {
	
	OrderDetailsBean orderDetail;
	
	@BeforeEach
	void setUp () {
	orderDetail = new OrderDetailsBean();
	}
	
	@Test
	void testConstructor() {
		orderDetail = new OrderDetailsBean("R01" , "Tent", 200);
		assertEquals("R01", orderDetail.getPackageId());
		assertEquals("Tent", orderDetail.getPackageName());
		assertEquals("200.00", orderDetail.getTotal());
		
	}
	
}
