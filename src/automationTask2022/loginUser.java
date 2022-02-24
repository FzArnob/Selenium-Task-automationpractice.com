package automationTask2022;
import org.openqa.selenium.*;
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
			System.out.println("Filling credentials...");
			if (!driver.findElements(By.linkText("Sign out")).isEmpty()) throw new Exception("A user is already logged in!");
			driver.findElement(By.linkText("Sign in")).click();
			sleep(1000);
			driver.findElement(By.id("email")).sendKeys(data[0]);
			driver.findElement(By.id("passwd")).sendKeys(data[1]);
			sleep(2000);
			driver.findElement(By.id("SubmitLogin")).click();
			sleep(1000);
			if(driver.findElements(By.linkText("Sign out")).isEmpty()) throw new Exception("Login failed, crud operation error");
			return "Login successful";
		} catch (Exception e) {
			return "Error: "+e;
		}
	}
}
