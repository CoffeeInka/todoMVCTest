package com.herokuapp.todomvc;

import com.herokuapp.todomvc.pages.ToDoMVCPage;
import org.junit.Test;

/**
 * Created by inna on 21/04/2017.
 */
public class TodosE2ETest extends BaseTest {

        ToDoMVCPage page = new ToDoMVCPage();

        @Test
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


    }



