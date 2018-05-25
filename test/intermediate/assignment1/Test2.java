package intermediate.assignment1;

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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class Test2 {
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
	  
	  String url="http://demoqa.com/";
	  
	  driver.get(url);
	  driver.findElement(By.xpath("//*[@id='menu-item-140']/a")).click();
	  driver.findElement(By.xpath("//*[@id='ui-id-5']")).click();
	  
	  WebElement dragmeDownBullet = driver.findElement(By.xpath("//*[@id='draggablebox']"));
	  WebElement belowItem2 = driver.findElement(By.xpath("//*[@id='sortablebox']/li[2]"));
	  WebElement belowItem4 = driver.findElement(By.xpath("//*[@id='sortablebox']/li[5]"));

	  Actions builder = new Actions(driver);

	  Action dragAndDropBelowItem2 = builder.clickAndHold(dragmeDownBullet).moveToElement(belowItem2).moveByOffset(0, 10).release().build();
	  Action dragAndDropBelowItem4 = builder.clickAndHold(dragmeDownBullet).moveToElement(belowItem4).moveByOffset(0, 10).release().build();
	  dragAndDropBelowItem2.perform();
	  others.waitUntilElementGetsValueEquals("//*[@id='sortablebox']/li[3]", "Drag me down");
	  dragAndDropBelowItem4.perform();

	  
    
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


