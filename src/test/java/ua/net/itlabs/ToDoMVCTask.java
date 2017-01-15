package ua.net.itlabs;

import com.codeborne.selenide.Condition;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by inna on 1/15/17.
 */

public class ToDoMVCTask {

    @Test
    public void testTask(){

        open("https://todomvc4tasj.herokuapp.com/");
        $("#new-todo").setValue("create task1").pressEnter();
        $("#new-todo").setValue("create task2").pressEnter();
        $("#new-todo").setValue("create task3").pressEnter();
        $("#new-todo").setValue("create task4").pressEnter();
        $("#todo-list li[data-index='1']").hover();
        $("#todo-list li[data-index='1'] .destroy").hover().click();
        $("#todo-list li[data-index='3'] .toggle").click();
        $("#clear-completed").click();
        $("#toggle-all").click();
        $("#clear-completed").click();
        $("#todo-list li").shouldNot(Condition.exist);



    }



}
