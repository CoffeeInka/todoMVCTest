package ua.net.itlabs.hw7;


import org.junit.Test;
import ua.net.itlabs.hw7.testconfigs.BaseTest;

import static ua.net.itlabs.hw7.pages.ToDoMVC.*;
import static ua.net.itlabs.hw7.pages.ToDoMVC.add;
import static ua.net.itlabs.hw7.pages.ToDoMVC.given;


public class ToDoMVCLifeCycleTest extends BaseTest {
    
    @Test
    public void testTasksLifeCycle() {
        given();

        add("A");
        toggle("A");
//        ToDoMVC.assertTasks("1");
//
//        ToDoMVC.filterActive();
//        ToDoMVC.assertNoTasks();
//
//        add("2");
//        ToDoMVC.assertTasks("2");
//        ToDoMVC.assertItemsLeft(1);
//
//        ToDoMVC.toggleAll();
//        ToDoMVC.assertNoTasks();
//
//        ToDoMVC.filterCompleted();
//        ToDoMVC.assertTasks("1", "2");
//
//        ToDoMVC.toggle("1");
//        ToDoMVC.assertTasks("2");
//
//        ToDoMVC.clearCompleted();
//        ToDoMVC.assertNoTasks();
//
//        ToDoMVC.filterAll();
//        ToDoMVC.assertTasks("1");

    }


}



