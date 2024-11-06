package org.example.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final static String mainPage = "https://stellarburgers.nomoreparties.site/";

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //  локатор для кнопки Войти
    private final By authorizationButton = By.xpath(".//*[text() = 'Войти в аккаунт']");
    //  локатор для кнопки личный кабинет
    private final By userAccountButton = By.xpath("//a[@class='AppHeader_header__link__3D_hX' and @href='/account']");
    //  локаторы для соуса
    private final By ingredientSauceButton = By.xpath("//span[text()='Соусы']/..");
    private final By checkSauceDisplayed = By.xpath(".//section[1]/div[2]/h2[2]");
    //  локаторы для булки
    private final By ingredientBunsButton = By.xpath("//span[text()='Булки']/..");
    private final By checkBunsDisplayed = By.xpath(".//section[1]/div[2]/h2[1]");
    // локаторы для начилки
    private final By ingredientFillingButton = By.xpath("//span[text()='Начинки']/..");
    private final By checkFillingDisplayed = By.xpath(".//section[1]/div[2]/h2[3]");
    // локаторы для кнопки "Оформить заказ"
    private final By orderButton = By.className("button_button__33qZ0");

    @Step("open main Page")
    public void openMainPage() {
        driver.get(mainPage);
    }

    // 	вход по кнопке «Войти в аккаунт» на главной
    @Step("Find, Check And Click Authorization Button on mainPage")
    public void clickAuthorizationButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(authorizationButton));
        Object elementAuthorizationButton = driver.findElement(authorizationButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementAuthorizationButton);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(authorizationButton));
        driver.findElement(authorizationButton).click();
    }

    // 	вход в личный кабинет
    @Step("Click Personal Account Button on MainPage")
    public void clickUserAccountButton() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(userAccountButton));
        driver.findElement(userAccountButton).click();
    }

    // загрузка стартовой страницы после авторизации и наличие кнопки "Оформить заказ"
    @Step("Get text from Order Button on MainPage")
    public Object textOrderButton() {
        WebElement textButton = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(orderButton));
        return textButton.getText();
    }

    private boolean isTabActive(By tabLocator) {
        return driver.findElement(tabLocator).getAttribute("class").contains("active");
    }

    @Step("Find And Click SauceLink on mainPage, check Sauce Element")
    public boolean checkSauceLinkDisplayed() {
        if (!isTabActive(ingredientSauceButton)) {
            driver.findElement(ingredientSauceButton).click();
        }
        waitUntilVisible(checkSauceDisplayed);
        return driver.findElement(checkSauceDisplayed).isDisplayed();
    }

    @Step("Find And Click StuffingLink on mainPage, check Stuffing Element")
    public boolean checkStuffingLinkDisplayed() {
        if (!isTabActive(ingredientFillingButton)) {
            driver.findElement(ingredientFillingButton).click();
        }
        waitUntilVisible(checkFillingDisplayed);
        return driver.findElement(checkFillingDisplayed).isDisplayed();
    }

    @Step("Find And Click BunsLink on mainPage, check Buns Element")
    public boolean checkBunsLinkDisplayed() {
        if (!isTabActive(ingredientFillingButton)) {
            driver.findElement(ingredientFillingButton).click();
        }
        if (!isTabActive(ingredientBunsButton)) {
            driver.findElement(ingredientBunsButton).click();
        }
        waitUntilVisible(checkBunsDisplayed);
        return driver.findElement(checkBunsDisplayed).isDisplayed();
    }


    private WebElement waitUntilVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
