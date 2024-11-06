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
    //  локаторы для булок
    private final By breadSection = By.cssSelector(".tab_tab__1SPyG:nth-child(1)");
    //локаторы для соусов
    private final By saucesSection =By.cssSelector(".tab_tab__1SPyG:nth-child(2)");
    //локаторы для начинок
    private final By  fillingSection = By.cssSelector(".tab_tab__1SPyG:nth-child(3)");
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


    //клик для перехода в раздел булки
    @Step("Click on the bread section")
    public MainPage clickBreadButton() {
        this.driver.findElement(this.breadSection).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(driver.findElement(breadSection), "class", "current"));
        return this;

    }
    @Step("clicking on the button leads to the appearance of the bread section")
    public boolean isCurrentClassBreadSection() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        clickBreadButton();
        String classAttribute = driver.findElement(breadSection).getAttribute("class");
        return classAttribute.contains("current");
    }
    //клик для перехода в раздел соусы
    @Step("Click on the sauces section")
    public MainPage clickSaucesSectionButton() {
        this.driver.findElement(this.saucesSection).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(driver.findElement(saucesSection), "class", "current"));
        return this;
    }


    @Step("clicking on the button leads to the appearance of the sauce section")
    public boolean isCurrentClassSauceSection() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        clickSaucesSectionButton();
        String classSauceAttribute = driver.findElement(saucesSection).getAttribute("class");
        return classSauceAttribute.contains("current");
    }
    //клик для перехода в раздел начинки
    @Step("Click on the fillings section")
    public MainPage clickFillingsSectionButton() {
        this.driver.findElement(this.fillingSection).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(driver.findElement(fillingSection), "class", "current"));
        return this;
    }
    @Step("clicking on the button leads to the appearance of the filling section")
    public boolean isCurrentClassFillingSection() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        clickFillingsSectionButton();
        String classFillingAttribute = driver.findElement(fillingSection).getAttribute("class");
        return classFillingAttribute.contains("current");
    }
}
