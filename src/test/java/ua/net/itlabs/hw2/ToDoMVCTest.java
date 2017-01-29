package ua.net.itlabs.hw2;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Test;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ToDoMVCTest {

    @Test
    public void tasksLifeCycle() {

        open("https://todomvc4tasj.herokuapp.com/");

        add("1", "2");
        cancelRename("1", "1 edit canceled");
        toggle("1");
        assertTasksAre("1", "2");

        switchFilterToActive();
        assertTasksAre("", "2");
        assertVisibleTasksAre("2");

        applyRename("2", "2 edited");
        toggleAll();
        assertTasksAre("", "");
        assertVisibleTasksAre();

        switchFilterToCompleted();
        assertVisibleTasksAre("1", "2 edited");

        toggle("1");
        assertTasksAre("", "2 edited");
        assertVisibleTasksAre("2 edited");

        clearCompleted();
        assertTasksAre("");
        assertVisibleTasksAre();

        assertItemsLeft(1);

        switchFilterToAll();
        delete("1");
        assertTasksAreEmpty();
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

    private void toggle(String taskText) {
        tasks.findBy(exactText(taskText)).find(".toggle").click();
    }

    private void toggleAll() {
        $("#toggle-all").click();
    }

    private boolean assertItemsLeft(int count) {
        $("#todo-count").equals(count);
        return true;
    }

    private void clearCompleted() {
        $("#clear-completed").click();
    }

    private SelenideElement setNewTaskName(String oldTaskText, String newTaskText) {
        tasks.findBy(exactText(oldTaskText)).doubleClick();
        return tasks.findBy(cssClass("editing")).find(".edit").setValue(newTaskText);
    }

    private void applyRename(String oldTaskText, String newTaskText) {
        setNewTaskName(oldTaskText, newTaskText).pressEnter();
    }

    private void cancelRename(String oldTaskText, String newTaskText) {
        setNewTaskName(oldTaskText, newTaskText).pressEscape();
    }

    private void switchFilterToCompleted() {
        filters.findBy(exactText("Completed")).click();
    }

    private void switchFilterToActive() {
        filters.findBy(exactText("Active")).click();
    }

    private void switchFilterToAll() {
        filters.findBy(exactText("All")).click();
    }

    private void assertTasksAreEmpty() {
        tasks.shouldBe(empty);
    }

    private void assertTasksAre(String... taskTexts) {
        tasks.shouldHave(texts(taskTexts));
    }

    private void assertVisibleTasksAre(String... taskText) {
        for (String text : taskText) {
            tasks.filterBy(visible).shouldHave(exactTexts(taskText));
        }

    }

}
