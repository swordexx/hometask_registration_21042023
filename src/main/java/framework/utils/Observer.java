package main.java.framework.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Observer {

    public WebDriver driver;

    public Observer(WebDriver driver) {
        this.driver = driver;
    }

    public void implicitWait() {
        driver.manage().timeouts().implicitlyWait(ofSeconds(10));
    }

    public WebElement elementPresent(By by) {
        return new WebDriverWait(driver, ofSeconds(30)).until(presenceOfElementLocated(by));
    }

    public WebElement elementClickable(By by) {
        return new WebDriverWait(driver, ofSeconds(30)).until(elementToBeClickable(by));
    }

    public WebElement webElementClickable(WebElement element) {
        return new WebDriverWait(driver, ofSeconds(30)).until(elementToBeClickable(element));
    }
}