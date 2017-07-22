package com.sqa.ec.adactin;

import org.openqa.selenium.*;
import org.testng.annotations.*;

public class AdactinTC119 extends AdactinTest
{

	public void cancelOrder(String orderNum)
	{
		WebElement cancel = getDriver().findElement(By.id("btn_id_" + orderNum));
		cancel.click();
		getDriver().switchTo().alert().accept();
		System.out.println("Order is cancelled.");
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
		String orderNumber = retBookingOrderNum();
		selectMyItinerary();
		searchOrderId(orderNumber);
		findOrder(orderNumber);
		String orderID = retItinOrderInfo(orderNumber);
		int parseOrderID = Integer.valueOf(orderID.replaceAll("[^\\d]+", ""));
		cancelOrder(String.valueOf(parseOrderID));
		confirmCancel(orderNumber);
	}

	public void confirmCancel(String orderCode)
	{
		try
		{
			WebElement order = getDriver().findElement(By.cssSelector("input[value='" + orderCode + "']"));
			getLog().error("Order Cancel Failed:  Order code is found.");
		} catch (NoSuchElementException e)
		{
			System.out.println("Test Success: Cancel Confirm. Order not found.");
		}
	}

	@DataProvider(name = "dp")
	public Object[][] dp()
	{
		return new Object[][]
		{ new Object[]
				{ "Sydney", "Hotel Creek", "Standard", "2 - Two", 0, 1, "1 - One", "0 - Zero" } };
	}

	public void findOrder(String orderCode)
	{
		try
		{
			WebElement order = getDriver().findElement(By.cssSelector("input[value='" + orderCode + "']"));
			System.out.println("Order code is found.");
		} catch (NoSuchElementException e)
		{
			getLog().error("Test Failed:  Search query for existing order does not show any result.");
		}
	}
}
