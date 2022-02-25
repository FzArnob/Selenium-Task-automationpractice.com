package automationTask2022;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	public String gotoSection(boolean useURL) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			if (useURL){
				// alternative [if class structure changes frequently]
				driver.navigate().to("http://automationpractice.com/index.php?id_category=9&controller=category");
				sleep(4000);
				if(!driver.findElement(By.className("category-name")).getAttribute("innerText").contains("Casual Dresses")) return "Fail: Casual Dresses Loading Failed";
				return "Pass: Casual Dresses Loaded (using url)";
			}
			if(!driver.findElements(By.linkText("DRESSES")).isEmpty()) {
				// works in desktop screen
				WebElement ele = driver.findElement(By.linkText("DRESSES"));
				Actions action = new Actions(driver);
				// hover for desktop
				action.moveToElement(ele).perform();
				sleep(1000);
				WebElement cad = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.linkText("CASUAL DRESSES")));
				cad.click();
				sleep(4000);
			} else if (!driver.findElements(By.className("cat-title")).isEmpty()) {
				// works in mobile screen
				driver.findElement(By.className("cat-title")).click();
				sleep(3000);
				driver.findElement(By.linkText("DRESSES")).click();
				sleep(2000);
				WebElement cad = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.linkText("CASUAL DRESSES")));
				cad.click();
				sleep(4000);
			}
			for(int i=0; i<20; i++) {
				sleep(1000);
				if(!driver.findElements(By.className("category-name")).isEmpty()) {
					if(driver.findElement(By.className("category-name")).getAttribute("innerText").contains("Casual Dresses")) return "Pass: Casual Dresses Loaded";
				}
			}
			return "Fail: Casual Dresses Loading Failed (waited 20 seconds)";
			
		} catch (Exception e) {
			return "Fail: "+e;
		}
	}
	public String addDressToCart() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement ele = driver.findElement(By.className("product-container"));
			Actions action = new Actions(driver);
			// hover for desktop
			if(Integer. parseInt(driver.findElement(By.cssSelector("body")).getAttribute("offsetWidth")) >1203) action.moveToElement(ele).perform();
			WebElement add = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.linkText("Add to cart")));
			add.click();
			sleep(2000);
			WebElement con = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()[contains(., 'Continue shopping')]]")));
			con.click();
			sleep(1000);
			return "Pass: First item(Dress) added to cart [name: "+driver.findElement(By.xpath("//h5[@itemprop='name']")).getAttribute("innerText")+"]";
		} catch (Exception e) {
			return "Fail: "+e;
		}
	}
}
