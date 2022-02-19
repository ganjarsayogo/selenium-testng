package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.List;

public class AddQuoteTest {

    private ChromeDriver driver;

    @BeforeTest
    public void initializeSession(){
        //webdrivermanager
        WebDriverManager.chromedriver().setup();
        //chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        //initialize browser
        driver = new ChromeDriver();
        //maximize
        driver.manage().window().maximize();
    }

    @AfterTest
    public void destroySession() throws InterruptedException {
        Thread.sleep(5000);
        //close browser
        driver.quit();
    }

    @Test
    public void addQuoteWithColorBlue(){
        driver.get("http://bdd.atlasid.tech/");
        //input quote
        By locatorInputQuote = By.id("inputQuote");
        WebElement inputQuote = driver.findElement(locatorInputQuote);
        inputQuote.sendKeys("You can make it!");

        //select color
        By locatorSelectColor = By.id("colorSelect");
        WebElement selectColor = driver.findElement(locatorSelectColor);
        Select select = new Select(selectColor);
        select.selectByVisibleText("Blue");

        //click
        By locatorButtonAddQuote = By.id("buttonAddQuote");
        WebElement buttonAddQuote = driver.findElement(locatorButtonAddQuote);
        buttonAddQuote.click();

        //find elements
        By locatorLabelQuotes = By.name("quoteText");
        List<WebElement> quotes = driver.findElements(locatorLabelQuotes);
        //ambil quote kedua
        WebElement secondQuote = quotes.get(1);
        String actualData = secondQuote.getText();
        //assertions
        Assert.assertEquals(actualData, "You can make it!", "quote is not same");
    }
}
