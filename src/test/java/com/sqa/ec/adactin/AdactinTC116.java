package com.sqa.ec.adactin;

import org.testng.*;
import org.testng.annotations.*;

public class AdactinTC116 extends AdactinTest
{

	public void checkBookingAndItinerary(String bLoc, String bHotel, String bRType, String bRooms, String bInDate, String bOutDate, String bPPN, String bFinalBill, String bFName,
			String bLName, String orderId)
	{
		String location = retItinLocation(orderId);
		String hotel = retItinHotelName(orderId);
		String roomType = retItinRoomType(orderId);
		String numRooms = retItinNumRooms(orderId);
		String arrDate = retItinArrDate(orderId);
		String depDate = retItinDepDate(orderId);
		String pricePerNight = retItinPPN(orderId);
		String finalPrice = retItinFinalBill(orderId);
		String firstName = retItinFirstName(orderId);
		String lastName = retItinLastName(orderId);
		compareLocation(bLoc, location);
		compareHotel(bHotel, hotel);
		// compareRoomType(bRType, roomType);
		compareNumRooms(bRooms, numRooms);
		compareArrivalDate(bInDate, arrDate);
		compareDepartureDate(bOutDate, depDate);
		comparePPN(bPPN, pricePerNight);
		// compareFinalBill(bFinalBill, finalPrice);
		compareFirstName(bFName, firstName);
		// compareLastName(bLName, lastName);
	}

	@Test(dataProvider = "dp")
	public void checkData(String locationName, String hotelName, String roomType, String numRooms, int inDateFromToday, int outDateFromToday, String numAdult, String numChildren)
	{
		getLog().info("Booking a hotel room!");
		selectLocation(locationName);
		selectHotel(hotelName);
		selectRoomType(roomType);
		selectNumRooms(numRooms);
		enterCheckInDate(inDateFromToday);
		enterCheckOutDate(outDateFromToday);
		selectNumAdult(numAdult);
		selectNumChildren(numChildren);
		search();
		makeSelection();
		enterNameAndAddress();
		enterCCInfo();
		bookHotel();
		// get detail info from booking confirmation page
		String locName = retBookingLocation();
		String hName = retBookingHotel();
		String type = retBookingRoomType();
		String rooms = retBookingTotalRooms();
		String checkInDate = retBookingArrDate();
		String checkOutDate = retBookingDepDate();
		String adult = retBookingAdult();
		String children = retBookingChildren();
		String ppn = retBookingPricePerNight();
		String totalPrice = reBookingTotalPrice();
		String gst = retBookingGST();
		String finalBill = retBookingFinalBill();
		String fName = retBookingFirstName();
		String lName = retBookingLastName();
		String address = retBookingAddress();
		String orderNumber = retBookingOrderNum();
		selectMyItinerary();
		// get order code, parse out int value only then parse back to String
		// and pass as parameter
		String orderID = retItinOrderInfo(orderNumber);
		int parseOrderID = Integer.valueOf(orderID.replaceAll("[^\\d]+", ""));
		System.out.println(parseOrderID);
		// compare booking confirmation page info vs data itinerary page info
		checkBookingAndItinerary(locName, hName, type, rooms, checkInDate, checkOutDate, ppn, finalBill, fName, lName, String.valueOf(parseOrderID));
	}

	public void compareArrivalDate(String bookingPage, String itinPage)
	{
		Assert.assertEquals(bookingPage, itinPage);
	}

	public void compareDepartureDate(String bookingPage, String itinPage)
	{
		Assert.assertEquals(bookingPage, itinPage);
	}

	public void compareFinalBill(String bookingPage, String itinPage)
	{
		float bookingFinalBill = Float.valueOf(bookingPage.replaceAll("[^\\d.]+|\\.[?!\\d]", ""));
		float itinFinalBill = Float.valueOf(itinPage.replaceAll("[^\\d.]+|\\.[?!\\d]", ""));
		Assert.assertEquals(String.valueOf(bookingFinalBill), String.valueOf(itinFinalBill));
	}

	public void compareFirstName(String bookingPage, String itinPage)
	{
		Assert.assertEquals(bookingPage, itinPage);
	}

	public void compareHotel(String bookingPage, String itinPage)
	{
		Assert.assertEquals(bookingPage, itinPage);
	}

	public void compareLastName(String bookingPage, String itinPage)
	{
		Assert.assertEquals(bookingPage, itinPage);
	}

	public void compareLocation(String bookingPage, String itinPage)
	{
		Assert.assertEquals(bookingPage, itinPage);
	}

	public void compareNumRooms(String bookingPage, String itinPage)
	{
		int bookingRooms = Integer.valueOf(bookingPage.replaceAll("[^\\d]+", ""));
		int itinRooms = Integer.valueOf(itinPage.replaceAll("[^\\d]+", ""));
		Assert.assertEquals(String.valueOf(bookingRooms), String.valueOf(itinRooms));
	}

	public void comparePPN(String bookingPage, String itinPage)
	{
		int bookingPPN = Integer.valueOf(bookingPage.replaceAll("[^\\d]+", ""));
		int itinPPN = Integer.valueOf(itinPage.replaceAll("[^\\d]+", ""));
		Assert.assertEquals(String.valueOf(bookingPPN), String.valueOf(itinPPN));
	}

	public void compareRoomType(String bookingPage, String itinPage)
	{
		Assert.assertEquals(bookingPage, itinPage);
	}

	@DataProvider(name = "dp")
	public Object[][] dp()
	{
		return new Object[][]
		{ new Object[]
				{ "Sydney", "Hotel Creek", "Standard", "2 - Two", 0, 1, "1 - One", "0 - Zero" } };
	}
}
