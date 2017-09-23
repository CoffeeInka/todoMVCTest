package ua.net.itlabs.hw7.conditions;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static ua.net.itlabs.hw7.core.ConciseAPI.getDriver;

public class ElementWithText extends Condition{

    private List<String> actualTexts;

    @Override
    public WebElement apply(By elementsListLocator, String text) {
        List<WebElement> elementsList = getDriver().findElements(elementsListLocator);
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
}
