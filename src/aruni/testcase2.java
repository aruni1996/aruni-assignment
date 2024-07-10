package aruni;


import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testcase2{

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
    public void testCase2() throws InterruptedException {
        driver.get("https://www.pos.com.my/");
        Thread.sleep(3000);

        // Verify the logo is displayed
        Assert.assertTrue(driver.findElement(By.xpath("//a[@class='logo-link']//img")).isDisplayed());

        // Close pop-up notices
        try {
            WebElement closenoticeButton = driver.findElement(By.xpath("//fa-icon[@class='ng-fa-icon remove']//*[name()='svg']"));
            closenoticeButton.click();
            Thread.sleep(3000);
            WebElement closepopupButton = driver.findElement(By.xpath("//img[@alt='Pos Close Alert Icon']"));
            closepopupButton.click();
        } catch (NoSuchElementException e) {
            System.out.println("Pop-up not found, continuing...");
        }

        // Verify login button is displayed
        Assert.assertTrue(driver.findElement(By.xpath("//button[@class='btn but-primary-red header-login-button hidden-xs ng-star-inserted']//img[@alt='profile_icon']")).isDisplayed());

        // Hover over "Send" menu
        WebElement sendMenu = driver.findElement(By.xpath("/html/body/app-root/div/app-floating-bar/div[1]/div/div/div/div/div[1]/a/span"));
        Actions actions = new Actions(driver);
        actions.moveToElement(sendMenu).perform();
        Thread.sleep(1000);

        // Click "Parcel"
        WebElement parcelMenu = driver.findElement(By.xpath("/html/body/app-root/div/app-floating-bar/div[1]/div/div/div[2]/div/a[1]/div/span"));
        parcelMenu.click();
        Thread.sleep(6000);

        // Scroll down to the "Create shipment now" button under the Cash section
        WebElement createShipmentLayout = driver.findElement(By.xpath("//*[@id=\"contentBody\"]/div/app-static-layout/app-send-parcel/div/div/div[3]/div[1]/div[2]/div/div[1]/div[3]"));
        WebElement createShipmentButton = driver.findElement(By.xpath("//*[@id=\"contentBody\"]/div/app-static-layout/app-send-parcel/div/div/div[3]/div[1]/div[2]/div/div[2]/div[5]/a"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", createShipmentLayout);
        Thread.sleep(5000);
        createShipmentButton.click();
        Thread.sleep(5000);
        
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
        String expectedUrl = "https://send.pos.com.my/home/e-connote?lg=en";
        Assert.assertEquals(newTabUrl, expectedUrl);

        // Verify the e-Consignment Note form sections
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"stepSender\"]/div[2]/div")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"stepReceiver\"]/div[2]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"stepParcel\"]/div[2]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"stepSummary\"]/div[2]")).isDisplayed());
    }


    @AfterTest
    public void teardown() {
        driver.quit();
    }

    public static void main(String[] args) {
        // No need to implement the main method for TestNG
    }
}


