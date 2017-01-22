package ua.net.itlabs;

import com.codeborne.selenide.ElementsCollection;
import org.junit.Test;
import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;


public class ToDoMVCTest {

    @Test
    public void tasksCommonFlow(){

        open("https://todomvc4tasj.herokuapp.com/");

        add("1", "2", "3", "4");
        assertTasksAre("1", "2", "3", "4");

        delete("2");
        assertTasksAre("1", "3", "4");

        toggle("4");
        clearCompleted();
        assertTasksAre("1", "3");

        toggleAll();
        clearCompleted();
        tasks.shouldBe(empty);

    }

    ElementsCollection tasks = $$("#todo-list li");

    private void clearCompleted() {
        $("#clear-completed").click();
    }
    private void delete(String taskText) {
        tasks.find(exactText(taskText)).hover().$(".destroy").click();
    }
    private void toggleAll() {
        $("#toggle-all").click();
    }
    private void add(String... taskText) {
        for (String text: taskText) {
        $("#new-todo").setValue(text).pressEnter();}

    }
    private void toggle(String taskText) {
        tasks.findBy(exactText(taskText)).$(".toggle").click();
    }
    private void assertTasksAre(String... taskText){
            tasks.shouldHave(exactTexts(taskText));
        }

}
