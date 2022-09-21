package com.camping.additionalservices;

import java.time.LocalDate;
import java.util.Date;

public class AdditionalServiceBooking {

	private String id;
	private Date date;
	private String serviceName;
	private String serviceType;
	private int noOfItems;
	private String reservationNumber;
	private String bookedForEmail;
	private String bookedByEmail;
	private double payment;
	private static int counter;
	String timeSlot;
	private String confirmationNumber;

	public AdditionalServiceBooking(String serviceName, String serviceType, int noOfGuests,
			String reservationNumber, String bookedForEmail, String bookedByEmail, double payment, String timeSlot) {
		this.serviceName = serviceName;
		this.serviceType = serviceType;
		this.noOfItems = noOfGuests;
		this.reservationNumber = reservationNumber;
		this.bookedByEmail = bookedByEmail;
		this.bookedForEmail = bookedForEmail;
		this.payment = payment;
		this.timeSlot = timeSlot;
		this.id = "ASB00" + ++counter;
	}
	
	public AdditionalServiceBooking(String serviceType, int noOfItems,
			String reservationNumber, String bookedForEmail, String bookedByEmail, double payment, String timeSlot) {
		
		this.serviceType = serviceType;
		this.noOfItems = noOfItems;
		this.reservationNumber = reservationNumber;
		this.bookedByEmail = bookedByEmail;
		this.bookedForEmail = bookedForEmail;
		this.payment = payment;
		this.timeSlot = timeSlot;
		this.id = "ASB00" + ++counter;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public int getNoOfItems() {
		return noOfItems;
	}

	public void setNoOfItems(int noOfItems) {
		this.noOfItems = noOfItems;
	}

	public String getReservationNumber() {
		return reservationNumber;
	}

	public void setReservationNumber(String reservationNumber) {
		this.reservationNumber = reservationNumber;
	}

	public String getBookedForEmail() {
		return bookedForEmail;
	}

	public void setBookedForEmail(String bookedForEmail) {
		this.bookedForEmail = bookedForEmail;
	}

	public String getBookedByEmail() {
		return bookedByEmail;
	}

	public void setBookedByEmail(String bookedByEmail) {
		this.bookedByEmail = bookedByEmail;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}

	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	@Override
	public String toString() {
		return "(serviceName: " + serviceName + ", serviceType: " + serviceType + ", noOfItems: " + noOfItems + ", reservationNumber: "
		+ reservationNumber + ", payment: "  + payment + ", timeSlot: " + timeSlot + ", date: "  + date + " )";
	}
}