package com.camping.Account;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import javax.faces.bean.ManagedBean;
/*
 * Author: Aleksandra Marjanovic
 * A00303157
 */

@ManagedBean (name ="OrderDetails")
public class OrderDetailsBean {
	 private String packageId;
	 private String packageName;
	 private float total;
	 private SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss z");
	
	 
    public OrderDetailsBean () {}

	public OrderDetailsBean(String packageId, String packageName, float total) {
		super();
		this.packageId = packageId;
		this.packageName = packageName;
		
		this.total = total;
	}
	

	public String getFormatDate() {
		String date = formatDate.format(new Date());
		return date;
	}
	public String getPackageId() {
		return packageId;
	}
	
	public String getPackageName() {
		return packageName;
	}
	public String getTotal() {
		System.out.println("ALEX         total: " + String.format("%.2f", total));
		return String.format("%.2f", total);
	}
}
