package com.camping.booking;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.camping.common.DataCache;

/**
 * @author Xiasong Chen A00291322
 * 
 * @date 202-8-7 1:26:29 AM
 * @version 1.0
 */

@ManagedBean(name = "editBookingBean")
@SessionScoped
public class EditBookingBean implements Serializable {
	public EditBookingBean() {
		System.out.println("EditBookingBean");
	}

	public void onload() {
		ArrayList<Booking> Allbookings = new ArrayList<>(DataCache.getSingleton().getBookingList());// get data from
																									// mock database
		bookings = new ArrayList<>();
		String loginedUserEmail = DataCache.getSingleton().getLoginedUserEmail();
		if (loginedUserEmail == null)
			return;
		for (Booking booking : Allbookings) {
			if (loginedUserEmail.equals(booking.getUser().getEmail())) {
				bookings.add(booking);
			}
		}
		if (SearchContent != null && !"".equals(SearchContent)) {
			ArrayList<Booking> tempbookings = new ArrayList<>(bookings);
			bookings.clear();
			for (Booking item : tempbookings) {
				if (item.getReservationNo().indexOf(SearchContent) >= 0)
					bookings.add(item);
			}
		}
	}

	private ArrayList<Booking> bookings;

	public ArrayList<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(ArrayList<Booking> bookings) {
		this.bookings = bookings;
	}

	private String SearchContent;

	public String getSearchContent() {
		return SearchContent;
	}

	public void setSearchContent(String searchContent) {
		SearchContent = searchContent;
	}

	public String bookingsFilter() {
		if ("".equals(SearchContent)) {
			bookings = new ArrayList<>(DataCache.getSingleton().getBookingList());// get data from fake database
		} else {
			ArrayList<Booking> tempbookings = new ArrayList<>(bookings);
			bookings.clear();
			for (Booking item : tempbookings) {
				if (item.getReservationNo().indexOf(SearchContent) >= 0)
					bookings.add(item);
			}
		}
		return "/BookingList.xhtml?faces-redirect=true";
	}

	public String Cancel(String reservationNo) {
		ArrayList<Booking> tempbookings = DataCache.getSingleton().getBookingList();
		for (Booking item : tempbookings) {
			if (item.getReservationNo().equals(reservationNo)) {
				item.setState("CANCELED");
				break;
			}
		}
		return "/BookingList.xhtml?faces-redirect=true";
	}

}
