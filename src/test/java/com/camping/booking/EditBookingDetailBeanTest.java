package com.camping.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.camping.common.DataCache;

/**
 * @author Xiasong Chen A00291322
 * 
 * @date 15 Aug 2022 22:44:05
 * @version 1.0
 */
class EditBookingDetailBeanTest {
	EditBookingDetailBean test;

	@BeforeEach
	void setUp() throws Exception {
		test = new EditBookingDetailBean();

		test.init();
	}

	@Test
	void testInit() {
		assertEquals(2, test.getLocations().size());
	}

	@Test
	void testGetDetail() {
		ArrayList<Booking> bookings = DataCache.getSingleton().getBookingList();
		Booking book = bookings.stream().filter(a -> a.getReservationNo().equals("R01")).findFirst().orElse(null);
		String a = test.GetDetail("R01");
		assertEquals(book, test.getBooking());

	}

	@Test
	void testCheckin() {
		ArrayList<Booking> bookings = DataCache.getSingleton().getBookingList();
		Booking book = bookings.stream().filter(a -> a.getReservationNo().equals("R01")).findFirst().orElse(null);
		String a = test.Checkin("R01");
		assertEquals(book.getState(), "CHECKIN");
	}

	@Test
	void testCheckout() {
		ArrayList<Booking> bookings = DataCache.getSingleton().getBookingList();
		Booking book = bookings.stream().filter(a -> a.getReservationNo().equals("R01")).findFirst().orElse(null);
		String a = test.Checkout("R01");
		assertEquals(book.getState(), "CHECKOUT");
	}

	@Test
	void testAddAservice() {
		String pagePath = test.AddAservice("R01");
		assertEquals(pagePath, "/AdditionalServices.xhtml?faces-redirect=true");
	}

	@Test
	void testAddExtras() {
		ArrayList<Booking> bookings = DataCache.getSingleton().getBookingList();
		Booking book = bookings.stream().filter(a -> a.getReservationNo().equals("R01")).findFirst().orElse(null);
		int count = book.getBookingDeatils().size();
		test.AddExtras("R01");
		int newCount = book.getBookingDeatils().size();
		assertEquals(count, newCount);
	}

	@Test
	void testAddExtrasHaveExtra() {
		ArrayList<Booking> bookings = DataCache.getSingleton().getBookingList();
		Booking book = bookings.stream().filter(a -> a.getReservationNo().equals("R01")).findFirst().orElse(null);
		int count = book.getBookingDeatils().size();
		test.getExtras().get(0).setQuantity(1);
		test.AddExtras("R01");
		int newCount = book.getBookingDeatils().size();
		assertEquals(count + 1, newCount);
	}

	@Test
	void testValueChangePackage() {// ValueChangeEvent

		ArrayList<Booking> bookings = DataCache.getSingleton().getBookingList();
		Booking book = bookings.stream().filter(a -> a.getReservationNo().equals("R01")).findFirst().orElse(null);
		test.setBooking(book);
		test.valueChangePackage(null);
	}

	@Test
	void testValueChangeLocation() {
		ArrayList<Booking> bookings = DataCache.getSingleton().getBookingList();
		Booking book = bookings.stream().filter(a -> a.getReservationNo().equals("R01")).findFirst().orElse(null);
		test.setBooking(book);
		test.valueChangeLocation(null);
	}

	@Test
	void testRemoveBookingDetail() {
		ArrayList<Booking> bookings = DataCache.getSingleton().getBookingList();
		Booking book = bookings.stream().filter(a -> a.getReservationNo().equals("R01")).findFirst().orElse(null);
		test.setBooking(book);
		BookingDetail detail = book.getBookingDeatils().get(0);
		int count = book.getBookingDeatils().size();
		test.RemoveBookingDetail(detail.getDetailNo());
		int newcount = book.getBookingDeatils().size();
		assertEquals(count - 1, newcount);

	}

	@Test
	void testOnSave() {
		String result = test.OnSave();
		assertEquals("/BookingList.xhtml?faces-redirect=true", result);
	}

	@Test
	void testPay() {
		test.pay("R01");
	}

	@Test
	void testSetGetMethod() {
		test.setAdditionalServicesBean(null);
		test.setExtras(null);
		test.setLocations(null);
		test.setPackages(null);
		test.setServices(null);
		test.getAdditionalServicesBean();
		test.getPackages();
		test.getServices();

	}
}
