package intermediate;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class Assignment1 {
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

  
/*  @DataProvider(name = "EACData")
  public Object[][] createData1() {
	  others=new SupportFunctions(driver);
      Object[][] retObjArr=others.getTableArray("C:\\Users\\10636643\\Documents\\Investment\\Compass_data.xls",
              "EACDataPool", "TestData1");
      return(retObjArr);
  }
*/  
  
//  @Test(dataProvider="EACData")
  @Test
  public void testCrossFinal() throws Exception {
	  
		iterator++;  
	  String url="http://demoqa.com/";
	  
	  driver.get(url);
	  
	  driver.findElement(By.xpath("//*[@id='menu-item-374']")).click();
	  driver.findElement(By.xpath("//*[@id='name_3_firstname']")).sendKeys("abc");
	  driver.findElement(By.xpath("//*[@id='name_3_lastname']")).sendKeys("abc");
	  driver.findElement(By.xpath("//*[@id='pie_register']/li[2]/div/div/input[1]")).click();
	  driver.findElement(By.xpath("//*[@id='pie_register']/li[3]/div/div[1]/input[1]")).click();
	  driver.findElement(By.xpath("//*[@id='dropdown_7']")).click();
	  others.selectOptionWithTextEquals("Algeria", "dropdown_7", "option");
	 
	  driver.findElement(By.xpath("//*[@id='mm_date_8']")).click();
	  others.selectOptionWithTextEquals("2", "mm_date_8", "option");	  
	  driver.findElement(By.xpath("//*[@id='dd_date_8']")).click();
	  others.selectOptionWithTextEquals("1", "dd_date_8", "option");	  
	  driver.findElement(By.xpath("//*[@id='yy_date_8']")).click();
	  others.selectOptionWithTextEquals("1994", "yy_date_8", "option");	  
	  
	  driver.findElement(By.xpath("//*[@id='phone_9']")).sendKeys("0896532147");
	  driver.findElement(By.xpath("//*[@id='username']")).sendKeys("akhil7000");
	  driver.findElement(By.xpath("//*[@id='email_1']")).sendKeys("ak@ls.com");
	  driver.findElement(By.xpath("//*[@id='profile_pic_10']")).sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\Penguins.jpg");
	  driver.findElement(By.xpath("//*[@id='description']")).sendKeys("yolo");
	  driver.findElement(By.xpath("//*[@id='password_2']")).sendKeys("password");
	  driver.findElement(By.xpath("//*[@id='confirm_password_password_2']")).sendKeys("password");
	  
	  String passwordStrength = driver.findElement(By.xpath("//*[@id='piereg_passwordStrength']")).getText();
//	  Assert.assertEquals(passwordStrength,"Very weak" || "");

	  
	  

    
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


