package main.java.framework.page.registration;

import io.qameta.allure.Step;
import main.java.framework.base.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.cssSelector;

public class RegistrationPage extends BasePage {

    private static final By REGISTRATION_PANEL_TITLE = cssSelector(".panel-title");

    private static final By REGISTRATION_USERNAME = cssSelector("#signup_user");

    private static final By REGISTRATION_USERNAME_VALIDATION = cssSelector("#check-uname");

    private static final By REGISTRATION_FIRST_NAME = cssSelector("#signup_forename");

    private static final By REGISTRATION_LAST_NAME = cssSelector("#signup_surname");

    private static final By REGISTRATION_PASSWORD = cssSelector("#signup_password_password");

    private static final By REGISTRATION_STRENGTH = cssSelector(".password-info__body");

    private static final By REGISTRATION_CONFIRM_PASSWORD = cssSelector("#signup_password_passwordRepeat");

    private static final By REGISTRATION_SIGNUP_PRIVACY_POLICY = cssSelector("#signup_privacy");

    private static final By REGISTRATION_SIGNUP_TERMS_OF_SERVICE = cssSelector("#signup_tos");

    private static final By FINISH_REGISTRATION_BUTTON = cssSelector("#signup_submit");

    WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        super(driver);
        verify();
        this.driver = driver;
    }

    @Override
    public void verify() {
        waitForElementToAppear(REGISTRATION_PANEL_TITLE);
        Assert.assertThat("Page tittle not present", getElementText(REGISTRATION_PANEL_TITLE), is("Registration"));
    }

    //Actions
    @Step
    public void setUsername(String username) {
        writeText(REGISTRATION_USERNAME, username);
        waitForElementToAppear(REGISTRATION_USERNAME_VALIDATION);
    }

    @Step
    public void setFirstName(String firstName) {
        writeText(REGISTRATION_FIRST_NAME, firstName);
    }

    @Step
    public void setLastName(String lastName) {
        writeText(REGISTRATION_LAST_NAME, lastName);
    }

    @Step
    public void setPassword(String password) {
        writeText(REGISTRATION_PASSWORD, password);
    }

    @Step
    public void setConfirmPassword(String password) {
        writeText(REGISTRATION_CONFIRM_PASSWORD, password);
    }

    @Step
    public PrivacyPolicyPage checkPrivacyPolicyAndOpenPrivacyPolicyPage() {
        click(REGISTRATION_SIGNUP_PRIVACY_POLICY);
        return new PrivacyPolicyPage(driver);
    }

    @Step
    public void pressFinishButton() {
        click(FINISH_REGISTRATION_BUTTON);
    }

    @Step
    public void pressCaptcha() {
        clickWebElement(getElementsBy(cssSelector("div,#checkbox")).stream().findFirst().get());
    }

    @Step
    public NewUserSignUpPage pressFinishButtonAndGoToNewUserSignUpPage() {
        pressFinishButton();
        return new NewUserSignUpPage(driver);
    }

    @Step
    public void checkTermsOfService() {
        click(REGISTRATION_SIGNUP_TERMS_OF_SERVICE);
    }

    //Verifications
    @Step
    public void verifyInboxRegistrationPageOpened() {
        waitForElementToAppear(REGISTRATION_PANEL_TITLE);
        assertTrue("Inbox Registration page is not opened", getElementText(REGISTRATION_PANEL_TITLE).contains("Registration"));
    }

    @Step
    public void verifyUsernameSet(String username) {
        assertTrue("Username is not - " + username, getElementBy(REGISTRATION_USERNAME).getAttribute("value").contains(username.toLowerCase()));
    }

    @Step
    public void verifyFirstNameSet(String firstName) {
        assertTrue("First Name is not - " + firstName, getElementBy(REGISTRATION_FIRST_NAME).getAttribute("value").contains(firstName));

    }

    @Step
    public void verifyLastNameSet(String lastName) {
        assertTrue("Last Name is not - " + lastName, getElementBy(REGISTRATION_LAST_NAME).getAttribute("value").contains(lastName));
    }

    @Step
    public void verifyPasswordSet(String password) {
        assertTrue("Password is not - " + password, getElementBy(REGISTRATION_PASSWORD).getAttribute("value").contains(password));
    }

    @Step
    public void verifyConfirmPasswordSet(String password) {
        assertTrue("Password is not - " + password, getElementBy(REGISTRATION_CONFIRM_PASSWORD).getAttribute("value").contains(password));
    }

    @Step
    public void verifyPassStrengthInfo(String passwordStrength) {
        assertTrue("Pass strength info label not appeared", getElementBy(REGISTRATION_STRENGTH).getText().contains(passwordStrength));
    }

    @Step
    public void verifyPrivacyPolicyCheckboxState(Boolean state) {
        waitForElementPresentAndEnabled(REGISTRATION_SIGNUP_PRIVACY_POLICY);
        assertThat("Privacy policy checkbox statue is not - " + state, isElementSelected(REGISTRATION_SIGNUP_PRIVACY_POLICY), is(state));
    }

    @Step
    public void verifyTermsOfServiceCheckboxState(Boolean state) {
        assertThat("Terms of Service checkbox statue is not - " + state, isElementSelected(REGISTRATION_SIGNUP_TERMS_OF_SERVICE), is(state));
    }

    @Step
    public void verifyCaptchaCheckboxStatus(Boolean state) {
        assertThat("Captcha checkbox statue is not - " + state, getElementsBy(cssSelector("div,.check")).stream().findFirst().get().isDisplayed(), is(state));
    }
}