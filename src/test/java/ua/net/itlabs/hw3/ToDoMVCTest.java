package ua.net.itlabs.hw3;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Step;
import ua.net.itlabs.hw2.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static ua.net.itlabs.hw3.ToDoMVCTest.TaskStatus.*;

public class ToDoMVCTest extends BaseTest {

    @Test
    public void tasksLifeCycle() {
        given();

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
    public void completeAtActive() {
        givenAtActive(ACTIVE, "1", "2");

        toggle("1");
        assertTasks("2");
        assertItemsLeft(1);
    }

    @Test
    public void completeAllAtAll() {
        given(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));

        toggleAll();
        assertTasks("1", "2");
        assertItemsLeft(0);
    }

    @Test
    public void clearCompletedAtAll() {
        given(COMPLETED, "1");

        clearCompleted();
        assertNoTasks();
    }

    @Test
    public void clearCompletedAtActive() {
        givenAtActive(aTask(COMPLETED, "1"), aTask(COMPLETED, "2"), aTask(ACTIVE, "3"));

        clearCompleted();
        assertTasks("3");
        assertItemsLeft(1);
    }

    @Test
    public void reactivateAtAll() {
        given(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));

        toggle("2");
        assertTasks("1", "2");
        assertItemsLeft(2);
    }

    @Test
    public void editAtAll() {
        given(ACTIVE, "1");

        edit("1", "1 edited");
        assertTasks("1 edited");
        assertItemsLeft(1);
    }

    @Test
    public void editAtActive() {
        givenAtActive(ACTIVE, "1", "2");

        edit("2", "2 edited");
        assertTasks("1", "2 edited");
        assertItemsLeft(2);
    }

    @Test
    public void deleteAtAll() {
        given(aTask(COMPLETED, "1"), aTask(ACTIVE, "2"));

        delete("1");
        assertTasks("2");
        assertItemsLeft(1);
    }

    @Test
    public void deleteAtActive() {
        givenAtActive(ACTIVE, "1");

        delete("1");
        assertNoTasks();
    }

    @Test
    public void deleteAtCompleted() {
        givenAtCompleted(COMPLETED, "1", "2");

        delete("1");
        assertTasks("2");
        assertItemsLeft(0);
    }

    @Test
    public void reactivateAllAtCompleted() {
        givenAtCompleted(COMPLETED, "1", "2");

        toggleAll();
        assertNoTasks();
        assertItemsLeft(2);
    }

    @Test
    public void cancelEditAtActive() {
        givenAtActive(ACTIVE, "1", "2");

        cancelEdit("2", "2 edit canceled");
        assertTasks("1", "2");
        assertItemsLeft(2);
    }

    @Test
    public void cancelEditAtAll() {
        given(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));

        cancelEdit("2", "2 edit canceled");
        assertTasks("1", "2");
        assertItemsLeft(1);
    }

    @Test
    public void editByTabAtAll() {
        given(COMPLETED, "1");

        editByTab("1", "1 edited");
        assertTasks("1 edited");
        assertItemsLeft(0);
    }

    @Test
    public void editByClickOutOfTaskAtActive() {
        givenAtActive(ACTIVE, "1", "2");

        editByClickOutOfTask("2", "2 edited");
        assertTasks("1", "2 edited");
        assertItemsLeft(2);
    }

    @Test
    public void deleteByClearTextAtCompleted() {
        givenAtCompleted(COMPLETED, "1", "2");

        edit("2", "");
        assertTasks("1");
        assertItemsLeft(0);
    }

    @Test
    public void switchFilterActiveToAll() {
        givenAtActive(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));

        filterAll();
        assertTasks("1", "2");
        assertItemsLeft(1);
    }

    @Test
    public void switchFilterCompletedToActive() {
        givenAtCompleted(aTask(COMPLETED, "1"), aTask(COMPLETED, "2"), aTask(ACTIVE, "3"));

        filterActive();
        assertTasks("3");
        assertItemsLeft(1);
    }

    @Test
    public void switchFilterAllToCompleted() {
        given(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));

        filterCompleted();
        assertTasks("2");
        assertItemsLeft(1);
    }

    public void ensureUrl() {
        if (!url().equals("https://todomvc4tasj.herokuapp.com/")) {
            open("https://todomvc4tasj.herokuapp.com/");
        }
    }

    public class Task {

        TaskStatus status;
        String taskText;

        Task(TaskStatus status, String taskText) {
            this.status = status;
            this.taskText = taskText;
        }

        @Override
        public String toString() {
            return String.format("{\"completed\":%s,\"title\":\"%s\"}", status, taskText);
        }
    }

    public Task aTask(TaskStatus status, String taskText) {
        Task aTask = new Task(status, taskText);
        return aTask;
    }

    public enum TaskStatus {
        ACTIVE("false"),
        COMPLETED("true");

        public String status;

        TaskStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return status;
        }
    }

    public Task[] tasksWithStatus(TaskStatus status, String... taskTexts) {
//        ArrayList<Task> tasks = new ArrayList<Task>();
//        for (String taskText : taskTexts) {
//            tasks.add(aTask(status, taskText));}
//        return tasks.toArray(new Task[tasks.size()]);

        return Arrays.stream(taskTexts).map(taskText ->
                aTask(status, taskText)).
                toArray(size -> new Task[size]);
    }

    public void given(Task... tasks) {
        ensureUrl();
        String jsCommand = "localStorage.setItem(\"todos-troopjs\", '[" + StringUtils.join(tasks, ",") + "]')";
        System.out.println(jsCommand);
        executeJavaScript(jsCommand);
        refresh();
    }

    public void given(TaskStatus status, String... taskTexts) {
        given(tasksWithStatus(status, taskTexts));
    }

    public void givenAtActive(Task... tasks) {
        given(tasks);
        filterActive();
    }

    public void givenAtActive(TaskStatus status, String... taskTexts) {
        given(tasksWithStatus(status, taskTexts));
        filterActive();
    }

    public void givenAtCompleted(Task... tasks) {
        given(tasks);
        filterCompleted();
    }

    public void givenAtCompleted(TaskStatus status, String... taskTexts) {
        given(tasksWithStatus(status, taskTexts));
        filterCompleted();
    }

    ElementsCollection tasks = $$("#todo-list li");

    ElementsCollection filters = $$("#filters li");

    @Step
    private void add(String... tasksTexts) {
        for (String text : tasksTexts) {
            $("#new-todo").shouldBe(enabled).setValue(text).pressEnter();
        }
    }

    @Step
    private void delete(String taskText) {
        tasks.find(exactText(taskText)).hover().$(".destroy").click();
    }

    @Step
    private void toggle(String taskText) {
        tasks.findBy(exactText(taskText)).find(".toggle").click();
    }

    @Step
    private void toggleAll() {
        $("#toggle-all").click();
    }

    @Step
    private void assertItemsLeft(int count) {
        $("#todo-count strong").shouldHave(text(Integer.toString(count)));
    }

    @Step
    private void clearCompleted() {
        $("#clear-completed").click();
    }

    @Step
    private SelenideElement startEdit(String oldTaskText, String newTaskText) {
        tasks.findBy(exactText(oldTaskText)).doubleClick();
        return tasks.findBy(cssClass("editing")).find(".edit").setValue(newTaskText);
    }

    @Step
    private void edit(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText, newTaskText).pressEnter();
    }

    @Step
    private void editByTab(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText, newTaskText).pressTab();
    }

    @Step
    private void editByClickOutOfTask(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText, newTaskText);
        $("#header h1").click();
    }

    @Step
    private void cancelEdit(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText, newTaskText).pressEscape();
    }

    @Step
    private void filterCompleted() {
        filters.findBy(exactText("Completed")).click();
    }

    @Step
    private void filterActive() {
        filters.findBy(exactText("Active")).click();
    }

    @Step
    private void filterAll() {
        filters.findBy(exactText("All")).click();
    }

    @Step
    private void assertNoTasks() {
        tasks.filterBy(visible).shouldBe(empty);
    }

    @Step
    private void assertTasks(String... tasksTexts) {
        tasks.filterBy(visible).shouldHave(exactTexts(tasksTexts));
    }

}



