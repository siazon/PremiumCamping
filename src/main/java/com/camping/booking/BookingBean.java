package com.camping.booking;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.context.RequestContext;

import com.camping.common.DataCache;
import com.camping.common.User;
import com.camping.packages.Additional;
import com.camping.packages.CampingPackage;

@ManagedBean(name = "BookingBean")
@SessionScoped
public class BookingBean {
	private User username;
	private String location;
	private String type;
	private int numberLinen, numberPack, numberTowel, numberDuvet;
	private Date checkInDate;
	private Date checkOutDate;
	

	// getters and setters
	public User getUsername() {
		return username;
	}

	public void setUsername(User username) {
		this.username = username;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	
	public int getNumberLinen() {
		return numberLinen;
	}

	public void setNumberLinen(int numberLinen) {
		this.numberLinen = numberLinen;
	}

	public int getNumberPack() {
		return numberPack;
	}

	public void setNumberPack(int numberPack) {
		this.numberPack = numberPack;
	}

	public int getNumberTowel() {
		return numberTowel;
	}

	public void setNumberTowel(int numberTowel) {
		this.numberTowel = numberTowel;
	}

	public int getNumberDuvet() {
		return numberDuvet;
	}

	public void setNumberDuvet(int numberDuvet) {
		this.numberDuvet = numberDuvet;
	}

	public String getSelecetedLocation() {
		return selecetedLocation;
	}

	public void setSelecetedLocation(String selecetedLocation) {
		this.selecetedLocation = selecetedLocation;
	}

	public String getSelecetedPackage() {
		return selecetedPackage;
	}

	public void setSelecetedPackage(String selecetedPackage) {
		this.selecetedPackage = selecetedPackage;
	}

	private String selecetedLocation;
	private String selecetedPackage;
	private Booking booking;
	private ArrayList<String> packages;
	private ArrayList<String> locations;
	private ArrayList<Booking> bookings = new ArrayList<Booking>();
	private ArrayList<Additional> extras;

	public ArrayList<Additional> getExtras() {
		return extras;
	}

	public void setExtras(ArrayList<Additional> extras) {
		this.extras = extras;
	}
	
	

	// Define getter

	@PostConstruct
	public void init() {
		// get data from fake database hardcoded values in DataCache
		bookings = new ArrayList<>(DataCache.getSingleton().getBookingList());
		extras = new ArrayList<>(DataCache.getSingleton().getExtras());
		ArrayList<Additional> packagelist = new ArrayList<>(DataCache.getSingleton().getPackages());
		packages = new ArrayList<>();
		for (Additional pkg : packagelist) {
			if (!packages.contains(pkg.getName()))
				packages.add(pkg.getName());
		}
		locations = new ArrayList<>();
		locations.add("Center");
		locations.add("Lake Side");
		locations.add("Forest Side");
		locations.add("Lake Side");
		locations.add("Mountain side");
		locations.add("Glamping village");
		
	}

	public ArrayList<Booking> getBookings() {
		return bookings;
	}

	// define a setter
	public void setBookings(ArrayList<Booking> bookings) {
		this.bookings = bookings;
	}

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

	public void addBooking() {
		Date now = new Date();
		ArrayList<User> userList = DataCache.getSingleton().getUserList();
		for (User user : userList) {
			if (user.getEmail().equals(DataCache.getSingleton().getLoginedUserEmail())) {
				username = user;
				break;
			}
		}
		CampingPackage cp = new CampingPackage("", type, 200, "tent.jpg", location);
		ArrayList<Additional> packagelist = new ArrayList<>(DataCache.getSingleton().getPackages());
		for (Additional a : packagelist) {
			CampingPackage pkg = (CampingPackage) a;
			if (selecetedPackage.equals(pkg.getName()) && selecetedLocation.equals(pkg.getLocation())) {
				cp = pkg;
				break;
			}
		}
		if(username == null) {
			System.out.print("User can't be null");
			return;
		}

		booking = new Booking("R" + now.getTime(), username, cp, new Date(), checkInDate, checkOutDate, "Reserved",
				null);
		bookings.add(booking);
		//System.out.println(booking);
		//System.out.println(bookings.size());
		//DataCache.getSingleton().setBookingList(bookings);
		

	}

	
	public void addExtraToBooking() {
		String loginedUserEmail = DataCache.getSingleton().getLoginedUserEmail();
		ArrayList<Booking> bookingList = DataCache.getSingleton().getBookingList();
		int index = bookingList.size() - 1;
		bookingList.get(index);
		Booking item = bookingList.get(index);
		System.out.println(item);
		
		for (Additional extra : extras) {
			if (extra.getQuantity() > 0) {
				BookingDetail newDtail = new BookingDetail(extra, "Extra", new Date(), extra.getQuantity(),
						loginedUserEmail);
				item.getBookingDeatils().add(newDtail);
			}
			extra.setQuantity(0);
		}
		
		addMessage("Confirmed", "Extras added to booking");
		
	}
	

	public void valueChangeLocation(ValueChangeEvent e) {
		//String location = e.getNewValue().toString();
		//ArrayList<Additional> packageList = new ArrayList<>(DataCache.getSingleton().getPackages());
		String location = "Lake Side";
		if (e != null)
			location = e.getNewValue().toString();
		ArrayList<Additional> packageList = new ArrayList<>(DataCache.getSingleton().getPackages());
		for (Additional a : packageList) {
			CampingPackage pkg = (CampingPackage) a;
			String packageName = booking.getCampingPackage().getName();
			if (packageName.equals(pkg.getName()) && location.equals(pkg.getLocation())) {
				booking.getCampingPackage();
				break;
			}
		}
	}
	
	
	public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	public String OnSave() {
		DataCache.getSingleton().setBookingList(bookings);
		addMessage("Confirmed", "Details saved");
		return "Details saved";
	}

	


}
