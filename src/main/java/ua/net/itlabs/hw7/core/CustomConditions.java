package ua.net.itlabs.hw7.core;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class CustomConditions {


    public static ExpectedCondition<WebElement> listElementWithText(final By elementsListLocator, final String text) {

        return new ExpectedCondition<WebElement>() {
            private List<String> actualTexts;

            public WebElement apply(WebDriver driver) {
                List<WebElement> elementsList = driver.findElements(elementsListLocator);
                actualTexts = new ArrayList<>();
                for (WebElement element : elementsList) {
                    if (element.isDisplayed()){
                        actualTexts.add(element.getText());}
                }
                for (int i = 0; i < actualTexts.size(); i++) {
                    if (actualTexts.get(i).equals(text)) {
                        return elementsList.get(i);
                    }
                }
                return null;
            }

            public String toString() {
                return String.format("\nFor list with locator %s \nlooking for text: %s \nwhile actual texts are: %s", elementsListLocator, text, actualTexts);
            }
        };
    }

    public static ExpectedCondition<WebElement> listElementWithCssClass(final By elementsListLocator, final String cssClass) {

        return new ExpectedCondition<WebElement>() {
            private List<WebElement> elementsList;

            public WebElement apply(WebDriver driver) {
                elementsList = driver.findElements(elementsListLocator);
                for (int i = 0; i < elementsList.size(); i++) {
                    if (StringUtils.split(elementsList.get(i).getAttribute("class"))[i].contains(cssClass)) {
                        return elementsList.get(i);
                    }
                }
                return null;
            }

            public String toString() {
                return String.format("\nFor list with locator %s \nlooking for CSS class: %s", elementsListLocator, cssClass);
            }
        };
    }


    public static ExpectedCondition<List<WebElement>> visibleTextsOf(final By elementsListlocator, final String... expectedTexts) {
        if (expectedTexts.length == 0) {
            throw new IllegalArgumentException("Array of expected texts is empty.");
        }
        return new ExpectedCondition<List<WebElement>>() {
            private List<String> actualTexts;
            private List<WebElement> elementsList;

            public List<WebElement> apply(WebDriver driver) {
                elementsList = driver.findElements(elementsListlocator);
                actualTexts = new ArrayList<>();
                for (WebElement element : elementsList) {
                    if (element.isDisplayed()) {
                        actualTexts.add(element.getText());
                    }
                }
                //if (getActualVisibleTexts(actualTexts, elementsList).size() != expectedTexts.length) {
                if (actualTexts.size() != expectedTexts.length) {
                    return null;
                }
                for (int i = 0; i < actualTexts.size(); i++) {
                    if (!actualTexts.get(i).equals(expectedTexts[i])) {
                        return null;
                    }
                }
                return elementsList;
            }

            public String toString() {
                return String.format("\nFor list with locator %s\nexpected texts are: %s \nwhile actual visible texts are: %s", elementsListlocator, Arrays.asList(expectedTexts), actualTexts);
            }
        };
    }
}
