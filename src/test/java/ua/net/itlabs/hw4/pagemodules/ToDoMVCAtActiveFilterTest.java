package ua.net.itlabs.hw4.pagemodules;

import org.junit.Test;
import ua.net.itlabs.hw2.BaseTest;
import ua.net.itlabs.hw4.pagemodules.pages.ToDoMVC;

import static ua.net.itlabs.hw4.pagemodules.pages.ToDoMVC.*;
import static ua.net.itlabs.hw4.pagemodules.pages.ToDoMVC.TaskStatus.ACTIVE;
import static ua.net.itlabs.hw4.pagemodules.pages.ToDoMVC.TaskStatus.COMPLETED;

/**
 * Created by inna on 4/10/17.
 */
public class ToDoMVCAtActiveFilterTest extends BaseTest {

    @Test
    public void complete() {
        givenAtActive(ACTIVE, "1", "2");

        toggle("1");
        assertTasks("2");
        assertItemsLeft(1);
    }

    @Test
    public void clearCompleted() {
        givenAtActive(aTask(COMPLETED, "1"), aTask(COMPLETED, "2"), aTask(ACTIVE, "3"));

        ToDoMVC.clearCompleted();
        assertTasks("3");
        assertItemsLeft(1);
    }

    @Test
    public void edit() {
        givenAtActive(ACTIVE, "1", "2");

        ToDoMVC.edit("2", "2 edited");
        assertTasks("1", "2 edited");
        assertItemsLeft(2);
    }

    @Test
    public void delete() {
        givenAtActive(ACTIVE, "1");

        ToDoMVC.delete("1");
        assertNoTasks();
    }

    @Test
    public void cancelEdit() {
        givenAtActive(ACTIVE, "1", "2");

        ToDoMVC.cancelEdit("2", "2 edit canceled");
        assertTasks("1", "2");
        assertItemsLeft(2);
    }

    @Test
    public void editByClickOutOfTask() {
        givenAtActive(ACTIVE, "1", "2");

        ToDoMVC.editByClickOutOfTask("2", "2 edited");
        assertTasks("1", "2 edited");
        assertItemsLeft(2);
    }

    @Test
    public void switchFilterToAll() {
        givenAtActive(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));

        filterAll();
        assertTasks("1", "2");
        assertItemsLeft(1);
    }
}