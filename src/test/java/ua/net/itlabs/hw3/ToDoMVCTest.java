package ua.net.itlabs.hw3;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Step;
import ua.net.itlabs.hw2.AtTodoMVCPageWithClearedDataAfterEachTest;

import java.util.ArrayList;
import java.util.Arrays;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static ua.net.itlabs.hw3.ToDoMVCTest.TaskStatus.*;

public class ToDoMVCTest extends AtTodoMVCPageWithClearedDataAfterEachTest {

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

//    public void givenAtActive(){
//        given();
//        filterActive();
//    }
//
//    public void givenAtCompleted(){
//        given();
//        filterCompleted();
//    }

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

    public void given(Task... tasks) {

        String jsCommand = "localStorage.setItem(\"todos-troopjs\", '[" + StringUtils.join(tasks, ",") + "]')";
        System.out.println(jsCommand);
        executeJavaScript(jsCommand);
        refresh();
    }

    public void given(TaskStatus status, String... taskTexts) {

        given(tasksWithStatus(status, taskTexts));
    }

    public Task[] tasksWithStatus(final TaskStatus status, String... taskTexts) {
//        ArrayList<Task> tasks = new ArrayList<Task>();
//        for (String taskText : taskTexts) {
//            tasks.add(aTask(status, taskText));
//        }
//        return tasks.toArray(new Task[tasks.size()]);

        return Arrays.stream(taskTexts).map(taskText ->
                aTask(status, taskText)).
                toArray(size -> new Task[size]);
    }

    @Test
    public void testGivenSameStatus() {
        given(tasksWithStatus(COMPLETED, "a", "b"));

        add("c");
        assertTasks("a", "b", "c");
        assertItemsLeft(1);
    }

    @Test
    public void testGivenDiffStatus() {
        given(aTask(COMPLETED, "a"), aTask(ACTIVE, "b"));

        add("c");
        assertTasks("a", "b", "c");
        assertItemsLeft(2);
    }

    @Test
    public void cancelEditAtActive() {
        given(tasksWithStatus(ACTIVE, "1", "2"));
        filterActive();

        cancelEdit("2", "2 edit canceled");
        assertTasks("1", "2");
        assertItemsLeft(2);
    }

    @Test
    public void editAtAll() {
        given(aTask(ACTIVE, "1"));

        edit("1", "1 edited");
        assertTasks("1 edited");
        assertItemsLeft(1);
    }

    @Test
    public void deleteTaskAtAll() {
        given(tasksWithStatus(ACTIVE, "1", "2"));

        delete("1");
        assertTasks("2");
        assertItemsLeft(1);
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



