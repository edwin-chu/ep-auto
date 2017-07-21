package com.sqa.ec.adactin;

import org.openqa.selenium.*;
import org.testng.annotations.*;

public class AdactinTC115 extends AdactinTest
{

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
		checkItinearyField();
	}

	public void checkHotelNameField()
	{
		WebElement hName = getDriver().findElement(By.id("hotel_name"));
		boolean hotelName = isEditable(hName);
		if (hotelName)
		{
			getLog().error("Test Failed: Location field is editable.");
		} else
		{
			System.out.println("Location field is NOT editable.");
		}
	}

	public void checkItinearyField()
	{
		checkHotelNameField();
		checkLocationField();
	}

	public void checkLocationField()
	{
		WebElement location = getDriver().findElement(By.id("location"));
		boolean loc = isEditable(location);
		if (loc)
		{
			getLog().error("Test Failed: Hotel Name field is editable.");
		} else
		{
			System.out.println("Hotel Name field is NOT editable.");
		}
	}

	@DataProvider(name = "dp")
	public Object[][] dp()
	{
		return new Object[][]
		{ new Object[]
				{ "Sydney", "Hotel Creek", "Standard", "2 - Two", 0, 1, "1 - One", "0 - Zero" } };
	}
}
