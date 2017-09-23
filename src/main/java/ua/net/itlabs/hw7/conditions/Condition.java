package ua.net.itlabs.hw7.conditions;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class Condition {

    public abstract WebElement apply(By elementsListLocator, String text);
}
