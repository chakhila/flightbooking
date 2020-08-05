package StepDefinition;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;

import Test.BaseClass;
import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;



public class Steps extends BaseClass{
	public static WebDriver driver;

	
	@Given("^Open the chromeBrowser and launch the application$")
	public void open_the_chromeBrowser_and_launch_the_application() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		System.setProperty("webdriver.chrome.driver","D:\\Lib\\chromedriver.exe");
		DesiredCapabilities cap = new DesiredCapabilities();
        cap.setJavascriptEnabled(true);
        ChromeOptions opt = new ChromeOptions();
        driver = new ChromeDriver(opt);
        driver.manage().window().maximize();
		 XSSFSheet s=Excel("TestData.xlsx");
		 driver.get("https://www.yatra.com/flights");
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	@When("^check Flights option is displaying or not$")
	public void checkFlightsOptionIsDisplayingOrNot() throws Throwable {
		driver.findElement(By.
				  xpath("//*[text()='Flights']")).click();
		
	}
	@Then("^Flights option should be displaying$")
	public void flightsOptionShouldBeDisplaying() throws Throwable {
		
		if(driver.findElement(By.
				  xpath("//*[text()='Flights']")).
				  isDisplayed()) {
				  passscreenshot(driver,"Flights","Flights option is displaying",true);
				  
				  }else {
				  failscreenshot(driver,"Flights","Flights option is not displaying",true);
				  
				  }
		 
	}
	
	@Given("^enter source and destination$")
	public void enterSourceAndDestination(DataTable usercredentials) throws Throwable {
			 
			 //Write the code to handle Data Table
			 List<List<String>> data = usercredentials.raw();
			
			driver.findElement(By.xpath("//*[@id='BE_flight_origin_city']")).click();
			 Thread.sleep(1000);
			 Thread.sleep(3000);
			 driver.findElement(By.xpath("//*[@id='BE_flight_origin_city']")).sendKeys(data.get(0).get(0));
			 Thread.sleep(4000);
			 driver.findElement(By.xpath("(//*[@class='ac_cityname'])[1]")).click();
				
			 Thread.sleep(1000);
			 driver.findElement(By.xpath("//*[@id='BE_flight_arrival_city']")).click();
			 Thread.sleep(1000);
			 driver.findElement(By.xpath("//*[@id='BE_flight_arrival_city']")).sendKeys(data.get(0).get(1));
			 Thread.sleep(5000);
			 
			 driver.findElement(By.xpath("(//*[@class='ac_cityname'])[1]")).click();
			 driver.findElement(By.xpath("(//*[@id='BE_flight_origin_date'])[1]")).click();
			 
			 Date dt = new Date();
				Calendar c = Calendar.getInstance(); 
				c.setTime(dt); 
				c.add(Calendar.DATE, 1);
				dt = c.getTime();
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
			    String strDate= formatter.format(dt); 
				System.out.println(strDate); 
		
			   driver.findElement(By.xpath("//*[@id='"+strDate+"']")).click();
			
				
	}
	@When("^click on search button$")
	public void clickOnSearchButton() throws Throwable {
	
		driver.findElement(By.xpath("//*[@id='BE_flight_flsearch_btn']")).click();
		
			
	}
	@Then("^user should be navigate to flights list page$")
	public void userShouldBeNavigateToFlightsListPageOrNot() throws Throwable {
		if(driver.findElement(By.
				  xpath("//*[@class='flight-det table full-width clearfix']")).
				  isDisplayed()) {
				  passscreenshot(driver,"Flights","Flights option is displaying",true);
				  
				  }else {
				  failscreenshot(driver,"Flights","Flights option is not displaying",true);
				  
				  }
		
	}
	@And("^select fastest flight$")
	public void selectFastestFlight() throws Throwable {
		driver.findElement(By.xpath("//*[text()='Depart Time ']")).click();
		 Thread.sleep(1000);
		driver.findElement(By.xpath("//*[text()='12 - 18']")).click();
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		 executor.executeScript("arguments[0].click();",  driver.findElement(By.xpath("//*[@value='Apply Filters']")));
		
		List <WebElement> w=driver.findElements(By.xpath("//*[@class='fs-12 bold du mb-2']"));
		List <WebElement> book=driver.findElements(By.xpath("//*[text()='Book']"));
		ArrayList<Integer> ar=new ArrayList<Integer>();
		
		
		int x=0;
		for(int i=0;i<=w.size()-1;i++)
		{
			String s=w.get(i).getText();
			for(int j=0;j<=s.length()-1;j++)
			{
				if(s.charAt(j)=='h')
				{
					int hr=Integer.parseInt(s.substring(0, j));
					//System.out.println(hr);
					hr=hr*60;
					int m=Integer.parseInt(s.substring(j+2, j+4));
					int sum=hr+m;
					ar.add(sum);
					System.out.println(sum);
				}
			}}
		int temp=ar.get(0);
		for(int i=0;i<=ar.size()-1;i++)
		{
					
					if(ar.get(i)<=temp)
					{
						temp=ar.get(i);
						x=i;
						System.out.println("i="+i);
					}
					
					
				}
		
		System.out.println(x);
		book.get(x).click();
	}
	
	
	
    
}
