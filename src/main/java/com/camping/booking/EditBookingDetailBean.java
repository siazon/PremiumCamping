package com.camping.booking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import com.camping.additionalservices.AdditionalServiceBean;
import com.camping.common.DataCache;
import com.camping.packages.Additional;
import com.camping.packages.CampingPackage;

/**
 * @author Xiasong Chen A00291322
 * 
 * @date 10 Aug 2022 20:18:17
 * @version 1.0
 */
@ManagedBean(name = "editBookingDetailBean")
@SessionScoped
public class EditBookingDetailBean implements Serializable {
	@ManagedProperty(value = "#{AdditionalServicesBean}")
	AdditionalServiceBean additionalServicesBean;

	@PostConstruct
	public void init() {
		bookings = new ArrayList<>(DataCache.getSingleton().getBookingList());
		extras = new ArrayList<>(DataCache.getSingleton().getExtras());
		services = new ArrayList<>(DataCache.getSingleton().getServices());
		ArrayList<Additional> packagelist = new ArrayList<>(DataCache.getSingleton().getPackages());
		packages = new ArrayList<>();
		for (Additional pkg : packagelist) {
			if (!packages.contains(pkg.getName()))
				packages.add(pkg.getName());
		}
		locations = new ArrayList<>();
		locations.add("Center");
		locations.add("Lake Side");

	}

	private ArrayList<Booking> bookings;
	private Booking booking;
	private ArrayList<String> packages;
	private ArrayList<String> locations;

	private ArrayList<Additional> extras;
	private ArrayList<Additional> services;

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public ArrayList<String> getPackages() {
		return packages;
	}

	public void setPackages(ArrayList<String> packages) {
		this.packages = packages;
	}

	public ArrayList<String> getLocations() {
		return locations;
	}

	public void setLocations(ArrayList<String> locations) {
		this.locations = locations;
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

	public AdditionalServiceBean getAdditionalServicesBean() {
		return additionalServicesBean;
	}

	public void setAdditionalServicesBean(AdditionalServiceBean additionalServicesBean) {
		this.additionalServicesBean = additionalServicesBean;
	}

	public String GetDetail(String reservationNo) {
		bookings = new ArrayList<>(DataCache.getSingleton().getBookingList());
		for (Booking item : bookings) {
			if (item.getReservationNo().equals(reservationNo)) {
				setBooking(item);
				break;
			}
		}
		return "/BookingDetailEdit.xhtml?faces-redirect=true";
	}

	public String Checkin(String reservationNo) {
		ArrayList<Booking> tempbookings = DataCache.getSingleton().getBookingList();
		for (Booking item : tempbookings) {
			if (item.getReservationNo().equals(reservationNo)) {
				item.setState("CHECKIN");
				break;
			}
		}
		return "/modifyReservation.xhtml?faces-redirect=true";
	}

	public String Checkout(String reservationNo) {
		ArrayList<Booking> tempbookings = DataCache.getSingleton().getBookingList();
		for (Booking item : tempbookings) {
			if (item.getReservationNo().equals(reservationNo)) {
				item.setState("CHECKOUT");
				break;
			}
		}
		return "/modifyReservation.xhtml?faces-redirect=true";
	}

	public String AddAservice(String reservationNo) {
		for (Booking item : bookings) {
			if (item.getReservationNo().equals(reservationNo)) {
				if (additionalServicesBean != null) {
					additionalServicesBean.EditBookingService(item);
					break;
				}
			}
		}
		return "/AdditionalServices.xhtml?faces-redirect=true";
	}

	public String AddExtras(String reservationNo) {
		String loginedUserEmail = DataCache.getSingleton().getLoginedUserEmail();
		ArrayList<Booking> bookingList = DataCache.getSingleton().getBookingList();
		for (Booking item : bookingList) {
			if (item.getReservationNo().equals(reservationNo)) {

				for (Additional extra : extras) {
					if (extra.getQuantity() > 0) {
						BookingDetail newDtail = new BookingDetail(extra, "Extra", new Date(), extra.getQuantity(),
								loginedUserEmail);
						item.getBookingDeatils().add(newDtail);
					}
					extra.setQuantity(0);
				}

				break;
			}
		}

		return "/BookingDetailEdit.xhtml?faces-redirect=true";
	}

	public void valueChangePackage(ValueChangeEvent e) {
		String packageName = "Tent";
		if (e != null)
			packageName = e.getNewValue().toString();
		ArrayList<Additional> packagelist = new ArrayList<>(DataCache.getSingleton().getPackages());
		for (Additional a : packagelist) {
			CampingPackage pkg = (CampingPackage) a;
			String location = booking.getCampingPackage().getLocation();
			if (packageName.equals(pkg.getName()) && location.equals(pkg.getLocation())) {
				booking.setCampingPackage(pkg);
				break;
			}
		}
	}

	public void valueChangeLocation(ValueChangeEvent e) {
		String location = "Lake Side";
		if (e != null)
			location = e.getNewValue().toString();
		ArrayList<Additional> packagelist = new ArrayList<>(DataCache.getSingleton().getPackages());
		for (Additional a : packagelist) {
			CampingPackage pkg = (CampingPackage) a;
			String packageName = booking.getCampingPackage().getName();
			if (packageName.equals(pkg.getName()) && location.equals(pkg.getLocation())) {
				booking.setCampingPackage(pkg);
				break;
			}
		}
	}

	public String RemoveBookingDetail(String detailNo) {
		booking.getBookingDeatils().removeIf(e -> e.getDetailNo().equals(detailNo));
		System.out.println("detailNo:" + detailNo);
		return "/BookingDetailEdit.xhtml?faces-redirect=true";
	}

	public String OnSave() {
		System.out.println("OnSaave");
		DataCache.getSingleton().setBookingList(bookings);
		String loginedUserEmail = DataCache.getSingleton().getLoginedUserEmail();
		if (loginedUserEmail.indexOf("ait.ie") >= 0)
			return "/modifyReservation.xhtml?faces-redirect=true";
		else
			return "/BookingList.xhtml?faces-redirect=true";
	}

	public String pay(String reservationNumber) {
		System.out.println("Sending to payment reservation number: " + reservationNumber);
		return "checkout?faces-redirect=true&reservationNumber=" + reservationNumber;
	}
}
