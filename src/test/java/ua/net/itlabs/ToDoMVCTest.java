package ua.net.itlabs;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.Test;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by inna on 1/15/17.
 */

public class ToDoMVCTest {

    @Test
    public void tasksCommonFlow(){

        open("https://todomvc4tasj.herokuapp.com/");
        $("#new-todo").setValue("create task1").pressEnter();
        $("#new-todo").setValue("create task2").pressEnter();
        $("#new-todo").setValue("create task3").pressEnter();
        $("#new-todo").setValue("create task4").pressEnter();
        $$("#todo-list li").shouldHave(exactTexts("create task1", "create task2", "create task3", "create task4"));

        $("#todo-list li:nth-child(2)").hover();
        $("#todo-list li:nth-child(2) .destroy").click();
        $$("#todo-list li").shouldHave(exactTexts("create task1", "create task3", "create task4"));

        $("#todo-list li:nth-child(3) .toggle").click();
        $$("#todo-list .completed").shouldHave(texts("create task4"));

        $("#clear-completed").click();
        //$$("#todo-list .completed").shouldBe(empty);
        $$("todo-list li").filterBy(cssClass("completed")).shouldBe(empty);

        $("#toggle-all").click();
        $$("#todo-list li.completed").shouldHaveSize(2);

        $("#clear-completed").click();
        $$("#todo-list li").shouldBe(empty);






    }



}
