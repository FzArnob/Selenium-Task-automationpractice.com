package automationTask2022;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	public String addColorFilter(String color, int boxID) {
		try {
				// works in desktop / mobile screen
				String Ucolor = color.toUpperCase();
				WebDriverWait wait = new WebDriverWait(driver, 10);
				WebElement cat = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("layered_block_left")));
				cat.click();
				sleep(1000);
				WebElement method = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("layered_id_attribute_group_"+boxID)));
				method.click();
				sleep(1000);
				for(int i=0; i<30; i++) {
					sleep(1000);
					if(!driver.findElements(By.className("cat-name")).isEmpty()) {
						if(driver.findElement(By.className("cat-name")).getAttribute("innerText").contains(Ucolor)) return "Pass: Added Filter: Color "+ color+"(using box click)";
					}
				}
				return "Fail: Filter Color: Doesn't work if "+color+" box is clicked (waited 30 seconds)";
		} catch (Exception e) {
			return "Fail: "+e;
		}
	}
	public String addColorFilter(String color) {
		try {
				// works in desktop / mobile screen
				String Ucolor = color.toUpperCase();
				WebDriverWait wait = new WebDriverWait(driver, 10);
				WebElement cat = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("layered_block_left")));
				cat.click();
				sleep(2000);
				List<WebElement> list = driver.findElements(By.className("layered_color"));
				for (WebElement i : list) {
		            if(i.getAttribute("innerText").contains(color)) {
						WebElement method = wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.linkText(i.getAttribute("innerText"))));
						method.click();
						for(int j=0; j<30; j++) {
							sleep(1000);
							if(!driver.findElements(By.className("cat-name")).isEmpty()) {
								if(driver.findElement(By.className("cat-name")).getAttribute("innerText").contains(Ucolor)) return "Pass: Added Filter: Color "+color+"(using text click)";
							}
						}
						return "Fail: Filter Color: Doesn't work if '"+color+"' Text is clicked (waited 30 seconds)";
		            }
		        }
		return "Pass: Filter not found: Color "+color;
		} catch (Exception e) {
			return "Fail: "+e;
		}
	}
	public String addTShirtToCart(int item) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement ele = driver.findElement(By.className("product-container"));
			Actions action = new Actions(driver);
			// hover for desktop
			action.moveToElement(ele).perform();
			WebElement add = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.linkText("Add to cart")));
			add.click();
			sleep(2000);
			WebElement con = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()[contains(., 'Continue shopping')]]")));
			con.click();
			sleep(1000);
			return "Pass: First item(T-shirt) added to cart [name: "+driver.findElement(By.xpath("//h5[@itemprop='name']")).getAttribute("innerText")+"]";
		} catch (Exception e) {
			return "Fail: "+e;
		}
	}
	public String gotoSection(boolean useURL) {
		try {
			if(useURL) {
				// alternative [if class structure changes frequently]
				driver.navigate().to("http://automationpractice.com/index.php?id_category=5&controller=category");
				sleep(4000);
				if(!driver.findElement(By.className("category-name")).getAttribute("innerText").contains("T-shirts")) return "Fail: T-shirts Loading Failed";
				return "Pass: T-shirts Section Loaded (using url)";
			}
			if(!driver.findElements(By.linkText("T-SHIRTS")).isEmpty()) {
				// works in desktop screen
				driver.findElement(By.linkText("T-SHIRTS")).click();
				sleep(4000);
			} else if (!driver.findElements(By.className("cat-title")).isEmpty()) {
				// works in mobile screen
				driver.findElement(By.className("cat-title")).click();
				sleep(2000);
				driver.findElement(By.linkText("T-SHIRTS")).click();
				sleep(4000);
			} 
			for(int i=0; i<20; i++) {
				sleep(1000);
				if(!driver.findElements(By.className("category-name")).isEmpty()) {
					if(driver.findElement(By.className("category-name")).getAttribute("innerText").contains("T-shirts")) return "Pass: T-shirts Section Loaded";
				}
			}
			return "Fail: T-shirts Loading Failed";
		} catch (Exception e) {
			return "Fail: "+e;
		}
	}
}
