package org.ABSTestCenter;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import static org.hamcrest.CoreMatchers.containsString;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import objectRepo.AcnMyAppObject;
import utility.ExcelUtils;

public class AcnMyApp {
	public WebDriver driver;
	private static int testCaseNumber=0;
	
	  @BeforeTest
	  public void beforeTest() {
		  System.setProperty("webdriver.gecko.driver", "C:\\Users\\A06438_p5.Training\\Downloads\\Drivers\\geckodriver-v0.20.1-win64\\geckodriver.exe");
		  driver = new FirefoxDriver();
		  driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  driver.get("http://localhost:8083/TestMeApp/login.htm");
		  PageFactory.initElements(driver, AcnMyAppObject.class);
		  }
	  
	  @Test (dataProvider = "loginData", priority='0')
	  public void login(String userName, String password) throws Exception {
		try {
		  AcnMyAppObject.uname.clear();
		  AcnMyAppObject.uname.sendKeys(userName);
		  AcnMyAppObject.pass.clear();
		  AcnMyAppObject.pass.sendKeys(password);
		  AcnMyAppObject.submitButton.click();
		  String title=driver.getTitle();
		  Assert.assertEquals(title.contains("Home"), true);
		  ExcelUtils.setExcelData(testCaseNumber, 2, "pass");
		}catch(AssertionError e) {
			ExcelUtils.setExcelData(testCaseNumber, 2, "Fail");
		}finally {
//		  driver.navigate().to("http://localhost:8083/TestMeApp/login.htm");
		  testCaseNumber++;
		}
	  }
	  @Test (priority='1')
	  public void select_category() throws Exception {
		  
		  WebElement category = driver.findElement(By.xpath("//span[text()='All Categories']"));
		  Actions action = new Actions(driver);
		  action.moveToElement(category).click().build().perform();
		  
//		  Thread.sleep(5000);
		  WebElement electronics = driver.findElement(By.xpath("//span[text()='Electronics']"));
		  action.moveToElement(electronics).click().build().perform();
		  
//		  Thread.sleep(5000);
		  WebElement headPhone = driver.findElement(By.xpath("//span[text()='Head Phone']"));
		  action.moveToElement(headPhone).click().build().perform();
		  
		  driver.findElement(By.xpath("//a[contains(text(),'Add to cart')]")).click();
		  
		  driver.findElement(By.xpath("//a[@href='displayCart.htm']")).click();
		  
		  driver.findElement(By.xpath("//a[@href='checkout.htm']")).click();
		  
		  driver.findElement(By.xpath("//*[@type='submit' and @value='Proceed to Pay']")).click();
	  }
	  
	  @Test (priority='2')
	  public void payment_gateway() {
		  WebDriverWait wait = new WebDriverWait(driver, 10);
		  
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Welcome to Payment Gateway')]")));
		  
		  WebElement continu = driver.findElement(By.xpath("//a[text()='Continue']"));
		  continu.click();
		  
		  WebElement LOGIN = driver.findElement(By.xpath("//input[@type='submit' and @value='LOGIN']"));
		  LOGIN.click();
		  
		  
	  }
	  
	  @DataProvider(name = "loginData")
	  public String[][] login_data() throws Exception
	  {
		  ExcelUtils.setExcelPath("Sheet1", "C:\\Users\\A06438_p5.Training\\Desktop\\SeleniumAdvance\\TestMeApp\\LoginData.xlsx");
		  String[][] tableData = ExcelUtils.getAllExcelData();
		  return tableData;
	  }
	  
	  @AfterTest
	  public void afterTest() {
//		  driver.close();
	  }
}
