package com.codurance.training.tasks;

import com.codurance.training.tasks.command.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class TaskListTest {
    private TaskList taskList;

    @Before
    public void init() {
        taskList = new TaskList();
    }

    @Test
    public void shouldAddSimpleCommandLineWithoutId() {
        new AddCommand("project p").execute(taskList);
        new AddCommand("task p t1").execute(taskList);
        assertEquals("p\n    [ ] 1: t1\n\n", new ShowCommand().execute(taskList));
    }

    @Test
    public void shouldAddSimpleCommandLineWithId() {
        new AddCommand("project p").execute(taskList);
        new AddCommand("task p t1").execute(taskList);
        assertEquals("p\n    [ ] 1: t1\n\n", new ShowCommand().execute(taskList));
    }

    @Test
    public void parseWithSplit() {
        String command = "add task(id) t1";
        var res = command.split(" ", 2);
        assertEquals("add", res[0]);
        assertEquals("task(id) t1", res[1]);
    }

}