package com.sqa.ec.adactin;

import org.openqa.selenium.*;
import org.testng.*;
import org.testng.annotations.*;

public class AdactinTC113 extends AdactinTest
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
		String name = collectHotelSelectPage();
		String loc = collectLocSelectPage();
		String rType = collectRTypeSelectPage();
		String nRooms = collectNRoomsSelectPage();
		String inDate = collectInDateSelectPage();
		String outDate = collectOutDateSelectPage();
		makeSelection();
		compareData(name, loc, rType, nRooms, inDateFromToday, outDateFromToday);
	}

	public String collectHotelSelectPage()
	{
		WebElement hName = getDriver().findElement(By.id("hotel_name_0"));
		String name = hName.getAttribute("value");
		return name;
	}

	public String collectInDateSelectPage()
	{
		WebElement checkInDate = getDriver().findElement(By.id("arr_date_0"));
		String inDate = checkInDate.getAttribute("value");
		return inDate;
	}

	public String collectLocSelectPage()
	{
		WebElement location = getDriver().findElement(By.id("location_0"));
		String loc = location.getAttribute("value");
		return loc;
	}

	public String collectNRoomsSelectPage()
	{
		WebElement nRooms = getDriver().findElement(By.id("rooms_0"));
		String nrooms = nRooms.getAttribute("value");
		return nrooms;
	}

	public String collectOutDateSelectPage()
	{
		WebElement checkOutDate = getDriver().findElement(By.id("dep_date_0"));
		String outDate = checkOutDate.getAttribute("value");
		return outDate;
	}

	public String collectRTypeSelectPage()
	{
		WebElement rType = getDriver().findElement(By.id("room_type_0"));
		String rtype = rType.getAttribute("value");
		return rtype;
	}

	public void compareData(String name, String loc, String rType, String nRooms, int in, int out)
	{
		WebElement hName = getDriver().findElement(By.id("hotel_name_dis"));
		String hotelName = hName.getAttribute("value");
		Assert.assertEquals(hotelName, name);
		WebElement lName = getDriver().findElement(By.id("location_dis"));
		String locName = lName.getAttribute("value");
		Assert.assertEquals(locName, loc);
		WebElement room = getDriver().findElement(By.id("room_type_dis"));
		String roomType = room.getAttribute("value");
		Assert.assertEquals(roomType, rType);
		WebElement totalRooms = getDriver().findElement(By.id("room_num_dis"));
		String numRooms = totalRooms.getAttribute("value");
		int expected = Integer.valueOf(nRooms.replaceAll("[^\\d]+", ""));
		int actual = Integer.valueOf(numRooms.replaceAll("[^\\d]+", ""));
		Assert.assertEquals(String.valueOf(actual), String.valueOf(expected));
		int totalDays = out - in;
		WebElement numDays = getDriver().findElement(By.id("total_days_dis"));
		String days = numDays.getAttribute("value");
		int nDays = Integer.valueOf(days.replaceAll("[^\\d]+", ""));
		Assert.assertEquals(String.valueOf(nDays), String.valueOf(totalDays));
	}

	@DataProvider(name = "dp")
	public Object[][] dp()
	{
		return new Object[][]
		{ new Object[]
				{ "Sydney", "Hotel Creek", "Standard", "2 - Two", 0, 1, "1 - One", "0 - Zero" } };
	}
}
