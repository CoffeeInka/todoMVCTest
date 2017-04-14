package ua.net.itlabs.hw4.pageobjects;

import org.junit.Test;
import ua.net.itlabs.hw2.BaseTest;
import ua.net.itlabs.hw4.pageobjects.pages.ToDoMVCPage;

import static ua.net.itlabs.hw4.pageobjects.pages.ToDoMVCPage.TaskStatus.ACTIVE;
import static ua.net.itlabs.hw4.pageobjects.pages.ToDoMVCPage.TaskStatus.COMPLETED;

/**
 * Created by inna on 4/10/17.
 */
public class ToDoMVCAtActiveFilterTest extends BaseTest {

    ToDoMVCPage page = new ToDoMVCPage();

    @Test
    public void complete() {
        page.givenAtActive(ACTIVE, "1", "2");

        page.toggle("1");
        page.assertTasks("2");
        page.assertItemsLeft(1);
    }

    @Test
    public void clearCompleted() {
        page.givenAtActive(page.aTask(COMPLETED, "1"), page.aTask(COMPLETED, "2"), page.aTask(ACTIVE, "3"));

        page.clearCompleted();
        page.assertTasks("3");
        page.assertItemsLeft(1);
    }

    @Test
    public void edit() {
        page.givenAtActive(ACTIVE, "1", "2");

        page.edit("2", "2 edited");
        page.assertTasks("1", "2 edited");
        page.assertItemsLeft(2);
    }

    @Test
    public void delete() {
        page.givenAtActive(ACTIVE, "1");

        page.delete("1");
        page.assertNoTasks();
    }

    @Test
    public void cancelEdit() {
        page.givenAtActive(ACTIVE, "1", "2");

        page.cancelEdit("2", "2 edit canceled");
        page.assertTasks("1", "2");
        page.assertItemsLeft(2);
    }

    @Test
    public void editByClickOutOfTask() {
        page.givenAtActive(ACTIVE, "1", "2");

        page.editByClickOutOfTask("2", "2 edited");
        page.assertTasks("1", "2 edited");
        page.assertItemsLeft(2);
    }

    @Test
    public void switchFilterToAll() {
        page.givenAtActive(page.aTask(ACTIVE, "1"), page.aTask(COMPLETED, "2"));

        page.filterAll();
        page.assertTasks("1", "2");
        page.assertItemsLeft(1);
    }
}