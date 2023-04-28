package main.java.framework.page.mail;

import io.qameta.allure.Step;
import main.java.framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.By.cssSelector;

public class MailboxPage extends BasePage {

    private static final By MAILBOX_LOGO = cssSelector(".branding__logo");

    private static final By MAILBOX_SIDEBAR = cssSelector(".btn.btn-default.btn_i3x-side-menu");

    private static final By MAIL_TYPE_UNREAD = cssSelector(".unread-mail");

    WebDriver driver;

    public MailboxPage(WebDriver driver) {
        super(driver);
        verify();
        this.driver = driver;
    }

    @Override
    public void verify() {
        waitForElementToAppear(MAILBOX_LOGO);
        assertThat("Page tittle not present or is not as expected", getElementBy(MAILBOX_LOGO).getAttribute("title").contains("Inbox Mail"), is(true));
    }

    @Step
    public SidebarPage openSidebar() {
        click(MAILBOX_SIDEBAR);
        return new SidebarPage(driver);
    }

    //ACTIONS
    @Step
    public List<String> collectUnreadMailsToList() {
        int unreadMailSize = getElementsBy(MAIL_TYPE_UNREAD).size();
        List<String> unreadMailList = new ArrayList<>();
        for (int count = 0; count < unreadMailSize; count++) {
            unreadMailList.add(getElementsBy(MAIL_TYPE_UNREAD).get(count).getText().replace("\n", " "));
        }
        return unreadMailList;
    }
}