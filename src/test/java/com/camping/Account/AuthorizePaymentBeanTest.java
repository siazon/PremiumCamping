package com.camping.Account;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.camping.additionalservices.AdditionalServiceBooking;
import com.camping.booking.Booking;
import com.camping.common.DataCache;
import com.camping.common.User;
import com.camping.packages.CampingPackage;

class AuthorizePaymentBeanTest {
	AuthorizePaymentBean authorize;
	
	@BeforeEach
	void setUp () {
		authorize = new AuthorizePaymentBean();
	}
	
	@Test
	void testGet() {
		authorize.setPackageName("Tent");
		authorize. setRegistrationId("000");
		authorize.setReservationNumber("1111");
		authorize.setTotalAmount(20.00);
		assertEquals("Tent", authorize.getPackageName());
		assertEquals("000", authorize.getRegistrationId());
		assertEquals("1111", authorize.getReservationNumber());
		assertEquals(20.00, authorize.getTotalAmount());
	}
	
	@Test
	void testChangeValue () {
		authorize.setPackageName("Lake side");
		assertEquals( "Lake side",  authorize.getPackageName());
	}
	
	@Test
	void testOnLoadBooking () throws ParseException {
		ArrayList <Booking> authorizeList = new ArrayList<Booking>();
		User user = new User("lankanipw@gmail.com", "123456", "Customer", "Lankani");
		CampingPackage tent = new CampingPackage("p1", "Tent", 200, "tent.jpg", "Center");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date bookingTime = new Date();
		bookingTime = df.parse("2020-08-01");
		Date inTime = new Date();
		Date outTime = new Date();
		inTime = df.parse("2020-08-01");
		outTime = df.parse("2020-08-01");
		String status = "Canceled";
		Booking booking = new Booking("R01", user, tent, bookingTime, inTime, outTime, status, null);
		authorizeList.add(booking);
		DataCache.getSingleton().setBookingList(authorizeList);
		authorize.setReservationNumber("R01");
		authorize.onLoad();
		assertEquals ("R01", authorize.getRegistrationId());
		assertEquals ("Tent", authorize.getPackageName());
		assertEquals (200, authorize.getTotalAmount());
	}
	
	@Test
	void testOnLoadAdditionalServices() {
		String serviceType = "TAKEWAY";
		int noOfItems = 2;
		String bookedForEmail = "yes";
		String bookedByEmail = "yes";
		double payment = 200.00;
		String timeSlot = "8:30 pm";
		AdditionalServiceBooking service = new AdditionalServiceBooking(serviceType, noOfItems, "R01", bookedForEmail, bookedByEmail, payment, timeSlot);
		AdditionalServiceBooking additionalServiceRestaurant = new AdditionalServiceBooking("RESTAURANT", "RESTAURANT",
				3, "R01", "lankanipw@lanki.com", "lankanipw@lanki.com", 75.0, "7:30 pm");
		ArrayList<AdditionalServiceBooking> additionalServiceBookings = new ArrayList<AdditionalServiceBooking>();
		additionalServiceBookings.add(service);
		additionalServiceBookings.add(additionalServiceRestaurant);
		DataCache.getSingleton().setAdditionalServiceBookings(additionalServiceBookings);
		authorize.setReservationNumber("ASB002");
		authorize.onLoad();
		assertEquals("ASB002", authorize.getRegistrationId());
		assertEquals("RESTAURANT", authorize.getPackageName());
		assertEquals (75, authorize.getTotalAmount());
	}
	
}
