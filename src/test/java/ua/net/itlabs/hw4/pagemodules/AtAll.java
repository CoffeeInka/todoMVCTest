package ua.net.itlabs.hw4.pagemodules;

import org.junit.Test;
import ua.net.itlabs.hw2.BaseTest;

import static ua.net.itlabs.hw4.pagemodules.pages.ToDoMVCPage.*;
import static ua.net.itlabs.hw4.pagemodules.pages.ToDoMVCPage.TaskStatus.ACTIVE;
import static ua.net.itlabs.hw4.pagemodules.pages.ToDoMVCPage.TaskStatus.COMPLETED;

/**
 * Created by inna on 4/5/17.
 */
public class AtAll extends BaseTest {
    
    @Test
    public void completeAllAtAll() {
        given(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));

        toggleAll();
        assertTasks("1", "2");
        assertItemsLeft(0);
    }

    @Test
    public void clearCompletedAtAll() {
        given(COMPLETED, "1");

        clearCompleted();
        assertNoTasks();
    }

    @Test
    public void reactivateAtAll() {
        given(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));

        toggle("2");
        assertTasks("1", "2");
        assertItemsLeft(2);
    }

    @Test
    public void editAtAll() {
        given(ACTIVE, "1");

        edit("1", "1 edited");
        assertTasks("1 edited");
        assertItemsLeft(1);
    }

    @Test
    public void deleteAtAll() {
        given(aTask(COMPLETED, "1"), aTask(ACTIVE, "2"));

        delete("1");
        assertTasks("2");
        assertItemsLeft(1);
    }

    @Test
    public void cancelEditAtAll() {
        given(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));

        cancelEdit("2", "2 edit canceled");
        assertTasks("1", "2");
        assertItemsLeft(1);
    }

    @Test
    public void editByTabAtAll() {
        given(COMPLETED, "1");

        editByTab("1", "1 edited");
        assertTasks("1 edited");
        assertItemsLeft(0);
    }


    @Test
    public void switchFilterAllToCompleted() {
        given(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));

        filterCompleted();
        assertTasks("2");
        assertItemsLeft(1);
    }
}
