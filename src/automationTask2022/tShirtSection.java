package automationTask2022;

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
}
