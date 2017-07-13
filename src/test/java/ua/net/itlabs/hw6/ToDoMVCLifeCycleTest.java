package ua.net.itlabs.hw6;


import org.junit.Test;
import ua.net.itlabs.hw6.core.BaseTest;

import static com.sun.tools.internal.xjc.reader.Ring.add;
import static ua.net.itlabs.hw6.pages.ToDoMVC.*;


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



