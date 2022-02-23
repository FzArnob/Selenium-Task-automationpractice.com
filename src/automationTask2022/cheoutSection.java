package automationTask2022;

import org.openqa.selenium.WebDriver;

public class cheoutSection {
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
	public cheoutSection(WebDriver driver) {
		this.driver = driver;
	}
}
