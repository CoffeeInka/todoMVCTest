package ua.net.itlabs.hw4.pageobjectes;

import org.junit.Test;
import ua.net.itlabs.hw2.BaseTest;
import ua.net.itlabs.hw4.pageobjectes.pages.ToDoMVCPage;

/**
 * Created by inna on 4/5/17.
 */
public class AtAll extends BaseTest {

    ToDoMVCPage page = new ToDoMVCPage();

    @Test
    public void completeAllAtAll() {
        page.given(page.aTask(ToDoMVCPage.TaskStatus.ACTIVE, "1"), page.aTask(ToDoMVCPage.TaskStatus.COMPLETED, "2"));

        page.toggleAll();
        page.assertTasks("1", "2");
        page.assertItemsLeft(0);
    }

    @Test
    public void clearCompletedAtAll() {
        page.given(ToDoMVCPage.TaskStatus.COMPLETED, "1");

        page.clearCompleted();
        page.assertNoTasks();
    }

    @Test
    public void reactivateAtAll() {
        page.given(page.aTask(ToDoMVCPage.TaskStatus.ACTIVE, "1"), page.aTask(ToDoMVCPage.TaskStatus.COMPLETED, "2"));

        page.toggle("2");
        page.assertTasks("1", "2");
        page.assertItemsLeft(2);
    }

    @Test
    public void editAtAll() {
        page.given(ToDoMVCPage.TaskStatus.ACTIVE, "1");

        page.edit("1", "1 edited");
        page.assertTasks("1 edited");
        page.assertItemsLeft(1);
    }

    @Test
    public void deleteAtAll() {
        page.given(page.aTask(ToDoMVCPage.TaskStatus.COMPLETED, "1"), page.aTask(ToDoMVCPage.TaskStatus.ACTIVE, "2"));

        page.delete("1");
        page.assertTasks("2");
        page.assertItemsLeft(1);
    }

    @Test
    public void cancelEditAtAll() {
        page.given(page.aTask(ToDoMVCPage.TaskStatus.ACTIVE, "1"), page.aTask(ToDoMVCPage.TaskStatus.COMPLETED, "2"));

        page.cancelEdit("2", "2 edit canceled");
        page.assertTasks("1", "2");
        page.assertItemsLeft(1);
    }

    @Test
    public void editByTabAtAll() {
        page.given(ToDoMVCPage.TaskStatus.COMPLETED, "1");

        page.editByTab("1", "1 edited");
        page.assertTasks("1 edited");
        page.assertItemsLeft(0);
    }


    @Test
    public void switchFilterAllToCompleted() {
        page.given(page.aTask(ToDoMVCPage.TaskStatus.ACTIVE, "1"), page.aTask(ToDoMVCPage.TaskStatus.COMPLETED, "2"));

        page.filterCompleted();
        page.assertTasks("2");
        page.assertItemsLeft(1);
    }
}
