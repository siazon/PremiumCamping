package com.camping.booking;

import java.util.Date;
import java.util.UUID;

import com.camping.packages.Additional;

/**
 * @author Xiasong Chen A00291322
 * 
 * @date 2022-8-7 1:23:49 AM
 * @version 1.0
 */
public class BookingDetail {
	private String detailNo;
	private Additional myPackage;
	private String pType;// Package,Service,Extra
	private Date time;
	private int quantity;
	private String createBy;

	/**
	 * 
	 * @param myPackage
	 * @param pType
	 * @param time
	 * @param createBy
	 */
	public BookingDetail(Additional myPackage, String pType, Date time, int quantity, String createBy) {
		super();
		detailNo = UUID.randomUUID().toString();
		this.myPackage = myPackage;
		this.pType = pType;
		this.time = time;
		this.quantity = quantity;
		this.createBy = createBy;
	}

	public String getDetailNo() {
		return detailNo;
	}

	public void setDetailNo(String detailNo) {
		this.detailNo = detailNo;
	}

	public Additional getMyPackage() {
		return myPackage;
	}

	public void setMyPackage(Additional myPackage) {
		this.myPackage = myPackage;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getpType() {
		return pType;
	}

	public void setpType(String pType) {
		this.pType = pType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return String.format("BookingDetail [myPackage: %s, pType: %s,quantity: %s, time: %s, createBy: %s]", myPackage,
				pType, quantity, time, createBy);
	}
}
