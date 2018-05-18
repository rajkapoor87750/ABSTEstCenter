package objectRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.annotations.Test;

public class LoginPageObject {
  
	@FindBy (how=How.ID, using = "identifierId")
	public static WebElement uname;
	
	@FindBy (how=How.XPATH, using = "//span[text()='Next']")
	public static WebElement next;
	
	@FindBy (how=How.NAME, using = "password")
	public static WebElement pass;
	
}
