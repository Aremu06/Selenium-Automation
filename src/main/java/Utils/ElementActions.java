package Utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.awt.*;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ElementActions {

    private WebDriver driver;
    FluentWait wait;

    public ElementActions(WebDriver driver) throws AWTException {
        this.driver = driver;
    }

    /**
     * Wait for element to be clickable for 30 seconds
     *
     * @param locator
     */
    private void waitForElementTobeClickable(By locator) {
        try {
            wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(10)).ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Failed to find element with locator " + locator.toString());
        }
    }

    /**
     * Wait for the element to be visible inside the page
     *
     * @param locator
     */
    public void waitForElementTobeVisible(By locator) {
        try {
            wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Failed to find element with locator " + locator.toString());
        }
    }

    /**
     * Perform click Action After waiting the element to be clickable
     *
     * @param locator
     */

    public void click(By locator) {
        waitForElementTobeClickable(locator);
        driver.findElement(locator).click();
        System.out.println("Successfully clicked on element with locator '" + locator.toString() + "'");
    }

    /**
     * Type text inside input field after waiting the element to be visible
     *
     * @param locator
     * @param text
     */
    public void write(By locator, String text) {
        waitUntilElementClickableThenSendkeys(locator, text);
        System.out.println("Successfully wrote text '" + text + "' in element with locator '" + locator.toString() + "'");
    }


    /**
     * Wait until the input field to be clickable ,click it then type inside it
     * used for specific elements inside the project
     *
     * @param element
     * @param data    : String
     */
    public void waitUntilElementClickableThenSendkeys(By element, String data) {
        // Wait for Element to be visible.
        try {
            wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(10)).ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            driver.findElement(element).click();
            driver.findElement(element).sendKeys(data);

        } catch (Exception e) {
            System.out.println("Element not found - sendKeys" + element.toString());
        }

    }


    /**
     * Wait until element is clickable then click it
     * Not used inside the project
     *
     * @param element
     * @deprecated
     */
    public void waitUntilElementClickableThenClick(By element) {

        try {
            wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(10)).ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            driver.findElement(element).click();

        } catch (Exception e) {
            System.out.println("Element not found - click");
        }
    }


    /**
     * Force the webdriver to wait for specific amount of time
     * as there are cases where the system(JavaScript ) is too fast and can't do specific
     *
     * @param seconds - integer
     * @throws InterruptedException
     */
    public void forceWait(int seconds) throws InterruptedException {

        Thread.sleep(Long.parseLong(seconds + "000"));
    }


    public void getElementsUsingTagName(String tagName) {

        List<WebElement> listOfElement = driver.findElements(By.tagName(tagName));

        for (WebElement eachLink : listOfElement) {
            if (eachLink.getText().length() > 0)
                System.out.println("Element Found with " + tagName + ": " + eachLink.getText());

        }
    }


    public List<WebElement> getStreamList(String tagName, String data) throws Exception {
        try {

            List<WebElement> listOfElement = driver.findElements(By.tagName(tagName));
            List<WebElement> streamList = listOfElement.stream()
                    .filter(x -> x.getText().toLowerCase().contains(data))
                    .collect(Collectors.toList());

            for (WebElement eachLink : streamList) {
                System.out.println("Stream Found : " + eachLink.getText());
            }
            return streamList;
        } catch (Exception e) {
            throw new Exception("Exception occurred while getting Stream of elements : " + e.getMessage());
        }

    }

}




