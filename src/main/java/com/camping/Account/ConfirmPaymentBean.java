package com.camping.Account;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.camping.additionalservices.AdditionalServiceBooking;
import com.camping.booking.Booking;
import com.camping.common.DataCache;

/*
 * Author: Aleksandra Marjanovic
 * A00303157
 */

@ManagedBean(name = "confirmPaymentBean")
@SessionScoped
public class ConfirmPaymentBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String reservationNumber;

	private Booking myBooking;

	private String confirmationNumber;

	public ConfirmPaymentBean() {
		// TODO Auto-generated constructor stub
	}

	public String getReservationNumber() {
		return reservationNumber;
	}

	public void setReservationNumber(String reservationNumber) {
		this.reservationNumber = reservationNumber;
	}

	public Booking getMyBooking() {
		return myBooking;
	}

	public void setMyBooking(Booking myBooking) {
		this.myBooking = myBooking;
	}

	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	public void onLoad() {
		System.out.println("Reservation number is: " + getReservationNumber());
		confirmationNumber = "C" + (int) (Math.random() * 900000 + 10000);
		ArrayList<Booking> bookings = new ArrayList<>(DataCache.getSingleton().getBookingList());
		boolean bookingFound = false;
		for (Booking item : bookings) {
			if (item.getReservationNo().equals(reservationNumber)) {
				bookingFound = true;
				item.setState("PAID");
				item.setConfirmationNumber(confirmationNumber);
				break;
			}
		}

		if (bookingFound == false) {
			ArrayList<AdditionalServiceBooking> additionalServices = new ArrayList<>(
					DataCache.getSingleton().getAdditionalServiceBookings());
			for (AdditionalServiceBooking item : additionalServices) {
				if (item.getId().equals(reservationNumber)) {
					item.setConfirmationNumber(confirmationNumber);
					refreshState(item.getReservationNumber());
					break;
				}
			}

		}
	}

	public void refreshState(String _reservationNumber) {
		ArrayList<Booking> bookings = new ArrayList<>(DataCache.getSingleton().getBookingList());
		for (Booking item : bookings) {
			if (item.getReservationNo().equals(_reservationNumber)) {
				item.setState("PAID");
				item.setConfirmationNumber(confirmationNumber);
				break;
			}
		}
	}

}
