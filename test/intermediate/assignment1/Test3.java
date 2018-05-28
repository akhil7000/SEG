package intermediate.assignment1;

import intermediate.support.Constants;
import intermediate.support.SupportFunctions;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**************************************************************************************************
 * Assignment1-Test3
 * 
 *************************************************************************************************/
public class Test3 {
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
	  
      String date="14";
      String month="Dec";
      String year="2017";
      
      FileInputStream objfile = new FileInputStream(Constants.Path_OR);
      obj.load(objfile);
	  driver.get(obj.getProperty("assignment1Url"));
	  driver.findElement(By.xpath(obj.getProperty("btn_datepicker"))).click();
	  driver.findElement(By.xpath(obj.getProperty("btn_displayMonthYear"))).click();
	  driver.findElement(By.xpath(obj.getProperty("txt_pickDate"))).click();
	  driver.findElement(By.xpath(obj.getProperty("dropdown_year"))).click();
	  others.selectOptionWithTextClass(year, obj.getProperty("dropdown_yearClass"), "option");
	  driver.findElement(By.xpath(obj.getProperty("dropdown_month"))).click();
	  others.selectOptionWithTextClass(month, obj.getProperty("dropdown_monthClass"), "option");
	 
		WebElement calenderBody=driver.findElement(By.xpath(obj.getProperty("ele_calenderBody")));
        List<WebElement> calenderColumns = calenderBody.findElements(By.tagName("td"));
        for (WebElement cell: calenderColumns) {
            if (cell.getText().equals(date)) {
                cell.click();
                break;
            }
        }		    
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


