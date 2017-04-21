package ua.net.itlabs.hw4.pageobjects;


import org.junit.Test;
import ua.net.itlabs.hw2.BaseTest;
import ua.net.itlabs.hw4.pageobjects.pages.ToDoMVCPage;

public class ToDoMVCLifeCycleTest extends BaseTest {

    ToDoMVCPage page = new ToDoMVCPage();

    @Test
    public void tasksLifeCycle() {
        page.given();

        page.add("1");
        page.toggle("1");
        page.assertTasks("1");

        page.filterActive();
        page.assertNoTasks();

        page.add("2");
        page.assertTasks("2");
        page.assertItemsLeft(1);

        page.toggleAll();
        page.assertNoTasks();

        page.filterCompleted();
        page.assertTasks("1", "2");

        page.toggle("1");
        page.assertTasks("2");

        page.clearCompleted();
        page.assertNoTasks();

        page.filterAll();
        page.assertTasks("1");

    }


}



