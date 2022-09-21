package com.camping.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.camping.common.User;
import com.camping.packages.Additional;
import com.camping.packages.CampingPackage;

/**
 * @author Xiasong Chen A00291322
 * 
 * @date 16 Aug 2022 00:15:44
 * @version 1.0
 */
class BookingTest {
	Booking test;

	@BeforeEach
	void setUp() throws Exception {
		ArrayList<BookingDetail> details = new ArrayList<>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date bookingTime = new Date();
		Date inTime = new Date();
		Date outTime = new Date();
		Additional restaurant = new Additional("a1", "restaurant", 25, "restaurant.png");
		User user = new User("lankanipw@gmail.com", "123456", "Customer", "Lankani");
		CampingPackage lakeTent = new CampingPackage("p1", "Tent", 40, "tent.jpg", "Lake Side");
		test = new Booking("R01", user, lakeTent, bookingTime, inTime, outTime, "RESERVED", details);
		BookingDetail newDtail = new BookingDetail(restaurant, "Service", new Date(), 1, "chen");
		test.getBookingDeatils().add(newDtail);
		BookingDetail newDtail1 = new BookingDetail(restaurant, "Extra", new Date(), 1, "chen");
		test.getBookingDeatils().add(newDtail1);
	}

	@Test
	void testSetAmount() {
		test.setAmount(3);
	}

	@Test
	void testGetAmount() {
		double amount = test.getAmount();
		System.out.println(amount);
		assertEquals(amount, 65);
	}

	@Test
	void testSetState() {
		test.setState("CANCELED");
	}

	@Test
	void testgetBookingDeatils() {
		test.getBookingDeatils();
	}

	@Test
	void testToString() {
		System.out.println(test.toString());
	}

	@Test
	void testGetDetailSummary() {
		test.getDetailSummary();
		test.getBookingDeatils().get(1).setpType("Extra");
		test.getBookingDeatils().get(0).setpType("Extra");
		test.getDetailSummary();
	}

	@Test
	void testGetDetailSummaryEmpty() {
		test.getBookingDeatils().clear();
		test.getDetailSummary();

		test.setBookingDeatils(null);
		test.getDetailSummary();
	}

	@Test
	void testGetSetMethod() {
		test.setBookingDisable(false);
		test.setBookingTime(new Date());
		test.setCampingPackage(null);
		test.setCheckinTime(new Date());
		test.setCheckoutTime(new Date());
		test.setConfirmationNumber("");
		test.setDetailSummary("");
		test.getUser();
		test.setUser(null);

	}

}
