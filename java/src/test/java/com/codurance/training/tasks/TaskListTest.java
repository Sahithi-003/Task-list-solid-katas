package com.codurance.training.tasks;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class TaskListTest {
    @Test
    public void shouldAddSimpleCommandLineWithoutId(){
        var taskList = new TaskList();
        taskList.execute("add project p");
        taskList.execute("add task p t1");
        assertEquals("p\n    [ ] 1: t1\n\n",taskList.execute("show"));
    }

    @Test
    public void shouldAddSimpleCommandLineWithId(){
        var taskList = new TaskList();
        taskList.execute("add project p");
        taskList.execute("add task(id) p t1");
        assertEquals("p\n    [ ] id: t1\n\n",taskList.execute("show"));
    }

    @Test
    public void parseWithSplit(){
       String command = "add task(id) t1";
        var res = command.split(" ", 2);
        assertEquals("add",res[0]);
        assertEquals("task(id) t1",res[1]);
    }
}
