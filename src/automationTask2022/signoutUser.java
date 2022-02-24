package automationTask2022;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class signoutUser {
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
	public signoutUser(WebDriver driver) {
		this.driver = driver;
	}
	public String signOut() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement sign = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign out")));
			sign.click();
			sleep(2000);
			for(int j=0; j<20; j++) {
				sleep(1000);
				if(!driver.findElements(By.linkText("Sign in")).isEmpty()) return "Pass: Sign out Success";
			}
			return "Fail: Sign out failed! (waited 20 seconds)";	
		} catch (Exception e) {
			return "Fail: "+e;
		}
	}
}
