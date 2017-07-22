package com.sqa.ec.adactin;

import org.openqa.selenium.*;
import org.testng.annotations.*;

public class AdactinTC115 extends AdactinTest
{

	public void checkAddressField()
	{
		WebElement address = getDriver().findElement(By.id("address"));
		boolean add = isEditable(address);
		if (add)
		{
			getLog().error("Test Failed: Address field is editable.");
		} else
		{
			System.out.println("Address field is NOT editable.");
		}
	}

	public void checkAdultField()
	{
		WebElement adultRoom = getDriver().findElement(By.id("adults_room"));
		boolean totalAdult = isEditable(adultRoom);
		if (totalAdult)
		{
			getLog().error("Test Failed: Number of adult field is editable.");
		} else
		{
			System.out.println("Number of adult field is NOT editable.");
		}
	}

	public void checkArrDateField()
	{
		WebElement arrDate = getDriver().findElement(By.id("arrival_date"));
		boolean inDate = isEditable(arrDate);
		if (inDate)
		{
			getLog().error("Test Failed: Arrival date field is editable.");
		} else
		{
			System.out.println("Arrival date field is NOT editable.");
		}
	}

	public void checkChildrenField()
	{
		WebElement childRoom = getDriver().findElement(By.id("children_room"));
		boolean totalChildren = isEditable(childRoom);
		if (totalChildren)
		{
			getLog().error("Test Failed: Number of children field is editable.");
		} else
		{
			System.out.println("Number of children field is NOT editable.");
		}
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
		checkItinearyField();
	}

	public void checkDepDateField()
	{
		WebElement depDate = getDriver().findElement(By.id("departure_text"));
		boolean outDate = isEditable(depDate);
		if (outDate)
		{
			getLog().error("Test Failed: Departure date field is editable.");
		} else
		{
			System.out.println("Departure date field is NOT editable.");
		}
	}

	public void checkFinalBillField()
	{
		WebElement finalBill = getDriver().findElement(By.id("final_price"));
		boolean finalCost = isEditable(finalBill);
		if (finalCost)
		{
			getLog().error("Test Failed: Final bill field is editable.");
		} else
		{
			System.out.println("Final bill field is NOT editable.");
		}
	}

	public void checkFirstNameField()
	{
		WebElement fName = getDriver().findElement(By.id("first_name"));
		boolean firstName = isEditable(fName);
		if (firstName)
		{
			getLog().error("Test Failed: First name field is editable.");
		} else
		{
			System.out.println("First name field is NOT editable.");
		}
	}

	public void checkGSTField()
	{
		WebElement gst = getDriver().findElement(By.id("gst"));
		boolean tax = isEditable(gst);
		if (tax)
		{
			getLog().error("Test Failed: GST field is editable.");
		} else
		{
			System.out.println("GST field is NOT editable.");
		}
	}

	public void checkHotelNameField()
	{
		WebElement hName = getDriver().findElement(By.id("hotel_name"));
		boolean hotelName = isEditable(hName);
		if (hotelName)
		{
			getLog().error("Test Failed: Hotel name field is editable.");
		} else
		{
			System.out.println("Hotel name field is NOT editable.");
		}
	}

	public void checkItinearyField()
	{
		checkHotelNameField();
		checkLocationField();
		checkRoomTypeField();
		checkNumRoomField();
		checkArrDateField();
		checkDepDateField();
		checkAdultField();
		checkChildrenField();
		checkPricePerNightField();
		checkTotalPriceField();
		checkGSTField();
		checkFinalBillField();
		checkFirstNameField();
		checkAddressField();
		checkOrderNumField();
	}

	public void checkLocationField()
	{
		WebElement location = getDriver().findElement(By.id("location"));
		boolean loc = isEditable(location);
		if (loc)
		{
			getLog().error("Test Failed: Location field is editable.");
		} else
		{
			System.out.println("Location field is NOT editable.");
		}
	}

	public void checkNumRoomField()
	{
		WebElement nRooms = getDriver().findElement(By.id("total_rooms"));
		boolean numRooms = isEditable(nRooms);
		if (numRooms)
		{
			getLog().error("Test Failed: Total rooms field is editable.");
		} else
		{
			System.out.println("Total rooms field is NOT editable.");
		}
	}

	public void checkOrderNumField()
	{
		WebElement orderNumber = getDriver().findElement(By.id("order_no"));
		boolean order = isEditable(orderNumber);
		if (order)
		{
			getLog().error("Test Failed: Order number field is editable.");
		} else
		{
			System.out.println("Order number field is NOT editable.");
		}
	}

	public void checkPricePerNightField()
	{
		WebElement ppn = getDriver().findElement(By.id("price_night"));
		boolean pricePerNight = isEditable(ppn);
		if (pricePerNight)
		{
			getLog().error("Test Failed: Price per night field is editable.");
		} else
		{
			System.out.println("Price per night field is NOT editable.");
		}
	}

	public void checkRoomTypeField()
	{
		WebElement rType = getDriver().findElement(By.id("room_type"));
		boolean room = isEditable(rType);
		if (room)
		{
			getLog().error("Test Failed: Type of room field is editable.");
		} else
		{
			System.out.println("Type of room field is NOT editable.");
		}
	}

	public void checkTotalPriceField()
	{
		WebElement totalPrice = getDriver().findElement(By.id("total_price"));
		boolean totalCost = isEditable(totalPrice);
		if (totalCost)
		{
			getLog().error("Test Failed: Total price field is editable.");
		} else
		{
			System.out.println("Total price field is NOT editable.");
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
