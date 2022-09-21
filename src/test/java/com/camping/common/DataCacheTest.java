package com.camping.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.camping.additionalservices.AdditionalServiceBooking;
import com.camping.booking.Booking;

/**
 * @author Xiasong Chen A00291322
 * 
 * @date 15 Aug 2022 20:57:27
 * @version 1.0
 */
class DataCacheTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetSingleton() {
		Field instance;
		try {
			instance = DataCache.class.getDeclaredField("instance");
			instance.setAccessible(true);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testGetLoginedUserEmail() {
		DataCache.getSingleton().setLoginedUserEmail("q@qq.com");
		String email = DataCache.getSingleton().getLoginedUserEmail();
		assertEquals(email, "q@qq.com");

	}

	@Test
	void testSetLoginedUserEmail() {
		DataCache.getSingleton().setLoginedUserEmail("q@qq.com");
		String email = DataCache.getSingleton().getLoginedUserEmail();
		assertEquals(email, "q@qq.com");
	}

//	@Test
//	void testGetUserList() {
//
//	}
//
//	@Test
//	void testSetUserList() {
//
//	}
//
//	@Test
//	void testGetPackageList() {
//
//	}
//
//	@Test
//	void testSetPackageList() {
//
//	}
//
//	@Test
//	void testGetBookingList() {
//
//	}
//
//	@Test
//	void testSetBookingList() {
//
//	}
//
//	@Test
//	void testGetPackages() {
//
//	}
//
//	@Test
//	void testSetPackages() {
//
//	}
//
//	@Test
//	void testGetCampingPackages() {
//
//	}
//
//	@Test
//	void testSetCampingPackages() {
//
//	}
//
//	@Test
//	void testGetExtras() {
//
//	}
//
//	@Test
//	void testSetExtras() {
//
//	}
//
//	@Test
//	void testGetServices() {
//
//	}
//
//	@Test
//	void testSetServices() {
//
//	}
//
//	@Test
//	void testGetAdditionalServiceBookings() {
//
//	}
//
//	@Test
//	void testSetAdditionalServiceBookings() {
//
//	}

	@Test
	void testAddAdditionalServiceBooking() {
		DataCache.getSingleton()
				.addAdditionalServiceBooking(new AdditionalServiceBooking("", "", 1, "", "", "", 0, ""));
		int count = DataCache.getSingleton().getAdditionalServiceBookings().size();
		assertEquals(count, 3);
	}

	@Test
	void testAddServiceToBooking() {
		ArrayList<Booking> bookings = DataCache.getSingleton().getBookingList();
		Booking book = bookings.stream().filter(a -> a.getReservationNo().equals("R01")).findFirst().orElse(null);
		int count = book.getBookingDeatils().size();
		DataCache.getSingleton()
				.addServiceToBooking(new AdditionalServiceBooking("", "RESTAURANT", 1, "R01", "", "", 0, ""));
		int Newcount = book.getBookingDeatils().size();
		assertEquals(count + 1, Newcount);
	}

	@Test
	void testAddServiceToBooking2() {
		ArrayList<Booking> bookings = DataCache.getSingleton().getBookingList();
		Booking book = bookings.stream().filter(a -> a.getReservationNo().equals("R01")).findFirst().orElse(null);
		int count = book.getBookingDeatils().size();
		DataCache.getSingleton()
				.addServiceToBooking(new AdditionalServiceBooking("", "TAKEAWAY", 1, "R01", "", "", 0, ""));
		int Newcount = book.getBookingDeatils().size();
		assertEquals(count + 1, Newcount);
	}

	@Test
	void testAddServiceToBooking3() {
		ArrayList<Booking> bookings = DataCache.getSingleton().getBookingList();
		Booking book = bookings.stream().filter(a -> a.getReservationNo().equals("R01")).findFirst().orElse(null);
		int count = book.getBookingDeatils().size();
		DataCache.getSingleton()
				.addServiceToBooking(new AdditionalServiceBooking("", "BABY_SITTING", 1, "R01", "", "", 0, ""));
		int Newcount = book.getBookingDeatils().size();
		assertEquals(count + 1, Newcount);
	}

}
