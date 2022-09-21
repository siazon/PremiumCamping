package com.camping.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.camping.additionalservices.AdditionalServiceBooking;
import com.camping.booking.Booking;
import com.camping.booking.BookingDetail;
import com.camping.packages.Additional;
import com.camping.packages.CampingPackage;

/**
 * @author Xiasong Chen A00291322
 * 
 * @date 30 Jul 2022 15:18:36
 * @version 1.0
 */
public class DataCache {

	// singleton pattern
	private volatile static DataCache instance;

	private DataCache() {
		// Mock database
		userList = new ArrayList<>();
		// Created two dummy users to test login functionality
		userList.add(new User("lankanipw@gmail.com", "123456", "Customer", "Lankani")); // customer
		userList.add(new User("lankanipw@lanki.com", "123456", "Customer", "Lankani")); // customer
		userList.add(new User("A00267225@student.ait.ie", "123456", "Staff", "Wijesinghe")); // staff

		// Create package
		CampingPackage tent = new CampingPackage("p1", "Tent", 30, "tent.jpg", "Center");
		CampingPackage mobileHome = new CampingPackage("p2", "Mobile Home", 80, "mobile_home.jpg", "Center");
		CampingPackage woodenLodge = new CampingPackage("p3", "Wooden Lodge", 60, "woodenLodge.png", "Center");
		CampingPackage treeHouse = new CampingPackage("1", "Tree House", 50, "treeHouse.png", "Center");

		CampingPackage lakeTent = new CampingPackage("p1", "Tent", 40, "tent.jpg", "Lake Side");
		CampingPackage lakeMobileHome = new CampingPackage("p2", "Mobile Home", 90, "mobile_home.png", "Lake Side");
		CampingPackage lakeWoodenLodge = new CampingPackage("p3", "Wooden Lodge", 70, "lakeWoodenLodge.png",
				"Lake Side");
		CampingPackage lateTreeHouse = new CampingPackage("1", "Tree House", 60, "lateTreeHouse.png", "Lake Side");

		packages = new ArrayList<>();
		packages.add(tent);
		packages.add(mobileHome);
		packages.add(woodenLodge);
		packages.add(treeHouse);
		packages.add(lakeTent);
		packages.add(lakeMobileHome);
		packages.add(lakeWoodenLodge);
		packages.add(lateTreeHouse);

		// temporarily added
		// campingPackages = new ArrayList<>();
		// campingPackages.add(lakeTent);
		// campingPackages.add(treeHouse);

		// Create extra
		Additional extraLinen = new Additional("e1", "Linen", 20, "extraLinen.png");
		Additional extraDuvet = new Additional("e2", "Duvet", 20, "extraDuvet.png");
		Additional extraTowel = new Additional("e3", "Towel", 5, "extraTowel.png");
		Additional extraPack = new Additional("e4", "WelcomePack", 5, "extraPack.png");
		extras = new ArrayList<>();
		extras.add(extraLinen);
		extras.add(extraDuvet);
		extras.add(extraTowel);
		extras.add(extraPack);

		// Create service
		Additional restaurant = new Additional("a1", "restaurant", 25, "restaurant.png");
		Additional foodTakeaway = new Additional("a1", "takeaway", 10, "foodTakeaway.png");
		Additional babysitting = new Additional("a1", "babysitting", 10, "babysitting.png");
		services = new ArrayList<>();
		services.add(restaurant);
		services.add(foodTakeaway);
		services.add(babysitting);

		// create booking data
		bookingList = new ArrayList<>();

		// booking
		ArrayList<BookingDetail> details = new ArrayList<>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date bookingTime = new Date();
		Date inTime = new Date();
		Date outTime = new Date();
		try {
			bookingTime = df.parse("2020-08-01");
			inTime = df.parse("2020-08-01");
			outTime = df.parse("2020-08-01");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date now = new Date();
		Booking bookingR1 = new Booking("R01", userList.get(0), lakeTent, bookingTime, inTime, outTime, "RESERVED",
				details);
		bookingList.add(bookingR1);

		// add service by customer
		// 1. search by R01
		BookingDetail newDtail = new BookingDetail(restaurant, "Service", new Date(), 1, "chen");
		bookingR1.getBookingDeatils().add(newDtail);

		// add Extra by customer
		// 1. search by R01
		BookingDetail newDtail1 = new BookingDetail(extraLinen, "Extra", new Date(), 1, "chen");
		bookingR1.getBookingDeatils().add(newDtail1);

		// booking with extra
		ArrayList<BookingDetail> details1 = new ArrayList<>();
		BookingDetail dtail2 = new BookingDetail(extraTowel, "Extra", new Date(), 1, "chen");

		details1.add(dtail2);

		try {
			bookingTime = df.parse("2020-08-05");
			inTime = df.parse("2020-08-06");
			outTime = df.parse("2020-08-15");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Booking bookingR2 = new Booking("R02", userList.get(0), mobileHome, bookingTime, inTime, outTime, "CANCELED",
				details1);
		bookingList.add(bookingR2);

		Booking bookingR3 = new Booking("R" + now.getTime(), userList.get(0), treeHouse, bookingTime, inTime, outTime,
				"CANCELED", details1);
		bookingList.add(bookingR3);

		// ------- Additional Service Bookings -------------------
		additionalServiceBookings = new ArrayList<>();
		AdditionalServiceBooking additionalServiceRestaurant = new AdditionalServiceBooking("RESTAURANT", "RESTAURANT",
				3, "R01", "lankanipw@lanki.com", "lankanipw@lanki.com", 75.0, "7:30 pm");
		additionalServiceRestaurant.setDate(new Date(122, 7, 30));

		AdditionalServiceBooking additionalServiceTakeaway = new AdditionalServiceBooking("TAKEAWAY", "TAKEAWAY", 4,
				"R02", "lankanip@lanki.com", "A00267225@student.ait.ie", 40.0, "7:30 pm");
		additionalServiceTakeaway.setDate(new Date(122, 7, 30));

		additionalServiceBookings.add(additionalServiceRestaurant);
		additionalServiceBookings.add(additionalServiceTakeaway);
		// --------------------------------------------------------
	}

	public static DataCache getSingleton() {
		if (instance == null) {
			synchronized (DataCache.class) {
				if (instance == null) {
					instance = new DataCache();
				}
			}
		}
		return instance;
	}

	private String LoginedUserEmail;
	private ArrayList<User> userList;
	private ArrayList<Package> packageList;
	private ArrayList<Booking> bookingList;
	private ArrayList<Additional> packages;
	private ArrayList<Additional> extras;
	private ArrayList<Additional> services;
	private ArrayList<CampingPackage> campingPackages;

	public String getLoginedUserEmail() {
		return LoginedUserEmail;
	}

	public void setLoginedUserEmail(String loginedUserEmail) {
		LoginedUserEmail = loginedUserEmail;
	}

	private ArrayList<AdditionalServiceBooking> additionalServiceBookings;

	public ArrayList<User> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}

	public ArrayList<Package> getPackageList() {
		return packageList;
	}

	public void setPackageList(ArrayList<Package> packageList) {
		this.packageList = packageList;
	}

	public ArrayList<Booking> getBookingList() {
		return bookingList;
	}

	public void setBookingList(ArrayList<Booking> bookingList) {
		this.bookingList = bookingList;
	}

	public ArrayList<Additional> getPackages() {
		return packages;
	}

	public void setPackages(ArrayList<Additional> packages) {
		this.packages = packages;
	}

	public ArrayList<CampingPackage> getCampingPackages() {
		return campingPackages;
	}

	public void setCampingPackages(ArrayList<CampingPackage> campingPkgs) {
		this.campingPackages = campingPkgs;
	}

	public ArrayList<Additional> getExtras() {
		return extras;
	}

	public void setExtras(ArrayList<Additional> extras) {
		this.extras = extras;
	}

	public ArrayList<Additional> getServices() {
		return services;
	}

	public void setServices(ArrayList<Additional> services) {
		this.services = services;
	}

	public ArrayList<AdditionalServiceBooking> getAdditionalServiceBookings() {
		return additionalServiceBookings;
	}

	public void setAdditionalServiceBookings(ArrayList<AdditionalServiceBooking> additionalServiceBookings) {
		this.additionalServiceBookings = additionalServiceBookings;
	}

	public void addAdditionalServiceBooking(AdditionalServiceBooking additionalServiceBooking) {
		this.additionalServiceBookings.add(additionalServiceBooking);
		addServiceToBooking(additionalServiceBooking);
	}

	public void addServiceToBooking(AdditionalServiceBooking additionalServiceBooking) {
		for (Booking item : bookingList) {
			if (item.getReservationNo().equals(additionalServiceBooking.getReservationNumber())) {
				int index = 0;
				switch (additionalServiceBooking.getServiceType()) {
				case "RESTAURANT":
					index = 0;
					break;
				case "TAKEAWAY":
					index = 1;
					break;
				case "BABY_SITTING":
					index = 2;
					break;
				}
				Additional additional = services.get(index);
				int qty = (int) (additionalServiceBooking.getPayment() / additional.getPrice());
				BookingDetail newDtail = new BookingDetail(additional, "Service", new Date(), qty, LoginedUserEmail);
				item.getBookingDeatils().add(newDtail);
			}

		}

	}
}
