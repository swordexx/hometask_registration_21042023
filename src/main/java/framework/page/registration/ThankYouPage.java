package main.java.framework.page.registration;

import io.qameta.allure.Step;
import main.java.framework.base.BasePage;
import main.java.framework.page.mail.MailboxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.By.cssSelector;

public class ThankYouPage extends BasePage {

    private static final By THANK_YOU_PAGE_TITLE = cssSelector(".mt-2.mb-4.text-center");

    private static final By DONE_BUTTON = cssSelector(".btn-default.btn.btn");

    private static final By FINAL_DONE_BUTTON = cssSelector("#continue");

    public ThankYouPage(WebDriver driver) {
        super(driver);
        verify();
        this.driver = driver;
    }

    @Override
    public void verify() {
        waitForElementToAppear(THANK_YOU_PAGE_TITLE);
        assertThat("Page tittle not present or is not - ", getElementText(THANK_YOU_PAGE_TITLE), is("Thank you"));
    }

    @Step
    public BirthdayGenderPage pressDoneAndGoToBirthDayGenderPage() {
        getElementBy(DONE_BUTTON).click();
        return new BirthdayGenderPage(driver);
    }

    @Step
    public MailboxPage pressDoneAndGoToInboxMailboxPage() {
        getElementBy(FINAL_DONE_BUTTON).click();
        return new MailboxPage(driver);
    }
}
