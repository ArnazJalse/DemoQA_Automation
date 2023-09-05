# DemoQA_Automation
# Automated Testing of [https://demoqa.com](https://demoqa.com) Using Selenium, TestNG, and Java

This repository contains an example of an automated testing script for the website [https://demoqa.com](https://demoqa.com) using the Selenium WebDriver, TestNG, and Java programming language. The script demonstrates how to navigate to the website, perform actions, and validate functionality.

## Prerequisites

Before running the automated test script, ensure you have the following prerequisites installed on your machine:

1. Java Development Kit (JDK)
2. Apache Maven
3. ChromeDriver (or WebDriver for your preferred browser)
4. An Integrated Development Environment (IDE) like Eclipse or IntelliJ IDEA (optional but recommended)

## Setup

1. Clone this repository to your local machine using the following command:

   ```shell
   git clone https://github.com/yourusername/your-repo.git
Open the project in your preferred IDE (Eclipse, IntelliJ IDEA, etc.).

Configure the project settings to use JDK and Maven.

Download the appropriate WebDriver for your browser (e.g., ChromeDriver) and place it in a directory included in your system's PATH.

Create a locators.properties file in the project directory and add locators in the following format:

fullName_textbox.Locator=userName
email_textbox.Locator=userEmail
currentAddress_textbox.Locator=currentAddress

Replace the locators and their values with the actual locators you want to use.

## Running the Test
Open the DemoQATest.java file in your IDE.

Modify the test script according to your test case requirements. You can retrieve locators from the locators.properties file using a properties reader.

Run the test script by right-clicking on the DemoQATest.java file and selecting "Run as TestNG Test" (if using Eclipse) or running the test from the TestNG XML configuration file.

The WebDriver will open the specified browser, navigate to https://demoqa.com, perform the defined actions using the locators, and validate the results.

## TestNG Reports
TestNG will generate test execution reports after the test run. You can find the HTML reports in the test-output directory. Open the index.html file to view the test results, including pass/fail status and detailed logs.

## Customization
Feel free to customize the test script and test cases according to your requirements. You can add more test methods, assertions, and test data as needed.

## References
Selenium Documentation
TestNG Documentation
Happy testing!
