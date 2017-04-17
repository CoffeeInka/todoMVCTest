package ua.net.itlabs.hw4.pagemodules;


import org.junit.Test;
import ua.net.itlabs.hw2.BaseTest;

import static ua.net.itlabs.hw4.pagemodules.pages.ToDoMVCPage.*;

public class ToDoMVCLifeCycleTest extends BaseTest {
    
    @Test
    public void testTasksLifeCycle() {
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


}



