package com.DemoQA;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Widgets {
	WebDriver driver;
	Properties properties;
	String baseURL;
	
	@BeforeMethod
	public void setUp() {
		try {
			FileInputStream file = new FileInputStream("configuration_5.properties");
			properties = new Properties();
			try {
				properties.load(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		baseURL = properties.getProperty("base.url");
		driver.get(baseURL);
		driver.manage().window().maximize();
	}
	
	@Test
	public void testProgrssBar() {
		//Get Locators from properties
		String progressBarLoc = properties.getProperty("progressBar.Locator");
		String progressbarLoc = properties.getProperty("progressbar.Locator");
		String startStopButtonLoc = properties.getProperty("startStop_Button.Locator");
		
		driver.get(progressBarLoc);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id(startStopButtonLoc)).click();
		
		WebElement progress = driver.findElement(By.id(progressbarLoc));
		for(int second = 0; second <= 30; second++)
	    {
	        if(progress.getAttribute("class").contains("progress-bar-success"))
	        return;
	        else if(progress.getAttribute("class").contains("progress-bar-danger"))
	        Assert.fail("No success");
	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
		Assert.assertTrue(true);
	}
	
	@Test
	public void testSlider() {
		//Get Locators from properties
		String sliderLoc = properties.getProperty("slider.Locator");
		String slideLoc = properties.getProperty("slide.Locator");
		String sliderValueLoc = properties.getProperty("sliderValue.Locator");
		
		driver.get(sliderLoc);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Actions ac = new Actions(driver);
		WebElement slider = driver.findElement(By.xpath(slideLoc));
		
		ac.moveToElement(slider).clickAndHold().moveByOffset(30, 0).release().build().perform();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement expectedValue = driver.findElement(By.id(sliderValueLoc));
		//System.out.println(expectedValue.getAttribute("value"));
		Assert.assertEquals(Integer.parseInt(expectedValue.getAttribute("value")), 54);	
	}
	
	@Test
	public void testAccordian() {
		//Get Locators from properties
		String accordianLoc = properties.getProperty("accordian.Locator");
		//String accordianContainerLoc = properties.getProperty("accordian_container.Locator");
		
		driver.get(accordianLoc);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=1;i<=3;i++) {
			WebElement selectHeading = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div/div["+ i +"]/div[1]"));
			selectHeading.click();
			System.out.println(selectHeading.getText());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Assert.assertTrue(true);
	}
	
	@Test
	public void testToolTip() {
		//Get Locators from properties
		String toolTipLoc = properties.getProperty("toolTip.Locator");
		String toolTipButtonLoc = properties.getProperty("toolTip_Button.Locator");
		String hoveredButtonLoc = properties.getProperty("hovered_Button.Locator");
		
		driver.get(toolTipLoc);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Actions actions = new Actions(driver);
		WebElement toolTipBtn = driver.findElement(By.id(toolTipButtonLoc));
		// Use action class to mouse hover 
		actions.moveToElement(toolTipBtn).perform();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement hoveredButtontxt = driver.findElement(By.xpath(hoveredButtonLoc));
		
		// To get the tool tip text and assert
		String toolTiptxt = hoveredButtontxt.getText();
		//System.out.println(toolTiptxt);
		//Verification if tooltip text is matching expected value 
		Assert.assertEquals(toolTiptxt, "You hovered over the Button");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testMenu() {
		//Get Locators from properties
		String menuLoc = properties.getProperty("menu.Locator");
		String menuItemLoc2 = properties.getProperty("menu_item2.Locator");
		String subItemLoc = properties.getProperty("sub_item.Locator");
		String sub_SubItemLoc = properties.getProperty("sub_subItem.Locator");
		
		driver.get(menuLoc);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Actions action = new Actions(driver);
		WebElement menuItem2 = driver.findElement(By.xpath(menuItemLoc2));
		WebElement subItem3 = driver.findElement(By.xpath(subItemLoc));
		WebElement sub_SubItem1 = driver.findElement(By.xpath(sub_SubItemLoc));
		
		action.moveToElement(menuItem2).perform();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		action.moveToElement(subItem3).perform();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		action.moveToElement(sub_SubItem1).perform();
		String sub_SubItem1txt = sub_SubItem1.getText();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(sub_SubItem1txt);
		Assert.assertEquals(sub_SubItem1txt, "Sub Sub Item 1");
	}
	
	@Test
	public void testDatePicker() {
		//Get Locators from properties
		String month = "March";
		String year = "1997";
		String day = "18";
		String datepickerLoc = properties.getProperty("Datepicker.Locator");
		String datepickertxtboxLoc = properties.getProperty("Datepicker_textbox.Locator");
		String select_monthLoc = properties.getProperty("select_month.Locator");
		String select_yearLoc = properties.getProperty("select_year.Locator");
		
		driver.get(datepickerLoc);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement datetxtbox = driver.findElement(By.id(datepickertxtboxLoc));
		datetxtbox.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Select monthSc = new Select(driver.findElement(By.xpath(select_monthLoc)));
		monthSc.selectByVisibleText(month);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Select yearSc = new Select(driver.findElement(By.xpath(select_yearLoc)));
		yearSc.selectByVisibleText(year);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Actions ac = new Actions(driver);
		for(int row = 1 ;row < 8 ;row++) {
			for(int col = 1 ;col < 7 ;col++) {
				WebElement day1 = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div[1]/div[2]/div[2]/div[2]/div/div/div[2]/div[2]/div["+ row +"]/div["+ col +"]"));
				String day2 = day1.getText();
				//System.out.println(day1.getText());
				if(day2.equals(day)) {
					//System.out.println(day2);
					//Assert.assertEquals(day2, day);
					ac.click(day1).perform();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
				}
			}
			
		}
			
	}
	
	@Test
	public void testAutoComplete() {
		// Get Locators from properties
		String autoCompleteLoc = properties.getProperty("autocomplete.Locator");
		String autoCompleteTxtboxLoc = properties.getProperty("autocomplete.Textbox.Locator");

		driver.get(autoCompleteLoc);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// WebElement autocompleteTxtbox =
		// driver.findElement(By.id(autoCompleteTxtboxLoc));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement autocompleteTxtbox = wait
				.until(ExpectedConditions.elementToBeClickable(By.id(autoCompleteTxtboxLoc)));
		autocompleteTxtbox.click();
		String valueToSet = "red";
		((JavascriptExecutor) driver).executeScript("arguments[0].textContent = arguments[1];", autocompleteTxtbox,
				valueToSet);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		autocompleteTxtbox.click();

		// Click on the first option in the dropdown (you can modify this based on your
		// needs)
//		WebElement autoOptions = driver
//				.findElement(By.xpath("(//div[contains(@class,'auto-complete__menu css-2613gy-menu')])[1]"));
//		WebElement firstOption = autoOptions.findElement(By.tagName("div"));
//		firstOption.click();

		// Wait for a few seconds to see the result
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
