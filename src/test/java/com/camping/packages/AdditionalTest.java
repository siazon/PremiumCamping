package com.camping.packages;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Xiasong Chen A00291322
 * 
 * @date 16 Aug 2022 10:35:06
 * @version 1.0
 */
class AdditionalTest {
	Additional test;

	@BeforeEach
	void setUp() throws Exception {
		test = new Additional();
	}

	@Test
	void testGetId() {
		assertEquals(test.getId(), null);
	}

	@Test
	void testSetId() {
		test.setId("1");
		assertEquals(test.getId(), "1");
	}

	@Test
	void testSetName() {
		test.setName("1");
		assertEquals(test.getName(), "1");
	}

	@Test
	void testSetPrice() {
		test.setPrice(1);
		assertEquals(test.getPrice(), 1);
	}

	@Test
	void testGetImgPath() {
		test.setPrice(1);
		assertEquals(test.getPrice(), 1);
	}

	@Test
	void testSetImgPath() {
		test.setImgPath("1");
		assertEquals(test.getImgPath(), "1");
	}

}
