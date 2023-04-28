package main.java.framework.page.mail;

import io.qameta.allure.Step;
import main.java.framework.base.BasePage;
import main.java.framework.page.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.By.cssSelector;

public class SidebarPage extends BasePage {

    private static final By SIDEBAR_BODY = cssSelector("#side-menu");

    private static final By LOGOUT = cssSelector(".logout-btn");


    WebDriver driver;

    public SidebarPage(WebDriver driver) {
        super(driver);
        verify();
        this.driver = driver;
    }

    @Override
    public void verify() {
        waitForElementToAppear(SIDEBAR_BODY);
        assertThat("Sidebar is not present ", getElementBy(SIDEBAR_BODY).isDisplayed(), is(true));
    }

    @Step
    public MainPage logout() {
        click(LOGOUT);
        return new MainPage(driver);
    }
}