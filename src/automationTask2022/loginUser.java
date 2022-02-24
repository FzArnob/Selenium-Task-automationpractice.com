package automationTask2022;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class loginUser {
	public WebDriver driver;
	public void sleep(int ms){
		try
		{
			Thread.sleep(ms);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	public loginUser(WebDriver driver) {
		this.driver = driver;
	}
	// Format: [data]
			//  String email, => 0
			//  String pass, => 1
	public String loginWithData(String[] data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement signbtn = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign in")));
			signbtn.click();
			sleep(1000);
			WebElement email = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.id("email")));
			email.sendKeys(data[0]);
			WebElement pass = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.id("passwd")));
			pass.sendKeys(data[1]);
			sleep(2000);
			driver.findElement(By.id("SubmitLogin")).click();
			sleep(2000);
			for(int i=0; i<20; i++) {
				sleep(1000);
				if(!driver.findElements(By.linkText("Sign out")).isEmpty()) {
					return "Pass: Login successful [email: "+data[0]+"]";
				}
			}
			return "Fail: Login failed, crud operation error (waited 20 seconds)";
		} catch (Exception e) {
			return "Fail: "+e;
		}
	}
}
