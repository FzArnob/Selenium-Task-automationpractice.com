package automationTask2022;

import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
				sleep(4000);
				if(driver.findElements(By.className("navigation_page")).isEmpty()) return "Fail: Cart loading failed!";	
				return "Cart Loaded (using url)";
			}
			// works in desktop / mobile screen
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement cart = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()[contains(., 'Cart')]]")));
			cart.click();	
			for(int j=0; j<20; j++) {
				sleep(1000);
				if(!driver.findElements(By.className("navigation_page")).isEmpty()) {
					if(driver.findElement(By.className("navigation_page")).getAttribute("innerText").contains("Your shopping cart")) return "Pass: Cart Loaded";
				}
			}
			return "Fail: Cart loading failed! (waited 20 seconds)";
		} catch (Exception e) {
			return "Fail: "+e;
		}
	}
	public String submitSummary() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement element = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.className("standard-checkout")));
			element.click();
			sleep(1000);
			for(int j=0; j<20; j++) {
				sleep(1000);
				if(!driver.findElements(By.linkText("Summary")).isEmpty() || !driver.findElements(By.linkText("01. Summary")).isEmpty()) return "Pass: Submitted Summary"; 
				
			}
			return "Fail: Submit Summary failed! (waited 20 seconds)"; 
		} catch (Exception e) {
			return "fail: "+e;
		}		
	}
	public String submitAddress() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement element = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='processAddress']")));
			element.click();
			sleep(1000);
			for(int j=0; j<20; j++) {
				sleep(1000);
				if(!driver.findElements(By.linkText("Address")).isEmpty() || !driver.findElements(By.linkText("03. Address")).isEmpty()) return "Pass: Submitted Address";  
				
			}
			return "Fail: Submit Address failed! (waited 20 seconds)";
		} catch (Exception e) {
			return "fail: "+e;
		}		
	}
	public String submitShipping() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement check = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='cgv']")));
			check.click();
			WebElement element = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='processCarrier']")));
			element.click();
			sleep(1000);
			for(int j=0; j<20; j++) {
				sleep(1000);
				if(!driver.findElements(By.linkText("Shipping")).isEmpty() || !driver.findElements(By.linkText("04. Shipping")).isEmpty()) return "Pass: Submitted Shipping";  
				
			}
			return "Fail: Submit Shipping failed! (waited 20 seconds)";
		} catch (Exception e) {
			return "Fail: "+e;
		}		
	}
	public String submitPayment(String cls) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement method = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.className(cls)));
			method.click();
			for(int j=0; j<20; j++) {
				sleep(1000);
				if(!driver.findElements(By.className("navigation_page")).isEmpty()) {
					if(driver.findElement(By.className("navigation_page")).getAttribute("innerText").contains("Check payment")) return "Pass: Payment selected [Classname: "+ cls+"]"; 
				}
			}
			return "Fail: Submit Payment failed! (waited 20 seconds)";
		} catch (Exception e) {
			return "Fail: "+e;
		}		
	}
	public String confirmOrder() {
		try {
			List<WebElement> list = driver.findElements(By.xpath("//button[@type='submit']"));
			for(WebElement i : list) {
				if(i.getAttribute("innerText").contains("I confirm my order")) {
					i.click();
					break;
				}
			}
			sleep(2000);
			for(int j=0; j<20; j++) {
				sleep(1000);
				if(!driver.findElements(By.className("alert-success")).isEmpty()) return "Pass: Order Placed"; 
			}
			return "Fail: Order Confirmation Failed (waited 20 seconds)";
		} catch (Exception e) {
			return "Fail: "+e;
		}		
	}
}
