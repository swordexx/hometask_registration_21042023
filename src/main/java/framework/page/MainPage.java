package main.java.framework.page;

import io.qameta.allure.Step;
import main.java.framework.base.BasePage;
import main.java.framework.page.mail.MailboxPage;
import main.java.framework.page.registration.RegistrationPage;
import main.java.framework.utils.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.By.cssSelector;

public class MainPage extends BasePage {
    private static final By INBOX_MAIN_PAGE = cssSelector(".branding__logo");

    private static final By LANGUAGE_BUTTON = By.id("lang-switch");

    private static final By LANGUAGE_OPTION_LIST = cssSelector("[data-lang]");

    private static final By USERNAME_INPUT = By.id("imapuser");
    private static final By PASSWORD_INPUT = By.id("pass");
    private static final By SIGN_IN_BUTTON = By.id("btn_sign-in");

    private static final By SIGN_UP_BUTTON = By.id("btn_signup");


    WebDriver driver;

    public MainPage(WebDriver driver) {
        super(driver);
        verify();
        this.driver = driver;
    }

    @Override
    public void verify() {
        waitForElementToAppear(INBOX_MAIN_PAGE);
        assertThat("Inbox main page logo title is not as an expected", getElementTitle(INBOX_MAIN_PAGE).contains("Inbox.lv - vienot savējos lielām lietām! "),is(true));
    }

    //Actions
    @Step
    public MainPage openLoginPage() {
        goToUrl(PropertyReader.readProperty("host.name"));
        return new MainPage(driver);
    }

    @Step
    public void setUsername(String username){
        writeText(USERNAME_INPUT, username);
    }

    @Step
    public void setPassword(String password){
        writeText(PASSWORD_INPUT, password);
    }

    @Step
    public MailboxPage signInAndGoToMailbox(){
        click(SIGN_IN_BUTTON);
        return new MailboxPage(driver);
    }

    @Step
    public RegistrationPage pressSignUpButton() {
        click(SIGN_UP_BUTTON);
        return new RegistrationPage(driver);

    }

    public void selectLanguage(String languageAbbreviation) {
        if(getCurrentLanguage().contains(languageAbbreviation)){
            return;
        }
        clickLanguageIcon();
        setLanguage(languageAbbreviation);
        waitForLoading();
    }

    public String getCurrentLanguage() {
        return getElementAttribute(cssSelector(".ico-lang"), "class").substring(14);
    }

    public void clickLanguageIcon() {
        click(LANGUAGE_BUTTON);
    }

    public void setLanguage(String languageAbbreviation) {
        clickWebElement(getLanguageOption(languageAbbreviation));
    }

    public List<WebElement> getLanguageOptions() {
        return getElementsBy(LANGUAGE_OPTION_LIST);
    }

    public WebElement getLanguageOption(String languageAbbreviation) {
        return getLanguageOptions().stream().filter(webElement -> webElement.getAttribute("data-lang").contains(languageAbbreviation)).findFirst().get();
    }

    //Verifications
    @Step
    public void verifyUsername(String username){
        assertThat("Username is  s not - " + username, getElementBy(USERNAME_INPUT).getAttribute("value").equals(username), is(true));
    }

    @Step
    public void verifyPassword(String password){
        assertThat("Password is  s not - " + password, getElementBy(PASSWORD_INPUT).getAttribute("value").equals(password), is(true));

    }
}