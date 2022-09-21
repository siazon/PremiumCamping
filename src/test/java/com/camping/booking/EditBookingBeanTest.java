package com.camping.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.camping.common.DataCache;

/**
 * @author Xiasong Chen A00291322
 * 
 * @date 202271:37:11
 * @version 1.0
 */
class EditBookingBeanTest {
	EditBookingBean test;

	@BeforeEach
	void setUp() throws Exception {
		test = new EditBookingBean();
		DataCache.getSingleton().setLoginedUserEmail("lankanipw@gmail.com");
		test.onload();
	}

	@Test
	void testbookingsFilter() {
		test.setSearchContent("R01");
		test.bookingsFilter();

		assertEquals(1, test.getBookings().size());
	}

	@Test
	void testbookingsFilterEmpty() {
		test.setSearchContent("");
		test.bookingsFilter();
		test.getSearchContent();
		assertEquals(3, test.getBookings().size());
	}

	@Test
	void testOnload() {
		assertEquals(3, test.getBookings().size());
	}

	@Test
	void testOnloadnoEmail() {

		DataCache.getSingleton().setLoginedUserEmail(null);
		test.onload();
		assertEquals(0, test.getBookings().size());
	}

	@Test
	void testOnloadWithSearch() {

		test.setSearchContent("R01");
		test.onload();
		assertEquals(1, test.getBookings().size());
	}

	@Test
	void testCancel() {
		test.Cancel("R01");
		ArrayList<Booking> bookings = DataCache.getSingleton().getBookingList();
		Booking book = bookings.stream().filter(a -> a.getReservationNo().equals("R01")).findFirst().orElse(null);

		assertEquals("CANCELED", book.getState());
	}

	@Test
	void testsetBookings() {
		ArrayList<Booking> bookings = new ArrayList<>();
		test.setBookings(bookings);
	}
}
