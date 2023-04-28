package main.java.framework.page.registration;

import io.qameta.allure.Step;
import main.java.framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.By.cssSelector;

public class BirthdayGenderPage extends BasePage {

    private static final By BIRTHDAY_GENDER_PAGE_TITLE = cssSelector(".mt-2.mb-0.text-center");

    private static final By BIRTH_DAY_DROPDOWN = cssSelector(".form-select.select_b-day");

    private static final By BIRTH_MONTH_DROPDOWN = cssSelector(".form-select.select_b-month");

    private static final By BIRTH_YEAR_INPUT = cssSelector(".form-control");

    private static final By GENDER_DROPDOWN = cssSelector(".form-select.select_gender");

    private static final By SAVE_BUTTON = cssSelector("#required_birthday_form_save");

    WebDriver driver;

    public BirthdayGenderPage(WebDriver driver) {
        super(driver);
        verify();
        this.driver = driver;
    }

    @Override
    public void verify() {
        waitForElementToAppear(BIRTHDAY_GENDER_PAGE_TITLE);
        assertThat("Page tittle not present or is not - ", getElementText(BIRTHDAY_GENDER_PAGE_TITLE), is("Add birthday and gender"));
    }

    //ACTIONS
    @Step
    public void selectBirthDay(String birthDay) {
        Select dropDownCountry = new Select(driver.findElement(BIRTH_DAY_DROPDOWN));
        dropDownCountry.selectByVisibleText(birthDay);
    }

    @Step
    public void selectBirthMonth(String birthMonth) {
        Select dropDownCountry = new Select(driver.findElement(BIRTH_MONTH_DROPDOWN));
        dropDownCountry.selectByVisibleText(birthMonth);
    }

    @Step
    public void setBirthYear(String birthYear) {
        writeText(BIRTH_YEAR_INPUT, birthYear);
    }

    @Step
    public void selectGender(String gender) {
        Select dropDownCountry = new Select(driver.findElement(GENDER_DROPDOWN));
        dropDownCountry.selectByVisibleText(gender);
    }

    @Step
    public ThankYouPage pressSaveAndGoToThankYouPage() {
        click(SAVE_BUTTON);
        return new ThankYouPage(driver);
    }

    //VERIFICATIONS
    @Step
    public void verifyBirthDay(String birthDay) {
        assertThat("Birth day is not - " + birthDay, getElementBy(BIRTH_DAY_DROPDOWN).getAttribute("value").equals(birthDay), is(true));
    }

    @Step
    public void verifyBirthMonth(String birthMonth) {
        assertThat("Birth month is not - " + birthMonth, getElementBy(BIRTH_MONTH_DROPDOWN).getAttribute("value").equals(birthMonth), is(true));
    }

    @Step
    public void verifyBirthYear(String birthYear) {
        assertThat("Birth year is not - " + birthYear, getElementBy(BIRTH_YEAR_INPUT).getAttribute("value").equals(birthYear), is(true));
    }

    @Step
    public void verifyGender(String gender) {
        assertThat("Gender is  is not - " + gender, getElementBy(GENDER_DROPDOWN).getAttribute("value").equals(gender), is(true));
    }
}