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

        add("1");
        cancelEdit("1", "1 edit canceled");
        toggle("1");
        assertTasks("1");

        filterActive();
        assertNoTasks();

        add("2");
        edit("2", "2 edited");
        assertTasks("2 edited");

        toggleAll();
        assertNoTasks();

        filterCompleted();
        assertTasks("1", "2 edited");

        toggle("1");
        assertTasks("2 edited");

        clearCompleted();
        assertItemsLeft(1);
        filterAll();
        assertTasks("1");

        delete("1");
        assertNoTasks();
    }


    ElementsCollection tasks = $$("#todo-list li");

    ElementsCollection filters = $$("#filters li");

    private void add(String... tasksText) {
        for (String text : tasksText) {
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

    private void assertItemsLeft(int count) {
        $("#todo-count strong").shouldHave(text(Integer.toString(count)));
    }

    private void clearCompleted() {
        $("#clear-completed").click();
    }

    private SelenideElement startEdit(String oldTaskText, String newTaskText) {
        tasks.findBy(exactText(oldTaskText)).doubleClick();
        return tasks.findBy(cssClass("editing")).find(".edit").setValue(newTaskText);
    }

    private void edit(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText, newTaskText).pressEnter();
    }

    private void cancelEdit(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText, newTaskText).pressEscape();
    }

    private void filterCompleted() {
        filters.findBy(exactText("Completed")).click();
    }

    private void filterActive() {
        filters.findBy(exactText("Active")).click();
    }

    private void filterAll() {
        filters.findBy(exactText("All")).click();
    }

    private void assertNoTasks() {
        tasks.filterBy(visible).shouldBe(empty);
    }

    private void assertTasks(String... tasksText) {
        tasks.filterBy(visible).shouldHave(exactTexts(tasksText));
    }

}
