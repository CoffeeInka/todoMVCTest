package ua.net.itlabs.hw7.conditions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by inna on 9/23/17.
 */
public class MyExpectedConditions {

        public static WebElement elementWithText(By elementListLocator, String text){
            return new ElementWithText().apply(elementListLocator, text);
        }
}
