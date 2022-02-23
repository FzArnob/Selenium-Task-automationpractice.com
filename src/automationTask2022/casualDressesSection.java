package automationTask2022;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class casualDressesSection {
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
	public casualDressesSection(WebDriver driver) {
		this.driver = driver;
	}
	public String gotoSection() {
		try {
		driver.findElement(By.linkText("DRESSES")).click();
		driver.findElements(By.linkText("Casual Dresses")).get(0).click();
		return "Casual Dresses Loaded";
		} catch (Exception e) {
			return "Error: "+e;
		}
	}
	public String addDressToCart() {
		try {
		driver.findElements(By.linkText("Add to cart")).get(0).click();
		sleep(3000);
		driver.findElement(By.linkText("Continue shopping")).click();		
		return "1st item added to cart";
		} catch (Exception e) {
			return "Error: "+e;
		}
	}
}