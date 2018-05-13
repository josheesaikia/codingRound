package flight;

import com.sun.javafx.PlatformUtil;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class HotelBookingTest {

   public static WebDriver driver;

    @FindBy(xpath  = "//a[@title='Find hotels in destinations around the world']")
    static WebElement hotelLink;
   
    @FindBy(id = "Tags")
    static WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    static WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    static WebElement travellerSelection;

    @Test
    public void shouldBeAbleToSearchForHotels() throws InterruptedException {
        setDriverPath();

        driver.get("https://www.cleartrip.com/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        
        PageFactory.initElements(driver, HotelBookingTest.class);
        hotelLink.click();
        localityTextBox.sendKeys("Indiranagar, Bangalore");

        new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
        searchButton.click();

        driver.quit();

    }

    @SuppressWarnings("restriction")
	public void setDriverPath() {
    	if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "Path/to/chromedriver"); // one have to give the chromedriver directory where it has been placed
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        if (PlatformUtil.isWindows()) {
        	ChromeOptions option = new ChromeOptions();
        	option.addArguments("--disable-notifications");
            System.setProperty("webdriver.chrome.driver", "C:\\Browser Drivers\\chromdriver\\2.38\\chromedriver.exe");
            driver = new ChromeDriver(option);
            driver.manage().window().maximize();
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "path/to/chromedriver_linux");
            driver = new ChromeDriver();// one have to give the chromedriver directory where it has been placed
            driver.manage().window().maximize();
        }
    }

}