package com.DemoQA;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Interactions {
	WebDriver driver;
	Properties properties;
	String baseURL;
	Actions builder;
	
	@BeforeMethod
	public void setUp() {
		try {
			FileInputStream file = new FileInputStream("configuration_4.properties");
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
	public void testDroppable() {
		//Get Locators from properties
		String droppableLoc = properties.getProperty("droppable.Locator");
		String dragLoc = properties.getProperty("drag.Locator");
		String dropLoc = properties.getProperty("drop.Locator");
		
		driver.get(droppableLoc);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		builder = new Actions(driver);
		
		WebElement from = driver.findElement(By.id(dragLoc));
		
		WebElement to = driver.findElement(By.id(dropLoc));
		String beforecolor = to.getCssValue("color");
		String c = Color.fromString(beforecolor).asHex();
	    System.out.println("Color is :" + beforecolor);
	    System.out.println("Hex code for color:" + c);
	    
	    builder.dragAndDrop(from, to).perform();
	    try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String aftercolor = to.getCssValue("background-color");
		String c1 = Color.fromString(aftercolor).asHex();
	    System.out.println("Color is :" + aftercolor);
	    System.out.println("Hex code for color:" + c1);
	    try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Assert.assertNotEquals(beforecolor, aftercolor);
	   
	}
	
	@Test
	public void testDraggable() {
		//Get Locators from properties
		String draggableLoc = properties.getProperty("draggable.Locator");
		String dragMeLoc = properties.getProperty("dragMe.Locator");
		
		driver.get(draggableLoc);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		builder = new Actions(driver);
		WebElement from = driver.findElement(By.id(dragMeLoc));
		
		builder.dragAndDropBy(from, 50, 50).perform();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(true);
		
	}
	
	@Test
	public void testResizable() {
		//Get Locators from properties
		String resizableLoc = properties.getProperty("resizable.Locator");
		String resizableBoxLoc = properties.getProperty("resizableBox.Locator");
		
		driver.get(resizableLoc);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		builder = new Actions(driver);
		WebElement resizable = driver.findElement(By.xpath(resizableBoxLoc));
		
		builder.clickAndHold(resizable).moveByOffset(500, 300).release().build().perform();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(true);
	}
	
	@Test
	public void testSelectable() {
		//Get Locators from properties
		String selectableLoc = properties.getProperty("selectable.Locator");
		String selectLoc1 = properties.getProperty("select.Locator1");
		String selectLoc2 = properties.getProperty("select.LOcator2");
		
		driver.get(selectableLoc);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		builder = new Actions(driver);
		WebElement select1 = driver.findElement(By.xpath(selectLoc1));
		String beforecolor = select1.getCssValue("background-color");
		builder.moveToElement(select1).click().build().perform();
		String Aftercolor = select1.getCssValue("background-color");
		//System.out.println(beforecolor +" "+Aftercolor);
		Assert.assertNotEquals(beforecolor, Aftercolor);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement select2 = driver.findElement(By.xpath(selectLoc2));
		String beforecolor2 = select2.getCssValue("background-color");
		builder.moveToElement(select2).click().build().perform();
		String Aftercolor2 = select2.getCssValue("background-color");
		//System.out.println(beforecolor +" "+Aftercolor);
		Assert.assertNotEquals(beforecolor2, Aftercolor2);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSortable() {
		//Get Locators from properties
		String sortableLoc = properties.getProperty("sortable.Locator");
		String listLoc = properties.getProperty("list.Locator");
		String oneLoc = properties.getProperty("one.Locator");
		String twoLoc = properties.getProperty("two.Locator");
		String threeLoc = properties.getProperty("three.Locator");
		String fourLoc = properties.getProperty("four.Locator");
		String fiveLoc = properties.getProperty("five.Locator");
		String sixLoc = properties.getProperty("six.Locator");
		
		driver.get(sortableLoc);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<WebElement> list = driver.findElements(By.id(listLoc));
		
		for(int i =list.size();i>0;i--) {
			WebElement element = driver.findElement(By.xpath("//*[@id='demo-tabpane-list']/div/div["+ i +"]"));
			
			WebElement destination6 = driver.findElement(By.xpath(sixLoc));
			WebElement destination5 = driver.findElement(By.xpath(fiveLoc));
			WebElement destination4 = driver.findElement(By.xpath(fourLoc));
			WebElement destination3 = driver.findElement(By.xpath(threeLoc));
			WebElement destination2 = driver.findElement(By.xpath(twoLoc));
			WebElement destination1 = driver.findElement(By.xpath(oneLoc));
			
			builder = new Actions(driver);
			if(element!=null) {
				builder.dragAndDrop(element, destination6).perform();
				builder.dragAndDrop(element, destination5).perform();
				builder.dragAndDrop(element, destination4).perform();
				builder.dragAndDrop(element, destination3).perform();
				builder.dragAndDrop(element, destination2).perform();
				builder.dragAndDrop(element, destination1).perform();
		        break;
		        }
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertTrue(true);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
