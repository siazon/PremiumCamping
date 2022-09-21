package com.camping.additionalservices;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.camping.booking.Booking;
import com.camping.common.DataCache;

/**
 * 
 * @author Lankani Wijesinghe A00267225
 *
 */
@ManagedBean(name = "AdditionalServicesBean")
@SessionScoped
public class AdditionalServiceBean implements Serializable {

	private static final long serialVersionUID = 1L;

	String reservationNumber;
	boolean reservationFound;
	String serviceType;
	List<String> timeSlots;
	List<String> durations;
	double total;

	String bookingId;

	String selectedTimeSlotRestaurant;
	Date bookingDateRestaurant;
	int noOfItemRestaurant;
	double totalRestaurant;

	String selectedTimeSlotTakeaway;
	Date bookingDateTakeaway;
	int noOfItemTakeaway;
	double totalTakeaway;

	String selectedTimeSlotBabySitting;
	Date bookingDateBabySitting;
	int noOfItemBabySitting;
	double totalBabySitting;

	final double PRICE_RESTAURANT = 25.0;
	final double PRICE_TAKEAWAY = 10.0;
	final double PRICE_BABYSITTING = 10.0;

//	addAdditionalServiceBooking
	@PostConstruct
	public void init() {
		// Remove this value after link
		bookingId = "R01";
		timeSlots = new ArrayList<String>();
		timeSlots.add("6:00 pm");
		timeSlots.add("6:30 pm");
		timeSlots.add("7:00 pm");
		timeSlots.add("7:30 pm");
		timeSlots.add("8:00 pm");
		timeSlots.add("8:30 pm");
		timeSlots.add("9:00 pm");
		timeSlots.add("9:30 pm");

		durations = new ArrayList<String>();
		durations.add("10 am - 11 am");
		durations.add("11 am - 11 am");
		durations.add("12 am - 1 pm");
		durations.add("1 pm - 2 pm");
		durations.add("2 pm - 3 pm");
		durations.add("3 pm - 4 pm");
		durations.add("4 pm - 5 pm");
		durations.add("5 pm - 6 pm");
	}

	public String getReservationNumber() {
		return reservationNumber;
	}

	public void setReservationNumber(String reservationNumber) {
		this.reservationNumber = reservationNumber;
	}

	public boolean isReservationFound() {
		return reservationFound;
	}

	public void setReservationFound(boolean isReservationFound) {
		this.reservationFound = isReservationFound;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public List<String> getTimeSlots() {
		return timeSlots;
	}

	public void setTimeSlots(List<String> timeSlots) {
		this.timeSlots = timeSlots;
	}

	public String getSelectedTimeSlotRestaurant() {
		return selectedTimeSlotRestaurant;
	}

	public void setSelectedTimeSlotRestaurant(String selectedTimeSlotRestaurant) {
		this.selectedTimeSlotRestaurant = selectedTimeSlotRestaurant;
	}

	public Date getBookingDateRestaurant() {
		return bookingDateRestaurant;
	}

	public void setBookingDateRestaurant(Date bookingDateRestaurant) {
		this.bookingDateRestaurant = bookingDateRestaurant;
	}

	public int getNoOfItemRestaurant() {
		return noOfItemRestaurant;
	}

	public void setNoOfItemRestaurant(int noOfItemRestaurant) {
		this.noOfItemRestaurant = noOfItemRestaurant;
	}

	public String getSelectedTimeSlotTakeaway() {
		return selectedTimeSlotTakeaway;
	}

	public void setSelectedTimeSlotTakeaway(String selectedTimeSlotTakeaway) {
		this.selectedTimeSlotTakeaway = selectedTimeSlotTakeaway;
	}

	public Date getBookingDateTakeaway() {
		return bookingDateTakeaway;
	}

	public void setBookingDateTakeaway(Date bookingDateTakeaway) {
		this.bookingDateTakeaway = bookingDateTakeaway;
	}

	public int getNoOfItemTakeaway() {
		return noOfItemTakeaway;
	}

	public void setNoOfItemTakeaway(int noOfItemTakeaway) {
		this.noOfItemTakeaway = noOfItemTakeaway;
	}

	public String getSelectedTimeSlotBabySitting() {
		return selectedTimeSlotBabySitting;
	}

	public void setSelectedTimeSlotBabySitting(String selectedTimeSlotBabySitting) {
		this.selectedTimeSlotBabySitting = selectedTimeSlotBabySitting;
	}

	public Date getBookingDateBabySitting() {
		return bookingDateBabySitting;
	}

	public void setBookingDateBabySitting(Date bookingDateBabySitting) {
		this.bookingDateBabySitting = bookingDateBabySitting;
	}

	public int getNoOfItemBabySitting() {
		return noOfItemBabySitting;
	}

	public void setNoOfItemBabySitting(int noOfItemBabySitting) {
		this.noOfItemBabySitting = noOfItemBabySitting;
	}

	public double getTotalRestaurant() {
		return totalRestaurant;
	}

	public void setTotalRestaurant(double totalRestaurant) {
		this.totalRestaurant = totalRestaurant;
	}

	public double getTotalTakeaway() {
		return totalTakeaway;
	}

	public void setTotalTakeaway(double totalTakeaway) {
		this.totalTakeaway = totalTakeaway;
	}

	public double getTotalBabySitting() {
		return totalBabySitting;
	}

	public void setTotalBabySitting(double totalBabySitting) {
		this.totalBabySitting = totalBabySitting;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<String> getDurations() {
		return durations;
	}

	public void setDurations(List<String> durations) {
		this.durations = durations;
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public boolean validateReservationNumber(String reservationNumber) {
		boolean result = false;
		ArrayList<Booking> bookings = DataCache.getSingleton().getBookingList();
		for (Booking booking : bookings) {
			// check confirmation number
			if (reservationNumber.equals(booking.getConfirmationNumber())) {
				result = true;
				setBookingId(booking.getReservationNo());
			}
		}
		return result;
	}

	public void EditBookingService(Booking booking) {
		init();
		setReservationNumber(booking.getReservationNo());// set Reservation number
//		searchReservationByChen();// Do Search
	}

	public void searchReservation() {
		if (validateReservationNumber(reservationNumber) || reservationNumber.equals("R01")) {
			System.out.println("Reservation number " + reservationNumber + " found.");
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage successMessage = new FacesMessage("Reservation number is valid. You can now make a booking.");
			successMessage.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage("successMsg", successMessage);
			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.addCallbackParam("msg", "Error");
			reservationFound = true;
			requestContext.addCallbackParam("reservationFound", reservationFound);

		} else {
			// Reservation could not be found
			System.out.println("Reservation number could not be found.");
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage errorMessage = new FacesMessage("Reservation number is not valid.");
			errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage("errorMsg", errorMessage);
			// callback to Javascript
			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.addCallbackParam("msg", "Error");
			reservationFound = false;
			requestContext.addCallbackParam("reservationFound", reservationFound);
		}
	}

	public void cancelReservationBooking() throws IOException {
		System.out.println("Additional service booking canceling....");
		RequestContext requestContext = RequestContext.getCurrentInstance();
		reservationFound = false;
		reservationNumber = null;
		bookingDateTakeaway = null;

		bookingDateRestaurant = null;
		selectedTimeSlotRestaurant = "";
		noOfItemRestaurant = 0;

		bookingDateTakeaway = null;
		selectedTimeSlotTakeaway = "";
		noOfItemTakeaway = 0;

		bookingDateBabySitting = null;
		selectedTimeSlotBabySitting = "";
		noOfItemBabySitting = 0;

		requestContext.addCallbackParam("reservationFound", reservationFound);
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
	}

	public boolean searchAvailability(Date date, String timeSlot) {
		boolean result = false;
		if (validateReservationNumber(reservationNumber)) {
			return false;
		}

		ArrayList<AdditionalServiceBooking> additionalServicesBookings = new ArrayList<>(
				DataCache.getSingleton().getAdditionalServiceBookings());

		for (AdditionalServiceBooking booking : additionalServicesBookings) {
			if (booking.getDate().compareTo(date) == 0) {
				// check any bookings on user selected date
				if (booking.getTimeSlot().equals(timeSlot)) {
					// time slot already booked
					result = false;
				} else {
					// time slot not booked, so available for booking
					result = true;
				}

			} else {
				// no booking on user selected date, so available for booking
				result = true;
			}
		}

		return result;
	}

	public void printAllBookings() {
//		System.out.println("****************************************************************\n");
		ArrayList<AdditionalServiceBooking> additionalServicesBookings = new ArrayList<>(
				DataCache.getSingleton().getAdditionalServiceBookings());
		for (AdditionalServiceBooking booking : additionalServicesBookings) {
			System.out.println(booking.toString());
		}
//		System.out.println("****************************************************************\n");
	}

	public void book(Date date, String timeSlot, AdditionalServiceBooking additionalServiceBooking) {
		if (searchAvailability(date, timeSlot)) {
			System.out.println("Able to book");

			// save to data cache
			DataCache.getSingleton().addAdditionalServiceBooking(additionalServiceBooking);
			// print
			printAllBookings();

			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage successMessage = new FacesMessage("Your booking was successful");
			successMessage.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage("errorMsg22", successMessage);
			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.addCallbackParam("msg", "OK");
			requestContext.addCallbackParam("id", additionalServiceBooking.getId());

		} else {
			System.out.println("Unable to book");
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage errorMessage = new FacesMessage(
					"Selected time slot is not available. Try different date or time slot.");
			errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage("errorMsg", errorMessage);
		}
	}

	public void restaurantTableBooking() {
		serviceType = "RESTAURANT";
//		System.out.println("\n\nHappy Camper Restaurant booking started with following data.");
//		System.out.println("confirmationNumber " + reservationNumber);
//		System.out.println("bookingId " + bookingId);
//		System.out.println("serviceType " + serviceType);
//		System.out.println("bookingDate " + bookingDateRestaurant);
//		System.out.println("selectedTimeSlot " + selectedTimeSlotRestaurant);
//		System.out.println("noOfItem " + noOfItemRestaurant);
//		System.out.println("\n");

		double total = PRICE_RESTAURANT * noOfItemRestaurant;
		AdditionalServiceBooking additionalServiceBooking = new AdditionalServiceBooking(serviceType, serviceType,
				noOfItemRestaurant, bookingId, "lankanipw@lanki.com", "lankanipw@lanki.com", total,
				selectedTimeSlotRestaurant);
		additionalServiceBooking.setDate(bookingDateRestaurant);

		book(bookingDateRestaurant, selectedTimeSlotRestaurant, additionalServiceBooking);
	}

	public void takeawayBooking() {
		serviceType = "TAKEAWAY";
//		System.out.println("\n\nTakeaway booking started with following data.");
//		System.out.println("confirmationNumber " + reservationNumber);
//		System.out.println("bookingId " + bookingId);
//		System.out.println("serviceType " + serviceType);
//		System.out.println("bookingDateTakeaway " + bookingDateTakeaway);
//		System.out.println("selectedTimeSlotTakeaway " + selectedTimeSlotTakeaway);
//		System.out.println("noOfItemTakeaway " + noOfItemTakeaway);
//		System.out.println("\n");

		double total = PRICE_TAKEAWAY * noOfItemTakeaway;
		AdditionalServiceBooking additionalServiceBooking = new AdditionalServiceBooking(serviceType, serviceType,
				noOfItemTakeaway, bookingId, "lankanipw@lanki.com", "lankanipw@lanki.com", total,
				selectedTimeSlotTakeaway);
		additionalServiceBooking.setDate(bookingDateTakeaway);

		book(bookingDateTakeaway, selectedTimeSlotTakeaway, additionalServiceBooking);
	}

	public void babySittingBooking() {
		serviceType = "BABY_SITTING";
//		System.out.println("\n\nHappy Kids Baby Sitting booking started with following data.");
//		System.out.println("confirmationNumber " + reservationNumber);
//		System.out.println("bookingId " + bookingId);
//		System.out.println("serviceType " + serviceType);
//		System.out.println("bookingDateBabySitting " + bookingDateBabySitting);
//		System.out.println("selectedTimeSlotBabySitting " + selectedTimeSlotBabySitting);
//		System.out.println("noOfItemBabySitting " + noOfItemBabySitting);
//		System.out.println("\n");

		double total = PRICE_BABYSITTING * noOfItemBabySitting;
		AdditionalServiceBooking additionalServiceBooking = new AdditionalServiceBooking(serviceType, serviceType,
				noOfItemBabySitting, bookingId, "lankanipw@lanki.com", "lankanipw@lanki.com", total,
				selectedTimeSlotBabySitting);
		additionalServiceBooking.setDate(bookingDateBabySitting);

		book(bookingDateBabySitting, selectedTimeSlotBabySitting, additionalServiceBooking);
	}

	public void changeTotalRestaurant(SelectEvent event) {
		double total = PRICE_RESTAURANT * (double) event.getObject();
//		System.out.println("total :" + total);
		totalRestaurant = total;
//		System.out.println("totalRestaurant :" + totalRestaurant);
	}

	public void changeTotalTakeaway(SelectEvent event) {
		double total = PRICE_TAKEAWAY * (double) event.getObject();
//		System.out.println("total :" + total);
		totalTakeaway = total;
//		System.out.println("totalTakeaway :" + totalTakeaway);
	}

	public void changeTotalBabySitting(SelectEvent event) {
		double total = PRICE_BABYSITTING * (double) event.getObject();
//		System.out.println("total :" + total);
		totalBabySitting = total;
//		System.out.println("totalRestaurant :" + totalBabySitting);
	}
}
