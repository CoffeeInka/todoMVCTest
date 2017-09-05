package ua.net.itlabs.hw7.core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class ConciseAPI {

    //private static WebDriver driver;
    private ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

//    public static WebDriver getDriver() {
//        return ConciseAPI.driver;
//    }
    public WebDriver getDriver() {
        return threadLocal.get();
    }

//    public static void setDriver(WebDriver driver) {
//        ConciseAPI.driver = driver;
//    }
    public void setDriver(WebDriver driver) {
        threadLocal.set(driver);
    }

    public static WebElement $(By elementLocator) {
        return assertThat(visibilityOfElementLocated(elementLocator));
    }

    public static WebElement $(ExpectedCondition<WebElement> conditionToWaitParentElement, String innerElementCssSelector) {
            return assertThat(conditionToWaitParentElement).findElement(byCss(innerElementCssSelector));
    }

    public static WebElement $(ExpectedCondition<WebElement> conditionToWaitParentElement) {
        return assertThat(conditionToWaitParentElement);
    }

    public static WebElement $(ExpectedCondition<WebElement> conditionToWaitParentElement, By innerElementLocator) {
        return assertThat(conditionToWaitParentElement).findElement(innerElementLocator);
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

    public static void enterValue(String cssSelector, String text) {
        $(byCss(cssSelector)).clear();
        $(byCss(cssSelector)).sendKeys(text + Keys.ENTER);
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

    public static void doubleclick(WebElement element) {
        ExpectedConditions.visibilityOf(element);
        Actions actions = new Actions(getDriver());
        actions.doubleClick(element).perform();
    }

    public static void hover(WebElement element) {
        ExpectedConditions.visibilityOf(element);
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).perform();
    }

    public static String url(){
        return getDriver().getCurrentUrl();
    }

    public static void refresh(){
        getDriver().navigate().refresh();
    }

    public static void executeJavaScript(String jsCommand){
        ((JavascriptExecutor) getDriver()).executeScript(jsCommand);
    }

//    public static List<String> getActualVisibleTexts(List<String>actualTexts, List<WebElement> elementsList){
//        actualTexts = new ArrayList<>();
//        for (WebElement element : elementsList) {
//            if (element.isDisplayed()) {
//                actualTexts.add(element.getText());
//            }
//        }
//        return actualTexts;
//    }
}
