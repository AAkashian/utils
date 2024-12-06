package utils;
 
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Set;
 
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
 
public class WebDriverManager {
    private WebDriver driver;
 
    public WebDriverManager(WebDriver driver) {
        this.driver = driver;
    }
 
    public void waitForElementToBeVisible(By locator, int timeoutInSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
            .until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    public void waitForElementToBeClickable(By locator, int timeoutInSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
            .until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
    public void verify(String acutalText, String expectedText, ExtentTest test, String message ) {
        try {
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertTrue(acutalText.contains(expectedText));
            LoggerManager.info(message);
            test.log(Status.PASS , message);
        } catch (Exception e) {
            String errorMessage = "Not " + message;
            LoggerManager.error(errorMessage);
            test.log(Status.FAIL , errorMessage);
            test.addScreenCaptureFromBase64String("Unsucessfull");
            e.printStackTrace();
        }
    }
 
    public void clickOnElement(By locator, ExtentTest test, String message) {
        try {
            waitForElementToBeClickable(locator, 5);
            WebElement webElement = driver.findElement(locator);
            webElement.click();
            LoggerManager.info(message);
            test.log(Status.PASS , message);
        } catch (Exception e) {
            String errorMessage = "Not " + message;
            LoggerManager.error(errorMessage);
            test.log(Status.FAIL , errorMessage);
//            highlightOnElement(locator, "FAIL");
            test.addScreenCaptureFromBase64String("Unsucessfull");
            e.printStackTrace();
        }
    }
    public void ClickTheElement(By locator) {
        try {
            WebElement webElement = driver.findElement(locator);
            webElement.click();
        } catch (Exception e) {
            // Handle or rethrow the exception here
            e.printStackTrace();
        }
    }
 
 
    public void sendKeys(By locator, String data, ExtentTest test, String message) {
        try {
            waitForElementToBeClickable(locator, 5);
            WebElement webElement = driver.findElement(locator);
            webElement.sendKeys(data);
            LoggerManager.info(message);
            test.log(Status.PASS , message);
        } catch (Exception e) {
            String errorMessage = "Not " + message;
            LoggerManager.error(errorMessage);
            test.log(Status.FAIL , errorMessage);
            test.addScreenCaptureFromBase64String("Unsucessfull");
            e.printStackTrace();
        }
    }
 
    public String getText(By locator, ExtentTest test, String message) {
        try {
            waitForElementToBeClickable(locator, 5);
            WebElement webElement = driver.findElement(locator);
            String text = webElement.getText();
            LoggerManager.info(message);
            test.log(Status.PASS , message);
            return text;
        } catch (Exception e) {
            String errorMessage = "Not " + message;
            LoggerManager.error(errorMessage);
            test.log(Status.FAIL , errorMessage);
            test.addScreenCaptureFromBase64String("Unsucessfull");
            e.printStackTrace();
            return "";
        }
    }
     public String getTexts(By locator) {
            try {
                WebElement webElement = driver.findElement(locator);
                return webElement.getText();
            } catch (Exception e) {
                // Handle or rethrow the exception here
                e.printStackTrace();
                return null;
            }
        }
 
 
    public void jsClick(By locator, ExtentTest test, String message) {
        try {
            waitForElementToBeClickable(locator, 5);
            WebElement element = driver.findElement(locator);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
            LoggerManager.info(message);
            test.log(Status.PASS , message);
//            highlightOnElement(locator, "PASS");
        } catch (Exception e) {
            String errorMessage = "Not " + message;
            LoggerManager.error(errorMessage);
            test.log(Status.FAIL , errorMessage);
            test.addScreenCaptureFromBase64String("Unsucessfull");
//            highlightOnElement(locator, "FAIL");
//          String path = ReportHandler.captureScreenShot(errorMessage);
//          test.addScreenCaptureFromPath(path, errorMessage);
            e.printStackTrace();
        }
    }
 
    public void javascriptScroll(By locator, ExtentTest test, String message) {
        try {
            waitForElementToBeClickable(locator, 5);
            WebElement element = driver.findElement(locator);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", element);
            LoggerManager.info(message);
            test.log(Status.PASS , message);
        } catch (Exception e) {
            String errorMessage = "Not " + message;
            LoggerManager.error(errorMessage);
            test.log(Status.FAIL , errorMessage);
            test.addScreenCaptureFromBase64String("Unsucessfull");
            e.printStackTrace();
        }
    }
       public void jsScroll(By locator) {
            try {
                WebElement element = driver.findElement(locator);
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView();", element);
            } catch (Exception e) {
                // Handle or rethrow the exception here
                e.printStackTrace();
                
            }
        }
 
    public void switchToNewWindow(ExtentTest test, String message) {
        try {
            Set<String> windowHandles = driver.getWindowHandles();
            for (String windowHandle : windowHandles) {
                if (!windowHandle.isEmpty()) {
                    driver.switchTo().window(windowHandle);
                } else {
                    throw new Exception("New window could not be retrieved");
                }
            }
            LoggerManager.info(message);
            test.log(Status.PASS , message);
        } catch (Exception e) {
            String errorMessage = "Not " + message;
            LoggerManager.error(errorMessage);
            test.log(Status.FAIL , errorMessage);
            test.addScreenCaptureFromBase64String("Unsucessfull");
//          String path = ReportHandler.captureScreenShot(errorMessage);
//          test.addScreenCaptureFromPath(path, errorMessage);
            e.printStackTrace();
        }
    }
 
    public void enterAction(By locator, ExtentTest test, String message) {
        try {
            waitForElementToBeClickable(locator, 5);
            WebElement webElement = driver.findElement(locator);
            webElement.sendKeys(Keys.ENTER);
            LoggerManager.info(message);
            test.log(Status.PASS , message);
        } catch (Exception e) {
            String errorMessage = "Not " + message;
            LoggerManager.error(errorMessage);
            test.log(Status.FAIL , errorMessage);
            test.addScreenCaptureFromBase64String("Unsucessfull");
            e.printStackTrace();
        }
    }
 
    public void hoverOverElement(By locator , ExtentTest test, String message) {
        try {
            waitForElementToBeClickable(locator, 5);
            WebElement webElement = driver.findElement(locator);
            Actions actions = new Actions(driver);
            actions.moveToElement(webElement).perform();
            LoggerManager.info(message);
            test.log(Status.PASS , message);
        } catch (Exception e) {
            String errorMessage = "Not " + message;
            LoggerManager.error(errorMessage);
            test.log(Status.FAIL , errorMessage);
            test.addScreenCaptureFromBase64String("Unsucessfull");
            ReportBuilder.addScreenshotWithReport(test, driver, errorMessage);
            e.printStackTrace();
        }
    }
 
    public void clearElement(By locator, ExtentTest test, String message) {
        try {
            waitForElementToBeClickable(locator, 5);
            WebElement webElement = driver.findElement(locator);
            webElement.clear();
            LoggerManager.info(message);
            test.log(Status.PASS , message);
        } catch (Exception e) {
            String errorMessage = "Not " + message;
            LoggerManager.error(errorMessage);
            test.log(Status.FAIL , errorMessage);
            test.addScreenCaptureFromBase64String("Unsucessfull");
            e.printStackTrace();
        }
    }
    public void verifyText(By locator, String expectedText, ExtentTest test, String message) {
        try {
        	SoftAssert softAssertions = new SoftAssert();
            WebElement element = driver.findElement(locator);
            softAssertions.assertTrue(element.isDisplayed(), "Element is not displayed");
            String actualText = element.getText();
            softAssertions.assertEquals(actualText, expectedText, "Text does not match");
            softAssertions.assertAll();
            LoggerManager.info(message);
            test.log(Status.PASS, message);
        } catch (Exception e) {
            String errorMessage = "Not " + message;
            LoggerManager.error(errorMessage);
            ReportBuilder.addScreenshotWithReport(test, driver, errorMessage);
        }
    }
   
 
}