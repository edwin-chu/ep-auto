package com.sqa.ec.auto;

import java.util.concurrent.*;

import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.safari.*;
import org.testng.annotations.*;

public class BasicTest extends Core
{

	public BasicTest(String baseUrl)
	{
		super(baseUrl);
	}

	@BeforeClass(enabled = true, groups = "chrome")
	public void setUpChrome()
	{
		// Set system property to use Chrome driver
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
		// Setup the driver to use Chrome
		System.out.println("Before Setting Chrome Driver");
		setDriver(new ChromeDriver());
		System.out.println("After setting Chrome Driver");
		// Set the base URL for this test
		// this.baseUrl = "https://www.amazon.com/";
		// Set an implicit wait of up to 30 seconds
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Maximize the window
		getDriver().manage().window().maximize();
	}

	@BeforeClass(enabled = true, groups = "firefox")
	public void setUpFirefox()
	{
		// Setup the driver to use Firefox
		System.out.println("Before Setting FF Driver");
		setDriver(new FirefoxDriver());
		System.out.println("After setting FF Driver");
		// Set the base URL for this test
		// this.baseUrl = "https://www.amazon.com/";
		// Set an implicit wait of up to 30 seconds
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Maximize the window
		getDriver().manage().window().maximize();
	}

	@BeforeClass(enabled = true, groups = "safari")
	public void setUpSafari()
	{
		// Setup the driver to use Safari
		System.out.println("Before Setting Safari Driver");
		setDriver(new SafariDriver());
		System.out.println("After setting Safari Driver");
		// Set the base URL for this test
		// this.baseUrl = "https://www.amazon.com/";
		// Set an implicit wait of up to 30 seconds
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Maximize the window
		getDriver().manage().window().maximize();
	}

	@BeforeMethod()
	public void setupTest()
	{
		// goto base URL
		// getDriver().get(getBaseURL());
	}

	@AfterClass
	public void tearDown()
	{
		// quit closes all instances of the browser.
		getDriver().quit();
		// close method would close current instance only.
		// ex: driver.close();
	}
}
