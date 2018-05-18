package objectRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AcnMyAppObject {

	@FindBy (how=How.ID, using = "userName")
	public static WebElement uname;
	
	@FindBy (how=How.CSS, using = "input.btn:nth-child(1)")
	public static WebElement submitButton;
	
	@FindBy (how=How.CSS, using = "#password")
	public static WebElement pass;
}
