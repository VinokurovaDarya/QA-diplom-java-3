import io.qameta.allure.junit4.DisplayName;
import org.example.pageobject.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import static org.junit.Assert.assertTrue;

public class CheckConstructorTest {
    MainPage objMainPage;
    WebDriver driver;
    @Before
    public void before() {
        // for testing in Chrome Browser
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        // for testing in Yandex Browser
//        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
//        ChromeOptions options = new ChromeOptions();
//        options.setBinary("/Applications/Yandex.app/Contents/Yandex");
//        driver = new ChromeDriver(options);
        objMainPage = new MainPage(driver);
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @DisplayName("clicking on the button leads to the appearance of the bun link")
    @Test
    public void testBunSectionButton() {
        objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        objMainPage.clickFillingsSectionButton();
        assertTrue("Класс не содержит 'current'", objMainPage.isCurrentClassBreadSection());
    }

    @DisplayName("clicking on the button leads to the appearance of the sauce section")
    @Test
    public void testSauceSectionButton() {

        objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        assertTrue("Класс не содержит 'current'", objMainPage.isCurrentClassSauceSection());
    }

    @DisplayName("clicking on the button leads to the appearance of the filling section")
    @Test
    public void testFillingSectionButton() {
        objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        assertTrue("Класс не содержит 'current'", objMainPage.isCurrentClassFillingSection());

    }

}
