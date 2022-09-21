package com.camping.booking;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.camping.common.DataCache;
import com.camping.common.User;
import com.camping.packages.Additional;
import com.camping.packages.CampingPackage;


class BookingBeanTest {
	private BookingBean myBookingBean;
	
	@BeforeEach
	public void setUp() {
		myBookingBean = new BookingBean ();
		myBookingBean.init();
	}
	
	@Test
	public void testChangeUser() {
		myBookingBean.setUsername(null);
		assertEquals(null, myBookingBean.getUsername());
		
	}

	@Test
	public void testChangeLocation() {
		myBookingBean.setLocation("Glamping");
		assertEquals("Glamping", myBookingBean.getLocation());
	}
	
	@Test
	public void testChangeType() {
		myBookingBean.setType("Tent");
		assertEquals("Tent", myBookingBean.getType());
	}
	
	@Test
	public void testChangeCheckInDate() {
		Date now = new Date();
		myBookingBean.setCheckInDate(now);
		assertEquals(now, myBookingBean.getCheckInDate());
	}
	
	@Test
	public void testChangeCheckOutDate() {
		Date now = new Date();
		myBookingBean.setCheckOutDate(now);
		assertEquals(now, myBookingBean.getCheckOutDate());
	}
	
	@Test
	public void testGetNumberLinen() {
		myBookingBean.setNumberLinen(5);
		assertEquals(5, myBookingBean.getNumberLinen());
	}
	
	@Test
	public void testGetNumberDuvet() {
		myBookingBean.setNumberDuvet(5);
		assertEquals(5, myBookingBean.getNumberDuvet());
	}
	
	@Test
	public void testGetNumberPack() {
		myBookingBean.setNumberPack(3);
		assertEquals(3,myBookingBean.getNumberPack());
	}
	
	@Test
	public void testGetNumberTowel() {
		myBookingBean.setNumberTowel(3);
		assertEquals(3,myBookingBean.getNumberTowel());
	}
	
	@Test
	public void testGetSelecetedLocation() {
		myBookingBean.setSelecetedLocation("Glamping");
		assertEquals("Glamping", myBookingBean.getSelecetedLocation());
	}
	
	@Test
	public void testGetSelecetedPackage() {
		myBookingBean.setSelecetedPackage("Tent");
		assertEquals("Tent", myBookingBean.getSelecetedPackage());
	}

	
	@Test
	public void testGetLocations() {
		ArrayList<String>locations = new ArrayList<>();
		locations.add("location1");
		locations.add("location2");
		myBookingBean.setLocations(locations);
		assertEquals(locations, myBookingBean.getLocations());
		
	}
	
	@Test
	public void testGetPackages() {
		ArrayList<String>packages = new ArrayList<>();
		packages.add("type 1");
		packages.add("type 2");
		myBookingBean.setPackages(packages);
		assertEquals(packages, myBookingBean.getPackages());
		
	}
	
	@Test
	public void testGetBooking() {
		ArrayList<BookingDetail> details = new ArrayList<>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date bookingTime = new Date();
		Date inTime = new Date();
		Date outTime = new Date();
		Additional restaurant = new Additional("a1", "restaurant", 25, "restaurant.png");
		User user = new User("lankanipw@gmail.com", "123456", "Customer", "Lankani");
		CampingPackage lakeTent = new CampingPackage("p1", "Tent", 40, "tent.jpg", "Lake Side");
		Booking test = new Booking("R01", user, lakeTent, bookingTime, inTime, outTime, "RESERVED", details);
		
		
		myBookingBean.setBooking(test);
		assertEquals(test,myBookingBean.getBooking());
	}
	
	
	@Test
	public void testGetBookings() {
		ArrayList<BookingDetail> details = new ArrayList<>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date bookingTime = new Date();
		Date inTime = new Date();
		Date outTime = new Date();
		Additional restaurant = new Additional("a1", "restaurant", 25, "restaurant.png");
		User user = new User("lankanipw@gmail.com", "123456", "Customer", "Lankani");
		CampingPackage lakeTent = new CampingPackage("p1", "Tent", 40, "tent.jpg", "Lake Side");
		Booking test = new Booking("R01", user, lakeTent, bookingTime, inTime, outTime, "RESERVED", details);
		
		ArrayList<Booking> bookingAL = new ArrayList<Booking>();
		bookingAL.add(test);
		
		myBookingBean.setBookings(bookingAL);
		assertEquals(bookingAL,myBookingBean.getBookings());
	}
	
	@Test
	void testInit() {
		assertEquals(6, myBookingBean.getLocations().size());
	}
	
	@Test
	void testValueChangeLocation() {
		ArrayList<Booking> bookings = DataCache.getSingleton().getBookingList();
		Booking book = bookings.stream().filter(a -> a.getReservationNo().equals("R01")).findFirst().orElse(null);
		myBookingBean.setBooking(book);
		myBookingBean.valueChangeLocation(null);
	}
	
	
	
	
	
	
	
	
	
}
