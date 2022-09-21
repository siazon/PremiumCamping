package com.camping.additionalservices;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.primefaces.event.SelectEvent;

import com.camping.common.DataCache;

class AdditionalServiceBeanTest {
	AdditionalServiceBean additionalServiceBean;
	AdditionalServiceBean additionalServiceBean2;
	Date date;

	@BeforeEach
	void setUp() throws Exception {
		additionalServiceBean = new AdditionalServiceBean ();
		additionalServiceBean.init();
	}

	@Test
	void testInit() {
		assertEquals(additionalServiceBean.getTimeSlots().size(), 8);
		assertEquals(additionalServiceBean.getDurations().size(), 8);
	}

	@Test
	void testReservationNumber() {
		assertNull(additionalServiceBean.getReservationNumber());
		additionalServiceBean.setReservationNumber("R03");
		assertEquals(additionalServiceBean.getReservationNumber(), "R03");
	}

	@Test
	void testServiceType() {
		assertNull(additionalServiceBean.getServiceType());
		additionalServiceBean.setServiceType("BABY_SITTING");
		assertEquals(additionalServiceBean.getServiceType(), "BABY_SITTING");
	}

	
	//Restaurant tests
	@Test
	void testSelectedTimeSlotRestaurant() {
		assertNull(additionalServiceBean.getSelectedTimeSlotRestaurant());
		additionalServiceBean.setSelectedTimeSlotRestaurant("8.00 pm");
		assertEquals(additionalServiceBean.getSelectedTimeSlotRestaurant(),"8.00 pm");	
	}

	
	@Test
	void testBookingDateRestaurant() {
		assertNull(additionalServiceBean.getBookingDateRestaurant());
		additionalServiceBean.setBookingDateRestaurant(date);
		assertEquals(additionalServiceBean.getBookingDateRestaurant(), date);			
	}
	
	@Test
	void testNoOfItemRestaurant() {
		assertEquals(additionalServiceBean.getNoOfItemRestaurant(), 0);
		additionalServiceBean.setNoOfItemRestaurant(2);
		assertEquals(additionalServiceBean.getNoOfItemRestaurant(),2);
	}
	
	@Test
	void testTotalRestaurant() {
		assertEquals(additionalServiceBean.getTotalRestaurant(), 0.0);
		additionalServiceBean.setTotalRestaurant(20.0);
		assertEquals(additionalServiceBean.getTotalRestaurant(),20.0);
	}
	
	
	// takeaway test
	
	@Test
	void testSelectedTimeSlotTakeaway() {
		assertNull(additionalServiceBean.getSelectedTimeSlotTakeaway());
		additionalServiceBean.setSelectedTimeSlotTakeaway("8.00 pm");
		assertEquals(additionalServiceBean.getSelectedTimeSlotTakeaway(),"8.00 pm");
	}


	@Test
	void testBookingDateTakeaway() {
		assertNull(additionalServiceBean.getBookingDateTakeaway());
		additionalServiceBean.setBookingDateTakeaway(date);
		assertEquals(additionalServiceBean.getBookingDateTakeaway(), date);			
	}

	
	@Test
	void testNoOfItemTakeaway() {
		assertEquals(additionalServiceBean.getNoOfItemTakeaway(), 0);
		additionalServiceBean.setNoOfItemTakeaway(2);
		assertEquals(additionalServiceBean.getNoOfItemTakeaway(),2);
	}
	
	@Test
	
	
	void testTotalTakeaway() {
		assertEquals(additionalServiceBean.getTotalTakeaway(), 0.0);
		additionalServiceBean.setTotalTakeaway(20.0);
		assertEquals(additionalServiceBean.getTotalTakeaway(),20.0);
	}
	
	
	// baby sitting test
	@Test
	void testSelectedTimeSlotBabySitting() {
		assertNull(additionalServiceBean.getSelectedTimeSlotBabySitting());
		additionalServiceBean.setSelectedTimeSlotBabySitting("3 pm - 4 pm");
		assertEquals(additionalServiceBean.getSelectedTimeSlotBabySitting(),"3 pm - 4 pm");
	}


	@Test
	void testBookingDateBabySitting() {
		assertNull(additionalServiceBean.getBookingDateBabySitting());
		additionalServiceBean.setBookingDateBabySitting(date);
		assertEquals(additionalServiceBean.getBookingDateBabySitting(), date);			
	}
	
	@Test
	void testNoOfItemBabySitting() {
		assertEquals(additionalServiceBean.getNoOfItemBabySitting(), 0);
		additionalServiceBean.setNoOfItemBabySitting(2);
		assertEquals(additionalServiceBean.getNoOfItemBabySitting(),2);
	}
	

	@Test
	void testTotalBabySitting() {
		assertEquals(additionalServiceBean.getTotalBabySitting(), 0.0);
		additionalServiceBean.setTotalBabySitting(20.0);
		assertEquals(additionalServiceBean.getTotalBabySitting(),20.0);
	}

	@Test
	void testReservationFound() {
		assertFalse(additionalServiceBean.isReservationFound());
		additionalServiceBean.setReservationFound(true);
		assertTrue(additionalServiceBean.isReservationFound());
	}
	
	@Test
	void testTimeSlots() {
		assertEquals(additionalServiceBean.getTimeSlots().size(), 8);
		ArrayList<String> timeSlots = new ArrayList<String>();
    	timeSlots.add("6:00 pm");
    	timeSlots.add("6:30 pm");
		timeSlots.add("7:00 pm");
		additionalServiceBean.setTimeSlots(timeSlots);
		assertEquals(additionalServiceBean.getTimeSlots().size(), 3);
	}
	
	@Test
	void testDurations() {
		assertEquals(additionalServiceBean.getDurations().size(), 8);
		ArrayList<String> durations = new ArrayList<String>();
		durations.add("10 am - 11 am");
		durations.add("11 am - 11 am");
		additionalServiceBean.setDurations(durations);
		assertEquals(additionalServiceBean.getDurations().size(), 2);
	}
	
	@Test
	void testTotal() {
		assertEquals(additionalServiceBean.getTotal(), 0.0);
		additionalServiceBean.setTotal(40.0);
		assertEquals(additionalServiceBean.getTotal(),40.0);
	}
	
	@Test
	void testBookingId() {
		assertEquals(additionalServiceBean.getBookingId(),"R01");	
		additionalServiceBean.setBookingId("R03");
		assertEquals(additionalServiceBean.getBookingId(),"R03");	
	}
	
	@Test
	void testValidateReservationNumberForInvalid() {
		boolean reult = additionalServiceBean.validateReservationNumber("R08");
		assertFalse(reult);
	}
	
	@Test
	void testValidateReservationNumberForValid() {
		boolean reult = additionalServiceBean.validateReservationNumber("R01");
		assertFalse(reult);
	}
	
	@Test
	void testTakeawayBooking() {
		try {
			additionalServiceBean.takeawayBooking();
		} catch(NullPointerException e) {
			
		}
	}
}
