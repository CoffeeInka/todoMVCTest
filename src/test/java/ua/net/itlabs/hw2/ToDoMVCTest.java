package ua.net.itlabs.hw2;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ToDoMVCTest {

    @Before
    public void openPage() {
        open("https://todomvc4tasj.herokuapp.com/");
    }

    @After
    public void clearData() {
        executeJavaScript("localStorage.clear()");
    }

    @Test
    public void tasksLifeCycle() {
        add("1");
        toggle("1");
        assertTasks("1");

        filterActive();
        assertNoTasks();

        add("2");
        assertTasks("2");
        assertItemsLeft(1);

        toggleAll();
        assertNoTasks();

        filterCompleted();
        assertTasks("1", "2");

        toggle("1");
        assertTasks("2");

        clearCompleted();
        assertNoTasks();

        filterAll();
        assertTasks("1");


    }

    @Test
    public void cancelEdit() {
        //given task "1" is created on All and task "2" is created on Active
        add("1");
        filterActive();
        add("2");

        cancelEdit("2", "2 edit canceled");
        assertTasks("1", "2");
        assertItemsLeft(2);
    }

    @Test
    public void editTask() {
        //given task "1" is created on All
        add("1");

        edit("1", "1 edited");
        assertTasks("1 edited");
        assertItemsLeft(1);
    }

    @Test
    public void deleteTask() {
        //given tasks "1" and "2" are created on All
        add("1", "2");

        delete("1");
        assertTasks("2");
        assertItemsLeft(1);
    }

    ElementsCollection tasks = $$("#todo-list li");

    ElementsCollection filters = $$("#filters li");

    private void add(String... tasksTexts) {
        for (String text : tasksTexts) {
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

    private void assertTasks(String... tasksTexts) {
        tasks.filterBy(visible).shouldHave(exactTexts(tasksTexts));
    }

}
