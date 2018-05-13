package flight;

import com.sun.javafx.PlatformUtil;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FlightBookingTest {

    //WebDriver driver = new ChromeDriver();
    private static WebDriver driver;

    @Test
    public void testThatResultsAppearForAOneWayJourney() throws InterruptedException {

        setDriverPath();
        driver.get("https://www.cleartrip.com/");
        waitFor(2000);
        
        driver.findElement(By.id("OneWay")).click();

        driver.findElement(By.id("FromTag")).clear();
        driver.findElement(By.id("FromTag")).sendKeys("Bangalore");

        //wait for the auto complete options to appear for the origin

       // waitFor(2000);
        List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
       originOptions.get(0).click();

        driver.findElement(By.id("ToTag")).clear();
        driver.findElement(By.id("ToTag")).sendKeys("Delhi");

        //wait for the auto complete options to appear for the destination

       // waitFor(2000);
        //select the first item from the destination auto complete list
        List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
        destinationOptions.get(0).click();

        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")).click();

        //all fields filled in. Now click on search
        driver.findElement(By.id("SearchBtn")).click();

        waitFor(5000);
        //verify that result appears for the provided journey search
        Assert.assertTrue(isElementPresent(By.className("searchSummary")));

        //close the browser
        driver.quit();

    }


    private void waitFor(int durationInMilliSeconds) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(durationInMilliSeconds, TimeUnit.MILLISECONDS);
    }


    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @SuppressWarnings("restriction")
	private void setDriverPath() {
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

