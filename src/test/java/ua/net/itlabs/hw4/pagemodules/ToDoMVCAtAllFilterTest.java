package ua.net.itlabs.hw4.pagemodules;

import org.junit.Test;
import ua.net.itlabs.hw2.BaseTest;
import ua.net.itlabs.hw4.pagemodules.pages.ToDoMVC;

import static ua.net.itlabs.hw4.pagemodules.pages.ToDoMVC.*;
import static ua.net.itlabs.hw4.pagemodules.pages.ToDoMVC.TaskStatus.ACTIVE;
import static ua.net.itlabs.hw4.pagemodules.pages.ToDoMVC.TaskStatus.COMPLETED;

/**
 * Created by inna on 4/5/17.
 */
public class ToDoMVCAtAllFilterTest extends BaseTest {
    
    @Test
    public void completeAll() {
        given(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));

        toggleAll();
        assertTasks("1", "2");
        assertItemsLeft(0);
    }

    @Test
    public void clearCompleted() {
        given(COMPLETED, "1");

        ToDoMVC.clearCompleted();
        assertNoTasks();
    }

    @Test
    public void reactivate() {
        given(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));

        toggle("2");
        assertTasks("1", "2");
        assertItemsLeft(2);
    }

    @Test
    public void edit() {
        given(ACTIVE, "1");

        ToDoMVC.edit("1", "1 edited");
        assertTasks("1 edited");
        assertItemsLeft(1);
    }

    @Test
    public void delete() {
        given(aTask(COMPLETED, "1"), aTask(ACTIVE, "2"));

        ToDoMVC.delete("1");
        assertTasks("2");
        assertItemsLeft(1);
    }

    @Test
    public void cancelEdit() {
        given(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));

        ToDoMVC.cancelEdit("2", "2 edit canceled");
        assertTasks("1", "2");
        assertItemsLeft(1);
    }

    @Test
    public void editByTab() {
        given(COMPLETED, "1");

        ToDoMVC.editByTab("1", "1 edited");
        assertTasks("1 edited");
        assertItemsLeft(0);
    }


    @Test
    public void switchFilterToCompleted() {
        given(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));

        filterCompleted();
        assertTasks("2");
        assertItemsLeft(1);
    }
}
