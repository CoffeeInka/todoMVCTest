package ua.net.itlabs.hw7.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

import static ua.net.itlabs.hw7.core.ConciseAPI.$;
import static ua.net.itlabs.hw7.core.ConciseAPI.assertThat;
import static ua.net.itlabs.hw7.core.ConciseAPI.listElementWithText;
import static ua.net.itlabs.hw7.pages.ToDoMVC.tasks;

public class CustomConditions {

        public static ExpectedCondition<WebElement> conditionToWaitParentElement(final By elementsListLocator, final String expectedText) {

            return new ExpectedCondition<WebElement>() {

                public WebElement apply(WebDriver driver) {
                    return assertThat(listElementWithText(elementsListLocator, expectedText));
                }

                public String toString() {
                    return String.format("\nExpected text of element of list with locator %s \nby index %d\nshould contain: %s\nwhile actual text is: %s", elementsListLocator, index, expectedText, elementText);
                }
            };
        }
//    public static ExpectedCondition<WebElement> nthElementHasText(final By elementsListLocator, final int index, final String expectedText) {
//
//        return new ExpectedCondition<WebElement>() {
//
//            private WebElement element;
//            private String elementText;
//            private List<WebElement> elementsList;
//
//            public WebElement apply(WebDriver driver) {
//                elementsList = driver.findElements(elementsListLocator);
//                element = elementsList.get(index);
//                elementText = element.getText();
//                return elementText.contains(expectedText) ? element : null;
//            }
//
//            public String toString() {
//                return String.format("\nExpected text of element of list with locator %s \nby index %d\nshould contain: %s\nwhile actual text is: %s", elementsListLocator, index, expectedText, elementText);
//            }
//        };
//    }
}
