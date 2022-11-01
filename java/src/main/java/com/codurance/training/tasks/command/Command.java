package com.codurance.training.tasks.command;

import com.codurance.training.tasks.TaskList;

public interface Command {
    String execute(TaskList taskList);
}
