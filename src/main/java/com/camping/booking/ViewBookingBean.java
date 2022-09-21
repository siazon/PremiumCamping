package com.camping.booking;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.camping.common.DataCache;

/**
 * @author Kate Goode A00290477
 
 * @version 1.0
 */

@ManagedBean(name = "viewBookingBean")
@SessionScoped

public class ViewBookingBean {
	public ViewBookingBean() {
		System.out.println("ViewBookingBean");
	}
	
	@PostConstruct
	public void init() {
		viewBookings = DataCache.getSingleton().getBookingList();// get data from fake database hardcoded values in DataCache
		System.out.println("bookings: " + viewBookings);

	}
	
	private ArrayList<Booking> viewBookings;

	public ArrayList<Booking> getBookings() {
		return viewBookings;
	}
	

	

	

}
