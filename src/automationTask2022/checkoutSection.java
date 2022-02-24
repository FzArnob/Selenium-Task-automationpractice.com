package automationTask2022;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class checkoutSection {
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
	public checkoutSection(WebDriver driver) {
		this.driver = driver;
	}
	
	public String gotoCheckout(boolean useURL) {
		try {
			if(useURL) {
				// alternative [if class structure changes frequently]
				driver.navigate().to("http://automationpractice.com/index.php?controller=order");
				return "Cart Loaded (using url)";
			}
			if(!driver.findElements(By.xpath("//*[text()[contains(., 'Cart')]]")).isEmpty()) {
				// works in desktop screen
				driver.findElement(By.xpath("//*[text()[contains(., 'Cart')]]")).click();
			}
			return "Cart Loaded";
		} catch (Exception e) {
			return "Error: "+e;
		}
	}
}
