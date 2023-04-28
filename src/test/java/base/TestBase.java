package test.java.base;

import main.java.framework.utils.DriverManager;
import main.java.framework.utils.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import static java.lang.System.getProperty;
import static java.lang.System.setProperty;
import static java.time.Duration.ofSeconds;
import static main.java.framework.utils.PropertyReader.readProperty;

public class TestBase extends DriverManager {
    public WebDriver driver;

    PropertyReader propertyReader = new PropertyReader();

    public TestBase() {
        this.driver = super.getDriver();
    }

    public void setup() {
        String browserDriver = propertyReader.readProperty("browser");
        switch (browserDriver) {
            case "chrome":
                setProperty("webdriver.chrome.driver", getProperty("user.dir") + "/src/main/drivers/chromedriver.exe");
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.get(readProperty("host.name"));
                driver.manage().timeouts().implicitlyWait(ofSeconds(10));
                break;
            case "firefox":
                setProperty("webdriver.gecko.driver", getProperty("user.dir") + "/src/main/drivers/geckodriver.exe");
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                driver.get(readProperty("host.name"));
                driver.manage().timeouts().implicitlyWait(ofSeconds(10));
                break;
            default:
                System.out.println("No such driver exists");
        }
    }

    public void tearDown() {
        driver.quit();
    }
}
