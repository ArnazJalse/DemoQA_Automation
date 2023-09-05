package com.DemoQA;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Elements {
	WebDriver driver;
	Properties properties;
	String baseURL;
	
	@BeforeMethod
	public void setUp() {
		try {
			FileInputStream file = new FileInputStream("D:\\test\\ArnazWork\\DemoQA_Automation\\src\\test\\resources\\configs\\configuration_1.properties");
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
	public void testTextbox() {
		//Get Locators from properties
		String textboxLocator = properties.getProperty("textbox.Locator");
		String fullNameTxtboxLoc = properties.getProperty("fullName_textbox.Locator");
		String emailTxtboxLoc = properties.getProperty("email_textbox.Locator");
		String currentAddressTxtboxLoc = properties.getProperty("currentAddress_textbox.Locator");
		String permanentAddressTxtboxLoc = properties.getProperty("PermanentAddress_textbox.Locator");
		String submitButtonLoc = properties.getProperty("submit_button.Locator");
		
		String fullName = properties.getProperty("fullName");
		String email = properties.getProperty("email");
		String currentAdd = properties.getProperty("currentAdd");
		String permanentAdd = properties.getProperty("PermanentAdd");
		String expectedName = properties.getProperty("result_name.Locator");
		
		driver.findElement(By.xpath(textboxLocator)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id(fullNameTxtboxLoc)).sendKeys(fullName);
		driver.findElement(By.id(emailTxtboxLoc)).sendKeys(email);
		driver.findElement(By.id(currentAddressTxtboxLoc)).sendKeys(currentAdd);
		driver.findElement(By.id(permanentAddressTxtboxLoc)).sendKeys(permanentAdd);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id(submitButtonLoc)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String expectName = driver.findElement(By.id(expectedName)).getText();
		Assert.assertEquals(expectName, "Name:John Smith");
		
	}
	
	@Test
	public void testCheckbox() {
		//Get Locators from properties
		String checkboxLoc = properties.getProperty("checkbox.Locator");
		String expandLoc = properties.getProperty("expand.Locator");
		String homeCheckboxLoc = properties.getProperty("home_checkbox.Locator");
		String dekstopUnCheckboxLoc = properties.getProperty("dekstop_checkbox.Locator");
		String documentUnCheckboxLoc = properties.getProperty("document_checkbox.Locator");
		String downloadExpandLoc = properties.getProperty("download_expand.Locator");
		String wordFileUnCheckboxLoc = properties.getProperty("wordfile_checkbox.Locator");
		
		driver.findElement(By.xpath(checkboxLoc)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(homeCheckboxLoc)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(expandLoc)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(dekstopUnCheckboxLoc)).click();
		driver.findElement(By.xpath(documentUnCheckboxLoc)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement expand = new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.elementToBeClickable(By.xpath(downloadExpandLoc)));
		Actions action =new Actions(driver);
		action.moveToElement(expand).click().build().perform();
		
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(wordFileUnCheckboxLoc)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertTrue(true);
		
	}
	
	@Test
	public void testRadioButton() {
		//Get Locators from properties
		String radioButtonLoc = properties.getProperty("radio.Locator");
		String yesRadioButtonLoc = properties.getProperty("yes_radioButton.Locator");
		String expectedYesLoc = properties.getProperty("expected_yes.Locator");
		
		driver.findElement(By.id(radioButtonLoc)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement yes_radio = driver.findElement(By.name(yesRadioButtonLoc));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", yes_radio);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String expectResult = driver.findElement(By.xpath(expectedYesLoc)).getText();
		Assert.assertEquals("Yes", expectResult);
			
	}
	
	@Test
	public void testWebTable() {
		//Get Locators from properties
		String webTableLoc = properties.getProperty("WebTable.Locator");
		String addButtonLoc = properties.getProperty("add_Button.Locator");
		String firstNametxtboxLoc = properties.getProperty("firstname_textbox.Locator");
		String lastNametxtboxLoc = properties.getProperty("lastname_textbox.Locator");
		String emailtxtboxLoc = properties.getProperty("Email_textbox.Locator");
		String agetxtboxLoc = properties.getProperty("age_textbox.Locator");
		String salarytxtboxLoc = properties.getProperty("salary_textbox.Locator");
		String deparmenttxtboxLoc = properties.getProperty("department_textbox.Locator");
		String submitButtonLoc = properties.getProperty("submit_Button2.Locator");
		String editButtonLoc = properties.getProperty("edit_Button.Locator");
		String deleteButtonLoc = properties.getProperty("delete_Button.Locator");
		
		String firstName = properties.getProperty("firstName");
		String lastName = properties.getProperty("lastName");
		String email = properties.getProperty("Email");
		String age = properties.getProperty("age");
		String salary = properties.getProperty("salary");
		String department = properties.getProperty("department");
		String updateDepartment = properties.getProperty("updatedepartment");
	
		
		driver.findElement(By.id(webTableLoc)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Add Data
		driver.findElement(By.id(addButtonLoc)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id(firstNametxtboxLoc)).sendKeys(firstName);
		driver.findElement(By.id(lastNametxtboxLoc)).sendKeys(lastName);
		driver.findElement(By.id(emailtxtboxLoc)).sendKeys(email);
		driver.findElement(By.id(agetxtboxLoc)).sendKeys(age);
		driver.findElement(By.id(salarytxtboxLoc)).sendKeys(salary);
		driver.findElement(By.id(deparmenttxtboxLoc)).sendKeys(department);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id(submitButtonLoc)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Update Data
		driver.findElement(By.id(editButtonLoc)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id(deparmenttxtboxLoc)).clear();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id(deparmenttxtboxLoc)).sendKeys(updateDepartment);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id(submitButtonLoc)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Delete Data
		driver.findElement(By.id(deleteButtonLoc)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(true);
		
	}
	
	@Test
	public void testButton() {
		//Get Locators from properties
		String buttonLoc = properties.getProperty("button.Locator");
		String doubleClickButtonLoc = properties.getProperty("doubleClick_Button.Locator");
		String rightClickButtonLoc = properties.getProperty("rightCLick_Button.Locator");
		String clickButtonLoc = properties.getProperty("click_Button.Locator");
		
		
		Actions actions = new Actions(driver);
		driver.findElement(By.id(buttonLoc)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Double Click
		WebElement doubleClick = driver.findElement(By.id(doubleClickButtonLoc));
		actions.doubleClick(doubleClick).perform();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement rightClick = driver.findElement(By.id(rightClickButtonLoc));
		actions.contextClick(rightClick).perform();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(clickButtonLoc)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertTrue(true);	
	}
	
	@Test
	public void testLinks() {
		//Get Locators from properties
		String linksLoc = properties.getProperty("link.Locator");
		String homeLinkLoc = properties.getProperty("home_link.Locator");
		String createAPILinkLoc = properties.getProperty("created_apilink.Locator");
		String movedAPILinkLoc = properties.getProperty("moved_apilink.Locator");
		String notFoundAPILinkLoc = properties.getProperty("notFound_apilink.Locator");
	
		
		driver.findElement(By.id(linksLoc)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id(homeLinkLoc)).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String parent=driver.getWindowHandle();
		driver.switchTo().window(parent);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id(createAPILinkLoc)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id(movedAPILinkLoc)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(notFoundAPILinkLoc)).click();
		Assert.assertTrue(true);	
	}
	
	@Test
	public void testUploadAndDownload() {
		//Get Locators from properties
		String uploadFileButtonLoc = properties.getProperty("chooseFile_button.Locator");
		String uploadFilePath = properties.getProperty("uploadFile_path.Locator");
		String downloadFileButtonLoc = properties.getProperty("download_button.Locator");
		driver.get("https://demoqa.com/upload-download");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement upload = driver.findElement(By.id(uploadFileButtonLoc));
		upload.sendKeys("C:\\Users\\arbaz\\OneDrive\\Desktop\\apple.jpg");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String expectedPath = driver.findElement(By.id(uploadFilePath)).getText();
		Assert.assertEquals("C:\\fakepath\\apple.jpg", expectedPath);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id(downloadFileButtonLoc)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testBrokenLink() {
		//Get Locators from properties
		String brokenLinksLoc = properties.getProperty("brokenLink.Locator");
		String listOfLinksLoc = properties.getProperty("listofLinks.Locator");
		String URLLoc = properties.getProperty("url.Locator");
		
		driver.findElement(By.id(brokenLinksLoc)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Storing the links in a list and traversing through the links
        List<WebElement> links = driver.findElements(By.tagName(listOfLinksLoc));
     // This line will print the number of links and the count of links.
        System.out.println("No of links are "+ links.size());  
      
        //checking the links fetched.
        for(int i=0;i<links.size();i++)
        {
            WebElement E1= links.get(i);
            String url= E1.getAttribute(URLLoc);
            verifyLinks(url);
        }
        
        Assert.assertTrue(true);
	}
	   public static void verifyLinks(String linkUrl)
	    {
	        try
	        {
	            URL url = new URL(linkUrl);

	            //Now we will be creating url connection and getting the response code
	            HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
	            httpURLConnect.setConnectTimeout(5000);
	            httpURLConnect.connect();
	            if(httpURLConnect.getResponseCode()>=400)
	            {
	            	System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage()+"is a broken link");
	            }    
	       
	            //Fetching and Printing the response code obtained
	            else{
	                System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage());
	            }
	        }catch (Exception e) {
	      }
	   }
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
}
