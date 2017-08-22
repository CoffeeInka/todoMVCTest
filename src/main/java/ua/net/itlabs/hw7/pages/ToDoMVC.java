package ua.net.itlabs.hw7.pages;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.Arrays;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;
import static ua.net.itlabs.hw7.core.ConciseAPI.*;
import static ua.net.itlabs.hw7.core.CustomConditions.listElementWithText;
import static ua.net.itlabs.hw7.core.CustomConditions.visibleTextsOf;


public class ToDoMVC {

    public static By tasks = by("#todo-list li");
    public static By filters = by("#filters li a");


    public static void add(String... tasksTexts) {
        for (String text : tasksTexts) {
            setValue(by("#new-todo"), text);
        }
    }

    public static void delete(String taskText) {
        $(byText(taskText)).click();
        $(listElementWithText(tasks, taskText), ".destroy").click();

    }

    public static void toggle(String taskText) {
        $(listElementWithText(tasks, taskText), ".toggle").click();
    }

    public static void toggleAll() {
        $(byCss("#toggle-all")).click();
    }

    public static void assertItemsLeft(int count) {
        textToBe(byCss("#todo-count strong"), Integer.toString(count));
    }

    public static void clearCompleted() {
        $(byCss("#clear-completed")).click();
    }

    public static WebElement startEdit(String oldTaskText) {
        doubleclick($(listElementWithText(tasks, oldTaskText), ".view>label"));
        $(byCss(".editing")).findElement(byCss(".edit")).clear();
        return $(byCss(".editing")).findElement(byCss(".edit"));
    }

    public static void edit(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText).sendKeys(newTaskText + Keys.ENTER);

    }

    public static void editByTab(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText).sendKeys(newTaskText + Keys.TAB);
    }

    public static void editByClickOutOfTask(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText).sendKeys(newTaskText);
        $(byCss("#header h1")).click();
    }

    public static void cancelEdit(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText).sendKeys(newTaskText + Keys.ESCAPE);
    }

    public static void filterCompleted() {
        $(filters).findElement(byText("Completed")).click();
    }

    public static void filterActive() {
        $(filters).findElement(byText("Active")).click();
    }

    public static void filterAll() {
        $(filters).findElement(byText("All")).click();
    }

    public static void assertNoTasks() {
        invisibilityOfElementLocated(tasks);
    }

    public static void assertTasks(String... tasksTexts) {
        assertThat(visibleTextsOf(tasks, tasksTexts));
    }

    public static void ensureUrl() {
        if (!getDriver().getCurrentUrl().equals("https://todomvc4tasj.herokuapp.com/")) {
            open("https://todomvc4tasj.herokuapp.com/");
        }
    }

    public static class Task {

        public TaskStatus status;
        public String taskText;

        Task(TaskStatus status, String taskText) {
            this.status = status;
            this.taskText = taskText;
        }

        @Override
        public String toString() {
            return String.format("{\"completed\":%s,\"title\":\"%s\"}", status, taskText);
        }
    }

    public static Task aTask(TaskStatus status, String taskText) {
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

    public static Task[] tasksWithStatus(TaskStatus status, String... taskTexts) {
        return Arrays.stream(taskTexts).map(taskText ->
                aTask(status, taskText)).
                toArray(size -> new Task[size]);
    }

    public static void given(Task... tasks) {
        ensureUrl();
        String jsCommand = "localStorage.setItem(\"todos-troopjs\", '[" + StringUtils.join(tasks, ",") + "]')";
        System.out.println(jsCommand);
        ((JavascriptExecutor) getDriver()).executeScript(jsCommand);
        getDriver().navigate().refresh();
    }

    public static void given(TaskStatus status, String... taskTexts) {
        given(tasksWithStatus(status, taskTexts));
    }

    public static void givenAtActive(Task... tasks) {
        given(tasks);
        filterActive();
    }

    public static void givenAtActive(TaskStatus status, String... taskTexts) {
        given(tasksWithStatus(status, taskTexts));
        filterActive();
    }

    public static void givenAtCompleted(Task... tasks) {
        given(tasks);
        filterCompleted();
    }

    public static void givenAtCompleted(TaskStatus status, String... taskTexts) {
        given(tasksWithStatus(status, taskTexts));
        filterCompleted();
    }
}
