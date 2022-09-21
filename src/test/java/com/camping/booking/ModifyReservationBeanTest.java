package com.camping.booking;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.camping.common.DataCache;


public class ModifyReservationBeanTest {
	ModifyBookingBean modify;
	
	
	@BeforeEach
	void setUp() throws Exception {
		modify = new ModifyBookingBean();
		DataCache.getSingleton().setLoginedUserEmail("A00267225@student.ait.ie");
		modify.init();
		
	}
	

	@Test
	public void searchgetBookings() {
//		modify.setBookings("R01", "Lankani", "Lake Side", "2020-08-01", "2020-08-01", "2020-08-01", "RESERVED",
//				details);
	}
	
	@Test
	public void searchAllBookings() {
		
		modify.getBookings();
	
	}
	
	/*String reservationNo, User user, CampingPackage campingPackage, Date bookingTime, Date checkinTime,
	Date checkoutTime, String state, ArrayList<BookingDetail> bookingDeatils)
	*/
	@Test
	public void searchByReservationNo() {
		
		
		modify.setSearchContent("R01");
		
		assertNotEquals("R07", modify.getSearchContent());
		
		
//		AssertEquals("R01", "Lankani", "Lake Side", "2020-08-01", "2020-08-01", "2020-08-01", "RESERVED", modify.Search());
		
		/* assertEquals(1, modify.getModifyBookingBean().size()); */
		
		/*
		 * Booking bookingR1 = new Booking("R01", userList.get(0), "lakeTent",
		 * bookingTime, inTime, outTime, "RESERVED", details);
		 */
		/*
		 * Booking bookingR1 = new Booking("R01", user.getName(), lakeTent,
		 * "2020-08-01", "2020-08-01", "RESERVED", details.getDetailNo());
		 * bookingList.add(bookingR1);
		 * 
		 * // Booking reservationNo = new Booking("R01", "Lankani", packages,
		 * "2020-08-01", "2020-08-01", "RESERVED", details) ;
		 * bookingR1.getBookingDeatils(); assertEquals("R01",
		 * bookingR1.getReservationNo());
		 */
	}
	
	@Test
	public void testGetDetail() {
		fail("Not yet implemented");
	}

	@Test
	public void testCancelBooking() {
		modify.Cancel();
		assertEquals("Cancel", modify.Cancel());
		
		
		
	}
	@Test
	public void testRebookBookings() {
		modify.Cancel();
		assertEquals("Cancel", modify.Cancel());
	}


}
