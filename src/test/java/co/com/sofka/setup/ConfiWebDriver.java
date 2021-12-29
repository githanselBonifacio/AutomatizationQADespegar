package co.com.sofka.setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ConfiWebDriver {

    private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
    private static final String WEBDRIVER_CHROME_DRIVER_PATH = "src/test/resources/driver/windows/chrome/chromedriver.exe";

    private static final String DESPEGAR_URL = "https://www.despegar.com.co/vuelos/";

    protected WebDriver driver;

    private void setUpWebdriver(){
        System.setProperty(WEBDRIVER_CHROME_DRIVER, WEBDRIVER_CHROME_DRIVER_PATH);
    }

    private void setUpWebdriverUrl(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(DESPEGAR_URL);
    }

    protected void generalSetup(){
        setUpWebdriver();
        setUpWebdriverUrl();
    }

    protected void quiteDriver(){
        driver.quit();
    }

}
