package ua.net.itlabs.hw4.pagemodules;

import org.junit.Test;
import ua.net.itlabs.hw2.BaseTest;

import static ua.net.itlabs.hw4.pagemodules.pages.ToDoMVCPage.*;
import static ua.net.itlabs.hw4.pagemodules.pages.ToDoMVCPage.TaskStatus.ACTIVE;
import static ua.net.itlabs.hw4.pagemodules.pages.ToDoMVCPage.TaskStatus.COMPLETED;

/**
 * Created by inna on 4/10/17.
 */
public class AtActive extends BaseTest {

    @Test
    public void completeAtActive() {
        givenAtActive(ACTIVE, "1", "2");

        toggle("1");
        assertTasks("2");
        assertItemsLeft(1);
    }

    @Test
    public void clearCompletedAtActive() {
        givenAtActive(aTask(COMPLETED, "1"), aTask(COMPLETED, "2"), aTask(ACTIVE, "3"));

        clearCompleted();
        assertTasks("3");
        assertItemsLeft(1);
    }

    @Test
    public void editAtActive() {
        givenAtActive(ACTIVE, "1", "2");

        edit("2", "2 edited");
        assertTasks("1", "2 edited");
        assertItemsLeft(2);
    }

    @Test
    public void deleteAtActive() {
        givenAtActive(ACTIVE, "1");

        delete("1");
        assertNoTasks();
    }

    @Test
    public void cancelEditAtActive() {
        givenAtActive(ACTIVE, "1", "2");

        cancelEdit("2", "2 edit canceled");
        assertTasks("1", "2");
        assertItemsLeft(2);
    }

    @Test
    public void editByClickOutOfTaskAtActive() {
        givenAtActive(ACTIVE, "1", "2");

        editByClickOutOfTask("2", "2 edited");
        assertTasks("1", "2 edited");
        assertItemsLeft(2);
    }

    @Test
    public void switchFilterActiveToAll() {
        givenAtActive(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));

        filterAll();
        assertTasks("1", "2");
        assertItemsLeft(1);
    }
}