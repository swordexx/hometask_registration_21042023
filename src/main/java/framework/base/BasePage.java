package main.java.framework.base;

import io.qameta.allure.Step;
import main.java.framework.utils.Observer;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void verify() {
    }

    //Waiters
    public void waitForLoading() {
        new Observer(driver).implicitWait();
    }

    public void waitForElementToAppear(By by) {
        new Observer(driver).elementPresent(by);
    }

    public void waitForElementToBeClickable(By by) {
        new Observer(driver).elementClickable(by);
    }

    public void waitForWebElementToBeClickable(WebElement element) {
        new Observer(driver).webElementClickable(element);
    }

    public void waitForElementPresentAndEnabled(By by) {
        waitForElementToAppear(by);
        waitForElementToBeClickable(by);
    }

    //Actions
    @Step
    public void goToUrl(String url) {
        driver.get(url);
    }

    @Step
    public void click(By by) {
        waitForElementPresentAndEnabled(by);
        driver.findElement(by).click();
    }

    @Step
    public void clickWebElement(WebElement element) {
        waitForWebElementToBeClickable(element);
        element.click();
    }

    @Step
    public void writeText(By by, String text) {
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(text);
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        waitForWebElementToBeClickable(element);
    }
    //GETTERS
    public String getElementTitle(By by) {
        return getElementAttribute(by, "title");
    }

    public String getElementAttribute(By by, String attribute) {
        return driver.findElement(by).getAttribute(attribute);
    }

    public String getElementText(By by) {
        return driver.findElement(by).getText();
    }

    public String getElementClass(By by) {
        return driver.findElement(by).getCssValue(".");
    }

    public WebElement getElementFromListBy(By by) {
        return getElementsBy(by).get(0);
    }
    public List<WebElement> getElementsBy(By by) {
        return driver.findElements(by);
    }

    public WebElement getElementBy(By by) {
        return driver.findElement(by);
    }

    public Boolean isElementSelected(By by) {
        return driver.findElement(by).isSelected();
    }

    public Boolean isWebElementSelected(WebElement element) {
        return element.isSelected();
    }
}