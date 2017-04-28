package com.herokuapp.todomvc;

import com.herokuapp.todomvc.categories.Smoke;
import com.herokuapp.todomvc.features.TodosE2ETest;
import com.herokuapp.todomvc.features.TodosOperationsAtAllFilter;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;

import static org.junit.experimental.categories.Categories.*;

/**
 * Created by inna on 4/21/17.
 */

@RunWith(Categories.class)
@IncludeCategory(Smoke.class)
@SuiteClasses({TodosE2ETest.class, TodosOperationsAtAllFilter.class})
public class SmokeSuiteTest {
}
