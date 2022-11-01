package com.codurance.training.tasks.command;

import com.codurance.training.tasks.TaskList;
import com.codurance.training.tasks.TaskNotFoundException;
import com.codurance.training.tasks.module.TaskId;

public class DeadLineCommand implements Command {
    private final TaskId taskId;
    private final String deadLine;

    public DeadLineCommand(String command) {
        String[] commandRest = command.split(" ", 2);
        taskId = new TaskId(commandRest[0]) ;
        deadLine = commandRest[1];
    }

    @Override
    public String execute(TaskList taskList) {
        try {
            taskList.setDeadline(taskId,deadLine);
            return "";
        }catch (TaskNotFoundException e){
            return e.getMessage();
        }
    }
}
