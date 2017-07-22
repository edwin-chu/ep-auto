/**
 * File Name: AdactinTest.java<br>
 * Chu, Edwin<br>
 * Java Boot Camp Exercise<br>
 * Instructor: Jean-francois Nepton<br>
 * Created: Jul 1, 2017
 */
package com.sqa.ec.adactin;

import java.text.*;
import java.util.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import com.sqa.ec.helpers.*;

/**
 * AdactinTest //ADDD (description of class)
 * <p>
 * //ADDD (description of core fields)
 * <p>
 * //ADDD (description of core methods)
 *
 * @author Chu, Edwin
 * @version 1.0.0
 * @since 1.0
 */
public abstract class AdactinTest extends AbstractLoginTest
{

	/**
	 * @param baseUrl
	 */
	public AdactinTest()
	{
		super("http://adactin.com/HotelApp");
	}

	public void bookHotel()
	{
		WebElement book = getDriver().findElement(By.id("book_now"));
		book.click();
	}

	public void enterCCInfo()
	{
		WebElement ccNum = getDriver().findElement(By.id("cc_num"));
		ccNum.clear();
		ccNum.sendKeys(getProp("ccnum"));
		String creditCardType = getProp("cctype");
		WebElement ccTypeDropdown = getDriver().findElement(By.id("cc_type"));
		ccTypeDropdown.click();
		boolean foundCardType = isValuePresent(creditCardType, ccTypeDropdown);
		Select selectCCTypeDropdown = new Select(ccTypeDropdown);
		if (foundCardType)
		{
			selectCCTypeDropdown.selectByVisibleText(creditCardType);
		} else
		{
			getLog().error("Test Failed: Credit card type " + creditCardType + " not found in the list.");
		}
		String month = getProp("expmonth");
		WebElement monthDropdown = getDriver().findElement(By.id("cc_exp_month"));
		monthDropdown.click();
		boolean foundMonth = isValuePresent(month, monthDropdown);
		Select selectMonthDropdown = new Select(monthDropdown);
		if (foundMonth)
		{
			selectMonthDropdown.selectByVisibleText(month);
		} else
		{
			getLog().info("Test Failed: Selected month " + month + " not found in the list.");
		}
		String year = getProp("expyear");
		WebElement yearDropdown = getDriver().findElement(By.id("cc_exp_year"));
		yearDropdown.click();
		boolean foundYear = isValuePresent(year, yearDropdown);
		Select selectYearDropdown = new Select(yearDropdown);
		if (foundYear)
		{
			selectYearDropdown.selectByVisibleText(year);
		} else
		{
			getLog().info("Test Failed: Selected year " + year + " is not available in the list.");
		}
		WebElement cvvNum = getDriver().findElement(By.id("cc_cvv"));
		cvvNum.clear();
		cvvNum.sendKeys(getProp("cvvnum"));
	}

	public String enterCheckInDate(int checkInFromToday)
	{
		// parameter checkInFromToday represent # of dates from today's date
		// find check in date element and clear the field
		WebElement checkInDate = getDriver().findElement(By.id("datepick_in"));
		checkInDate.clear();
		// declare format for date
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		// declare Calander instance and set today's date
		Calendar checkIn = Calendar.getInstance();
		checkIn.setTime(new Date());
		// add days specified in checkInFromToday
		checkIn.add(Calendar.DATE, checkInFromToday);
		// send formatted resulting date in checkInDate field
		checkInDate.sendKeys(dateFormat.format(checkIn.getTime()));
		// return date (to use in some test)
		return dateFormat.format(checkIn.getTime());
	}

	public String enterCheckOutDate(int checkOutFromToday)
	{
		// parameter checkOutFromToday represent # of dates from today's date
		// find checkout date element and clear the field
		WebElement checkOutDate = getDriver().findElement(By.id("datepick_out"));
		checkOutDate.clear();
		// declare format for date
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		// declare Calander instance and set today's date
		Calendar checkOut = Calendar.getInstance();
		checkOut.setTime(new Date());
		// add days specified in checkOutFromToday
		checkOut.add(Calendar.DATE, checkOutFromToday);
		// send formatted resulting date in checkOutDate field
		checkOutDate.sendKeys(dateFormat.format(checkOut.getTime()));
		// return date (to use in some test)
		return dateFormat.format(checkOut.getTime());
	}

	public void enterNameAndAddress()
	{
		WebElement fName = getDriver().findElement(By.id("first_name"));
		fName.clear();
		fName.sendKeys(getProp("guestfname"));
		WebElement lName = getDriver().findElement(By.id("last_name"));
		lName.clear();
		lName.sendKeys(getProp("guestlname"));
		WebElement address = getDriver().findElement(By.id("address"));
		address.clear();
		address.sendKeys(getProp("billingaddress"));
	}

	public void goToBookedItin()
	{
		WebElement bookedItin = getDriver().findElement(By.cssSelector("a[href='BookedItinerary.php']"));
		bookedItin.click();
	}

	public boolean isEditable(WebElement element)
	{
		try
		{
			element.clear();
			return true;
		} catch (InvalidElementStateException e)
		{
			System.out.println("Unable to clear text.");
			return false;
		}
	}

	public boolean isValuePresent(String value, WebElement element)
	{
		boolean valuePresent = false;
		// get a list of all values in specified dropdown element
		Select selectDropdown = new Select(element);
		List<WebElement> listOfAllValue = selectDropdown.getOptions();
		// iterate the list and find a match for parameter "value"
		for (WebElement i : listOfAllValue)
		{
			System.out.println(i.getText());
			// set return value to true if a match is found
			if (value.equalsIgnoreCase(i.getText()))
			{
				valuePresent = true;
				System.out.println("Value Found!");
				break;
			}
		}
		return valuePresent;
	}

	@Override
	public void login(String username, String password)
	{
		this.takeScreenshot("Pre Login");
		WebElement usernameField = getDriver().findElement(By.id("username"));
		WebElement passwordField = getDriver().findElement(By.id("password"));
		WebElement loginButton = getDriver().findElement(By.id("login"));
		usernameField.clear();
		usernameField.sendKeys(username);
		passwordField.clear();
		passwordField.sendKeys(password);
		loginButton.click();
		this.takeScreenshot("Post Login");
	}

	@Override
	public void logout()
	{
		WebElement logoutField = getDriver().findElement(By.cssSelector("a[href='Logout.php']"));
		logoutField.click();
	}

	public void makeSelection()
	{
		// select hotel and continue
		WebElement radialSelect = getDriver().findElement(By.id("radiobutton_0"));
		radialSelect.click();
		WebElement continueButton = getDriver().findElement(By.id("continue"));
		continueButton.click();
	}

	public String reBookingTotalPrice()
	{
		WebElement bTotalPrice = getDriver().findElement(By.id("total_price"));
		String bTotal = bTotalPrice.getAttribute("value");
		return bTotal;
	}

	public String retBookingAddress()
	{
		WebElement bAddress = getDriver().findElement(By.id("address"));
		String bAdd = bAddress.getAttribute("value");
		return bAdd;
	}

	public String retBookingAdult()
	{
		WebElement bNumAdult = getDriver().findElement(By.id("adults_room"));
		String bAdult = bNumAdult.getAttribute("value");
		return bAdult;
	}

	public String retBookingArrDate()
	{
		WebElement bArrival = getDriver().findElement(By.id("arrival_date"));
		String bArr = bArrival.getAttribute("value");
		return bArr;
	}

	public String retBookingChildren()
	{
		WebElement bNumChildren = getDriver().findElement(By.id("children_room"));
		String bChildren = bNumChildren.getAttribute("value");
		return bChildren;
	}

	public String retBookingDepDate()
	{
		WebElement bDeparture = getDriver().findElement(By.id("departure_text"));
		String bDep = bDeparture.getAttribute("value");
		return bDep;
	}

	public String retBookingFinalBill()
	{
		WebElement bFinalBill = getDriver().findElement(By.id("final_price"));
		String bFinal = bFinalBill.getAttribute("value");
		return bFinal;
	}

	public String retBookingFirstName()
	{
		WebElement bFirstName = getDriver().findElement(By.id("first_name"));
		String bFName = bFirstName.getAttribute("value");
		return bFName;
	}

	public String retBookingGST()
	{
		WebElement bGST = getDriver().findElement(By.id("gst"));
		String bTax = bGST.getAttribute("value");
		return bTax;
	}

	public String retBookingHotel()
	{
		WebElement bHotel = getDriver().findElement(By.id("hotel_name"));
		String bookingHotel = bHotel.getAttribute("value");
		return bookingHotel;
	}

	public String retBookingLastName()
	{
		WebElement bLastName = getDriver().findElement(By.id("last_name"));
		String bLName = bLastName.getAttribute("value");
		return bLName;
	}

	public String retBookingLocation()
	{
		WebElement bLocation = getDriver().findElement(By.id("location"));
		String bLoc = bLocation.getAttribute("value");
		return bLoc;
	}

	public String retBookingOrderNum()
	{
		WebElement orderNum = getDriver().findElement(By.id("order_no"));
		String order = orderNum.getAttribute("value");
		return order;
	}

	public String retBookingPricePerNight()
	{
		WebElement bPricePerNight = getDriver().findElement(By.id("price_night"));
		String bPPN = bPricePerNight.getAttribute("value");
		return bPPN;
	}

	public String retBookingRoomType()
	{
		WebElement bRoomType = getDriver().findElement(By.id("room_type"));
		String bRType = bRoomType.getAttribute("value");
		return bRType;
	}

	public String retBookingTotalRooms()
	{
		WebElement bTotalRooms = getDriver().findElement(By.id("total_rooms"));
		String bRooms = bTotalRooms.getAttribute("value");
		return bRooms;
	}

	public String retItinArrDate(String orderId)
	{
		WebElement itinArrDate = getDriver().findElement(By.id("arr_date_" + orderId));
		String itinArr = itinArrDate.getAttribute("value");
		System.out.println(itinArr);
		return itinArr;
	}

	public String retItinDepDate(String orderId)
	{
		WebElement itinDepDate = getDriver().findElement(By.id("dep_date_" + orderId));
		String itinDep = itinDepDate.getAttribute("value");
		System.out.println(itinDep);
		return itinDep;
	}

	public String retItinFinalBill(String orderId)
	{
		WebElement itinFinalBill = getDriver().findElement(By.id("total_price_" + orderId));
		String itinFBill = itinFinalBill.getAttribute("value");
		System.out.println(itinFBill);
		return itinFBill;
	}

	public String retItinFirstName(String orderId)
	{
		WebElement itinFirstName = getDriver().findElement(By.id("first_name_" + orderId));
		String itinFName = itinFirstName.getAttribute("value");
		System.out.println(itinFName);
		return itinFName;
	}

	public String retItinHotelName(String orderId)
	{
		WebElement itinHotelName = getDriver().findElement(By.id("hotel_name_" + orderId));
		String itinHotel = itinHotelName.getAttribute("value");
		System.out.println(itinHotel);
		return itinHotel;
	}

	public String retItinLastName(String orderId)
	{
		WebElement itinLastName = getDriver().findElement(By.id("last_name_" + orderId));
		String itinLName = itinLastName.getAttribute("value");
		System.out.println(itinLName);
		return itinLName;
	}

	public String retItinLocation(String orderId)
	{
		WebElement itinLocation = getDriver().findElement(By.id("location_" + orderId));
		String itinLoc = itinLocation.getAttribute("value");
		System.out.println(itinLoc);
		return itinLoc;
	}

	public String retItinNumRooms(String orderId)
	{
		WebElement itinNumRooms = getDriver().findElement(By.id("rooms_" + orderId));
		String itinRooms = itinNumRooms.getAttribute("value");
		System.out.println(itinRooms);
		return itinRooms;
	}

	public String retItinOrderInfo(String orderNum)
	{
		// get order code
		WebElement orderInfo = getDriver().findElement(By.cssSelector("input[value='" + orderNum + "']"));
		String orderId = orderInfo.getAttribute("name");
		return orderId;
	}

	public String retItinPPN(String orderId)
	{
		WebElement itinPPN = getDriver().findElement(By.id("price_night_" + orderId));
		String itinPrice = itinPPN.getAttribute("value");
		System.out.println(itinPrice);
		return itinPrice;
	}

	public String retItinRoomType(String orderId)
	{
		WebElement itinRoomType = getDriver().findElement(By.id("room_type_" + orderId));
		String itinRType = itinRoomType.getAttribute("value");
		System.out.println(itinRType);
		return itinRType;
	}

	public void search()
	{
		// click search button to search
		WebElement searchButton = getDriver().findElement(By.id("Submit"));
		searchButton.click();
	}

	public void searchOrderId(String order)
	{
		WebElement searchField = getDriver().findElement(By.id("order_id_text"));
		searchField.clear();
		searchField.sendKeys(order);
		WebElement goButton = getDriver().findElement(By.id("search_hotel_id"));
		goButton.click();
	}

	public void selectHotel(String hotel)
	{
		// select parameter hotel value from hotels dropdown
		WebElement hotelsDropdown = getDriver().findElement(By.id("hotels"));
		hotelsDropdown.click();
		// check parameter hotel value exist in the dropdown list
		boolean foundHotel = isValuePresent(hotel, hotelsDropdown);
		Select selectHotelsDropdown = new Select(hotelsDropdown);
		if (foundHotel)
		{
			selectHotelsDropdown.selectByValue(hotel);
		} else
		{
			getLog().error("Test Failed: Hotel name " + hotel + " not found in the list.");
		}
	}

	public void selectLocation(String loc)
	{
		// select parameter value loc from location dropdown
		WebElement locationDropdown = getDriver().findElement(By.id("location"));
		locationDropdown.click();
		// check parameter loc value exist in dropdown list
		boolean foundLocation = isValuePresent(loc, locationDropdown);
		Select selectLocDropdown = new Select(locationDropdown);
		if (foundLocation)
		{
			selectLocDropdown.selectByValue(loc);
		} else
		{
			getLog().error("Test Failed: Location " + loc + " not found in the list.");
		}
	}

	public void selectMyItinerary()
	{
		WebElement myItin = getDriver().findElement(By.id("my_itinerary"));
		myItin.click();
	}

	public void selectNumAdult(String numAdult)
	{
		// select parameter value numAdult from numer of adult dropdown
		WebElement numAdultDropdown = getDriver().findElement(By.id("adult_room"));
		numAdultDropdown.click();
		// check parameter numAdult value exist in dropdown list
		boolean foundNumAdult = isValuePresent(numAdult, numAdultDropdown);
		Select selectNumAdultDropdown = new Select(numAdultDropdown);
		if (foundNumAdult)
		{
			selectNumAdultDropdown.selectByVisibleText(numAdult);
		} else
		{
			getLog().error("Test Failed: Selection " + numAdult + "for number of adult is not available.");
		}
	}

	public void selectNumChildren(String numChildren)
	{
		// select parameter value numChildren from number of children dropdown
		WebElement numChildrenDropdown = getDriver().findElement(By.id("child_room"));
		numChildrenDropdown.click();
		// check parameter numChildren value exist in dropdown list
		boolean foundNumChildren = isValuePresent(numChildren, numChildrenDropdown);
		Select selectNumChildrenDropdown = new Select(numChildrenDropdown);
		if (foundNumChildren)
		{
			selectNumChildrenDropdown.selectByVisibleText(numChildren);
		} else
		{
			getLog().error("Test Failed! Selection " + numChildren + " for number of children is not available.");
		}
	}

	public void selectNumRooms(String numRooms)
	{
		// select parameter value numRooms from number of rooms dropdown
		WebElement numRoomsDropdown = getDriver().findElement(By.id("room_nos"));
		numRoomsDropdown.click();
		// check parameter numRooms value exists in dropdown list
		boolean foundNumRooms = isValuePresent(numRooms, numRoomsDropdown);
		Select selectNumRoomsDropdown = new Select(numRoomsDropdown);
		if (foundNumRooms)
		{
			selectNumRoomsDropdown.selectByVisibleText(numRooms);
		} else
		{
			getLog().error("Test Failed: Selected number of rooms " + numRooms + ", is not available.");
		}
	}

	public void selectRoomType(String roomType)
	{
		// select parameter value roomType from room type dropdown
		WebElement roomTypeDropdown = getDriver().findElement(By.id("room_type"));
		roomTypeDropdown.click();
		// check parameter roomType value exist in dropdown list
		boolean foundRoomType = isValuePresent(roomType, roomTypeDropdown);
		Select selectRoomTypeDropdown = new Select(roomTypeDropdown);
		if (foundRoomType)
		{
			selectRoomTypeDropdown.selectByValue(roomType);
		} else
		{
			getLog().error("Test Failed: Room type " + roomType + " not found in the list.");
		}
	}
}
