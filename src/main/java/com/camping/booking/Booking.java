package com.camping.booking;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import com.camping.common.User;
import com.camping.packages.CampingPackage;

/**
 * @author Xiasong Chen A00291322
 * 
 * @date 2022-8-7 12:09:46 PM
 * @version 1.0
 */
public class Booking {
	private String reservationNo;
	private User user;
	private CampingPackage campingPackage;
	private Date bookingTime;
	private Date checkinTime;
	private Date checkoutTime;
	private String state;// Reserved,Paid,Checkin,Checkout,Done,Canceled
	private double amount;
	private boolean bookingDisable;
	private String detailSummary = "";
	private String confirmationNumber;
	private String amountString;

	private ArrayList<BookingDetail> bookingDeatils;

	public Booking(String reservationNo, User user, CampingPackage campingPackage, Date bookingTime, Date checkinTime,
			Date checkoutTime, String state, ArrayList<BookingDetail> bookingDeatils) {
		super();
		this.reservationNo = reservationNo;
		this.user = user;
		this.campingPackage = campingPackage;
		this.bookingTime = bookingTime;
		this.checkinTime = checkinTime;
		this.checkoutTime = checkoutTime;
		setState(state);
		this.amount = getAmount();
		this.bookingDeatils = bookingDeatils;
	}

	public String getAmountString() {
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(amount);
	}

	public void setAmountString(String amountString) {
		this.amountString = amountString;
	}

	public String getReservationNo() {
		return reservationNo;
	}

	public void setReservationNo(String reservationNo) {
		this.reservationNo = reservationNo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CampingPackage getCampingPackage() {
		return campingPackage;
	}

	public void setCampingPackage(CampingPackage campingPackage) {
		this.campingPackage = campingPackage;
	}

	public Date getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(Date bookingTime) {
		this.bookingTime = bookingTime;
	}

	public Date getCheckinTime() {
		return checkinTime;
	}

	public void setCheckinTime(Date checkinTime) {
		this.checkinTime = checkinTime;
	}

	public Date getCheckoutTime() {
		return checkoutTime;
	}

	public void setCheckoutTime(Date checkoutTime) {
		this.checkoutTime = checkoutTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		if ("CANCELED".equals(state)) {
			bookingDisable = true;
		} else {
			bookingDisable = false;
		}
		this.state = state;
	}

	public double getAmount() {
		if (campingPackage != null || checkoutTime != null || checkinTime != null) {
			int outDay = checkoutTime.getDate();
			int inDay = checkinTime.getDate();
			int days = outDay - inDay;
			if (days <= 0)
				days = 1;
			amount = campingPackage.getPrice() * days;
			if (bookingDeatils != null) {
				for (BookingDetail item : bookingDeatils) {
					amount += (item.getMyPackage().getPrice() * item.getQuantity());
				}
			}
		}
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public ArrayList<BookingDetail> getBookingDeatils() {
		if (bookingDeatils == null)
			bookingDeatils = new ArrayList<>();
		return bookingDeatils;
	}

	public void setBookingDeatils(ArrayList<BookingDetail> bookingDeatils) {
		this.bookingDeatils = bookingDeatils;
	}

	public boolean isBookingDisable() {
		return bookingDisable;
	}

	public void setBookingDisable(boolean bookingDisable) {
		this.bookingDisable = bookingDisable;
	}

	public String getDetailSummary() {
		int serviceQty = 0, extraQty = 0;
		if (bookingDeatils != null) {
			for (BookingDetail item : bookingDeatils) {
				if ("Service".equals(item.getpType())) {
					serviceQty++;
				}
				if ("Extra".equals(item.getpType())) {
					extraQty++;
				}
			}
			if (serviceQty > 0) {
				detailSummary = serviceQty + " Services";
				if (extraQty > 0) {
					detailSummary += " and " + extraQty + " Extras";
				}
			} else if (extraQty > 0) {
				detailSummary = extraQty + " Extras";
			} else {
				detailSummary = "Noting.";
			}
		}
		return detailSummary;
	}

	public void setDetailSummary(String detailSummary) {
		this.detailSummary = detailSummary;
	}

	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	@Override
	public String toString() {
		return String.format(
				"Booking [reservationNo: %s, user: %s, bookingTime: %s, checkinTime: %s, checkoutTime: %s, state: %s, amount: %s,\r\n"
						+ "bookingDeatils: %s]",
				reservationNo, user, bookingTime, checkinTime, checkoutTime, state, amount, bookingDeatils);
	}
}
