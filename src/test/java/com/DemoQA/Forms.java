package com.DemoQA;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
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

public class Forms {
	WebDriver driver;
	Properties properties;
	String baseURL;
	
	@BeforeMethod
	public void setUp() {
		try {
			FileInputStream file = new FileInputStream("configuration_2.properties");
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
	public void testPracticeForm() {
		//Get Locators from properties
		String firstNametxtboxLoc = properties.getProperty("firstName_textbox.Locator");
		String lastNametxtboxLoc = properties.getProperty("lastName_textbox.Locator");
		String emailtxtboxLoc = properties.getProperty("email_textbox.Locator");
		String genderRadioButtonLoc = properties.getProperty("gender_radioButton.Locator");
		String mobileNumbertxtboxLoc = properties.getProperty("mobileNumber_textbox.Locator");
		String hobbiesCheckboxLoc1 = properties.getProperty("hobbies_Checkbox.Locator1");
		String hobbiesCheckboxLoc2 = properties.getProperty("hobbies_Checkbox.Locator2");
		String pictureUploadButtonLoc = properties.getProperty("picture_upload.Locator");
		String currentAddresstxtboxLOc = properties.getProperty("currentAddress_textbox.Locator");
		String submitButtonLoc = properties.getProperty("submit_Button.Locator");
		
		String firstName = properties.getProperty("firstName");
		String lastName = properties.getProperty("lastName");
		String email = properties.getProperty("email");
		String mobile = properties.getProperty("mobile");
		String currentAddress = properties.getProperty("currentaddress");
		
		driver.findElement(By.id(firstNametxtboxLoc)).sendKeys(firstName);
		driver.findElement(By.id(lastNametxtboxLoc)).sendKeys(lastName);
		driver.findElement(By.id(emailtxtboxLoc)).sendKeys(email);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(genderRadioButtonLoc)).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id(mobileNumbertxtboxLoc)).sendKeys(mobile);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Actions ac = new Actions(driver);
		ac.click(driver.findElement(By.id(hobbiesCheckboxLoc1))).build().perform();
		ac.click(driver.findElement(By.id(hobbiesCheckboxLoc2))).build().perform();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement upload = driver.findElement(By.id(pictureUploadButtonLoc));
		upload.sendKeys("C:\\Users\\arbaz\\OneDrive\\Desktop\\apple.jpg");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id(currentAddresstxtboxLOc)).sendKeys(currentAddress);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ac.click(driver.findElement(By.id(submitButtonLoc))).build().perform();
		Assert.assertTrue(true);
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
