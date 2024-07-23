package Utilities;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.function.Function;

public class Utility {
    private static final String SCREENSHOTS_PATH = "test-outputs/TestNG_Screenshots/";

    // Click on an element
    public static void ClickingOnElement(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    // Click on one of the multiple elements
    public static void ClickingOnElements(WebDriver driver, By locator, int i) {
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElements(locator).get(i).click();
    }

    // Send data to an input field
    public static void SendData(WebDriver driver, By locator, String data) {
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(data);
    }

    // Get text from an element
    public static String getText(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    // General wait
    public static WebDriverWait generalWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Scroll to an element
    public static void scrollToElement(WebDriver driver, By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", findWebelement(driver, locator));
    }

    // Find an element
    public static WebElement findWebelement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }

    // Get a timestamp
    public static String getTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd-h-m-ssa").format(new Date());
    }


    // Select dropdown option by visible text
    public static void selectDropdownByText(WebDriver driver, By locator, String option) {
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        new Select(findWebelement(driver, locator)).selectByVisibleText(option);
    }

    // Select dropdown option by value
    public static void selectDropdownByValue(WebDriver driver, By locator, String value) {
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        new Select(findWebelement(driver, locator)).selectByValue(value);
    }

    // Select dropdown option by index
    public static void selectDropdownByIndex(WebDriver driver, By locator, int index) {
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        new Select(findWebelement(driver, locator)).selectByIndex(index);
    }


    // JavaScript click on an element
    public static void javascriptClick(WebDriver driver, By locator) {
        WebElement element = findWebelement(driver, locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    // Wait for an element to disappear
    public static void waitForElementToDisappear(WebDriver driver, By locator, int timeout) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // Get attribute value from an element
    public static String getAttributeValue(WebDriver driver, By locator, String attribute) {
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getAttribute(attribute);
    }

    // Sleep for a specified number of milliseconds
    public static void sleepMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
            e.printStackTrace();
        }
    }

    // Sleep for a specified number of seconds
    public static void sleepSeconds(long seconds) {
        sleepMillis(seconds * 1000);
    }

    // Sleep for a specified number of minutes
    public static void sleepMinutes(long minutes) {
        sleepMillis(minutes * 60 * 1000);
    }

    // Set implicit wait
    public static void setImplicitWait(WebDriver driver, int timeout) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
    }

    // Explicit wait for element to be clickable
    public static WebElement waitForElementToBeClickable(WebDriver driver, By locator, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Explicit wait for element to be visible
    public static WebElement waitForElementToBeVisible(WebDriver driver, By locator, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Explicit wait for element to be present in the DOM
    public static WebElement waitForElementPresence(WebDriver driver, By locator, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Fluent wait for element with custom polling interval
    public static WebElement fluentWaitForElement(WebDriver driver, final By locator, int timeout, int pollingTime) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(NoSuchElementException.class);

        return wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
    }

    // Navigate to a URL
    public static void navigateToUrl(WebDriver driver, String url) {
        driver.get(url);
    }

    // Refresh the current page
    public static void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    // Navigate back in the browser history
    public static void navigateBack(WebDriver driver) {
        driver.navigate().back();
    }

    // Navigate forward in the browser history
    public static void navigateForward(WebDriver driver) {
        driver.navigate().forward();
    }

    // Switch to a new window or tab
    public static void switchToNewWindow(WebDriver driver, String windowTitle) {
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
            if (driver.getTitle().equals(windowTitle)) {
                return;
            }
        }
        driver.switchTo().window(originalWindow);
    }

    // Close the current window or tab
    public static void closeCurrentWindow(WebDriver driver) {
        driver.close();
    }

    // Switch to the original window
    public static void switchToOriginalWindow(WebDriver driver, String originalWindowHandle) {
        driver.switchTo().window(originalWindowHandle);
    }

    // Maximize the browser window
    public static void maximizeWindow(WebDriver driver) {
        driver.manage().window().maximize();
    }

    // Quit the browser (closes all windows and ends the WebDriver session)
    public static void quitBrowser(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }

    // Take a screenshot
    public static void takeScreenshot(WebDriver driver, String screenshotName) {
        try {
            File screenshotSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screenshootFile = new File(SCREENSHOTS_PATH + screenshotName + "-" + getTimeStamp() + ".png");
            FileUtils.copyFile(screenshotSrc, screenshootFile);
            Allure.addAttachment(screenshotName, Files.newInputStream(Path.of(screenshootFile.getPath())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}