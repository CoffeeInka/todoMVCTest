package ua.net.itlabs.hw7;

import org.junit.Test;
import ua.net.itlabs.hw7.testconfigs.BaseTest;

import static ua.net.itlabs.hw7.pages.ToDoMVC.*;
import static ua.net.itlabs.hw7.pages.ToDoMVC.TaskStatus.ACTIVE;
import static ua.net.itlabs.hw7.pages.ToDoMVC.TaskStatus.COMPLETED;


public class ToDoMVCAtActiveFilterTest extends BaseTest {

    @Test
    public void testComplete() {
        givenAtActive(ACTIVE, "1", "2");

        toggle("1");
        assertTasks("2");
        assertItemsLeft(1);
    }

    @Test
    public void testClearCompleted() {
        givenAtActive(aTask(COMPLETED, "1"), aTask(COMPLETED, "2"), aTask(ACTIVE, "3"));

        clearCompleted();
        assertTasks("3");
        assertItemsLeft(1);
    }

    @Test
    public void testEdit() {
        givenAtActive(ACTIVE, "1", "2");

        edit("2", "2 edited");
        assertTasks("1", "2 edited");
        assertItemsLeft(2);
    }

    @Test
    public void testDelete() {
        givenAtActive(ACTIVE, "1");

        delete("1");
        assertNoTasks();
    }

    @Test
    public void testCancelEdit() {
        givenAtActive(ACTIVE, "1", "2");

        cancelEdit("2", "2 edit canceled");
        assertTasks("1", "2");
        assertItemsLeft(2);
    }

//    @Test
//    public void testEditByClickOutOfTask() {
//        givenAtActive(ACTIVE, "1", "2");
//
//        editByClickOutOfTask("2", "2 edited");
//        assertTasks("1", "2 edited");
//        assertItemsLeft(2);
//    }

    @Test
    public void testSwitchFilterToAll() {
        givenAtActive(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));

        filterAll();
        assertTasks("1", "2");
        assertItemsLeft(1);
    }
}