package intermediate.assignment1;

import intermediate.support.SupportFunctions;

import java.util.List;
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



public class Test3 {
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
	  
	  driver.findElement(By.xpath("//*[@id='menu-item-146']/a")).click();
	  driver.findElement(By.xpath("//*[@id='ui-id-3']")).click();
	  driver.findElement(By.xpath("//*[@id='datepicker3']")).click();
	  driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/select[2]")).click();
	  others.selectOptionWithTextClass("2017", "ui-datepicker-year", "option");
	  driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/select[1]")).click();
	  others.selectOptionWithTextClass("Dec", "ui-datepicker-month", "option");
	 
		WebElement calenderBody=driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody"));
        List<WebElement> calenderColumns = calenderBody.findElements(By.tagName("td"));
        for (WebElement cell: calenderColumns) {
            if (cell.getText().equals("17")) {
                cell.click();
                break;
            }
        }
		

    
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


