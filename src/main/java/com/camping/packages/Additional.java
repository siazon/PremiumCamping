package com.camping.packages;

import java.text.DecimalFormat;

/**
 * @author Xiasong Chen A00291322
 * 
 * @date 2022-8-7 1:17:20 AM
 * @version 1.0
 */
public class Additional {
	private String id;
	private String name;
	private double price;
	private String imgPath;
	private int quantity;
	private String priceString;

	public Additional() {
	}

	public Additional(String id, String name, double price, String imgPath) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.imgPath = imgPath;
		this.quantity = 0;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPriceString() {
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(price);
	}

	public void setPriceString(String priceString) {
		this.priceString = priceString;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return super.toString() + String.format("id: %s, name: %s, price: %s, imgPath: %s", id, name, price, imgPath);
	}
}
