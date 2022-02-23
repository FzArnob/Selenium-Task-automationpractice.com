package automationTask2022;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class tShirtSection {
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
	public tShirtSection(WebDriver driver) {
		this.driver = driver;
	}
	public String gotoSection() {
		try {
		if(!driver.findElements(By.linkText("T-SHIRTS")).isEmpty()) {
			// works in desktop screen
			driver.findElement(By.linkText("T-SHIRTS")).click();
		} else if (!driver.findElements(By.className("cat-title")).isEmpty()) {
			// works in mobile screen
			driver.findElement(By.className("cat-title")).click();
			sleep(2000);
			driver.findElement(By.linkText("T-SHIRTS")).click();
		} else {
			// alternative [if class structure changes frequently]
			driver.navigate().to("http://automationpractice.com/index.php?id_category=5&controller=category");
		}
		return "T-shirts Section Loaded";
		} catch (Exception e) {
			return "Error: "+e;
		}
	}
}
