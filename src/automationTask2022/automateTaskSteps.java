package automationTask2022;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
public class automateTaskSteps {
	public static void sleep(int ms){
		try
		{
			Thread.sleep(ms);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	public static WebDriver LoadSiteInChrome() {
		// setting browser driver property
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\fzarn\\eclipse-workspace\\automationTask2022\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://automationpractice.com/index.php");
		return driver;
	}
	public static void main(String[] args) {
		WebDriver driver = LoadSiteInChrome();
		// 1st account
		// Format:
		// {"String email",
		//  "String gender",
		//  "String fname",
		//  "String lname",
		//  "String pass",
		//  "String bday",
		//  "String bmonth",
		//  "String byear",
		//  "String newsletter",
		//  "String offers",
		//  "String company",
		//  "String address1",
		//  "String address2",
		//  "String city",
		//  "String state",
		//  "String zip",
		//  "String country",
		//  "String addinfo",
		//  "String hphone",
		//  "String mphone",
		//  "String alias"}
		
		String[] userData = {
				 "abcd32414562@gmail.com",
				 "1",
				 "John",
				 "Kobber",
				 "12345",
				 "3",
				 "9",
				 "1999",
				 "true",
				 "true",
				 "FINIEN",
				 "4578 ",
				 "Young Road",
				 "Wilder",
				 "Idaho",
				 "83676",
				 "United States",
				 "Graphics Designer",
				 "208-482-8798",
				 "208-579-8139",
				 "Home Address"
				 };
		String[] loginData = {
				 "abcd32414562@gmail.com",
				 "12345"
		};
		// Create user account test
		createAccount user = new createAccount(driver);
//		System.out.println(user.createAccountWithData(userData));
		
		// Sign out to test login feature
//		if(!driver.findElements(By.linkText("Sign out")).isEmpty()) driver.findElement(By.linkText("Sign out")).click();
		
		// Log into user account test
		loginUser login = new loginUser(driver);
//		System.out.println(login.loginWithData(loginData));
		
		// Casual Dresses Section
		casualDressesSection cdress = new casualDressesSection(driver);
		System.out.println(cdress.gotoSection());
//		System.out.println(cdress.addDressToCart());
		
		// T-shirts Section
		tShirtSection tshirt = new tShirtSection(driver);
		System.out.println(tshirt.gotoSection());
		
	}
}
