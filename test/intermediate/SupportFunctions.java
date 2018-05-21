package intermediate;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class SupportFunctions {
	public  WebDriver driver;

	public SupportFunctions(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}

	
	public WebDriver startChrome()
	{	
		System.setProperty("webdriver.chrome.driver", "test/resources/drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
    	options.setBinary("D:\\Chrome App\\chrome.exe");
		
		driver =  new ChromeDriver(options);
		return driver;
	}
	
	public WebDriver startFirefox()
	{
		 File pathToBinary = new File("C:\\Users\\10636643\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		  FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		  FirefoxProfile firefoxProfile = new FirefoxProfile();
		  driver = new FirefoxDriver(ffBinary,firefoxProfile);
		  return driver;
	}
	
	
	public WebDriver startIE()
	{
		System.setProperty("webdriver.ie.driver","test/resources/drivers/IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		return driver;
	}
		
	
	
}