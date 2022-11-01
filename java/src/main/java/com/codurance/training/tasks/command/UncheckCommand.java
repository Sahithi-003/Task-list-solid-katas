package com.codurance.training.tasks.command;

import com.codurance.training.tasks.TaskList;
import com.codurance.training.tasks.TaskNotFoundException;
import com.codurance.training.tasks.module.TaskId;

public record UncheckCommand(String taskId) implements Command {

    @Override
    public String execute(TaskList taskList) {
        try {
            taskList.setDone(new TaskId(taskId),false);
            return "";
        }catch (TaskNotFoundException e){
            return e.getMessage();
        }
    }
}
