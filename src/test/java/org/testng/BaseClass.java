package org.testng;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	static WebDriver driver;

	//BrowserLaunch
		public static void browserLaunch(String browser) {
			if (browser.equals("edge")) {
				WebDriverManager.edgedriver().setup();
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--remote-allow-origins=*");
				driver = new EdgeDriver(options);

			} else if (browser.equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions options =new ChromeOptions();
				options.addArguments("--remote-allow-origins=*");
				driver = new ChromeDriver(options);
			} else if (browser.equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();

			} else {
				WebDriverManager.edgedriver().setup();
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--remote-allow-origins=*");
				driver = new EdgeDriver();
			}
		}

	//Geturl
		public static void loadingurl(String url) {
			driver.get(url);

		}

	//locators
		public static WebElement findingElements(String locator, String value) {
			if (locator.equals("id")) {
				WebElement element = driver.findElement(By.id(value));
				return element;
			} else if (locator.equals("name")) {
				WebElement element = driver.findElement(By.name(value));
				return element;
			} else if (locator.equals("tagname")) {
				WebElement element = driver.findElement(By.tagName(value));
				return element;
			} else if (locator.equals("linkText")) {
				WebElement element = driver.findElement(By.linkText(value));
				return element;
			} else if (locator.equals("partiallinktext")) {
				WebElement element = driver.findElement(By.partialLinkText(value));
				return element;
			} else if (locator.equals("xpath")) {
				WebElement element = driver.findElement(By.xpath(value));
				return element;
			} else {
				WebElement element = driver.findElement(By.cssSelector(value));
				return element;
			}

		}

		// passvalue
		public static void passvalue(WebElement element, String value) {
			element.sendKeys(value);

		}

		// clickelement
		public static void clickElement(WebElement element) {
			element.click();
		}

		// Navigation
		public static void donavigateurl(String url) {
			driver.navigate().to(url);

		}

		public static void navigateRefresh() {
			driver.navigate().refresh();
		}

		public static void donavigateForward() {
			driver.navigate().forward();
		}

		public static void donavigateBack() {
			driver.navigate().back();
		}

		public static void doClick(WebElement element) {
			element.click();
		}

		public static void domaximize() {
			driver.manage().window().maximize();
		}

		public static void dominimize() {
			driver.manage().window().minimize();
		}

	//TakeScreenshot
		public static void TakeScreenshot(File file) throws IOException {
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotAs, file);

		}

		// select
		public static void selectByIndex(WebElement element, int index) {
			Select select = new Select(element);
			select.selectByIndex(index);

		}

		public static void selectByValue(WebElement element, String value) {
			Select select = new Select(element);
			select.selectByValue(value);

		}

		public static void selectByText(WebElement element, String text) {
			Select select = new Select(element);
			select.selectByVisibleText(text);

		}

		// Deselect
		public static void DeselectByIndex(WebElement element, int index) {
			Select select = new Select(element);
			select.deselectByIndex(index);

		}

		public static void DeselectByValue(WebElement element, String value) {
			Select select = new Select(element);
			select.deselectByValue(value);

		}

		public static void DeselectByText(WebElement element, String text) {
			Select select = new Select(element);
			select.deselectByVisibleText(text);
		}

		// Alert
		public static void accept() {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}

		public static void dismiss() {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
		}

		public static void promptAlert(String text) {
			Alert alert = driver.switchTo().alert();
			alert.sendKeys(text);
			alert.accept();
		}

		// Datadriven
		public static String excelReading(String location, int row, int cell) throws IOException {
			File file = new File(location);
			FileInputStream inputsream = new FileInputStream(file);
			Workbook workbook = new XSSFWorkbook(inputsream);
			Sheet sheet = workbook.getSheet("Sheet1");
			Row rows = sheet.getRow(row);
			Cell userdetail = rows.getCell(cell);
			String detail = userdetail.getStringCellValue();
			return detail;
		}
		public static String numericValue(String location, int row, int cell) throws IOException {
			File file = new File(location);
			FileInputStream inputsream = new FileInputStream(file);
			Workbook workbook = new XSSFWorkbook(inputsream);
			Sheet sheet = workbook.getSheet("Sheet1");
			Row rows = sheet.getRow(row);
			Cell userdetail = rows.getCell(cell);
			double numericCellValue = userdetail.getNumericCellValue();
			long l =(long)numericCellValue;
			String string=Long.toString(l);
			return string;
		}

		// Frames
		public static void frameByIndex(int i) {
			driver.switchTo().frame(i);

		}

		public static void frameById(String id) {
			driver.switchTo().frame(id);
		}

		public static void frameByElement(WebElement element) {
			driver.switchTo().frame(element);
		}

		public static void parentFrame() {
			driver.switchTo().parentFrame();
		}

		public static void returnFrame() {
			driver.switchTo().defaultContent();

		}

		public static String getParentwindowId() {
			String windowHandle = driver.getWindowHandle();
			return windowHandle;
		}
		//DataFomatter
		public static String dataFormatter() {
	    DataFormatter dataformatter = new DataFormatter();
		String formatCellValue = dataformatter.formatCellValue(null);
		return formatCellValue;
	    
	    
		}


}
