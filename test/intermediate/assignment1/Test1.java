package intermediate.assignment1;

import intermediate.support.SupportFunctions;

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

  
  @DataProvider(name = "Assignment1_data")
  public Object[][] createData1() {
	  others=new SupportFunctions(driver);
      Object[][] retObjArr=others.getTableArray(".\\test\\resources\\data\\Assignment1_data.xls",
              "DataPool", "TestData1");
      return(retObjArr);
  }
  
  
  @Test(dataProvider="Assignment1_data")
  public void testCrossFinal(String firstname,String lastname,String martialStatus,String hobbyDance,String hobbyReading,
		  					 String hobbyCricket,String country,String dob,String phoneNumber,String username,String email,
		  					 String profilePath,String aboutYourself,String password) throws Exception {
	  
		iterator++;  
	  String url="http://demoqa.com/";
	  
	  driver.get(url);
	  
	  driver.findElement(By.xpath("//*[@id='menu-item-374']")).click();
	  driver.findElement(By.xpath("//*[@id='name_3_firstname']")).sendKeys(firstname);
	  driver.findElement(By.xpath("//*[@id='name_3_lastname']")).sendKeys(lastname);
	  
	  if(martialStatus.equals("single"))
		  driver.findElement(By.xpath("//*[@id='pie_register']/li[2]/div/div/input[1]")).click();
	  else if(martialStatus.equals("married"))
		  driver.findElement(By.xpath("//*[@id='pie_register']/li[2]/div/div/input[2]")).click();
	  else 	
		  driver.findElement(By.xpath("//*[@id='pie_register']/li[2]/div/div/input[3]")).click();
		  
			 
 
	  if(hobbyDance.equals("Yes"))
	  driver.findElement(By.xpath("//*[@id='pie_register']/li[3]/div/div[1]/input[1]")).click();
	  if(hobbyReading.equals("Yes"))
	  driver.findElement(By.xpath("//*[@id='pie_register']/li[3]/div/div[1]/input[2]")).click();
	  if(hobbyCricket.equals("Yes"))
	  driver.findElement(By.xpath("//*[@id='pie_register']/li[3]/div/div[1]/input[3]")).click();

	  driver.findElement(By.xpath("//*[@id='dropdown_7']")).click();
	  others.selectOptionWithTextEquals(country, "dropdown_7", "option");
	 
	  String[] dobComponents = dob.split("/");
	  String month = dobComponents[0];
	  String day = dobComponents[1]; 
	  String year = dobComponents[2]; 
	  System.out.println(year);


	  driver.findElement(By.xpath("//*[@id='mm_date_8']")).click();
	  others.selectOptionWithTextEquals(month, "mm_date_8", "option");	  
	  driver.findElement(By.xpath("//*[@id='dd_date_8']")).click();
	  others.selectOptionWithTextEquals(day, "dd_date_8", "option");	  
	  driver.findElement(By.xpath("//*[@id='yy_date_8']")).click();
	  others.selectOptionWithTextEquals(year, "yy_date_8", "option");	  
	  
	  driver.findElement(By.xpath("//*[@id='phone_9']")).sendKeys(phoneNumber);
	  driver.findElement(By.xpath("//*[@id='username']")).sendKeys(username);
	  driver.findElement(By.xpath("//*[@id='email_1']")).sendKeys(email);
	  driver.findElement(By.xpath("//*[@id='profile_pic_10']")).sendKeys(profilePath);
	  driver.findElement(By.xpath("//*[@id='description']")).sendKeys(aboutYourself);
	  driver.findElement(By.xpath("//*[@id='password_2']")).click();
	  driver.findElement(By.xpath("//*[@id='password_2']")).sendKeys(password);
	  driver.findElement(By.xpath("//*[@id='confirm_password_password_2']")).click();
	  driver.findElement(By.xpath("//*[@id='confirm_password_password_2']")).sendKeys(password);
	  
	  String passwordStrength = driver.findElement(By.xpath("//*[@id='piereg_passwordStrength']")).getText();
	  if(!(passwordStrength.equals("Medium")||passwordStrength.equals("Strong")))
		  Assert.fail("Test failed because of weak password");
	  driver.findElement(By.xpath("//*[@id='pie_register']/li[14]/div/input")).click();
	  
	  String confirmationMsg=driver.findElement(By.xpath("//*[@id='post-49']/div/p")).getText();
	  
	  Assert.assertEquals(confirmationMsg, "Thank you for your registration");
    
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


