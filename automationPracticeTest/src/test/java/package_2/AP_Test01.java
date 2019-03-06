package package_2;


import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import topmallTools.LogLibrary;
import topmallTools.topmallTools;
import atu.testng.reports.ATUReports;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;	
import org.testng.annotations.AfterTest;		


@Listeners({ ATUReportsListener.class, ConfigurationListener.class,
	MethodListener.class })
public class AP_Test01 {		
	{
		topmallTools.AtuReportConfiguration(System.getProperty("user.dir"));
		System.setProperty("atu.reporter.config",  System.getProperty("user.dir") + "\\lib\\ATUReporter\\atu.properties");
	}
	
		public WebDriver driver, driverMailWindow;	
	    String baseUrl, mailURL, nodeURL;
		
	    @BeforeTest
		public void setUp() throws Exception {
	    	baseUrl = "http://automationpractice.com/index.php";
	    	mailURL = "https://10minutemail.net";
	    	nodeURL = "http://192.168.12.29:5556/wd/hub";
	    	
	    	DesiredCapabilities capability = DesiredCapabilities.chrome(); 
	    	capability.setPlatform(Platform.WIN10);
			
			
			System.out.println("***************** Starting browser");
			driver = new RemoteWebDriver(new URL(nodeURL), capability);
			driverMailWindow= new RemoteWebDriver(new URL(nodeURL), capability);
	    	
	    	LogLibrary.setUpLogging();
	    	
		}		
	    
	    @Test				
		public void testEasy() throws Exception {
	    				    	
	    	ATUReports.setAuthorInfo("Author ATU", "05-03-2019", "1.0");
			ATUReports.currentRunDescription = "TESTS";
			ATUReports.setTestCaseReqCoverage("Test");
			ATUReports.setWebDriver(driver);
	    	
	    	
	    	 driverMailWindow.manage().window().maximize();
	    	 driverMailWindow.get(mailURL);
	    	 WebElement temporaryMail =  driverMailWindow.findElement(By.id("fe_text"));
	    	 String mail = temporaryMail.getAttribute("value");
	    	
	    	
	    	driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
									
			System.out.println("*********************** Starting test case  *********************");
						
			try {
				
			  driver.get(baseUrl);
			  String actualValue= "";
			  String expectedValue= "";
			  String stepDesc= "";
			  String inputValue= "";
			  		  
			  JavascriptExecutor js = (JavascriptExecutor)driver;
			  Actions actions = new Actions(driver);
			  
			  //Scenariusz 1: Rejestracja nowego użytkownika
			  //Początek Scenariusza 1
			  WebElement logIn = driver.findElement(By.className("login"));
			  logIn.click();
			  
			  actionTipingMethod ("email_create", mail, "sendKeys");
			  actionTipingMethod ("SubmitCreate", "", "click");
			  
			  WebElement gender=driver.findElement(By.id("id_gender1"));
			  
			  
			  expectedValue= "Wyświetla się formularz rejestarcji użytkownika";
			  stepDesc= "Jest możliwość wyświetlenia formularza rejestracji użytkownika";
			  inputValue= "";
			  if(gender.isDisplayed()){
				  actualValue= "Radiobutton wyboru płci wyświetla się na stronie";
				  ATUReports.add(stepDesc, inputValue, expectedValue, actualValue,
							false);
			  }else{
				  actualValue= "Radiobutton wyboru płci nie wyświetla się na stronie";
				  ATUReports.add(stepDesc, inputValue, expectedValue, actualValue,
							LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			  }
			  
			  actionTipingMethod ("id_gender1", "", "click");
			  actionTipingMethod ("customer_firstname", "Adam", "sendKeys");
			  actionTipingMethod ("customer_lastname", "Testowy", "sendKeys");
			  actionTipingMethod ("passwd", "qwerty", "sendKeys");
			  actionTipingMethod ("days", "1", "select");
			  actionTipingMethod ("months", "1", "select");
			  actionTipingMethod ("years", "2012", "select");
			  actionTipingMethod ("company", "QAZ", "sendKeys");
			  actionTipingMethod ("address1", "Nowa", "sendKeys");
			  actionTipingMethod ("address2", "12", "sendKeys");
			  actionTipingMethod ("id_state", "1", "select");
			  actionTipingMethod ("city", "Warszawa", "sendKeys");
			  actionTipingMethod ("postcode", "02998", "sendKeys");
			  actionTipingMethod ("phone_mobile", "222444555", "sendKeys");
			  actionTipingMethod ("alias", "Adres_1", "sendKeys");
			  actionTipingMethod ("submitAccount", "", "click");
			  //Koniec Scenariusza 1
			  
			  
			  //Scenariusz 2: Autoryzacja istniejącego użytkownika (w tym przypadku użytkownik autoryzuje się obecnym 
			  //hasłem, aby wpisać nowe hasło)
			  //Początek Scenariusza 2
			  WebElement personalInformation = driver.findElement(By.xpath("//a[@title='Information']"));

			  
			  expectedValue= "Widoczny link do informacji o koncie użytkownika";
			  stepDesc= "Rejestracja użytkownika powiodła się";
			  inputValue= "";
			  if(personalInformation.isDisplayed()){
				  actualValue= "Link jest widoczny";
				  ATUReports.add(stepDesc, inputValue, expectedValue, actualValue,
							false);
			  }else{
				  actualValue= "Brak linku";
				  ATUReports.add(stepDesc, inputValue, expectedValue, actualValue,
							LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			  }
			  			  
			  personalInformation.click();
			  
			  WebElement oldPassword=driver.findElement(By.id("old_passwd"));
			  expectedValue= "Wyświetla się formularz zmiany hasła użytkownika";
			  stepDesc= "Użytkownik zmienia hasło";
			  inputValue= "";
			  if(oldPassword.isDisplayed()){
				  actualValue= "Pole do wpisania starego hasła jest widoczne";
				  ATUReports.add(stepDesc, inputValue, expectedValue, actualValue,
							false);
			  }else{
				  actualValue= "Pole do wpisania starego hasła nie jest widoczne";
				  ATUReports.add(stepDesc, inputValue, expectedValue, actualValue,
							LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			  }
			  
			  actionTipingMethod ("old_passwd", "qwerty", "sendKeys");
			  actionTipingMethod ("passwd", "qwerty1", "sendKeys");
			  actionTipingMethod ("confirmation", "qwerty1", "sendKeys");
			  			  
			  WebElement saveButton = driver.findElement(By.xpath("//button[@name='submitIdentity']"));
			  saveButton.click();
			  
			  WebElement alertSucess = driver.findElement(By.xpath("//p[@class='alert alert-success']"));
			  
			  expectedValue= "Formularz zmiany hasła został zamknięty";
			  stepDesc= "Użytkownik zmienił hasło";
			  inputValue= "";
			  if(alertSucess.isDisplayed()){
				  actualValue= "Komunikat potwierdzenia widoczny";
				  ATUReports.add(stepDesc, inputValue, expectedValue, actualValue,
							false);
			  }else{
				  actualValue= "Komunikat potwierdzenia nie widoczny";
				  ATUReports.add(stepDesc, inputValue, expectedValue, actualValue,
							LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			  }
			  //Koniec Scenariusza 2
			  
			  
			  //Scenariusz 3 Zaproponowany Scenariusz: Złożenie zamówienia na konkretny produkt.
			  //Początek Scenariusza 3
		        WebElement myStoreLink = driver.findElement(By.xpath("//a[@title='My Store']"));
		        myStoreLink.click();
		        
		        
		        actionTipingMethod ("search_query_top", "Blouse", "sendKeys");
		        		        
		         WebElement searchButton = driver.findElement(By.xpath("//button[@name='submit_search']"));
				 searchButton.click();
				 
				 Thread.sleep(1500);
				 WebElement buttonAddToCard = driver.findElement(By.xpath("//a[@title='Add to cart']"));
				 actions.moveToElement(buttonAddToCard);
				 actions.perform();				 
				 js.executeScript("arguments[0].click();", buttonAddToCard);
				
				 
				 Thread.sleep(1500); 
				 WebElement buttonGoToCheckout = driver.findElement(By.xpath("//div[@class='button-container']/a[@title='Proceed to checkout']"));
				 
				  expectedValue= "Wyświetla się przycisk Proceed to checkout";
				  stepDesc= "Produkt został dodany do koszyka";
				  inputValue= "";
				  if(buttonGoToCheckout.isDisplayed()){
					  actualValue= "Produkt dodany do koszyka";
					  ATUReports.add(stepDesc, inputValue, expectedValue, actualValue,
								false);
				  }else{
					  actualValue= "Produkt nie dodany do koszyka";
					  ATUReports.add(stepDesc, inputValue, expectedValue, actualValue,
								LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				  }
				 
				 js.executeScript("arguments[0].click();", buttonGoToCheckout);
				
				 Thread.sleep(500); 
				 buttonGoToCheckout = driver.findElement(By.xpath("//p[@class='cart_navigation clearfix']/a[@title='Proceed to checkout']"));
				 
				 actions.moveToElement(buttonGoToCheckout);
				 actions.perform();				 
				 js.executeScript("arguments[0].click();", buttonGoToCheckout);
				 
				 
				 WebElement submitButton = driver.findElement(By.xpath("//button[@name='processAddress']"));
				 actions.moveToElement(submitButton);
				 actions.perform();
				 
				 expectedValue= "Wyświetla się przycisk potwierdzenia danych adresowych";
				  stepDesc= "Formularz potwierdzenia danych adresowych";
				  inputValue= "";
				  if(submitButton.isDisplayed()){
					  actualValue= "Przycik potwierdzenia danych adresowaych widoczny";
					  ATUReports.add(stepDesc, inputValue, expectedValue, actualValue,
								false);
				  }else{
					  actualValue= "Przycik potwierdzenia danych adresowaych nie widoczny";
					  ATUReports.add(stepDesc, inputValue, expectedValue, actualValue,
								LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				  }
				 
				 submitButton.click();
				 
				  
				  actionTipingMethod ("cgv", "", "click"); //checkbox
				  
				  submitButton = driver.findElement(By.xpath("//button[@name='processCarrier']"));
				  actions.moveToElement(submitButton);
				  actions.perform();
				  
				  expectedValue= "Wyświetla się przycisk potwierdzenia danych dostawy";
				  stepDesc= "Formularz potwierdzenia danych dostawy";
				  inputValue= "";
				  if(submitButton.isDisplayed()){
					  actualValue= "Przycik potwierdzenia danych dostawy widoczny";
					  ATUReports.add(stepDesc, inputValue, expectedValue, actualValue,
								false);
				  }else{
					  actualValue= "Przycik potwierdzenia danych dostawy nie widoczny";
					  ATUReports.add(stepDesc, inputValue, expectedValue, actualValue,
								LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				  }
				  
				  
				  submitButton.click();
				  
				  WebElement payByBankWireLink = driver.findElement(By.xpath("//a[@title='Pay by bank wire']"));
				  
				  expectedValue= "Wyświetla się przycisk potwierdzenia płatności";
				  stepDesc= "Formularz potwierdzenia płatności";
				  inputValue= "";
				  if(payByBankWireLink.isDisplayed()){
					  actualValue= "Przycik potwierdzenia płatności widoczny";
					  ATUReports.add(stepDesc, inputValue, expectedValue, actualValue,
								false);
				  }else{
					  actualValue= "Przycik potwierdzenia płatności nie widoczny";
					  ATUReports.add(stepDesc, inputValue, expectedValue, actualValue,
								LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				  }
				  
				  payByBankWireLink.click();
				  
				  WebElement confirmMyOrder = driver.findElement(By.xpath("//p[@id='cart_navigation']/button[@type='submit']"));
				  actions.moveToElement(confirmMyOrder);
					 actions.perform();
				  confirmMyOrder.click();
				  //Koniec Secenariusza 3
			  
				  
			  	        
			  
			  System.out.println("Page title is: " + driver.getTitle());
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("*********************** Ending test case  *********************");
		
	    }
	    	
	    
	    public void actionTipingMethod (String idName, String value, String action)
	    {
	    	WebElement tempElement=driver.findElement(By.id(idName));
	    	
	    	switch(action){
	    	case "click":
	    		tempElement.click();
	    		 break;
	    	
	    	case "select":
	    		Select oSelect = new Select(tempElement);
				 oSelect.selectByValue(value);
	    		 break;
	    		 
	    	case "sendKeys":
	    		tempElement.sendKeys(value);
	    		 break;
	    	default: 
			   	   break;	
	    	}
	    }
	    		
		@AfterTest
		public void afterTest() {
			LogLibrary.endTestCase("testEasy");
			System.out.println("***************** killing browser");
			driverMailWindow.quit();
			driver.quit();			
		}	
	
}	
