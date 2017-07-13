package ua.net.itlabs.hw6.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.Arrays;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by inna on 4/5/17.
 */
public class ToDoMVC {

    public static ElementsCollection tasks = $$("#todo-list li");
    public static ElementsCollection filters = $$("#filters li");
    public static String tasksString = "#todo-list li";
    public static WebDriver driver = getWebDriver();
    public static WebDriverWait wait = new WebDriverWait(driver, 6);



    @Step
    public static void add(String... tasksTexts) {
        for (String text : tasksTexts) {
            $("#new-todo").shouldBe(enabled).setValue(text).pressEnter();
        }
    }

    @Step
    public static void delete(String taskText) {
        tasks.find(exactText(taskText)).hover().$(".destroy").click();
    }

    @Step
    public static void toggle(String taskText) {
        tasks.findBy(exactText(taskText)).find(".toggle").click();
    }

    @Step
    public static void toggleAll() {
        $("#toggle-all").click();
    }

    @Step
    public static void assertItemsLeft(int count) {
        $("#todo-count strong").shouldHave(text(Integer.toString(count)));
    }

    @Step
    public static void clearCompleted() {
        $("#clear-completed").click();
    }

    @Step
    public static SelenideElement startEdit(String oldTaskText, String newTaskText) {
        Actions actions = new Actions(driver);
        actions.doubleClick(driver.findElements(By.cssSelector(tasksString).findElement(By.)));
        //tasks.findBy(exactText(oldTaskText)).doubleClick();
        return tasks.findBy(cssClass("editing")).find(".edit").setValue(newTaskText);
    }

    @Step
    public static void edit(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText, newTaskText).pressEnter();
    }

    @Step
    public static void editByTab(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText, newTaskText).pressTab();
    }

    @Step
    public static void editByClickOutOfTask(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText, newTaskText);
        $("#header h1").click();
    }

    @Step
    public static void cancelEdit(String oldTaskText, String newTaskText) {
        //startEdit(oldTaskText, newTaskText).pressEscape();
        startEdit(oldTaskText, newTaskText).sendKeys(Keys.ESCAPE);
    }

    @Step
    public static void filterCompleted() {
        filters.findBy(exactText("Completed")).click();
    }

    @Step
    public static void filterActive() {
        filters.findBy(exactText("Active")).click();
    }

    @Step
    public static void filterAll() {
        filters.findBy(exactText("All")).click();
    }

    @Step
    public static void assertNoTasks() {
        tasks.filterBy(visible).shouldBe(empty);
    }

    @Step
    public static void assertTasks(String... tasksTexts) {
        tasks.filterBy(visible).shouldHave(exactTexts(tasksTexts));
    }

    public static void ensureUrl() {
        if (!url().equals("https://todomvc4tasj.herokuapp.com/")) {
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

    @Step
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

    @Step
    public static Task[] tasksWithStatus(TaskStatus status, String... taskTexts) {
//        ArrayList<Task> tasks = new ArrayList<Task>();
//        for (String taskText : taskTexts) {
//            tasks.add(aTask(status, taskText));}
//        return tasks.toArray(new Task[tasks.size()]);

        return Arrays.stream(taskTexts).map(taskText ->
                aTask(status, taskText)).
                toArray(size -> new Task[size]);
    }

    @Step
    public static void given(Task... tasks) {
        ensureUrl();
        String jsCommand = "localStorage.setItem(\"todos-troopjs\", '[" + StringUtils.join(tasks, ",") + "]')";
        System.out.println(jsCommand);
        executeJavaScript(jsCommand);
        refresh();
    }

    @Step
    public static void given(TaskStatus status, String... taskTexts) {
        given(tasksWithStatus(status, taskTexts));
    }

    @Step
    public static void givenAtActive(Task... tasks) {
        given(tasks);
        filterActive();
    }

    @Step
    public static void givenAtActive(TaskStatus status, String... taskTexts) {
        given(tasksWithStatus(status, taskTexts));
        filterActive();
    }

    @Step
    public static void givenAtCompleted(Task... tasks) {
        given(tasks);
        filterCompleted();
    }

    @Step
    public static void givenAtCompleted(TaskStatus status, String... taskTexts) {
        given(tasksWithStatus(status, taskTexts));
        filterCompleted();
    }
}
