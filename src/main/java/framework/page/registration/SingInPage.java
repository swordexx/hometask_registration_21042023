package main.java.framework.page.registration;

import io.qameta.allure.Step;
import main.java.framework.base.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.openqa.selenium.By.cssSelector;

public class SingInPage extends BasePage {

    private static final By YOU_ARE_SIGNED_IN_TITLE = cssSelector(".mt-2.mb-0.text-center");

    private static final By CONTINUE_BUTTON = cssSelector("#continue");

    WebDriver driver;

    public SingInPage(WebDriver driver) {
        super(driver);
        verify();
        this.driver = driver;
    }

    @Override
    public void verify() {
        waitForElementToAppear(YOU_ARE_SIGNED_IN_TITLE);
        Assert.assertThat("Page tittle not present", getElementText(YOU_ARE_SIGNED_IN_TITLE), is("You are signed in"));
    }

    //Actions
    @Step
    public PhonePage pressContinueAndGoToPhoneVerificationPage() {
        click(CONTINUE_BUTTON);
        return new PhonePage(driver);
    }

    //Verifications
}