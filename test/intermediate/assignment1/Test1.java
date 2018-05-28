package intermediate.assignment1;

import intermediate.support.Constants;
import intermediate.support.SupportFunctions;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**************************************************************************************************
 * Assignment1-Test1
 * 
 *************************************************************************************************/

public class Test1 {
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

  @DataProvider(name = "Assignment1_data")
  public Object[][] createData1() {
	  others=new SupportFunctions(driver);
      Object[][] retObjArr=others.getTableArray(Constants.Path_TestData_Assignment1, Constants.Path_TestData_SheetName, 
				Constants.Path_TestData_Delimiter);
      return(retObjArr);
  }
  
  @Test(dataProvider="Assignment1_data")
  public void testCrossFinal(String firstname,String lastname,String martialStatus,String hobbyDance,String hobbyReading,
		  					 String hobbyCricket,String country,String dob,String phoneNumber,String username,String email,
		  					 String profilePath,String aboutYourself,String password,
		  					 String expectedConfirmationMsg) throws Exception {	  
	  
	  FileInputStream objfile = new FileInputStream(Constants.Path_OR);
	  obj.load(objfile);
	  
	  String[] dobComponents = dob.split("/");
	  String month = dobComponents[0];
	  String day = dobComponents[1]; 
	  String year = dobComponents[2]; 
	  String passwordStrength;
	  String confirmationMsg;
	 
	  driver.get(obj.getProperty("assignment1Url"));
	  driver.findElement(By.xpath(obj.getProperty("btn_registration"))).click();
	  driver.findElement(By.xpath(obj.getProperty("txt_firstName"))).sendKeys(firstname);
	  driver.findElement(By.xpath(obj.getProperty("text_lastName"))).sendKeys(lastname);
	  
	  if(martialStatus.equals("single"))
		  driver.findElement(By.xpath(obj.getProperty("radio_MartialStatusSingle"))).click();
	  else if(martialStatus.equals("married"))
		  driver.findElement(By.xpath(obj.getProperty("radio_MartialStatusMarried"))).click();
	  else 	
		  driver.findElement(By.xpath(obj.getProperty("radio_MartialStatusDivorced"))).click();
		  
	  if(hobbyDance.equals("Yes"))
	  driver.findElement(By.xpath(obj.getProperty("chck_hobbyDance"))).click();
	  if(hobbyReading.equals("Yes"))
	  driver.findElement(By.xpath(obj.getProperty("chck_hobbyReading"))).click();
	  if(hobbyCricket.equals("Yes"))
	  driver.findElement(By.xpath(obj.getProperty("chck_hobbyCricket"))).click();

	  driver.findElement(By.id(obj.getProperty("dropdown_country"))).click();
	  others.selectOptionWithTextEquals(country, obj.getProperty("dropdown_country"), "option");
	  driver.findElement(By.id(obj.getProperty("dropdown_dobMonth"))).click();
	  others.selectOptionWithTextEquals(month, obj.getProperty("dropdown_dobMonth"), "option");	  
	  driver.findElement(By.id(obj.getProperty("dropdown_dobDay"))).click();
	  others.selectOptionWithTextEquals(day, obj.getProperty("dropdown_dobDay"), "option");	  
	  driver.findElement(By.id(obj.getProperty("dropdown_dobYear"))).click();
	  others.selectOptionWithTextEquals(year, obj.getProperty("dropdown_dobYear"), "option");	  
	  driver.findElement(By.xpath(obj.getProperty("txt_phoneNumber"))).sendKeys(phoneNumber);
	  driver.findElement(By.xpath(obj.getProperty("txt_username"))).sendKeys(username);
	  driver.findElement(By.xpath(obj.getProperty("txt_email"))).sendKeys(email);
	  driver.findElement(By.xpath(obj.getProperty("txt_profilePicture"))).sendKeys(profilePath);
	  driver.findElement(By.xpath(obj.getProperty("txt_aboutYourself"))).sendKeys(aboutYourself);
	  driver.findElement(By.xpath(obj.getProperty("txt_password"))).click();
	  driver.findElement(By.xpath(obj.getProperty("txt_password"))).sendKeys(password);
	  driver.findElement(By.xpath(obj.getProperty("txt_confirmPassword"))).click();
	  driver.findElement(By.xpath(obj.getProperty("txt_confirmPassword"))).sendKeys(password);
	  
	  passwordStrength = driver.findElement(By.xpath(obj.getProperty("ele_passwordStrength"))).getText();
	  if(!(passwordStrength.equals("Medium")||passwordStrength.equals("Strong")))
		  Assert.fail("Test failed because of weak password");
	  driver.findElement(By.xpath(obj.getProperty("btn_submit"))).click();
	  confirmationMsg=driver.findElement(By.xpath(obj.getProperty("ele_confirmationMsg"))).getText();
	  
	  Assert.assertEquals(confirmationMsg, expectedConfirmationMsg);   
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


