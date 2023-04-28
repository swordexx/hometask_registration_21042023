package main.java.framework.page.registration;

import io.qameta.allure.Step;
import main.java.framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.By.cssSelector;

public class PhonePage extends BasePage {

    private static final By VERIFY_YOUR_PHONE_NUMBER_TITLE = cssSelector(".mt-2.mb-0.text-center");

    private static final By COUNTRY_DROPDOWN = cssSelector("#phone_form_phone_country");

    private static final By PHONE_NUMBER_INPUT = cssSelector("#phone_form_phone");

    private static final By NEXT_BUTTON = cssSelector(".btn.btn-primary.float-end");

    WebDriver driver;

    public PhonePage(WebDriver driver) {
        super(driver);
        verify();
        this.driver = driver;
    }

    @Override
    public void verify() {
        waitForElementToAppear(VERIFY_YOUR_PHONE_NUMBER_TITLE);
        assertThat("Page tittle not present", getElementText(VERIFY_YOUR_PHONE_NUMBER_TITLE), is("Verify your phone number"));
    }

    //Actions
    @Step
    public void selectCountry(String country) {
        if (getCurrentCountry().contains(country)) {
            return;
        }
        setCountry(country);
    }

    @Step
    public void setPhone(String phone) {
        writeText(PHONE_NUMBER_INPUT, phone);
    }

    @Step
    public String getCurrentCountry() {
        Select dropDownCountry = new Select(driver.findElement(COUNTRY_DROPDOWN));
        return dropDownCountry.getFirstSelectedOption().getText();
    }

    @Step
    public void setCountry(String country) {
        Select dropDownCountry = new Select(driver.findElement(COUNTRY_DROPDOWN));
        dropDownCountry.selectByVisibleText(country);
    }


    @Step
    public PhoneNumberVerificationPage pressNextAndGoToPhoneNumberVerificationPage() {
        click(NEXT_BUTTON);
        return new PhoneNumberVerificationPage(driver);
    }

    //Verifications
    @Step
    public void verifyCountry(String country) {
        assertThat("Current country is not - " + country, getCurrentCountry(), is(country));
    }

    @Step
    public void verifyPhone(String phone) {
        assertThat("Current phone is not - " + phone, getElementBy(PHONE_NUMBER_INPUT).getAttribute("value"), is(phone));
    }
}