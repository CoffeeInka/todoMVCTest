package ua.net.itlabs.hw7;


import org.junit.Test;
import ua.net.itlabs.hw7.pages.ToDoMVC;
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
        assertTasks("A");

        filterActive();
        assertNoTasks();

        add("B");
        assertTasks("B");
        assertItemsLeft(1);

        toggleAll();
        assertNoTasks();

        filterCompleted();
        assertTasks("A", "B");

        toggle("A");
        assertTasks("B");

        clearCompleted();
        assertNoTasks();

        filterAll();
        assertTasks("A");
    }


}



