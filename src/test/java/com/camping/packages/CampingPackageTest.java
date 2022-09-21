package com.camping.packages;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Xiasong Chen A00291322
 * 
 * @date 16 Aug 2022 10:35:44
 * @version 1.0
 */
class CampingPackageTest {
	CampingPackage test;

	@BeforeEach
	void setUp() throws Exception {
		test = new CampingPackage();
	}

	@Test
	void testToString() {
		test.toString();
	}

	@Test
	void testSetLocation() {

		test.setLocation("1");
		assertEquals(test.getLocation(), "1");
	}

	@Test
	void testSetDescription() {
		test.setDescription("1");
		assertEquals(test.getDescription(), "1");
	}

	@Test
	void testSetAdultsPax() {
		test.setAdultsPax(1);
		assertEquals(test.getAdultsPax(), 1);
	}

	@Test
	void testSetKidsPax() {
		test.setKidsPax(1);
		assertEquals(test.getKidsPax(), 1);
	}

}
