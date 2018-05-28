package intermediate.assignment2;

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
 * Assignment2-Test1
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

  @DataProvider(name = "Assignment2_data")
  public Object[][] createData1() {
	  others=new SupportFunctions(driver);
      Object[][] retObjArr=others.getTableArray(Constants.Path_TestData_Assignment2, Constants.Path_TestData_SheetName, 
    		  									Constants.Path_TestData_Delimiter);
      return(retObjArr);
  }
  
  @Test(dataProvider="Assignment2_data")
  public void testCrossFinal(String searchKeyword,String expectedSearchResult1,String expectedSearchResult2,
		  						String expectedSearchResult3) throws Exception {
	  
	  FileInputStream objfile = new FileInputStream(Constants.Path_OR);
	  obj.load(objfile);
	  driver.get(obj.getProperty("assignment2Url"));
	  others.frameSwitcher("no", obj.getProperty("frame_destination"));
	  driver.findElement(By.xpath(obj.getProperty("txt_search"))).sendKeys(searchKeyword);
	  driver.findElement(By.xpath(obj.getProperty("btn_search"))).click();
	  others.waitUntilElementGetsValueEquals(obj.getProperty("ele_searchResultTitle"),obj.getProperty("ele_searchResultTitleWord"));
	  String result1=driver.findElement(By.xpath(obj.getProperty("ele_searchResult_1"))).getText();
	  String result2=driver.findElement(By.xpath(obj.getProperty("ele_searchResult_2"))).getText();
	  Assert.assertEquals(result1, expectedSearchResult1, "Test failed as result 1 is not matching");
	  Assert.assertEquals(result2, expectedSearchResult2, "Test failed as result 2 is not matching");
	  others.testStep("Passed");	
	  driver.findElement(By.xpath(obj.getProperty("ele_searchResult_1"))).click();
	  others.waitUntilElementGetsValueEquals(obj.getProperty("ele_jmeterQuiz"), expectedSearchResult3);
	  others.testStep("Passed");
}

  @AfterMethod
public void tearDown(ITestResult result) throws Exception {
	  if(result.getStatus()==ITestResult.FAILURE){
		  others.testStep("Failed");
	  }
  	driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      Assert.fail(verificationErrorString);
    }
  }

}



