package com.camping.Account;
/*
 * Author: Aleksandra Marjanovic;
 * A00303157
 */
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

import com.camping.additionalservices.AdditionalServiceBooking;
import com.camping.booking.Booking;
import com.camping.common.DataCache;
import com.paypal.base.rest.PayPalRESTException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
@ManagedBean
@SessionScoped
public class AuthorizePaymentBean {
	private String reservationNumber;
	private String registrationId;
	private String packageName;
	private double totalAmount;

	public String getReservationNumber() {
		return reservationNumber;
	}

	public void setReservationNumber(String reservationNumber) {
		this.reservationNumber = reservationNumber;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getTotalAmount() {
		DecimalFormat df = new DecimalFormat("#.00");
		df.format(totalAmount);
		return df.format(totalAmount);
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void onLoad() {
		System.out.println("Reservation number is: " + getReservationNumber());
		ArrayList<Booking> bookings = new ArrayList<>(DataCache.getSingleton().getBookingList());
		boolean bookingFound = false;
		for (Booking item : bookings) {
			System.out.println("Booking reservation number: " + item.getReservationNo());
			System.out.println("Amount: " + item.getAmount());
			System.out.println("Package: " + item.getCampingPackage().getName());
			System.out.println("------------------------------------------------------------------");
			if (item.getReservationNo().equals(reservationNumber)) {
				bookingFound = true;
				setRegistrationId(item.getReservationNo());
				setPackageName(item.getCampingPackage().getName());
				setTotalAmount(item.getAmount());
				System.out.println("Alex bookings");
//				break; Commented out for testing to print all bookings from list
			}
		}
		
		if (bookingFound == false) {
			System.out.println("Alex additionalServices");
			ArrayList<AdditionalServiceBooking> additionalServices = new ArrayList<>(DataCache.getSingleton().getAdditionalServiceBookings());
			for (AdditionalServiceBooking item : additionalServices) {
				if (item.getId().equals(reservationNumber)) {
					setRegistrationId(item.getId());
					setPackageName(item.getServiceName());
					setTotalAmount(item.getPayment());
					break;
				}
			}
			
		}
	}

	public String checkOut() throws IOException{
		float floatAmount = (float) totalAmount;
		
		try {
			System.out.println("ALEX amount is: " + floatAmount);
			OrderDetailsBean orderDetail = new OrderDetailsBean(registrationId, packageName, floatAmount);
            PaymentServices paymentServices = new PaymentServices();
            String approvalLink = paymentServices.authorizePayment(orderDetail);
            FacesContext.getCurrentInstance().
            getExternalContext().redirect(approvalLink);   
        } catch (PayPalRESTException ex) {
        	ex.printStackTrace();
        }
		return null;
	}

}
