package ua.net.itlabs.hw4;

import org.junit.Test;
import ua.net.itlabs.hw2.BaseTest;
import ua.net.itlabs.hw4.pages.ToDoMVCPage;

import static ua.net.itlabs.hw4.pages.ToDoMVCPage.TaskStatus.ACTIVE;
import static ua.net.itlabs.hw4.pages.ToDoMVCPage.TaskStatus.COMPLETED;

/**
 * Created by inna on 4/10/17.
 */
public class AtCompleted extends BaseTest {

    ToDoMVCPage page = new ToDoMVCPage();

    @Test
    public void deleteAtCompleted() {
        page.givenAtCompleted(COMPLETED, "1", "2");

        page.delete("1");
        page.assertTasks("2");
        page.assertItemsLeft(0);
    }

    @Test
    public void reactivateAllAtCompleted() {
        page.givenAtCompleted(COMPLETED, "1", "2");

        page.toggleAll();
        page.assertNoTasks();
        page.assertItemsLeft(2);
    }

    @Test
    public void deleteByClearTextAtCompleted() {
        page.givenAtCompleted(COMPLETED, "1", "2");

        page.edit("2", "");
        page.assertTasks("1");
        page.assertItemsLeft(0);
    }

    @Test
    public void switchFilterCompletedToActive() {
        page.givenAtCompleted(page.aTask(COMPLETED, "1"), page.aTask(COMPLETED, "2"), page.aTask(ACTIVE, "3"));

        page.filterActive();
        page.assertTasks("3");
        page.assertItemsLeft(1);
    }
}
