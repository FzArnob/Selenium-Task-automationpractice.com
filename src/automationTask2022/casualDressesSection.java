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
		if(!driver.findElements(By.linkText("DRESSES")).isEmpty()) {
			// works in desktop screen
			driver.findElement(By.linkText("DRESSES")).click();
			driver.findElements(By.linkText("Casual Dresses")).get(0).click();
		} else if (!driver.findElements(By.className("cat-title")).isEmpty()) {
			// works in mobile screen
			driver.findElement(By.className("cat-title")).click();
			sleep(2000);
			driver.findElement(By.linkText("DRESSES")).click();
			driver.findElements(By.linkText("CASUAL DRESSES")).get(0).click();
		} else {
			// alternative [if class structure changes frequently]
			driver.navigate().to("http://automationpractice.com/index.php?id_category=9&controller=category");
		}
		return "Casual Dresses Loaded";
		} catch (Exception e) {
			return "Error: "+e;
		}
	}
	public String addDressToCart() {
		try {
		driver.findElements(By.linkText("Add to cart")).get(0).click();
		sleep(3000);
		driver.findElement(By.xpath("//*[text()[contains(., 'Continue shopping')]]")).click();		
		return "1st item added to cart";
		} catch (Exception e) {
			return "Error: "+e;
		}
	}
}
