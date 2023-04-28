package main.java.framework.page.registration;

import io.qameta.allure.Step;
import main.java.framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.cssSelector;

public class PrivacyPolicyPage extends BasePage {

    private static final By PRIVACY_POLICY_HEADER = cssSelector("#myModalLabel");

    private static final By PRIVACY_POLICY_OVERLAY = cssSelector(".modal.show");

    private static final By ACCEPT_BUTTON = cssSelector(".btn-primary");

    WebDriver driver;

    public PrivacyPolicyPage(WebDriver driver) {
        super(driver);
        verify();
        this.driver = driver;
    }

    @Override
    public void verify() {
        waitForElementToAppear(PRIVACY_POLICY_OVERLAY);
    }

    //Actions
    @Step
    public RegistrationPage clickAcceptButtonAndReturnToRegistrationPage() {
        clickWebElement(getAcceptButton());
        return new RegistrationPage(driver);
    }

    @Step
    public void scrollToAcceptButton() {
        scrollToElement(getAcceptButton());
    }

    private WebElement getAcceptButton() {
        return getElementsBy(ACCEPT_BUTTON).get(1);
        //TODO add more specific search of accept button on policy page if available
    }

    //Verifications
    @Step
    public void verifyPrivacyPolicyPageOpened() {
        assertTrue("Privacy policy page not shown", getElementBy(PRIVACY_POLICY_HEADER).getText().contains("Read Privacy policy"));
    }

    @Step
    public void verifyAcceptButtonPresent() {
        assertTrue("Privacy policy page not shown", getAcceptButton().getText().contains("Accept"));
    }
}