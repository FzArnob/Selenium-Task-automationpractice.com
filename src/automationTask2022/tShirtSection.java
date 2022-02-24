package automationTask2022;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
			if(!driver.findElements(By.className("layered_color")).isEmpty()) {
				// works in desktop screen
				driver.findElement(By.id("layered_id_attribute_group_"+boxID)).click();
				if(driver.findElement(By.className("cat-name")).getAttribute("innerText").contains(color)) throw new Exception("Filter Color: box click failed");
            	return "Added Filter: Color "+ color+"(using box click)";
			} else if (!driver.findElements(By.id("layered_block_left")).isEmpty()) {
				// works in mobile screen
				driver.findElement(By.id("layered_block_left")).click();
				sleep(2000);
				driver.findElement(By.id("layered_id_attribute_group_"+boxID)).click();
				if(driver.findElement(By.className("cat-name")).getAttribute("innerText").contains(color)) throw new Exception("Filter Color: box click failed");
            	return "Added Filter: Color "+ color+"(using box click)";
			} else {
				// alternative [if class structure changes frequently]
				driver.navigate().to("http://automationpractice.com/index.php?id_category=5&controller=category#/color-"+color);
				return "Added Filter using url:Color "+color;
			}
		} catch (Exception e) {
			return "Error: "+e;
		}
	}
	public String addColorFilter(String color) {
		try {
			if(!driver.findElements(By.className("layered_color")).isEmpty()) {
				// works in desktop screen
				List<WebElement> list = driver.findElements(By.className("layered_color"));
				for (WebElement i : list) {
		            if(i.getAttribute("innerText").contains(color)) {
		            	driver.findElement(By.linkText(i.getAttribute("innerText"))).click();
		            	if(!driver.findElement(By.className("cat-name")).getAttribute("innerText").contains(color)) throw new Exception("Filter Color: text click failed");
		            	return "Added Filter: Color "+color+"(using text click)";
		            }
		        }
			} else if (!driver.findElements(By.id("layered_block_left")).isEmpty()) {
				// works in mobile screen
				driver.findElement(By.id("layered_block_left")).click();
				sleep(2000);
				List<WebElement> list = driver.findElements(By.className("layered_color"));
				for (WebElement i : list) {
		            if(i.getAttribute("innerText").contains(color)) {
		            	driver.findElement(By.linkText(i.getAttribute("innerText"))).click();
		            	if(driver.findElement(By.className("cat-name")).getAttribute("innerText").contains(color)) throw new Exception("Filter Color: text click failed");
		            	return "Added Filter: Color "+color+"(using text click)";
		            }
		        }
			} else {
				// alternative [if class structure changes frequently]
				driver.navigate().to("http://automationpractice.com/index.php?id_category=5&controller=category#/color-"+color);
				return "Added Filter using url:Color "+color;
			}
		return "Filter not found: Color "+color;
		} catch (Exception e) {
			return "Error: "+e;
		}
	}
	public String addTShirtToCart(int item) {
		try {
			driver.findElements(By.linkText("Add to cart")).get(item).click();
			sleep(3000);
			if(driver.findElements(By.xpath("//*[text()[contains(., 'Continue shopping')]]")).isEmpty()) throw new Exception("Adding T-shirt to cart failed!");	
			driver.findElement(By.xpath("//*[text()[contains(., 'Continue shopping')]]")).click();
			return "No. "+(item+1)+" item(T-shirt) added to cart";
		} catch (Exception e) {
			return "Error: "+e;
		}
	}
	public String gotoSection(boolean useURL) {
		try {
			if(useURL) {
				// alternative [if class structure changes frequently]
				driver.navigate().to("http://automationpractice.com/index.php?id_category=5&controller=category");
				return "T-shirts Section Loaded (using url)";
			}
			if(!driver.findElements(By.linkText("T-SHIRTS")).isEmpty()) {
				// works in desktop screen
				driver.findElement(By.linkText("T-SHIRTS")).click();
			} else if (!driver.findElements(By.className("cat-title")).isEmpty()) {
				// works in mobile screen
				driver.findElement(By.className("cat-title")).click();
				sleep(2000);
				driver.findElement(By.linkText("T-SHIRTS")).click();
			} 
			return "T-shirts Section Loaded";
		} catch (Exception e) {
			return "Error: "+e;
		}
	}
}
