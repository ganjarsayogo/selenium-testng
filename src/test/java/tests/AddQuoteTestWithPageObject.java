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
import pages.QuoteHomePage;

import java.util.List;

public class AddQuoteTestWithPageObject {

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
        QuoteHomePage homePage = new QuoteHomePage(driver);
        homePage.openPage();
        homePage.inputQuote("You can do it");
        homePage.selectColor("Blue");
        homePage.clickButtonAddQuote();
        String actualData = homePage.getSecondQuote();
        Assert.assertEquals(actualData, "You can do it");
    }
}
