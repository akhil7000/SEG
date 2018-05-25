package intermediate.assignment2;

import intermediate.support.SupportFunctions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class Test1 {
	String ab="";
  private WebDriver driver;
  SupportFunctions others;
  int iterator=0;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeMethod
public void setUp() throws Exception {
	  others=new SupportFunctions(driver);
	  
//	  driver=others.startFirefox();
	driver=others.startChrome();
//	  driver=others.startIE();
	  
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();

  }

    @Test
  public void testCrossFinal() throws Exception {
	  
	  String url="http://www.globalsqa.com/demo-site/frames-and-windows/#iFrame";
	  
	  driver.get(url);
	  others.frameSwitcher("no", "globalSqa");
	  driver.findElement(By.xpath("//*[@id='s']")).sendKeys("jmeter");
	  driver.findElement(By.xpath("//*[@id='subheader']/div/div/div[1]/form/button")).click();
	  
	  others.CaptureScreenshot(1);
	  
	  
	  others.waitUntilElementGetsValueEquals("//*[@id='wrapper']/div[1]/div[1]/div/div/div/div[2]/h1", "Search results for: jmeter");
	  String result1=driver.findElement(By.xpath("//*[@id='wrapper']/div[1]/div[2]/div/div[2]/ol/li[1]/div/h3")).getText();
	  
	  String result2=driver.findElement(By.xpath("//*[@id='wrapper']/div[1]/div[2]/div/div[2]/ol/li[2]/div/h3/a")).getText();
	  
	  Assert.assertEquals(result1, "JMeter (minimax_tabs)", "Test failed as result 1 is not matching");
	  Assert.assertEquals(result2, "JMeter Training", "Test failed as result 1 is not matching");
	  others.CaptureScreenshot(2);
	  
	  driver.findElement(By.xpath("//*[@id='wrapper']/div[1]/div[2]/div/div[2]/ol/li[1]/div/h3/a")).click();
    
	  others.waitUntilElementGetsValueEquals("//*[@id='post-2194']/div/div/div[1]/h2", "Apache Jmeter Quiz");
	  others.CaptureScreenshot(3);

}

  @AfterMethod
public void tearDown(ITestResult result) throws Exception {
	  	  
    //driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      Assert.fail(verificationErrorString);
    }
  }



}



