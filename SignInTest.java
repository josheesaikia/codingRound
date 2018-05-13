package flight;

import com.sun.javafx.PlatformUtil;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest {

	private static WebDriver driver;

	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() throws InterruptedException {

		setDriverPath();

		driver.get("https://www.cleartrip.com/");
		waitFor(2000);

		driver.findElement(By.linkText("Your trips")).click();
		driver.findElement(By.id("SignIn")).click();
		driver.switchTo().frame(driver.findElement(By.id("modal_window")));

		driver.findElement(By.id("signInButton")).click();

		String errors1 = driver.findElement(By.id("errors1")).getText();
		Assert.assertTrue(errors1.contains("There were errors in your submission"));
		driver.quit();
	}

	private void waitFor(int durationInMilliSeconds) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(durationInMilliSeconds, TimeUnit.MILLISECONDS);
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
			System.setProperty("webdriver.chrome.driver", "path/to/chromedriver_linux"); // one have to give the chromedriver directory where it has been placed
			driver = new ChromeDriver(); 
			driver.manage().window().maximize();
		}
	}

}