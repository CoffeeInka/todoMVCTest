package ua.net.itlabs.hw4.pageobjects;

import org.junit.Test;
import ua.net.itlabs.hw2.BaseTest;
import ua.net.itlabs.hw4.pageobjects.pages.ToDoMVCPage;

import static ua.net.itlabs.hw4.pageobjects.pages.ToDoMVCPage.TaskStatus.COMPLETED;

/**
 * Created by inna on 4/10/17.
 */
public class ToDoMVCAtCompletedFilterTest extends BaseTest {

    ToDoMVCPage page = new ToDoMVCPage();

    @Test
    public void delete() {
        page.givenAtCompleted(COMPLETED, "1", "2");

        page.delete("1");
        page.assertTasks("2");
        page.assertItemsLeft(0);
    }

    @Test
    public void reactivateAll() {
        page.givenAtCompleted(COMPLETED, "1", "2");

        page.toggleAll();
        page.assertNoTasks();
        page.assertItemsLeft(2);
    }

    @Test
    public void deleteByClearText() {
        page.givenAtCompleted(COMPLETED, "1", "2");

        page.edit("2", "");
        page.assertTasks("1");
        page.assertItemsLeft(0);
    }

    @Test
    public void switchFilterToActive() {
        page.givenAtCompleted(page.aTask(COMPLETED, "1"), page.aTask(COMPLETED, "2"), page.aTask(ToDoMVCPage.TaskStatus.ACTIVE, "3"));

        page.filterActive();
        page.assertTasks("3");
        page.assertItemsLeft(1);
    }
}
