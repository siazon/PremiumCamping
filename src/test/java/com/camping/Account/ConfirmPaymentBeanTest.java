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
	/*
	 * Aleksandra Marjanovic
	 * A00303157
	 * 
	 */
class ConfirmPaymentBeanTest {
	ConfirmPaymentBean confirm;
	
	@BeforeEach
	void setUp () {
		confirm = new ConfirmPaymentBean();
	}
	
	@Test
	void testGettersAndSetters () {
		confirm.setReservationNumber("R01");
		confirm.setConfirmationNumber("A0000123");
		assertEquals("A0000123", confirm.getConfirmationNumber());
		
	}
	
	@Test
	void testOnLoad() throws ParseException {
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
		confirm.setReservationNumber("R01");
		confirm.onLoad();
		assertEquals("R01", confirm.getReservationNumber());
		assertEquals("R01", booking.getReservationNo());
	}
	@Test
	void testOnLoadAdditional() throws ParseException {
		String serviceType = "TAKEWAY";
		int noOfItems = 2;
		String bookedForEmail = "yes";
		String bookedByEmail = "yes";
		double payment = 200.00;
		String timeSlot = "8:30 pm";
		ArrayList<AdditionalServiceBooking> additionalServiceBookings = new ArrayList<AdditionalServiceBooking>();
		AdditionalServiceBooking service = new AdditionalServiceBooking(serviceType, noOfItems, "R01", bookedForEmail, bookedByEmail, payment, timeSlot);
		additionalServiceBookings.add(service);
		DataCache.getSingleton().setAdditionalServiceBookings(additionalServiceBookings);
		assertEquals("ASB001", service.getId());
		
	}
	

}
