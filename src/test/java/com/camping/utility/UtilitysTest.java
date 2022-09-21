package com.camping.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Xiasong Chen A00291322
 * 
 * @date 1 Aug 2022 20:19:11
 * @version 1.0
 */
class UtilitysTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testcheckEmailFormat() {
		assertEquals(true, Utilitys.checkEmailFormat("siazon@gamil.com"));
		assertEquals(false, Utilitys.checkEmailFormat("siazon.com"));
	}

}
