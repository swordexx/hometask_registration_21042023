package test.java.tests;

import main.java.framework.page.MainPage;
import main.java.framework.page.mail.MailboxPage;
import main.java.framework.page.mail.SidebarPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import test.java.base.TestBase;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class LogInAndCollectMailsTest extends TestBase {
    private static final Logger LOGGER = getLogger(InboxPortalRegistrationTest.class);

    private final String testUsername = "";

    private final String testPassword = "";


    @Before
    public void openBrowserForTesting() {
        LOGGER.info("Open browser");
        setup();
    }

    @Test
    public void testLogInAndCollectMails() {
        LOGGER.info("Open www.inbox.lv in browser");
        MainPage mainPage = new MainPage(driver).openLoginPage();

        LOGGER.info("Set test username in 'Username' input field");
        mainPage.setUsername(testUsername);

        LOGGER.info("Verify test username is present 'Username' input field");
        mainPage.verifyUsername(testUsername);

        LOGGER.info("Set test password in 'Password' input field");
        mainPage.setPassword(testPassword);

        LOGGER.info("Verify test password is present 'Password' input field");
        mainPage.verifyPassword(testPassword);

        LOGGER.info("Press 'Sign in' button");
        MailboxPage mailboxPage1 = mainPage.signInAndGoToMailbox();

        LOGGER.info("Verify Inbox mail page is opened"); //done in scopes of page initialization

        LOGGER.info("Collect unread mails to list");
        List<String> unreadMailsInInbox = mailboxPage1.collectUnreadMailsToList();

        LOGGER.info("Unread mails list - " +unreadMailsInInbox);

        LOGGER.info("Open 'Profile Sidebar'");
        SidebarPage sidebarPage = mailboxPage1.openSidebar();

        LOGGER.info("Press 'Logout' button");
        sidebarPage.logout();
    }

    @After
    public void closeBrowserForTesting() {
        LOGGER.info("Close browser");
        tearDown();
    }
}
