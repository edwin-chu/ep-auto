/**
 * File Name: AutoBasics.java<br>
 * Chu, Edwin<br>
 * Java Boot Camp Exercise<br>
 * Instructor: Jean-francois Nepton<br>
 * Created: Jun 28, 2017
 */
package com.sqa.ec.helpers;

import java.io.*;
import java.util.*;
import java.util.NoSuchElementException;

import org.apache.commons.io.*;
import org.openqa.selenium.*;
import org.testng.log4testng.*;

/**
 * AutoBasics //ADDD (description of class)
 * <p>
 * //ADDD (description of core fields)
 * <p>
 * //ADDD (description of core methods)
 *
 * @author Chu, Edwin
 * @version 1.0.0
 * @since 1.0
 */
public class AutoBasics
{

	public static List<WebElement> getByTagName(WebDriver driver, String tagName)
	{
		return driver.findElements(By.tagName(tagName));
	}

	public static List<WebElement> getCSSPropBasedElements(WebDriver driver, By locator, String prop, String value)
	{
		List<WebElement> elements = driver.findElements(locator);
		ArrayList<WebElement> filteredElements = new ArrayList<WebElement>();
		for (int i = 0; i < elements.size(); i++)
		{
			if (elements.get(i).getCssValue(prop).equalsIgnoreCase("value"))
			{
				filteredElements.add(elements.get(i));
			}
		}
		return filteredElements;
	}

	public static List<WebElement> getLinks(WebDriver driver)
	{
		return driver.findElements(By.tagName("a"));
	}

	public static List<WebElement> getPictures(WebDriver driver)
	{
		return driver.findElements(By.tagName("img"));
	}

	public static String getProp(String propName, String fileLocation, String fileName, Logger logger)
	{
		Properties props = new Properties();
		InputStream input;
		try
		{
			input = new FileInputStream(fileLocation + fileName);
			props.load(input);
		} catch (FileNotFoundException e)
		{
			logger.warn("Can not load config properties file because it was not found: " + fileName);
		} catch (IOException e)
		{
			logger.warn("Can not load config properties file can not be read: " + fileName);
		}
		return props.getProperty(propName);
	}

	public static List<String> getTextContents(WebDriver driver, By locator)
	{
		// *.getText()
		List<String> textElement = new ArrayList<String>();
		List<WebElement> elements = driver.findElements(locator);
		for (WebElement we : elements)
		{
			textElement.add(we.getText());
			System.out.println(we.getText());
		}
		return textElement;
	}

	public static boolean isElementPresent(WebDriver driver, By by, Logger logger)
	{
		try
		{
			WebElement element = driver.findElement(by);
			return true;
		} catch (NoSuchElementException e)
		{
			// TODO Auto-generated catch block
			logger.warn("Element was not found: " + by);
			return false;
		}
	}

	public static boolean takeScreenshot(String fileLocation, String fileName, WebDriver driver, Logger logger)
	{
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try
		{
			FileUtils.copyFile(srcFile, new File(fileLocation + "/" + fileName + " .png"));
			return true;
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			logger.warn("Screenshot " + fileName + " was not captured to disk correctly.");
			return false;
		}
	}
}
