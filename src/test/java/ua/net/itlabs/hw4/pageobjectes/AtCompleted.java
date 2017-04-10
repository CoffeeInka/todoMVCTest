package ua.net.itlabs.hw4.pageobjectes;

import org.junit.Test;
import ua.net.itlabs.hw2.BaseTest;
import ua.net.itlabs.hw4.pageobjectes.pages.ToDoMVCPage;

/**
 * Created by inna on 4/10/17.
 */
public class AtCompleted extends BaseTest {

    ToDoMVCPage page = new ToDoMVCPage();

    @Test
    public void deleteAtCompleted() {
        page.givenAtCompleted(ToDoMVCPage.TaskStatus.COMPLETED, "1", "2");

        page.delete("1");
        page.assertTasks("2");
        page.assertItemsLeft(0);
    }

    @Test
    public void reactivateAllAtCompleted() {
        page.givenAtCompleted(ToDoMVCPage.TaskStatus.COMPLETED, "1", "2");

        page.toggleAll();
        page.assertNoTasks();
        page.assertItemsLeft(2);
    }

    @Test
    public void deleteByClearTextAtCompleted() {
        page.givenAtCompleted(ToDoMVCPage.TaskStatus.COMPLETED, "1", "2");

        page.edit("2", "");
        page.assertTasks("1");
        page.assertItemsLeft(0);
    }

    @Test
    public void switchFilterCompletedToActive() {
        page.givenAtCompleted(page.aTask(ToDoMVCPage.TaskStatus.COMPLETED, "1"), page.aTask(ToDoMVCPage.TaskStatus.COMPLETED, "2"), page.aTask(ToDoMVCPage.TaskStatus.ACTIVE, "3"));

        page.filterActive();
        page.assertTasks("3");
        page.assertItemsLeft(1);
    }
}
