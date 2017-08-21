package ua.net.itlabs.hw7;

import org.junit.Test;
import ua.net.itlabs.hw7.testconfigs.BaseTest;

import static ua.net.itlabs.hw7.pages.ToDoMVC.TaskStatus.ACTIVE;
import static ua.net.itlabs.hw7.pages.ToDoMVC.TaskStatus.COMPLETED;
import static ua.net.itlabs.hw7.pages.ToDoMVC.aTask;
import static ua.net.itlabs.hw7.pages.ToDoMVC.given;

/**
 * Created by inna on 4/5/17.
 */
public class ToDoMVCAtAllFilterTest extends BaseTest {
    
//    @Test
//    public void testCompleteAll() {
//        given(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));
//
//        toggleAll();
//        assertTasks("1", "2");
//        assertItemsLeft(0);
//    }
//
//    @Test
//    public void testClearCompleted() {
//        given(COMPLETED, "1");
//
//        clearCompleted();
//        assertNoTasks();
//    }
//
//    @Test
//    public void testReactivate() {
//        given(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));
//
//        toggle("2");
//        assertTasks("1", "2");
//        assertItemsLeft(2);
//    }
//
//    @Test
//    public void testEdit() {
//        given(ACTIVE, "1");
//
//        edit("1", "1 edited");
//        assertTasks("1 edited");
//        assertItemsLeft(1);
//    }
//
//    @Test
//    public void testDelete() {
//        given(aTask(COMPLETED, "1"), aTask(ACTIVE, "2"));
//
//        delete("1");
//        assertTasks("2");
//        assertItemsLeft(1);
//    }
//
//    @Test
//    public void testCancelEdit() {
//        given(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));
//
//        cancelEdit("2", "2 edit canceled");
//        assertTasks("1", "2");
//        assertItemsLeft(1);
//    }
//
//    @Test
//    public void testEditByTab() {
//        given(COMPLETED, "1");
//
//        editByTab("1", "1 edited");
//        assertTasks("1 edited");
//        assertItemsLeft(0);
//    }
//
//
//    @Test
//    public void testSwitchFilterToCompleted() {
//        given(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));
//
//        filterCompleted();
//        assertTasks("2");
//        assertItemsLeft(1);
//    }
}