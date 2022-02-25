package automationTask2022;

import org.apache.commons.io.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
public class automateTaskSteps {
	public static WebDriver driver;
	public static String Rdir;
	public static String Rname;
	public static String St;
	public static int ssCount;
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
	
	
	public static void writeFile(String value){
	    String PATH = System.getProperty("user.dir")+"/reports/";
	    String directoryName = PATH.concat(Rdir);
	    String fileName = "log "+Rname + ".txt";

	    File directory = new File(directoryName);
	    if (! directory.exists()){
	        directory.mkdirs();
	    }

	    File file = new File(directoryName + "/" + fileName);
	    try{
	        FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write(value);
	        bw.close();
	    }
	    catch (IOException e){
	        e.printStackTrace();
	        System.exit(-1);
	    }
	}
	
	
	public static String getTimeStamp() {
		Date date= new Date();
	 	long time = date.getTime();
	 	Timestamp ts = new Timestamp(time);
	 	String res = String.valueOf(ts).replace(":", "_");
	 	return " "+res;
	}
	
	
	public static void takeScreenShot(String filename) throws IOException {
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(System.getProperty("user.dir")+"/reports/"+Rdir+"/"+filename+".png"));
	}
	
	
	public static void reportLog(String log) throws IOException {
		System.out.println(log);
		writeFile("Step_"+String.valueOf(ssCount)+"\n"+log+"\n\n");
		takeScreenShot("Step_"+String.valueOf(ssCount)+" ss "+getTimeStamp());
		ssCount++;
	}
	
	
	public static WebDriver LoadSiteInChrome() {
		// setting browser driver property
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		// Task 1
		driver.get("http://automationpractice.com/index.php");
		// initiate report
		St = getTimeStamp();
		Rname = St;
		Rdir = "Chrome report"+St;
		ssCount = 1;
		return driver;
	}
	public static WebDriver LoadSiteInFirefox() {
		// setting browser driver property
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/Drivers/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		
		// Task 1
		driver.get("http://automationpractice.com/index.php");
		// initiate report
		St = getTimeStamp();
		Rname = St;
		Rdir = "Firefox report"+St;
		ssCount = 1;
		return driver;
	}
	public static WebDriver LoadSiteInOpera() {
		// setting browser driver propertySystem.setProperty("webdriver.opera.driver", "E:\\operadriver.exe");
		 
		// it will open the opera browser
		System.setProperty("webdriver.opera.driver", System.getProperty("user.dir")+"/Drivers/operadriver97.exe");
		WebDriver driver = new OperaDriver();
		
		// Task 1
		driver.get("http://automationpractice.com/index.php");
		// initiate report
		St = getTimeStamp();
		Rname = St;
		Rdir = "Opera report"+St;
		ssCount = 1;
		return driver;
	}
	
	
	
	public static void task2(String[] userData) throws IOException {
		
		// Task 2
		System.out.println("// Create user account test");
		// Create user account test
		createAccount user = new createAccount(driver);
		reportLog(user.createAccountWithData(userData));
	}
	
	
	public static void task3to7(String[] loginData) throws IOException {
		
		// Task 3
		System.out.println("// Log into user account test");
		// Log into user account test
		loginUser login = new loginUser(driver);
		reportLog(login.loginWithData(loginData));
		
		
		// Task 4
		System.out.println("// Casual Dresses Section");
		// Casual Dresses Section
		casualDressesSection cdress = new casualDressesSection(driver);
			// goto Casual Dresses with url
//		reportLog(cdress.gotoSection(true));
			// user interaction click
		reportLog(cdress.gotoSection(false)); // parameter <true> for checking with url
			// add dress to cart
		reportLog(cdress.addDressToCart());
		
//		
		// Task 5
		System.out.println("// T-shirts Section");
		// T-shirts Section
		tShirtSection tshirt = new tShirtSection(driver);
			// goto T-shirt with url
// 		reportLog(tshirt.gotoSection(true));
		
			// user interaction click
				// add filter by clicking the color text
		System.out.println("// case 01: add filter by clicking the 'Blue' text");
 	    reportLog(tshirt.gotoSection(false)); // parameter <true> for checking with url
		reportLog(tshirt.addColorFilter("Blue"));
				// add filter by clicking the color box
		System.out.println("// case 02: add filter by clicking the Blue text");
		reportLog(tshirt.gotoSection(false)); // parameter <true> for checking with url
		reportLog(tshirt.addColorFilter("Blue", 14));
			// add t-shirt to cart
		reportLog(tshirt.addTShirtToCart(0));
		
		
		// Task 6
		System.out.println("// Checkout Section");
		// Checkout Section
		checkoutSection checkout = new checkoutSection(driver);
		// goto checkout with url
//		reportLog(checkout.gotoCheckout(true));
		
			// user interaction click
		reportLog(checkout.gotoCheckout(false));
		reportLog(checkout.submitSummary());
		reportLog(checkout.submitAddress());
		reportLog(checkout.submitShipping());
		reportLog(checkout.submitPayment("cheque"));
		reportLog(checkout.confirmOrder());
		
		
		// Task 7
		System.out.println("// Sign out Section");
		// Sign out Section
		signoutUser signoutSection = new signoutUser(driver);
		reportLog(signoutSection.signOut());
	}
	
	
	public static void main(String[] args) throws IOException {
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
		
		String[] userData1 = {
				 "johnkobber1955@gmail.com", // change the numbers in email to test new data
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
				 "Office Address"
				 };
		String[] loginData1 = {
				 "johnkobber1955@gmail.com", // change the numbers in email to test new data
				 "12345"
		};
		String[] userData2 = {
				 "penymodreguz1905@gmail.com",
				 "2",
				 "Peny",
				 "Modreguz",
				 "abcde",
				 "13",
				 "7",
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
				 "Visual Artist",
				 "208-482-8798",
				 "208-579-8139",
				 "Office Address"
				 };
		String[] loginData2 = {
				 "penymodreguz1905@gmail.com",
				 "abcde"
		};
		
		// Task 1
		// Compatibility testing with different browsers (Select one for test)
//		driver = LoadSiteInChrome();
//		driver = LoadSiteInFirefox();
		driver = LoadSiteInOpera();
		// Create two accounts
		task2(userData1);
		// Sign out to test new data
		new signoutUser(driver).signOut();
		
		task2(userData2);
		// Sign out to test new data
		new signoutUser(driver).signOut();
		
		// Test Scenario for both users
		task3to7(loginData1);
		task3to7(loginData2);
		
		driver.quit();
	}
}
