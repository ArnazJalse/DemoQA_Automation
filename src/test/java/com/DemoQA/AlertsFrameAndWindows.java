package com.DemoQA;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AlertsFrameAndWindows {
	WebDriver driver;
	Properties properties;
	String baseURL;
	
	@BeforeMethod
	public void setUp() {
		try {
			FileInputStream file = new FileInputStream("configuration_3.properties");
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
	public void testBrowserWindow() {
		//Get Locators from properties
		String newTabLoc = properties.getProperty("newTab.Locator");
		String newWindowLoc = properties.getProperty("newWindow.Locator");
		String newWindowMessage = properties.getProperty("newWindowMessage.Locator");
		
		driver.get("https://demoqa.com/browser-windows");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id(newTabLoc)).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        java.util.Iterator<String> iterator = allWindowHandles.iterator();

        // Here we will check if child window has other child windows and will fetch the heading of the child window
        while (iterator.hasNext()) {
            String ChildWindow = iterator.next();
                if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
                WebElement text = driver.findElement(By.id("sampleHeading"));
                Assert.assertEquals("This is a sample page", text.getText());
            }
        }
        driver.switchTo().window(mainWindowHandle);
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        driver.findElement(By.id(newWindowLoc)).click();
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        driver.switchTo().window(mainWindowHandle);
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        driver.findElement(By.id(newWindowMessage)).click();
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testAlerts() {
		//Get Locators from properties
		String clickMeLoc1 = properties.getProperty("ClickME_Alert.Locator1");
		String clickMeLoc2 = properties.getProperty("ClickME_Alert.Locator2");
		String clickMeLoc3 = properties.getProperty("ClickME_Alert.Locator3");
		String clickMeLoc4 = properties.getProperty("ClickME_Alert.Locator4");
		
		driver.get("https://demoqa.com/alerts");
		//handle a Simple Alert
		driver.findElement(By.id(clickMeLoc1)).click();
		Alert simpleAlert = driver.switchTo().alert();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    simpleAlert.accept();
	    try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    //handle unexpected Alerts
	    try {
            driver.findElement(By.id(clickMeLoc2)).click();
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
            wait.until(ExpectedConditions.alertIsPresent());
            Alert simpleAlert1 = driver.switchTo().alert();
            simpleAlert1.accept();
           // System.out.println("Unexpected alert accepted");
        } catch (Exception e) {
           // System.out.println("unexpected alert not present");
        }
	    try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    //handle a Confirmation Alert
	    WebElement element = driver.findElement(By.id(clickMeLoc3));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
	    Alert confirmationAlert = driver.switchTo().alert();
	    try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String alertText = confirmationAlert.getText();
	    Assert.assertEquals(alertText, "Do you confirm action?");
	    confirmationAlert.accept();
	    try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    //handle a Prompt Alert
	    WebElement promptelement = driver.findElement(By.id(clickMeLoc4));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", promptelement);
	    Alert promptAlert  = driver.switchTo().alert();
	    String alertText1 = promptAlert.getText();
	    System.out.println("Alert text is " + alertText1);
	   //Send some text to the alert
	    promptAlert.sendKeys("Test User");
	    try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    promptAlert.accept();
	    try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	}
	
	@Test
	public void testFrame() {
		//Get Locators from properties
		String frameLoc1 = properties.getProperty("frame1.Locator");
		String frameLoc2 = properties.getProperty("frame2.Locator");
		
		driver.get("https://demoqa.com/frames");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 //Switch to Frame using id of the frame
        driver.switchTo().frame(frameLoc1);
        //Identifying the heading in webelement
        WebElement frame1Heading= driver.findElement(By.id("sampleHeading"));
        
        //Finding the text of the heading
        String frame1Text=frame1Heading.getText();
        Assert.assertEquals(frame1Text, "This is a sample page");
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        driver.switchTo().defaultContent();
        driver.switchTo().frame(frameLoc2);
        WebElement frame1Heading2= driver.findElement(By.id("sampleHeading")); 
        //Finding the text of the heading
        String frame1Text2=frame1Heading2.getText();
        Assert.assertEquals(frame1Text2, "This is a sample page");
        
	}
	
	@Test
	public void testNestedFrame() {
		//Get Locators from properties
		String frameLoc = properties.getProperty("nestedFrame.Locator");
		
		driver.get("https://demoqa.com/nestedframes");
		//Number of Frames on a Page
        int countIframesInPage = driver.findElements(By.tagName("iframe")).size();
        System.out.println("Number of Frames on a Page:" + countIframesInPage);
        
        //Locate the frame1 on the webPage
        WebElement frame1=driver.findElement(By.id(frameLoc));
        
        //Switch to Frame1
        driver.switchTo().frame(frame1);
        
        //Locate the Element inside the Frame1
        WebElement frame1Element= driver.findElement(By.tagName("body"));
        
        //Get the text for frame1 element
        String frame1Text=frame1Element.getText();
        Assert.assertEquals(frame1Text, "Parent frame");
       
        //Number of Frames on a Frame1
        int countIframesInFrame1 =driver.findElements(By.tagName("iframe")).size();
        Assert.assertEquals(countIframesInFrame1, 1);
       
        
        //switch to child frame
        driver.switchTo().frame(0);
        
        int countIframesInFrame2 =driver.findElements(By.tagName("iframe")).size();

        Assert.assertEquals(countIframesInFrame2, 0);
		
	}
	
	@Test
	public void testModelDialogs() {
		//Get Locators from properties
		String modelDialogLoc = properties.getProperty("modelDialod_link.Locator");
		String smallModelButtonLoc = properties.getProperty("smallModel_Button.Locator");
		String closeButtonLoc1 = properties.getProperty("close_Button.Locator");
		String largeModelButtonLoc = properties.getProperty("largeModel_Button.Locator");
		String closeButtonLoc2 = properties.getProperty("close_Button.Locator2");
		
		String smallModelHeaderLoc = properties.getProperty("smallModel_header.Locator");
		String largeModelHeaderLoc = properties.getProperty("largeModel_Header.Locator");
		
		driver.get(modelDialogLoc);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id(smallModelButtonLoc)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement header = driver.findElement(By.id(smallModelHeaderLoc));
		Assert.assertEquals(header.getText(), "Small Modal");
		driver.findElement(By.id(closeButtonLoc1)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id(largeModelButtonLoc)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement header2 = driver.findElement(By.id(largeModelHeaderLoc));
		Assert.assertEquals(header2.getText(), "Large Modal");
		driver.findElement(By.id(closeButtonLoc2)).click();
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
