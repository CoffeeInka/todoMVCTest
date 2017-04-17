package ua.net.itlabs.hw4.pageobjects;

import org.junit.Test;
import ua.net.itlabs.hw2.BaseTest;
import ua.net.itlabs.hw4.pageobjects.pages.ToDoMVCPage;

import static ua.net.itlabs.hw4.pageobjects.pages.ToDoMVCPage.TaskStatus.ACTIVE;
import static ua.net.itlabs.hw4.pageobjects.pages.ToDoMVCPage.TaskStatus.COMPLETED;

/**
 * Created by inna on 4/5/17.
 */
public class ToDoMVCAtAllFilterTest extends BaseTest {

    ToDoMVCPage page = new ToDoMVCPage();

    @Test
    public void completeAll() {
        page.given(page.aTask(ACTIVE, "1"), page.aTask(COMPLETED, "2"));

        page.toggleAll();
        page.assertTasks("1", "2");
        page.assertItemsLeft(0);
    }

    @Test
    public void clearCompleted() {
        page.given(COMPLETED, "1");

        page.clearCompleted();
        page.assertNoTasks();
    }

    @Test
    public void reactivate() {
        page.given(page.aTask(ACTIVE, "1"), page.aTask(COMPLETED, "2"));

        page.toggle("2");
        page.assertTasks("1", "2");
        page.assertItemsLeft(2);
    }

    @Test
    public void edit() {
        page.given(ACTIVE, "1");

        page.edit("1", "1 edited");
        page.assertTasks("1 edited");
        page.assertItemsLeft(1);
    }

    @Test
    public void delete() {
        page.given(page.aTask(COMPLETED, "1"), page.aTask(ACTIVE, "2"));

        page.delete("1");
        page.assertTasks("2");
        page.assertItemsLeft(1);
    }

    @Test
    public void cancelEdit() {
        page.given(page.aTask(ACTIVE, "1"), page.aTask(COMPLETED, "2"));

        page.cancelEdit("2", "2 edit canceled");
        page.assertTasks("1", "2");
        page.assertItemsLeft(1);
    }

    @Test
    public void editByTab() {
        page.given(COMPLETED, "1");

        page.editByTab("1", "1 edited");
        page.assertTasks("1 edited");
        page.assertItemsLeft(0);
    }


    @Test
    public void switchFilterToCompleted() {
        page.given(page.aTask(ACTIVE, "1"), page.aTask(COMPLETED, "2"));

        page.filterCompleted();
        page.assertTasks("2");
        page.assertItemsLeft(1);
    }
}
