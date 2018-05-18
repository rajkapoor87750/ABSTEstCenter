package org.ABSTestCenter;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import objectRepo.LoginPageObject;

public class LoginGmail {
	
	public WebDriver driver;
	
	
  @BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.gecko.driver", "C:\\Users\\A06438_p5.Training\\Downloads\\Drivers\\geckodriver-v0.20.1-win64\\geckodriver.exe");
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.get("https://www.gmail.com");
	  PageFactory.initElements(driver, LoginPageObject.class);
	  }
  @Test
  public void login() {
	  LoginPageObject.uname.sendKeys("rajipper");
	  LoginPageObject.next.click();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@type='password']")));
	  LoginPageObject.pass.sendKeys("1234567");
  }
  
  @AfterTest
  public void afterTest() {
//	  driver.close();
  }

}
