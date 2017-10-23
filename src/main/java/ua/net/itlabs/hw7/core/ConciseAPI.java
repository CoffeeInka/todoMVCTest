package ua.net.itlabs.hw7.core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class ConciseAPI {

    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    public static WebElement $(By elementLocator) {
        return assertThat(visibilityOfElementLocated(elementLocator));
    }

    public static WebElement $(WebElement element) {
        return assertThat(visibilityOf(element));
    }

    public static WebElement $(ExpectedCondition<WebElement> conditionToWaitElement, String innerElementCssSelector) {
        return assertThat(conditionToWaitElement).findElement(byCss(innerElementCssSelector));
    }

    public static WebElement $(ExpectedCondition<WebElement> conditionToWaitParentElement) {
        return assertThat(conditionToWaitParentElement);
    }

    public static WebElement $(ExpectedCondition<WebElement> conditionToWaitElement, By innerElementLocator) {
        return assertThat(conditionToWaitElement).findElement(innerElementLocator);
    }

    public static <V> V assertThat(ExpectedCondition<V> condition, long timeout, long polling) {
        return new FluentWait<>(getDriver())
                .withTimeout(timeout, TimeUnit.SECONDS)
                .pollingEvery(polling, TimeUnit.MILLISECONDS)
                .ignoring(WebDriverException.class, IndexOutOfBoundsException.class).until(condition);
    }

    public static <V> V assertThat(ExpectedCondition<V> condition) {
        return assertThat(condition, Configuration.timeout, Configuration.pollingInterval);
    }

    public static WebElement setValue(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
        return element;
    }

    public static By byText(String text) {
        return By.xpath(String.format("//*[text()='%s']", text));
    }

    public static By byCss(String cssSelector) {
        return By.cssSelector(cssSelector);
    }

    public static void open(String url) {
        getDriver().get(url);
    }

    public static void doubleClick(WebElement element) {
        Actions actions = new Actions(getDriver());
        actions.doubleClick(element).perform();
    }

    public static void hover(WebElement element) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).perform();
    }

    public static String url() {
        return getDriver().getCurrentUrl();
    }

    public static void refresh() {
        getDriver().navigate().refresh();
    }

    public static void executeJavaScript(String jsCommand) {
        ((JavascriptExecutor) getDriver()).executeScript(jsCommand);
    }

}
