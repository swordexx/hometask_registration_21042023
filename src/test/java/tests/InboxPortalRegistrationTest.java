package test.java.tests;

import main.java.framework.page.MainPage;
import main.java.framework.page.mail.MailboxPage;
import main.java.framework.page.mail.SidebarPage;
import main.java.framework.page.registration.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import test.java.base.TestBase;

import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.slf4j.LoggerFactory.getLogger;

public class InboxPortalRegistrationTest extends TestBase {

    private static final Logger LOGGER = getLogger(InboxPortalRegistrationTest.class);

    private final String testUsername = randomAlphanumeric(6).toLowerCase();

    private final String testFirstName = randomAlphanumeric(6);

    private final String testLastName = randomAlphanumeric(6);

    private final String testPasswordShort = randomAlphanumeric(5);

    //defect of testing sometimes testPassword is weak du to its nature like "ZnZCZC"
    private final String testPassword = randomAlphanumeric(10);

    private final String testPhoneNumber = "";

    private final String verificationCode = "";

    /**
     * Test Case #1 Test Inbox Page Registration (Happy Path, execution daily)
     * Preconditions:
     * test username is available for registration
     * Scenario:
     * Open www.inbox.lv in browser
     * Verify Inbox mane page is opened
     * Press 'Sign up' button
     * Verify 'Registration' page is opened
     * Enter test username in 'Username' input field
     * Verify test username is present in 'Username' input field
     * Press rCaptcha checkbox
     * Process validation on both rCaptcha pages
     * Verify rCaptcha is passed
     * Enter test first name in 'First Name' input field
     * Verify test first name is present in 'First Name' input field
     * Enter test last name in 'Last Name' input field
     * Verify test last name is present in 'Last Name' input field
     * AUTOMATION SPECIFIC REQUIREMENTS STEPS START
     * 1. During registration, your test should check validation on any field (chosen by you)
     * Enter 5 letter test password
     * Verify password strength info label 'Password strength: Too short' is shown
     * AUTOMATION SPECIFIC REQUIREMENTS STEPS END
     * Enter test password in 'Password' input field
     * Verify test password is present in 'Password' input field
     * Enter test password in 'Confirm Password' input field
     * Verify test password is present in 'Confirm Password' input field
     * Check 'I agree to the Privacy policy' option
     * Verify 'Privacy Policy' pop-up is opened
     * Scroll 'Read privacy policy' to bottom
     * Verify 'Accept' button is present
     * Press 'Accept' button
     * Verify 'Registration' page is opened
     * Verify 'I agree to the Privacy policy' option is checked
     * Check 'I agree to the Terms of Service' option
     * Verify 'I agree to the Terms of Service' option is checked
     * Press 'Finish' button
     * Verify New user sign up pop-up opened
     * Press 'Proceed' button
     * Verify 'You need to add or confirm your phone number or recovery email' pop-up is opened
     * Press 'Continue' button
     * Verify 'Verify your phone number' pop-up is opened
     * Select test country in 'Country' dropdown
     * Verify test Country is selected in 'Country' dropdown
     * Enter test phone number in 'Phone number here...' input field
     * Verify test phone is present in 'Phone number here...' input field
     * Press 'Next' button
     * Verify 'Phone number verification' pop-up is opened
     * Select 'We send SMS to your phone' option in 'Step 2: Select phone number validation option' list
     * Verify 'We send SMS to your phone' option is selected in 'Step 2: Select phone number validation option' list
     * Press 'Next step' button
     * Verify 'Phone number verification' pop-up is opened
     * Press 'Next step' button
     * Verify 'Phone number verification' pop-up is opened
     * Set test code in 'Enter the code you have received:' input field
     * Verify test code is set in 'Enter the code you have received:' input field
     * Press 'Next' button
     * Verify 'Thank you' pop-up is opened
     * Press 'Done' button
     * Verify 'Add birthday and gender' pop-up is opened
     * Select test day in 'Day' dropdown
     * Verify test day is present in 'Day' dropdown
     * Select test month in 'Month' dropdown
     * Verify test month is present in 'Month' dropdown
     * Set test year in 'Year' input field
     * Verify test year is present in 'Year' input field
     * Select test gender in 'Gender' dropdown
     * Verify test gender is present in 'Gender' dropdown
     * Press 'Save'
     * Verify 'Thank you' pop-up is opened
     * Press 'Done' button
     * Verify Inbox Mail is opened
     * AUTOMATION SPECIFIC STEPS START
     * 2. After the registration test should make login in email and collect all active emails in the table/list
     * 3. After collecting data test should log out.
     * Open 'Profile Sidebar'
     * Verify 'Profile Sidebar' is opened
     * Press 'Logout' button
     * Verify Inbox main page is opened
     * Set test username in 'Username' input field
     * Verify test username is present 'Username' input field
     * Set test password in 'Password' input field
     * Verify test password is present 'Password' input field
     * Press 'Sign in' button
     * Verify Inbox mail page is opened
     * Select 'Unread' type of emails in 'Email type dropdown'
     * Collect selected Email data - Date, From, Subject to list
     * Write collected data to .txt file
     * Open 'Profile Sidebar'
     * Press 'Logout' button
     * AUTOMATION SPECIFIC STEPS END
     **/

    @Before
    public void openBrowserForTesting() {
        LOGGER.info("Open browser");
        setup();
    }

    @Test
    public void testInboxPageRegistration() {
        LOGGER.info("Open www.inbox.lv in browser");
        MainPage inboxMainPage = new MainPage(driver).openLoginPage();

//Let's pretend that we have only english in our dev environment and translations are tested later,
//This step is required to get correct value in case you are testing from VPN or other countries like I do
        inboxMainPage.selectLanguage("en");

        LOGGER.info("Verify Inbox mane page is opened");  //done in scope of page initialization

        LOGGER.info("Press 'Sign up' button");
        RegistrationPage registrationPage = inboxMainPage.pressSignUpButton();

        LOGGER.info("Verify Registration page is opened");
        registrationPage.verifyInboxRegistrationPageOpened();

        LOGGER.info("Enter test username in 'Username' input field");
        registrationPage.setUsername(testUsername);

        LOGGER.info("Enter test username in 'Username' input field");
        registrationPage.verifyUsernameSet(testUsername);

//captcha is never part of test automation and not sure if it is required for whole registration or only Username availability check
//If we assume captcha is required for registration than application has bug and registration is possible without captcha.
        //Start of Semi-automated step
        LOGGER.info("Press rCaptcha checkbox");
        //Set debug break points here
        registrationPage.pressCaptcha();
        LOGGER.info("Process validation on rCaptcha pop-up");
        //Manually solve hCaptcha
        //End of Semi-automated step

        LOGGER.info("Verify rCaptcha is passed");
        registrationPage.verifyCaptchaCheckboxStatus(true);

        LOGGER.info("Enter test first name in 'First Name' input field");
        registrationPage.setFirstName(testFirstName);

        LOGGER.info("Verify test first name is present in 'First Name' input field");
        registrationPage.verifyFirstNameSet(testFirstName);

        LOGGER.info("Enter test last name in 'Last Name' input field");
        registrationPage.setLastName(testLastName);

        LOGGER.info("Verify test last name is present in 'Last Name' input field");
        registrationPage.verifyLastNameSet(testLastName);

        //Automation specific requirements start
        LOGGER.info("Enter 5 letter test password");
        registrationPage.setPassword(testPasswordShort);
        System.out.println(testPassword);

        LOGGER.info("Verify password strength info label 'Password strength: Too short' is shown");
        registrationPage.verifyPassStrengthInfo("Too short");
        //Automation specific requirements end

        LOGGER.info("Enter test password in 'Password' input field");
        registrationPage.setPassword(testPassword);

        LOGGER.info("Verify test password is present in 'Password' input field");
        registrationPage.verifyPasswordSet(testPassword);

        LOGGER.info("Enter test password in 'Confirm Password' input field");
        registrationPage.setConfirmPassword(testPassword);

        LOGGER.info("Verify test password is present in 'Confirm Password' input field");
        registrationPage.verifyConfirmPasswordSet(testPassword);

        LOGGER.info("Check 'I agree to the Privacy policy' option");
        PrivacyPolicyPage privacyPolicyPage = registrationPage.checkPrivacyPolicyAndOpenPrivacyPolicyPage();

        LOGGER.info("Verify 'Privacy Policy' pop-up is opened");
        privacyPolicyPage.verifyPrivacyPolicyPageOpened();

        LOGGER.info("Scroll 'Read privacy policy' to bottom");
        privacyPolicyPage.scrollToAcceptButton();

        LOGGER.info("Verify 'Accept' button is present");
        privacyPolicyPage.verifyAcceptButtonPresent();

        LOGGER.info("Press 'Accept' button");
        RegistrationPage registrationPageNew = privacyPolicyPage.clickAcceptButtonAndReturnToRegistrationPage();

        LOGGER.info("Verify 'Registration' page is opened");
        registrationPageNew.verifyInboxRegistrationPageOpened();

        LOGGER.info("Verify 'I agree to the Privacy policy' option is checked");
        registrationPageNew.verifyPrivacyPolicyCheckboxState(true);

        LOGGER.info("Check 'I agree to the Terms of Service' option");
        registrationPageNew.checkTermsOfService();

        LOGGER.info("Verify 'I agree to the Terms of Service' option is checked");
        registrationPageNew.verifyTermsOfServiceCheckboxState(true);

        LOGGER.info("Press 'Finish' button");
        NewUserSignUpPage inboxNewUserSignUpPage = registrationPageNew.pressFinishButtonAndGoToNewUserSignUpPage();

        LOGGER.info("Verify New user sign up page opened");
        inboxNewUserSignUpPage.verifyInboxSingInPageIsShown();

        LOGGER.info("Press 'Proceed' button");
        SingInPage inboxSingInPage = inboxNewUserSignUpPage.pressProceedAndGoToSignInPage();

        LOGGER.info("Verify 'You are signe id' page is opened"); //done in scope of page initialization

        LOGGER.info("Verify 'Press 'Continue' button");
        LOGGER.info("Verify 'Verify your phone number' page is opened");//done in scope of page initialization
        PhonePage inboxPhonePage = inboxSingInPage.pressContinueAndGoToPhoneVerificationPage();

        //Used Latvia in case you want to pass this test, you will require to pass verification code from sms
        LOGGER.info("Select test country in 'Country' dropdown");
        inboxPhonePage.selectCountry("Latvia");

        LOGGER.info("Verify test Country is selected in 'Country' dropdown");
        inboxPhonePage.verifyCountry("Latvia");

        //REQUIRES REAL PHONE NUMBER
        LOGGER.info("Enter test phone number in 'Phone number here...' input field");
        inboxPhonePage.setPhone(testPhoneNumber);

        //REQUIRES REAL PHONE NUMBER
        LOGGER.info("Verify test phone is present in 'Phone number here...' input field");
        inboxPhonePage.verifyPhone(testPhoneNumber);

        LOGGER.info("Press 'Next' button");
        PhoneNumberVerificationPage inboxPhoneNumberVerificationPage = inboxPhonePage.pressNextAndGoToPhoneNumberVerificationPage();

        LOGGER.info("Verify 'Phone number verification' page is opened");//done in scope of page initialization

        LOGGER.info("Select 'We send SMS to your phone' option in 'Step 2: Select phone number validation option' list");
        inboxPhoneNumberVerificationPage.selectValidationOption(2);

        LOGGER.info("Verify 'We send SMS to your phone' option is selected in 'Step 2: Select phone number validation option' list");
        inboxPhoneNumberVerificationPage.verifyValidationOption(2);

        LOGGER.info("Press 'Next step' button");
        PhoneNumberVerificationPage phoneNumberVerificationPage = inboxPhoneNumberVerificationPage.pressNextAndWaitNumberVerificationPage();

        LOGGER.info("Verify 'Phone number verification' page is opened"); //done in scope of page initialization

        LOGGER.info("Press 'Next step' button");
        PhoneNumberVerificationPage phoneNumberVerificationPage2 =  phoneNumberVerificationPage.pressNextAndWaitNumberVerificationPage();

        LOGGER.info("Verify 'Phone number verification' page is opened"); //done in scope of page initialization

        //MANUAL STEPS START
        LOGGER.info("Set test code in 'Enter the code you have received:' input field");
        //manual sms code adding required
        //inboxPhoneNumberVerificationPage.setSmsCode(verificationCode);

        LOGGER.info("Verify test code is set in 'Enter the code you have received:' input field");
        //inboxPhoneNumberVerificationPage.verifySmsCodeSet(verificationCode);
        //MANUAL STEPS END

        LOGGER.info("Press 'Next' button");
        ThankYouPage thankYouPage1 = phoneNumberVerificationPage2.pressNextAndFinishPhoneNumberVerification();

        LOGGER.info("Verify 'Thank you' page is opened");
        //done in scope of page initialization

        LOGGER.info("Press 'Done' button");
        LOGGER.info("Verify 'Add birthday and gender' page is opened"); //done in scopes of page initialization
        BirthdayGenderPage birthdayGenderPage = thankYouPage1.pressDoneAndGoToBirthDayGenderPage();

        LOGGER.info("Select test day in 'Day' dropdown");
        birthdayGenderPage.selectBirthDay("1");

        LOGGER.info("Verify test day is present in 'Day' dropdown");
        birthdayGenderPage.verifyBirthDay("01");

        LOGGER.info("Select test month in 'Month' dropdown");
        birthdayGenderPage.selectBirthMonth("January");

        LOGGER.info("Verify test month is present in 'Month' dropdown");
        birthdayGenderPage.verifyBirthMonth("01");

        LOGGER.info("Set test year in 'Year' input field");
        birthdayGenderPage.setBirthYear("1980");

        LOGGER.info("Verify test year is present in 'Year' input field");
        birthdayGenderPage.verifyBirthYear("1980");

        LOGGER.info("Select test gender in 'Gender' dropdown");
        birthdayGenderPage.selectGender("Male");

        LOGGER.info("Verify test gender is present in 'Gender' dropdown");
        birthdayGenderPage.verifyGender("1");

        LOGGER.info("Press 'Save'");
        LOGGER.info("Verify 'Thank you' page is opened"); //done in scopes of page initialization
        ThankYouPage thankYouPage2 = birthdayGenderPage.pressSaveAndGoToThankYouPage();

        LOGGER.info("Press 'Done' button");
        LOGGER.info("Verify Inbox Mail is opened"); //done in scopes of page initialization
        MailboxPage mailboxPage = thankYouPage2.pressDoneAndGoToInboxMailboxPage();

        //AUTOMATION SPECIFIC STEPS START
        //2. After the registration test should make login in email and collect all active emails in the table/list
        //3. After collecting data test should log out.
        //PRECONDITION START, LEST LOG OUT FIRST
        LOGGER.info("Open 'Profile Sidebar'");
        SidebarPage sidebarPage = mailboxPage.openSidebar();

        LOGGER.info("Verify 'Profile Sidebar' is opened");  //done in scopes of page initialization

        LOGGER.info("Press 'Logout' button");
        MainPage mainPage = sidebarPage.logout();

        LOGGER.info("Verify Inbox main page is opened"); //done in scopes of page initialization

        //PRECONDITION END
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
        System.out.println(unreadMailsInInbox);

        LOGGER.info("Open 'Profile Sidebar'");
        SidebarPage sidebarPage1 =  mailboxPage1.openSidebar();

        LOGGER.info("Press 'Logout' button");
        sidebarPage1.logout();
        //AUTOMATION SPECIFIC STEPS END
    }

    @After
    public void closeBrowserForTesting() {
        LOGGER.info("Close browser");
        tearDown();
    }
}