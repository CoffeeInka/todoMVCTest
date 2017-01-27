package ua.net.itlabs.hw2;

import com.codeborne.selenide.ElementsCollection;
import org.junit.Test;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class ToDoMVCTest {

    @Test
    public void e2eSmokeTest() {

        open("https://todomvc4tasj.herokuapp.com/");

        add("1");
        delete("1");
        tasks.shouldBe(empty);

        add("2");
        cancelEditTask("2", "3");
        editTask("2", "3");

        complete("3");
        assertTaskIsVisible("3");

        switchFilterTo("Completed");
        //here I could avoid reopen and complete terminology and use "toggle" instead, but this way readability is better
        reopen("3");
        assertTaskDisappears("3");

        switchFilterTo("Active");
        assertTaskIsVisible("3");

        completeAll();
        assertItemsLeft("0");
        clearCompleted();
        tasks.shouldBe(empty);
    }


    ElementsCollection tasks = $$("#todo-list li");

    ElementsCollection filters = $$("#filters li");

    private void add(String... taskText) {
        for (String text : taskText) {
            $("#new-todo").setValue(text).pressEnter();
        }
    }

    private void delete(String taskText) {
        tasks.find(exactText(taskText)).hover().$(".destroy").click();
    }

    private void complete(String taskText) {
        tasks.findBy(exactText(taskText)).find(".toggle").click();
    }

    private void reopen(String taskText) {
        //this method implies task is already in completed state;
        complete(taskText);
    }

    private void completeAll() {
        $("#toggle-all").click();
    }

    private void assertItemsLeft(String itemsLeft) {
        $("#todo-count").equals(itemsLeft);
    }

    private void clearCompleted() {
        $("#clear-completed").click();
    }

    private void cancelEditTask(String initText, String newText) {
        tasks.findBy(exactText(initText)).doubleClick();
        tasks.findBy(cssClass("editing")).find(".edit").setValue(newText).pressEscape();
    }

    private void editTask(String initText, String newText) {
        tasks.findBy(exactText(initText)).click();
        tasks.findBy(exactText(initText)).doubleClick();
        tasks.findBy(cssClass("editing")).find(".edit").setValue(newText).pressEnter();
    }

    private void assertTaskIsVisible(String taskText) {
        tasks.findBy(exactText(taskText)).is(visible);
    }

    private void switchFilterTo(String filterName) {
        filters.findBy(exactText(filterName)).click();
    }

    private void assertTaskDisappears(String taskText) {
        tasks.findBy(exactText(taskText)).should(disappear);
    }

}
