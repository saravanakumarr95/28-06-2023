package org.testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ClassTestng extends BaseClass {
	@Parameters("browser1")
	@Test(groups = "smoke")
	public void firstMethod(String browser) {
		browserLaunch(browser);
		driver.get("https://www.facebook.com/");
		WebElement user = findingElements("id", "email");
		user.sendKeys("qwerty");
		WebElement password = findingElements("id", "pass");
		password.sendKeys("12345");
		WebElement login = findingElements("name", "login");
		login.click();
	}

	@Parameters("browser2")
	@Test(groups = "sanity")
	public void thirdMethod(String browser) {

		browserLaunch(browser);
		driver.get("https://www.facebook.com/");
		WebElement user = findingElements("id", "email");
		user.sendKeys("saran");
		WebElement password = findingElements("id", "pass");
		password.sendKeys("45678");
		WebElement login = findingElements("name", "login");
		login.click();
	}

	public void mango() {
		System.out.println("i love mangos");
	}

}
