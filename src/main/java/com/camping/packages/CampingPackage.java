/*
 *
 *  Author: Thuduhenage Laleesha Wijetunga
 *  
 */
package com.camping.packages;

/**
 * @author Thuduhenage Laleesha Wijetunga
 *
 */
public class CampingPackage extends Additional {
	private String location;
	private int adultsPax;
	private int kidsPax;
	private String description;
	private String priceWithDecimal;
	
	public CampingPackage() {
		super();
	}

	public CampingPackage(String id, String name, double price, String imgPath, String location) {
		super(id, name, price, imgPath);
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String desc) {
		this.description = desc;
	}
	
	public String getPriceWithDecimal() {
		return this.priceWithDecimal;
	}

	public void setPriceWithDecimal(String priceDec) {
		this.priceWithDecimal = priceDec;
	}
	
	public int getAdultsPax() {
		return this.adultsPax;
	}

	public void setAdultsPax(int adults) {
		this.adultsPax = adults;
	}
	
	public int getKidsPax() {
		return this.kidsPax;
	}

	public void setKidsPax(int kids) {
		this.kidsPax = kids;
	}

	@Override
	public String toString() {
		return 	super.toString() 
				+ String.format("location: %s ", this.location)
				+ String.format("adults: %d ", this.adultsPax)
				+ String.format("kids: %d ", this.kidsPax); 
	}
}
