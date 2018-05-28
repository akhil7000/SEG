package intermediate.assignment1;

import intermediate.support.Constants;
import intermediate.support.SupportFunctions;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**************************************************************************************************
 * Assignment1-Test2
 * 
 *************************************************************************************************/
public class Test2 {
  private WebDriver driver;
  SupportFunctions others;
  Properties obj = new Properties();
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeMethod
public void setUp() throws Exception {
	  others=new SupportFunctions(driver);
	  driver=others.startChrome();		  
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }

  @Test
  public void testCrossFinal() throws Exception {
	 	  
      WebElement dragmeDownBullet,belowItem2,belowItem4;
      Actions builder;
      Action dragAndDropBelowItem2,dragAndDropBelowItem4;
      FileInputStream objfile = new FileInputStream(Constants.Path_OR);
  	  obj.load(objfile);
	  driver.get(obj.getProperty("assignment1Url"));
	  driver.findElement(By.xpath(obj.getProperty("btn_dragabble"))).click();
	  driver.findElement(By.xpath(obj.getProperty("btn_dragabbleSortable"))).click();
	  
	  dragmeDownBullet = driver.findElement(By.xpath("//*[@id='draggablebox']"));
	  belowItem2 = driver.findElement(By.xpath("//*[@id='sortablebox']/li[2]"));
	  belowItem4 = driver.findElement(By.xpath("//*[@id='sortablebox']/li[5]"));

	  builder = new Actions(driver);
	  dragAndDropBelowItem2 = builder.clickAndHold(dragmeDownBullet).moveToElement(belowItem2).moveByOffset(0, 10).release().build();
	  dragAndDropBelowItem4 = builder.clickAndHold(dragmeDownBullet).moveToElement(belowItem4).moveByOffset(0, 10).release().build();
	  dragAndDropBelowItem2.perform();
	  others.waitUntilElementGetsValueEquals(obj.getProperty("bullet_list3"), "Drag me down");
	  dragAndDropBelowItem4.perform();      
}

  @AfterMethod
public void tearDown(ITestResult result) throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      Assert.fail(verificationErrorString);
    }
  }
}


