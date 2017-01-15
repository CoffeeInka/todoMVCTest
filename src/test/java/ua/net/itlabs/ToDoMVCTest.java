package ua.net.itlabs;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.CollectionCondition.size;
import com.codeborne.selenide.Configuration;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;


public class ToDoMVCTest {


    @Test
    public void testCreateTask(){


        /*System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        Configuration.browser = "chrome";*/

        open("http://todomvc.com/examples/troopjs_require/#/");
        $(".new-todo").setValue("get grand piano").pressEnter();
        $(".new-todo").setValue("be happy").pressEnter();

        $$(".todo-list li").shouldHave(exactTexts("get grand piano", "be happy"));

    }


}
