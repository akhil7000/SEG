package intermediate.support;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Reporter;

public class SupportFunctions {
	public  WebDriver driver;

	public SupportFunctions(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}

	
	public WebDriver startChrome()
	{	
		System.setProperty("webdriver.chrome.driver", "test/resources/drivers/chromedriverV2.38.exe");
		ChromeOptions options = new ChromeOptions();
    	options.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		
		driver =  new ChromeDriver(options);
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

	public void selectOptionWithTextClass(String textToSelect,String clname,String tag) {
		try {
			WebElement autoOptions = driver.findElement(By.className(clname));
//			WebDriverWait wait=new WebDriverWait(driver, 5);
//			wait.until(ExpectedConditions.visibilityOf(autoOptions));
			
			List<WebElement> optionsToSelect = autoOptions.findElements(By.tagName(tag));
			for(WebElement option : optionsToSelect){
		        if(option.getText().trim().contains(textToSelect)) {
		        	System.out.println("Trying to select: "+textToSelect);
		            option.click();
		            break;
		        }
		    }
			
		} catch (NoSuchElementException e) {
			System.out.println(e.getStackTrace());
			System.out.println("No such element");
		}
		catch (Exception e) {
			System.out.println(e.getStackTrace());
			System.out.println("Exception");
		}
	}

	
	  public String[][] getTableArray(String xlFilePath, String sheetName, String tableName){
	      String[][] tabArray=null;
	      try{
	          Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
	          Sheet sheet = workbook.getSheet(sheetName);
	          
	          int startRow,startCol, endRow, endCol,ci,cj;
	          
	          Cell tableStart=sheet.findCell(tableName);
	          startRow=tableStart.getRow();
	          startCol=tableStart.getColumn();

	          Cell tableEnd= sheet.findCell(tableName, startCol+1,startRow+1, 100, 64000,  false);                

	          endRow=tableEnd.getRow();
	          endCol=tableEnd.getColumn();
	          
	          System.out.println("startRow="+startRow+", endRow="+endRow+", " +
	                  "startCol="+startCol+", endCol="+endCol);
	          tabArray=new String[endRow-startRow-1][endCol-startCol-1];
	          ci=0;

	          for (int i=startRow+1;i<endRow;i++,ci++){
	              cj=0;
	              for (int j=startCol+1;j<endCol;j++,cj++){
	                  tabArray[ci][cj]=sheet.getCell(j,i).getContents();
	              }
	          }
	      }
	      catch (Exception e)    {
	          System.out.println("error in getTableArray()");
	          
	      }

	      return(tabArray);
	  }
	  

	  public void waitUntilElementGetsValueEquals(final String elementXpath,final String value){
		  new FluentWait<WebDriver>(driver).withTimeout(5,TimeUnit.SECONDS).pollingEvery(2,TimeUnit.SECONDS).until(new ExpectedCondition<Boolean>(){
		    public Boolean apply(    WebDriver wd){
		      WebElement element=wd.findElement(By.xpath(elementXpath));
		      return element.getText().equals(value);
		    }
		  }
		);
		}
	  
	  public void frameSwitcher(String parentFrame,String targetFrameId)
	  {
		  WebElement fr; 
		  if(parentFrame=="default")
		  {
			  driver.switchTo().defaultContent();
			  fr= driver.findElement(By.name(targetFrameId));
			  driver.switchTo().frame(fr); 
		  }
		  
		  if(parentFrame=="no")
		  {
			  fr= driver.findElement(By.name(targetFrameId));
			  driver.switchTo().frame(fr);
		  }
  
	  }

		public void CaptureScreenshot(int count)
		{
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				try {
					FileUtils.copyFile(scrFile, new File("C:\\Users\\Akhil\\Documents\\Screenshots Failed\\Failed"+count+".jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				String filePath = scrFile.toString();
				String path = "<img src=\"file://" + filePath + "\" alt=\"\"/>";
				Reporter.log(path);

		}

	
	
}