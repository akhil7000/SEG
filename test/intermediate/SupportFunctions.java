package intermediate;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
		
	
	public void selectOptionWithTextEquals(String textToSelect,String ids,String tag) {
		try {
			WebElement autoOptions = driver.findElement(By.id(ids));
//			WebDriverWait wait=new WebDriverWait(driver, 5);
//			wait.until(ExpectedConditions.visibilityOf(autoOptions));

			List<WebElement> optionsToSelect = autoOptions.findElements(By.tagName(tag));
			for(WebElement option : optionsToSelect){
		        if(option.getText().equals(textToSelect)) {
		        	System.out.println("Trying to select: "+textToSelect);
		            option.click();
		            break;
		        }
		    }
			
		} catch (NoSuchElementException e) {
			System.out.println(e.getStackTrace());
		}
		catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}


	
	
}