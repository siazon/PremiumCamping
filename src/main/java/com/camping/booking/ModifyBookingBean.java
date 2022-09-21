package com.camping.booking;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.camping.common.DataCache;
import com.camping.common.User;
import com.camping.packages.CampingPackage;

@ManagedBean(name = "modifyBookingBean")
@SessionScoped
public class ModifyBookingBean {

	private ArrayList<Booking> bookings;
	private Booking booking;
	private String searchContent;

	@PostConstruct
	public void init() {
		bookings = new ArrayList<>(DataCache.getSingleton().getBookingList());
	}

	public ModifyBookingBean() {
		System.out.println("ModifyBookingBean");
	}

	

	public ArrayList<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(ArrayList<Booking> bookings) {
		this.bookings = bookings;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public String getSearchContent() {
		return searchContent;
	}

	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}

	public String GetDetail() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String action = getCountryParam(fc);
		for (Booking item : bookings) {
			if (item.getReservationNo().equals(action)) {
				if (item.isBookingDisable()) {
					return "";
				}
				setBooking(item);
				break;
			}
		}
		return "/modifyReservation.xhtml?faces-redirect=true";
	}

	public String getCountryParam(FacesContext fc) {
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("action");

	}

	public String Search() {
		if (null == (searchContent) || "".equals(searchContent)) {
			bookings = new ArrayList<>(DataCache.getSingleton().getBookingList());
		} else {
			ArrayList<Booking> allbookings = new ArrayList<>(DataCache.getSingleton().getBookingList());
			bookings.clear();
			for (Booking item : allbookings) {
				if (item.getReservationNo().equals(searchContent)) {
					bookings.add(item);
				}
			}
		}
		return "/modifyReservation.xhtml?faces-redirect=true";
	}

	public String Cancel() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String reservationNo = getCountryParam(fc);
		for (Booking item : bookings) {
			if (item.getReservationNo().equals(reservationNo)) {
				item.setState("CANCELED");
			}
		}
		DataCache.getSingleton().setBookingList(bookings);
		return "/modifyReservation.xhtml?faces-redirect=true";
	}

	public String Rebook() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String reservationNo = getCountryParam(fc);
		for (Booking item : bookings) {
			if (item.getReservationNo().equals(reservationNo)) {
				item.setState("RESERVED");
			}
		}
		DataCache.getSingleton().setBookingList(bookings);
		return "/modifyReservation.xhtml?faces-redirect=true";
	}


}
