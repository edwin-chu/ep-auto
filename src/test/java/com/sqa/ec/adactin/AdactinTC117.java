package com.sqa.ec.adactin;

import org.openqa.selenium.*;
import org.testng.annotations.*;

public class AdactinTC117 extends AdactinTest
{

	@Test
	public void checkSearchQuery()
	{
		String orderId = "390G85AT4V";
		goToBookedItin();
		searchOrderId(orderId);
		confirmResult(orderId);
	}

	public void confirmResult(String orderCode)
	{
		try
		{
			WebElement order = getDriver().findElement(By.cssSelector("input[value='" + orderCode + "']"));
			System.out.println("Order code is found.");
		} catch (NoSuchElementException e)
		{
			getLog().error("Test Failed:  Search query for existing order does not show any result.");
		}
		WebElement result = getDriver().findElement(By.id("search_result_error"));
		String display = result.getText();
		System.out.println(display);
		int queryResult = Integer.valueOf(display.replaceAll("[^\\d]+", ""));
		if (queryResult == 1)
		{
			System.out.println("Test Success: 1 result found.");
		} else if (queryResult > 1)
		{
			System.out.println("Test Failed:  Multiple results are found.");
		}
	}
}
