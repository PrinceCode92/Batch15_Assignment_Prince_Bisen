package com.qa.automation.stepdefs;

import java.awt.Font;
import java.sql.DriverAction;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.layout.HtmlLayout.FontSize;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefs {

	private static final Logger logger = LogManager.getLogger(stepDefs.class); 

	WebDriver driver;
	String base_url = "http://automationpractice.com";
	int implictl_wait_timeout_in_sec = 20;
	Scenario scn;
	final String LogoButtenFontSize = "350*99";

	@Before
	public void setUp(Scenario scn)
	{
		this.scn = scn;
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(implictl_wait_timeout_in_sec, TimeUnit.SECONDS);
		logger.info("Browser invoked");
		scn.log("Browser invoked");
	}

	@After(order=1)
	public void tearDown() 
	{
		driver.quit();
		logger.info("Browser Closed");
		scn.log("Browser Closed");

	}

	@After(order=2)
	public void takeScreenShot(Scenario s)
	{
		if(s.isFailed())
		{
			TakesScreenshot scrShot = (TakesScreenshot)driver;
			byte[] data = scrShot.getScreenshotAs(OutputType.BYTES);
			scn.attach(data, "image/png", "failed Step Name: " + s.getName());
		}
		else
		{
			scn.log("Test case is passed, no screen shot capture");
			logger.info("Test case is passed, no screen shot capture");

		}
	}



	@Given("User navigated to the home application url")
	public void user_navigated_to_the_home_application_url() 
	{
		driver.get(base_url);
		scn.log("Browser navigated to URL: " + base_url);
		logger.info("Browser navigated to URL: " + base_url);

		String expected = "My Store";
		String actual = driver.getTitle();
		Assert.assertEquals("Page Title validation", expected, actual);

		logger.info("Page Title is Successfull Actual title: " + actual);
		scn.log("Page Title is Successfull Actual title: " + actual);

	}


		@When("User Search for url")
		public void user_search_for_url() 
		{
			WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
			driver.findElement(By.id("search_query_top")).click();
			
		}
		
		@Then("Search Result page is displayed")
		public void search_result_page_is_displayed() 
		{
			WebDriverWait webDriverWait1 = new WebDriverWait(driver,20);
	        webDriverWait1.until(ExpectedConditions.urlContains(base_url));
			
			Assert.assertEquals("After launching url be redirect ","http://automationpractice.com/index.php" ,driver.getCurrentUrl() );
			logger.info("current page url" + driver.getCurrentUrl());
			
		}
		
		@When("User click on Logo")
		public void user_click_on_logo() 
		{
		   WebElement logoEle = driver.findElement(By.xpath("//div[@id='header_logo']//a/img"));
		   scn.log("The font is: " + logoEle.isDisplayed());
		   logger.info("The font is: " + logoEle.isDisplayed());
			
		}
	
		@Then("Fetch The Application Logo")
		public void fetch_the_application_logo() 
		{
		  JavascriptExecutor js = ((JavascriptExecutor)driver);
		  js.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", LogoButtenFontSize);
		  
		  logger.info("App Logo size: " + LogoButtenFontSize  );
		  scn.log("App Logo size: " + LogoButtenFontSize );
			
		}

	@When("User Search for product")
	public void user_search_for_product(List<String> productName) {

		WebDriverWait wait = new WebDriverWait(driver, 20);
		List<WebElement> text = driver.findElements(By.xpath("//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']"));
		
		scn.log("the text shown that " + productName);
	    logger.info("the text shown that " + productName);

	}




	@Then("product categories show {int} quantity")
	public void product_categories_show_quantity(Integer int1) {
		logger.info("page title mast " + DriverAction.class );
		
	}
	
	@When("User Search for text")
	public void user_search_for_text() 
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement serchBox = driver.findElement(By.xpath("//input[@id='search_query_top']"));
		serchBox.sendKeys("T-shirts");
		
		logger.info("Entering the text" + driver.getClass() );
		scn.log("Entering the text" + driver.getTitle());
		
	}


	@Then("Search result page is displayed")
	public void search_result_page_is_displayed1() 
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		List<WebElement> searchRe = driver.findElements(By.xpath("//strong[text()='T-shirts']"));
		wait.until(ExpectedConditions.visibilityOfAllElements(searchRe));
		
		for (int i=0; i< searchRe.size(); i++);
		{
			System.out.println(searchRe.get(0).getText());
		}
		
	    
		logger.info("search result contain text" + searchRe);
		
	}
	
	
	@When("User click on the link")
	public void user_click_on_the_link() 
	{
	    WebElement clickhLink = driver.findElement(By.xpath("//li[@class='twitter']"));
	    
	    JavascriptExecutor js1 = ((JavascriptExecutor) driver);
		js1.executeScript("arguments[0].scrollIntoView(true);", clickhLink);
		js1.executeScript("arguments[0].click();", clickhLink);
		
		clickhLink.click();
	    logger.info("Search for link" + driver.getCurrentUrl());
	}


	@Then("Search result page")
	public void search_result_page() 
	{
		Set<String> handles = driver.getWindowHandles();
		logger.info("List of windows found: "+handles);
		
		Iterator<String> it = handles.iterator();
		String original = it.next();
		String nextTab = it.next();
		driver.switchTo().window(nextTab);
		
		logger.info("Switched to the new window" + driver.getCurrentUrl());
		scn.log("Switched to the new window");
		
		WebElement accountNam = driver.findElement(By.xpath("//div[@class='css-1dbjc4n r-1awozwy r-18u37iz r-dnmrzs']//parent::span/span[1]"));
		logger.info("page url is" +driver.getCurrentUrl());
		
		
	    
	}




}
