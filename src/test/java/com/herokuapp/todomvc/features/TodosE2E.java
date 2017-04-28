package com.herokuapp.todomvc.features;

import com.herokuapp.todomvc.base.BaseTest;
import com.herokuapp.todomvc.categories.All;
import com.herokuapp.todomvc.categories.FullAcceptance;
import com.herokuapp.todomvc.categories.Smoke;
import com.herokuapp.todomvc.pages.ToDoMVCPage;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.herokuapp.todomvc.pages.ToDoMVCPage.TaskStatus.ACTIVE;
import static com.herokuapp.todomvc.pages.ToDoMVCPage.TaskStatus.COMPLETED;

/**
 * Created by inna on 21/04/2017.
 */

@Category({FullAcceptance.class, All.class})
public class TodosE2E extends BaseTest {

        ToDoMVCPage page = new ToDoMVCPage();

        @Test
        @Category(Smoke.class)
        public void tasksLifeCycle() {
            page.given();

            page.add("1");
            page.toggle("1");
            page.assertTasks("1");

            page.filterActive();
            page.assertNoTasks();

            page.add("2");
            page.assertTasks("2");
            page.assertItemsLeft(1);

            page.toggleAll();
            page.assertNoTasks();

            page.filterCompleted();
            page.assertTasks("1", "2");

            page.toggle("1");
            page.assertTasks("2");

            page.clearCompleted();
            page.assertNoTasks();

            page.filterAll();
            page.assertTasks("1");


        }

    @Test
    public void switchFilterToCompleted() {
        page.given(page.aTask(ACTIVE, "1"), page.aTask(COMPLETED, "2"));

        page.filterCompleted();
        page.assertTasks("2");
        page.assertItemsLeft(1);
    }

    @Test
    public void switchFilterToActive() {
        page.givenAtCompleted(page.aTask(COMPLETED, "1"), page.aTask(COMPLETED, "2"), page.aTask(ACTIVE, "3"));

        page.filterActive();
        page.assertTasks("3");
        page.assertItemsLeft(1);
    }


    @Test
    public void switchFilterToAll() {
        page.givenAtActive(page.aTask(ACTIVE, "1"), page.aTask(COMPLETED, "2"));

        page.filterAll();
        page.assertTasks("1", "2");
        page.assertItemsLeft(1);
    }


    }



