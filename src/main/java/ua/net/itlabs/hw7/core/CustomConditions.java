package ua.net.itlabs.hw7.core;

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
            private List<WebElement> elementsList;

            public WebElement apply(WebDriver driver) {
                //actualTexts = new ArrayList<>();
                elementsList = driver.findElements(elementsListLocator);
//                    for (WebElement element : elementsList) {
//                        actualTexts.add(element.getText());
//                    }
                for (int i = 0; i < elementsList.size(); i++) {
                    if (elementsList.get(i).getText().contains(text)) {
                        return elementsList.get(i);
                    }
                }
                return null;
            }

            public String toString() {
                return String.format("\nElement by text %s is not found by locator %s", text, elementsListLocator);
            }
        };
    }

//    public static ExpectedCondition<List<WebElement>> textsOf(final By elementsListlocator, final String... expectedTexts) {
//        if (expectedTexts.length == 0) {
//            throw new IllegalArgumentException("Array of expected texts is empty.");
//        }
//        return new ExpectedCondition<List<WebElement>>() {
//            private List<String> actualTexts;
//            private List<WebElement> elementsList;
//
//            public List<WebElement> apply(WebDriver driver) {
//                actualTexts = new ArrayList<>();
//                elementsList = driver.findElements(elementsListlocator);
//                for (WebElement element : elementsList) {
//                    actualTexts.add(element.getText());
//                }
//                if (elementsList.size() != expectedTexts.length) {
//                    return null;
//                }
//                for (int i = 0; i < expectedTexts.length; i++) {
//                    if (!elementsList.get(i).getText().contains(expectedTexts[i])) {
//                        return null;
//                    }
//                }
//                return elementsList;
//            }
//
//            public String toString() {
//                return String.format("\nFor list with locator %s\nexpected texts should contain: %s \nwhile actual texts are: %s", elementsListlocator, Arrays.asList(expectedTexts), actualTexts);
//            }
//        };
//    }
}
