/**
 * File Name: AdactinTC120.java<br>
 * Chu, Edwin<br>
 * Java Boot Camp Exercise<br>
 * Instructor: Jean-francois Nepton<br>
 * Created: Jul 21, 2017
 */
package com.sqa.ec.adactin;

import org.testng.annotations.*;

/**
 * AdactinTC120 //ADDD (description of class)
 * <p>
 * //ADDD (description of core fields)
 * <p>
 * //ADDD (description of core methods)
 *
 * @author Chu, Edwin
 * @version 1.0.0
 * @since 1.0
 */
public class AdactinTC120 extends AdactinTest
{

	@Test(dataProvider = "dp")
	public void checkPageTitle(String locationName, String hotelName, String roomType, String numRooms, int inDateFromToday, int outDateFromToday, String numAdult,
			String numChildren)
	{
		getLog().info("Booking a hotel room!");
		// search hotel page
		String searchHotelPage = getDriver().getTitle();
		System.out.println(searchHotelPage);
		selectLocation(locationName);
		selectHotel(hotelName);
		selectRoomType(roomType);
		selectNumRooms(numRooms);
		enterCheckInDate(inDateFromToday);
		enterCheckOutDate(outDateFromToday);
		selectNumAdult(numAdult);
		selectNumChildren(numChildren);
		search();
		// search result page
		String searchResultPage = getDriver().getTitle();
		System.out.println(searchResultPage);
		makeSelection();
		// booking page
		String bookingPage = getDriver().getTitle();
		System.out.println(bookingPage);
		enterNameAndAddress();
		enterCCInfo();
		bookHotel();
		// booking confirmation page
		String bookingConfirmation = getDriver().getTitle();
		System.out.println(bookingConfirmation);
		selectMyItinerary();
		// booked itinerary page
		String bookedItinerary = getDriver().getTitle();
		System.out.println(bookedItinerary);
		System.out.println("Test Complete");
	}

	@DataProvider(name = "dp")
	public Object[][] dp()
	{
		return new Object[][]
		{ new Object[]
				{ "Sydney", "Hotel Creek", "Standard", "2 - Two", 0, 1, "1 - One", "0 - Zero" } };
	}
}
