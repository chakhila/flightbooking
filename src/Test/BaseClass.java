package Test;


import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseClass {
	public WebDriver driver;
	public  DateFormat df=new SimpleDateFormat("dd MMM yyyy");
    public  Date d=new Date();  
    public  String time=df.format(d);
	
	public  File fi;
	public  XSSFWorkbook wb;
	public  XSSFSheet s;
	public ExtentReports extent=new ExtentReports("ExtentReports\\bookingflight.html",true);
	public ExtentTest logger=extent.startTest("VerifyEvenet site");
	
   
	public XSSFSheet Excel(String file) throws  IOException, InvalidFormatException
		{
	  this.	fi=new File(file);
		 this.wb=new XSSFWorkbook(fi);
		 this.s=wb.getSheet("sheet1");
       	return s;
		}

		public  void passscreenshot(WebDriver driver,String screenshotname,String data,boolean attachedfile) throws IOException
		{
	 File f1= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(f1, new File("Screenshots\\pass\\" + screenshotname +time+".jpg"));
	if(attachedfile=true) {
	logger.log(LogStatus.PASS,data,logger.addScreenCapture("Screenshots\\pass\\" + screenshotname +time+".jpg"));
	}else {
		
		logger.log(LogStatus.PASS,data);
	}
	extent.endTest(logger);
	extent.flush();
	
		}
public  void failscreenshot(WebDriver driver,String screenshotname,String data,boolean attachedfile) throws IOException
	{
	File f1= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(f1, new File("Screenshots\\fail\\" + screenshotname +time+".jpg"));
	if(attachedfile=true) {
		logger.log(LogStatus.PASS,data,logger.addScreenCapture("Screenshots\\pass\\" + screenshotname +time+".jpg"));
		}else {
			logger.log(LogStatus.PASS,data);
		}
	extent.flush();
	
	}
public void  Expiclitwait(int time,String locater) {
	
	WebDriverWait wait=new WebDriverWait(driver,time);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locater)));

}
public void select(WebElement e,String s) {
	 Select sl=new Select(e);
	 sl.selectByVisibleText(s);
}
}
