package com.camping.packages;

/**
 * @author Thuduhenage Laleesha Wijetunga
 *
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddPackageBeanTest {

	AddPackageBean addPack;

	@BeforeEach
	void setUp() {
		addPack = new AddPackageBean();

	}

	@Test // Number of Adults, when loading the form
	public void testGetPaxAdults() {
		assertEquals(0, addPack.getPaxAdults());
	}

	@Test // Number of Kids, when loading the form
	public void testGetPaxKids() {
		assertEquals(0, addPack.getPaxKids());
	}

	@Test // Description, when loading the form
	public void testGetDescription() {
		assertEquals("", addPack.getDescription());
	}

	@Test // Price, when loading the form
	public void testGetPrice() {
		assertEquals("", addPack.getPrice());
	}

	@Test // Package Type, when loading the form
	public void testGetPackageType() {
		assertEquals("", addPack.getPackageType());
	}

	@Test // Location name, when loading the form
	public void testGetSelectedLocation() {
		assertEquals("Select One", addPack.getSelectedLocation());
	}

	@Test // Get Selected location from the location list
	public void testGetLocations() {
		assertEquals("Forest View", addPack.getLocations().get(0));
		assertEquals("Lake Side View", addPack.getLocations().get(1));
		assertEquals("Center of Park", addPack.getLocations().get(2));
		assertEquals("Mountain View", addPack.getLocations().get(3));
		assertEquals("Glamping Village", addPack.getLocations().get(4));	

	}
	@Test//set Number of Adults
	public void testSetPaxAdults(){
		addPack.setPaxAdults(3);
		assertEquals(3,addPack.getPaxAdults());
	}
	@Test//set Number of Kids
	public void testSetPaxKids(){
		addPack.setPaxKids(2);
		assertEquals(2,addPack.getPaxKids());
	}
	
	@Test//set Description
	public void testSetDescription(){
		addPack.setDescription("Testing Description");
		assertEquals("Testing Description",addPack.getDescription());
	}
	@Test//set Price
	public void testSetPrice(){
		addPack.setPrice("530");
		assertEquals("530",addPack.getPrice());
	}

}
