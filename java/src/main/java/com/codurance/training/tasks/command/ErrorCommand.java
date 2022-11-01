package com.codurance.training.tasks.command;

import com.codurance.training.tasks.TaskList;

public class ErrorCommand implements Command {
    private final String command;
    public ErrorCommand(String command) {
        this.command=command;
    }

    @Override
    public String execute(TaskList taskList) {
        return String.format("I don't know what the command \"%s\" is.\n", command);

    }
}
