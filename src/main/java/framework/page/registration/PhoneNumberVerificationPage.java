package main.java.framework.page.registration;

import io.qameta.allure.Step;
import main.java.framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.By.cssSelector;

public class PhoneNumberVerificationPage extends BasePage {

    private static final By PHONE_NUMBER_VERIFICATION_TITLE = cssSelector(".mt-2");

    private static final By TELEGRAM_APP = cssSelector("#confirm_identity_method_method_0");

    private static final By RECEIVE_SMS = cssSelector("#confirm_identity_method_method_1");

    private static final By SEND_SMS = cssSelector("#confirm_identity_method_method_2");

    private static final By SMS_CODE_INPUT = cssSelector("sms_code_form_code");

    private static final By NEXT_STEP_BUTTON = cssSelector(".btn.btn-primary.float-end");

    WebDriver driver;

    public PhoneNumberVerificationPage(WebDriver driver) {
        super(driver);
        verify();
        this.driver = driver;
    }

    @Override
    public void verify() {
        waitForElementToAppear(PHONE_NUMBER_VERIFICATION_TITLE);
        assertThat("Page tittle not present or is not - ", getElementText(PHONE_NUMBER_VERIFICATION_TITLE), is("Phone number verification"));
    }

    //Actions
    @Step
    public void selectValidationOption(int validationOption) {
        getElementBy(getNumberValidationOption(validationOption)).click();
    }

    @Step
    public PhoneNumberVerificationPage pressNextAndWaitNumberVerificationPage() {
        click(NEXT_STEP_BUTTON);
        return new PhoneNumberVerificationPage(driver);
    }

    @Step
    public ThankYouPage pressNextAndFinishPhoneNumberVerification() {
        click(NEXT_STEP_BUTTON);
        return new ThankYouPage(driver);
    }

    @Step
    public void setSmsCode(String smsCode) {
        writeText(SMS_CODE_INPUT, smsCode);
    }

    @Step
    public By getNumberValidationOption(int validationOption) {
        switch (validationOption) {
            case 1:
                return TELEGRAM_APP;
            case 2:
                return RECEIVE_SMS;
            case 3:
                return SEND_SMS;
            default:
                System.out.println("Only 3 options possible");
                break;
        }
        return null;
    }

    //Verifications
    @Step
    public void verifyValidationOption(int verificationOption) {
        assertThat("Verification option is not selected", getElementBy(getNumberValidationOption(verificationOption)).isSelected(), is(true));
    }

    @Step
    public void verifySmsCodeSet(String smsCode) {
        assertThat("SMS code is not - " + smsCode, getElementBy(SMS_CODE_INPUT).getAttribute("value").equals(smsCode), is(true));
    }
}