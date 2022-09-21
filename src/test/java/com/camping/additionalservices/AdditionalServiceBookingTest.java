package com.camping.additionalservices;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AdditionalServiceBookingTest {
	AdditionalServiceBooking additionalServiceBooking;
	AdditionalServiceBooking additionalServiceBooking2;
	Date date;

	@BeforeEach
	void setUp() throws Exception {
		additionalServiceBooking = new AdditionalServiceBooking("RESTAURANT", "RESTAURANT",
				3, "R01", "lankanipw@gmail.com", "lankanipw@gmail.com", 75.0, "7:30 pm");
		additionalServiceBooking2 = new AdditionalServiceBooking("TAKEAWAY",
				2, "R01", "lankanipw@gmail.com", "lankanipw@gmail.com", 75.0, "7:30 pm");
		date = new Date(122, 7, 30);
	}

	@Test
	void testAdditionalServiceBooking() {
		assertNotNull(additionalServiceBooking);
	}

	@Test
	void testAdditionalServiceBooking2() {
		assertNotNull(additionalServiceBooking2);
	}

	@Test
	void testSetId() {
		additionalServiceBooking.setId("R03");;
		assertEquals(additionalServiceBooking.getId(), "R03");
	}

	@Test
	void testDate() {
		assertNull(additionalServiceBooking.getDate());
		additionalServiceBooking.setDate(date);
		assertEquals(additionalServiceBooking.getDate(), date);
	}

	@Test
	void testServiceName() {
		assertEquals(additionalServiceBooking.getServiceName(), "RESTAURANT");
		additionalServiceBooking.setServiceName("BABY_SITTING");
		assertEquals(additionalServiceBooking.getServiceName(), "BABY_SITTING");
	}

	@Test
	void testServiceType() {
		assertEquals(additionalServiceBooking.getServiceType(), "RESTAURANT");
		additionalServiceBooking.setServiceType("BABY_SITTING");
		assertEquals(additionalServiceBooking.getServiceType(), "BABY_SITTING");
	}

	@Test
	void testNoOfItems() {
		assertEquals(additionalServiceBooking.getNoOfItems(), 3);
		additionalServiceBooking.setNoOfItems(2);
		assertEquals(additionalServiceBooking.getNoOfItems(), 2);
	}	

	@Test
	void testReservationNumber() {
		assertEquals(additionalServiceBooking.getReservationNumber(), "R01");
		additionalServiceBooking.setReservationNumber("R03");
		assertEquals(additionalServiceBooking.getReservationNumber(), "R03");
	}

	@Test
	void testBookedForEmail() {
		assertEquals(additionalServiceBooking.getBookedForEmail(),"lankanipw@gmail.com" );
		additionalServiceBooking.setBookedForEmail("A00267225@student.ait.ie");
		assertEquals(additionalServiceBooking.getBookedForEmail(),"A00267225@student.ait.ie");
	
	}

	@Test
	void testBookedByEmail() {
		assertEquals(additionalServiceBooking.getBookedByEmail(),"lankanipw@gmail.com" );
		additionalServiceBooking.setBookedByEmail("A00267225@student.ait.ie");
		assertEquals(additionalServiceBooking.getBookedByEmail(),"A00267225@student.ait.ie");
	}

	@Test
	void testPayment() {
		assertEquals(additionalServiceBooking.getPayment(),75.0);
		additionalServiceBooking.setPayment(80.0);
		assertEquals(additionalServiceBooking.getPayment(),80.0);
	}

	@Test
	void testTimeSlot() {
		assertEquals(additionalServiceBooking.getTimeSlot(),"7:30 pm");
		additionalServiceBooking.setTimeSlot("8.00 pm");
		assertEquals(additionalServiceBooking.getTimeSlot(),"8.00 pm"); 
	}

	@Test
	void testConfirmationNumber() {
		assertNull(additionalServiceBooking.getConfirmationNumber());
		additionalServiceBooking.setConfirmationNumber("C01");
		assertEquals(additionalServiceBooking.getConfirmationNumber(),"C01"); 
	}

	@Test
	void testToString() {
		String expected =  "(serviceName: RESTAURANT, serviceType: RESTAURANT, noOfItems: 3, reservationNumber: R01, payment: 75.0, timeSlot: 7:30 pm, date: null )";
		assertEquals(additionalServiceBooking.toString(), expected);
	}

}
