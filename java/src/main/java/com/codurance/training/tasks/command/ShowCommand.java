package com.codurance.training.tasks.command;

import com.codurance.training.tasks.TaskList;
import com.codurance.training.tasks.module.Project;
import com.codurance.training.tasks.module.Task;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ShowCommand implements Command {
    private final Map<Project, List<Task>> tasks = new LinkedHashMap<>();
    @Override
    public String execute(TaskList taskList) {
        return taskList.show();
    }
}
