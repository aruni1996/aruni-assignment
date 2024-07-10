package aruni;


import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testcase1 {

	 WebDriver driver;
	    WebDriverWait wait;
	    @BeforeTest
	    public void setup() {
	        System.setProperty("webdriver.chrome.driver", "C:\\Users\\DELL\\eclipse-workspace\\test\\Resoucefiles\\ChromeDriver\\V126.exe");
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }
	    
	    @Test
	    public void testcase1() throws InterruptedException {
	        driver.get("https://www.pos.com.my/");
	        Thread.sleep(2000);

	        // Verify the logo is displayed
	        Assert.assertTrue(driver.findElement(By.xpath("//a[@class='logo-link']//img")).isDisplayed());

	        // Close pop-up notices
	        try {
	            WebElement closenoticeButton = driver.findElement(By.xpath("//fa-icon[@class='ng-fa-icon remove']//*[name()='svg']"));
	            closenoticeButton.click();
	            Thread.sleep(2000);
	            WebElement closepopupButton = driver.findElement(By.xpath("//img[@alt='Pos Close Alert Icon']"));
	            closepopupButton.click();
	        } catch (NoSuchElementException e) {
	            System.out.println("Pop-up not found, continuing...");
	        }

	        // Verify login button is displayed
	        Assert.assertTrue(driver.findElement(By.xpath("//button[@class='btn but-primary-red header-login-button hidden-xs ng-star-inserted']//img[@alt='profile_icon']")).isDisplayed());

	        // Scroll to the section “What can Pos Malaysia do for you, today?”
	        WebElement section = driver.findElement(By.xpath("//*[@id=\"contentBody\"]/div/app-auth-layout/app-home/div/div/div/div[2]/div"));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", section);
	        Thread.sleep(3000);

	        // Click "Buy Insurance" button
	        WebElement buyinsuranceButton = driver.findElement(By.xpath("//*[@id=\"contentBody\"]/div/app-auth-layout/app-home/div/div/div/div[2]/div/div[1]/a[3]"));
	        buyinsuranceButton.click();
	        Thread.sleep(2000);
	        
	        // Switch to the new tab
	        Set<String> handles = driver.getWindowHandles();
	        String currentHandle = driver.getWindowHandle();
	        for (String handle : handles) {
	            if (!handle.equals(currentHandle)) {
	                driver.switchTo().window(handle);
	                break;
	            }
	        }

	        // Get the URL of the new tab
	        String newTabUrl = driver.getCurrentUrl();

	        // Verify the URL of the new tab
	        String expectedUrl = "https://insurance.pos.com.my/";
	        Assert.assertEquals(newTabUrl, expectedUrl);

	        // Verify the main title is displayed
	        //Assert.assertTrue(driver.findElement(By.xpath("//h1[@class='mainTitle bold']")).isDisplayed());

	        // Verify and click "I drive a car" button
	        WebElement driveCarButton = driver.findElement(By.xpath("//*[@id=\"vhl\"]/div[1]/div[2]/div/div[1]/div/div[2]"));
	        Assert.assertTrue(driveCarButton.isDisplayed());
	        driveCarButton.click();
	        Thread.sleep(3000);
	        
	        // Scroll to the Button section”
	        WebElement btnsection = driver.findElement(By.xpath("//*[@id=\"vhl\"]/div[1]/div[1]"));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnsection);
	        Thread.sleep(3000);

	        // Verify "Ok, let’s get to know you" section is displayed
	        WebElement newSection = driver.findElement(By.xpath("//*[@id=\"section2\"]/h5"));
	        Assert.assertTrue(newSection.isDisplayed());

	        // Verify and click "I ride a motorcycle" button
	        WebElement rideMotorcycleButton = driver.findElement(By.xpath("//*[@id=\"vhl\"]/div[1]/div[2]/div/div[2]"));
	        Assert.assertTrue(rideMotorcycleButton.isDisplayed());
	        rideMotorcycleButton.click();
	        Thread.sleep(3000);

	        // Verify "Ok, let’s get to know you" section is displayed again
	        WebElement newSectionMotorcycle = driver.findElement(By.xpath("//*[@id=\"section2\"]/h5"));
	        Assert.assertTrue(newSectionMotorcycle.isDisplayed());

	        // Scroll to the new section
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", newSection);
	    }
	    
	  
	   

	    @AfterTest
	    public void teardown() {
	        driver.quit();
	    }

	    public static void main(String[] args) {
	        // No need to implement the main method for TestNG
	    }
	}



