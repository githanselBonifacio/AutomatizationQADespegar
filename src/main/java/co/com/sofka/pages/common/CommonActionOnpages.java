package co.com.sofka.pages.common;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class CommonActionOnpages {
    private static  final Logger logger = LogManager.getLogger(CommonActionOnpages.class);
    private final WebDriver driver;

    public CommonActionOnpages(WebDriver driver) {
        this.driver = driver;
    }

    protected void AddWaitByVisibility(By locator, int wait){
        WebDriverWait await = new WebDriverWait(driver,wait);
        await.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    //accion
    protected void TypeInto(By locator, String value){
        driver.findElement(locator).sendKeys(value);
    }

    protected void Click(By locator){driver.findElement(locator).click();}

    protected  void RepeatClick (By locator, int repeats){
        for(int i = 0;i<repeats;i++){
            Click(locator);
        }
    }
    protected  void PressEnter(By locator){driver.findElement(locator).sendKeys(Keys.ENTER);}

    protected  void PressScope(By locator){driver.findElement(locator).sendKeys(Keys.ESCAPE);}

    public void ScrollTo(By locator){
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(locator));
    }

    protected String getTex(By locator){
        return driver.findElement(locator).getText();
    }

    protected ArrayList<String[]> getTextElements(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        ArrayList<String[]> elementsText = new ArrayList<>();
        for(WebElement webElement: elements){
            String [] detalle = webElement.getText().split("\\n");
            elementsText.add(detalle);
        }
        return elementsText;
    }
}
