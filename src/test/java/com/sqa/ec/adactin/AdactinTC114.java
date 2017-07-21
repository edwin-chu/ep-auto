package com.sqa.ec.adactin;

import org.openqa.selenium.*;
import org.testng.annotations.*;

public class AdactinTC114 extends AdactinTest
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
		checkOrderNumber();
	}

	public void checkOrderNumber()
	{
		WebElement orderNumber = getDriver().findElement(By.id("order_no"));
		String orderNum = orderNumber.getAttribute("value");
		if (orderNum.isEmpty())
		{
			getLog().error("Test Failed:  Order number is NOT generated.");
		} else
		{
			System.out.println("Test Success.  Order number found.");
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
