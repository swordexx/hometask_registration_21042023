package main.java.framework.page.registration;

import io.qameta.allure.Step;
import main.java.framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.cssSelector;

public class NewUserSignUpPage extends BasePage {

    private static final By NEW_USER_SIGN_UP = cssSelector(".card-title");

    private static final By PROCEED_BUTTON = cssSelector(".btn-primary");

    WebDriver driver;

    public NewUserSignUpPage(WebDriver driver) {
        super(driver);
        verify();
        this.driver = driver;
    }

    @Override
    public void verify() {
        waitForElementToAppear(NEW_USER_SIGN_UP);
    }

    //Actions
    @Step
    public SingInPage pressProceedAndGoToSignInPage() {
        click(PROCEED_BUTTON);
        return new SingInPage(driver);
    }

    //Verifications
    @Step
    public void verifyInboxSingInPageIsShown() {
        assertTrue("Pass strength info label not appeared", getElementBy(NEW_USER_SIGN_UP).getText().contains("New user sign up"));
    }
}