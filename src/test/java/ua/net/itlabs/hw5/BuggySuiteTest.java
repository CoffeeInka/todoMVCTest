package ua.net.itlabs.hw5;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import ua.net.itlabs.hw5.categories.Buggy;
import ua.net.itlabs.hw5.features.TodosE2ETest;
import ua.net.itlabs.hw5.features.TodosOperationsAtAllFilter;

import static org.junit.experimental.categories.Categories.*;

/**
 * Created by inna on 4/21/17.
 */

@RunWith(Categories.class)
@IncludeCategory(Buggy.class)
@SuiteClasses({TodosOperationsAtAllFilter.class, TodosE2ETest.class})
public class BuggySuiteTest {
}
